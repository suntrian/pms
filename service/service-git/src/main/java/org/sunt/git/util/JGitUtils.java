package org.sunt.git.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.dircache.DirCacheIterator;
import org.eclipse.jgit.errors.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.merge.MergeStrategy;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.treewalk.*;
import org.eclipse.jgit.treewalk.filter.TreeFilter;
import org.eclipse.jgit.util.FS;
import org.eclipse.jgit.util.FileUtils;
import org.eclipse.jgit.util.StringUtils;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

/**
 * git操作相关的工具类，
 * Author: Suntrian
 * Date: 2017-12-15
 */

enum Authentication {
    HTTP, HTTPS, SSH_PK, SSH_PWD, GIT, FTP, RSYNC, FILE
}

enum FileListMode {
    FILE, DIR, ALL;
}

public class JGitUtils {

    public static Boolean REMOTE = true;
    public static Boolean ALL = false;
    SshSessionFactory sshSessionFactory;
    private Integer changeRecordCount;
    private Authentication auth;
    private Git git;
    private List<String> uris = new ArrayList<>();
    private Repository repo;
    private String username;
    private String password;
    private String privatekeyFile = new File(System.getProperty("user.home"), "/.ssh/id_rsa").toString();
    private String knowHostFile;

    public JGitUtils() {
    }

    public JGitUtils(File gitDir) throws IOException {
        setRepository(gitDir);
    }

    public JGitUtils(String gitDir) throws IOException {
        setRepository(new File(gitDir));
    }

    public JGitUtils(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static byte[] getContentByRevisionFile(final String repodir, final String revision, final String filename) throws GitAPIException, IOException {
        try {
            JGitUtils git = new JGitUtils(new File(repodir));
            return git.getContentByRevisionFile(revision, filename);
        } catch (RepositoryNotFoundException e) {
            return null;
        }
    }

    public void setRepository(File gitDir) throws IOException {
        try {
            this.git = Git.open(gitDir, FS.detect());
            this.repo = this.git.getRepository();
        } catch (RepositoryNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    private Authentication guessAuthentication(String uri) throws Exception {
        if (uri.matches("^ssh:.+") || uri.matches(".+@.+")) {
            if (this.password != null && !"".equals(this.password)) {
                this.sshSessionFactory = new JschConfigSessionFactory() {
                    @Override
                    protected void configure(OpenSshConfig.Host hc, Session session) {
                        session.setPassword(password);
                        session.setConfig("StrictHostKeyChecking", "no");
                    }
                };
                return Authentication.SSH_PWD;
            } else {
                sshSessionFactory = new JschConfigSessionFactory() {
                    @Override
                    protected void configure(OpenSshConfig.Host hc, Session session) {
                        session.setConfig("StrictHostKeyChecking", "no");
                    }
                };
                return Authentication.SSH_PK;
            }
        } else if (uri.matches("^http:.+")) {
            if (this.password == null) {
                throw new UnsupportedCredentialItem(new URIish(uri), "username and password not provided");
            }
            this.sshSessionFactory = new JschConfigSessionFactory() {
                @Override
                protected void configure(OpenSshConfig.Host hc, Session session) {
                    session.setPassword(password);
                    session.setConfig("StrictHostKeyChecking", "no");
                }
            };
            return Authentication.HTTP;
        } else if (uri.matches("^https:.+")) {
            return Authentication.HTTPS;
        } else if (uri.matches("^git:.+")) {
            return Authentication.GIT;
        }
        return null;
    }

    /**
     * 初始化git仓库, 非本类的初始化工作
     *
     * @param bare 是否创建裸库
     * @param wc   工作目录
     * @throws GitAPIException
     */
    public void doInit(boolean bare, File wc) throws GitAPIException {
        InitCommand init = Git.init();
        init.setBare(bare);
        init.setDirectory(wc);
        init.call();
    }

    /**
     * ￥￥￥克隆远程仓库
     *
     * @param uri
     * @param dir
     * @throws GitAPIException
     */
    public Git doClone(String uri, List<String> branches, File dir, String remote) throws Exception {
        CloneCommand clone = Git.cloneRepository();
        Authentication auth = guessAuthentication(uri);
        if (auth == null) {
            throw new IllegalStateException("未识别的git仓库地址" + uri);
        }
        switch (auth) {
            case HTTP:
            case HTTPS:
                //doClone.setCredentialsProvider(new UsernamePasswordCredentialsProvider(this.username, this.password));
                System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");     //解决java1.7版本默认使用tlsv1.1协议与github最低支持tls1.2版本不兼容问题。
                setCredentialsProvider(clone, this.username, this.password);
                break;
            case SSH_PWD:
            case SSH_PK:
                setTransportConfigCallback(clone, sshSessionFactory);
                break;
            case GIT:
                break;
        }
        clone.setURI(uri);
        if (branches == null || branches.size() == 0) {
            clone.setCloneAllBranches(true);
        } else if (branches.size() == 1) {
            clone.setBranch(branches.get(0));
        } else {
            clone.setBranchesToClone(branches);
        }
        clone.setDirectory(dir);
        clone.setRemote(remote);
        try {
            this.git = clone.call();
        } catch (GitAPIException e) {
            FileUtils.delete(dir, FileUtils.RECURSIVE | FileUtils.IGNORE_ERRORS);
            throw e;
        }
        this.repo = this.git.getRepository();
        this.uris.add(uri);
        return this.git;
    }

    public Git doClone(String uri, List<String> branches, File dir) throws Exception {
        return doClone(uri, branches, dir, null);
    }

    public Git doClone(String uri, File dir) throws Exception {
        return doClone(uri, new ArrayList<String>(), dir);
    }

    public Git doClone(String uri, String branch, File dir) throws Exception {
        if (branch == null || "".equals(branch)) {
            return doClone(uri, new ArrayList<String>(), dir);
        } else {
            List<String> branchList = new ArrayList<>();
            branchList.add(branch);
            return doClone(uri, branchList, dir);
        }
    }

    /**
     * ￥￥￥
     *
     * @param file
     * @return
     * @throws GitAPIException
     */
    public DirCache doAdd(String file) throws GitAPIException {
        AddCommand add = git.add();
        return add.addFilepattern(file).call();
    }

    public DirCache doAdd(String[] files) throws GitAPIException {
        AddCommand add = git.add();
        for (String file : files) {
            add.addFilepattern(file);
        }
        return add.call();
    }

    public DirCache doAdd() throws GitAPIException {
        return git.add().addFilepattern(".").call();
    }

    /**
     * ￥￥￥
     *
     * @param files
     * @return
     * @throws GitAPIException
     */
    public DirCache doRemove(String[] files) throws GitAPIException {
        RmCommand rm = git.rm();
        for (String file : files) {
            rm.addFilepattern(file);
        }
        return rm.call();
    }

    public DirCache doRemove(String file) throws GitAPIException {
        return git.rm().addFilepattern(file).call();
    }

    public Map parseDirCache(DirCache dirCache) {
        Map<String, Object> result = new HashMap<>();
        result.put("entrycount", dirCache.getEntryCount());
        //TO DO
        return result;
    }

    /**
     * ￥￥￥获取当前状态
     *
     * @return
     * @throws GitAPIException
     */
    public Status doStatus() throws GitAPIException {
        StatusCommand statusCmd = git.status();
        Status status = statusCmd.call();
        return status;
    }

    public Status doStatus(String file) throws GitAPIException {
        StatusCommand statusCommand = git.status();
        Status status = statusCommand.addPath(file).call();
        return status;
    }

    public Map parseStatus(Status status) {
        Map<String, Object> result = new HashMap<>();
        result.put("isclean", status.isClean());
        result.put("added", status.getAdded());
        result.put("removed", status.getRemoved());
        result.put("modified", status.getModified());
        return result;
    }

    /**
     * ￥￥￥列出所有分支
     * git branch
     *
     * @return
     * @throws GitAPIException
     */
    public List<Ref> listBranch() throws GitAPIException {
        return listBranch(null);
    }

    public List<Ref> listBranch(Boolean remoteOrAll) throws GitAPIException {
        ListBranchCommand listBranchCommand = git.branchList();
        if (remoteOrAll != null) {
            listBranchCommand.setListMode(remoteOrAll ? ListBranchCommand.ListMode.REMOTE : ListBranchCommand.ListMode.ALL);
        }
        List<Ref> refList = listBranchCommand.call();
        return refList;
    }

    /**
     * ￥￥￥删除分支
     *
     * @param branch
     * @param forceDelete
     * @return
     * @throws GitAPIException
     */
    public List<String> deleteBranch(String branch, boolean forceDelete) throws GitAPIException {
        DeleteBranchCommand deleteBranchCommand = git.branchDelete();
        deleteBranchCommand.setForce(forceDelete);
        deleteBranchCommand.setBranchNames(branch);
        List<String> result = deleteBranchCommand.call();
        return result;
    }

    public List<String> deleteBranch(String branch) throws GitAPIException {
        return deleteBranch(branch, false);
    }

    /**
     * ￥￥￥创建分支，从当前工作分区的startPoint, startPoint为某版本的序列号，若null则最新版本
     *
     * @param branch
     * @param startPoint
     * @param forceCreate
     * @return
     * @throws GitAPIException
     */
    public Ref createBranch(String branch, String startPoint, boolean forceCreate) throws GitAPIException {
        CreateBranchCommand createBranchCommand = git.branchCreate();
        createBranchCommand.setName(branch);
        createBranchCommand.setForce(forceCreate);
        createBranchCommand.setStartPoint(startPoint);
        return createBranchCommand.call();
    }

    public Ref createBranch(String branch) throws GitAPIException {
        return createBranch(branch, null, false);
    }

    /**
     * 分支重命名
     *
     * @param oldname
     * @param newname
     * @return
     * @throws GitAPIException
     */
    public Ref renameBranch(String oldname, String newname) throws GitAPIException {
        RenameBranchCommand renameBranchCommand = git.branchRename();
        renameBranchCommand.setOldName(oldname);
        renameBranchCommand.setNewName(newname);
        return renameBranchCommand.call();
    }

    public Ref renameBranch(String newname) throws GitAPIException {
        return renameBranch(null, newname);
    }

    /**
     * @throws GitAPIException
     * @deprecated since origin
     */
    private void doBranch() throws GitAPIException {
        CreateBranchCommand createBranchCommand;
        DeleteBranchCommand deleteBranchCommand;
        ListBranchCommand listBranchCommand;
        RenameBranchCommand renameBranchCommand;
    }

    /**
     * to do
     *
     * @throws GitAPIException
     */
    public void doTag() throws GitAPIException {
        TagCommand tag = git.tag();
        DeleteTagCommand deleteTagCommand = git.tagDelete();
        ListTagCommand listTagCommand = git.tagList();
    }

    public RevCommit doCommit(String message) throws GitAPIException {
        CommitCommand commit = git.commit();
        commit.setMessage(message);
        return commit.call();
    }

    /**
     * 当前分支的所有log
     *
     * @return
     * @throws GitAPIException
     */
    public Iterable<RevCommit> doLog(AnyObjectId since, AnyObjectId until, boolean all, RevFilter filter, String path, Integer maxCount)
            throws MissingObjectException, IncorrectObjectTypeException, GitAPIException, IOException {
        LogCommand log = git.log();
        if (since != null) log.add(since);
        if (until != null) log.not(until);
        if (all) log.all();
        if (path != null) log.addPath(path);
        if (maxCount != null && maxCount != 0) log.setMaxCount(maxCount);
        if (filter != null) log.setRevFilter(filter);
        return log.call();
    }

    /**
     * AnyObjectId parent of RevCommit
     *
     * @param since 从since往前查找
     * @return
     * @throws MissingObjectException
     * @throws IncorrectObjectTypeException
     * @throws GitAPIException
     */
    public Iterable<RevCommit> doLog(AnyObjectId since) throws MissingObjectException, IncorrectObjectTypeException, GitAPIException, IOException {
        return doLog(since, null, true, null, null, null);
    }

    /**
     * 从since往前查找
     *
     * @param since
     * @return
     * @throws GitAPIException
     * @throws IOException
     */
    public Iterable<RevCommit> doLog(String since) throws GitAPIException, IOException {
        return doLog(this.repo.resolve(since));
    }

    public Iterable<RevCommit> doLog(AnyObjectId since, AnyObjectId until) throws MissingObjectException, IncorrectObjectTypeException, GitAPIException, IOException {
        return doLog(since, until, true, null, null, null);
    }

    public Iterable<RevCommit> doLog(String since, String until) throws GitAPIException, IOException {
        return doLog(this.repo.resolve(since), this.repo.resolve(until));
    }

    public Iterable<RevCommit> doLog(boolean all) throws GitAPIException, IOException {
        return doLog(null, null, all, null, null, null);
    }

    public Iterable<RevCommit> doLog() throws GitAPIException {
        LogCommand logCmd = git.log();
        return logCmd.call();
    }

    /**
     * TODO: Implement
     *
     * @return
     * @throws GitAPIException
     */
    public List<DiffEntry> doDiff(AbstractTreeIterator older, AbstractTreeIterator newer, OutputStream out, boolean simple,
                                  String sourcePrefix, String destinationPrefix, TreeFilter treeFilter
    ) throws GitAPIException {
        DiffCommand diffCmd = git.diff();
        diffCmd.setOldTree(older);
        diffCmd.setNewTree(newer);
        diffCmd.setShowNameAndStatusOnly(simple);
        diffCmd.setSourcePrefix(sourcePrefix);
        diffCmd.setDestinationPrefix(destinationPrefix);
        diffCmd.setPathFilter(treeFilter);
        if (out == null) out = System.out;
        diffCmd.setOutputStream(out);
        return diffCmd.call();
    }

    public List<DiffEntry> doDiff(AbstractTreeIterator older, AbstractTreeIterator newer, OutputStream out) throws GitAPIException {
        return doDiff(older, newer, out, false, null, null, null);
    }

    public List<DiffEntry> doDiff(OutputStream out) throws GitAPIException {
        return doDiff(null, null, out, false, null, null, null);
    }

    public List<DiffEntry> doDiff() throws GitAPIException {
        return doDiff(null, null, null, false, null, null, null);
    }

    /**
     * 根据版本号取得ＴｒｅｅＩｔｅｒａｔｏｒ，用作比较的对象
     *
     * @param commitId
     * @return
     */
    public AbstractTreeIterator getTreeIteratorByCommit(RevCommit commitId) {
        try {
            if (commitId == null) {
                return getTreeIteratorByEmpty();
            }
            RevWalk walk = new RevWalk(this.repo);
            RevTree tree = walk.parseTree(commitId);

            CanonicalTreeParser oldTreeParser = new CanonicalTreeParser();
            try (ObjectReader oldReader = this.repo.newObjectReader()) {
                oldTreeParser.reset(oldReader, tree.getId());
            }
            walk.dispose();
            return oldTreeParser;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    public AbstractTreeIterator getTreeIteratorByCommit(String commitId) throws IOException {
        ObjectId commit = this.repo.resolve(commitId);
        return getTreeIteratorByCommit(commitId);
    }

    /**
     * TODO: find usage & test
     * 根据文件取得ＴｒｅｅＩｔｅｒａｔｏｒ，用例比较的对象
     *
     * @param file
     * @return
     */
    public AbstractTreeIterator getTreeIteratorByFile(String file) {
        AbstractTreeIterator treeIterator = new FileTreeIterator(this.repo);
        return treeIterator;
    }

    public AbstractTreeIterator getTreeIteratorByEmpty() {
        AbstractTreeIterator treeIterator = new EmptyTreeIterator();
        return treeIterator;
    }

    public AbstractTreeIterator getTreeIteratorByDirCache() throws IOException {
        AbstractTreeIterator treeIterator = new DirCacheIterator(this.repo.readDirCache());
        return treeIterator;
    }

    public Map parseDiffEntry(DiffEntry diffEntry) {
        Map<String, Object> result = new HashMap<>();
        result.put("changetype", diffEntry.getChangeType());
        return result;
    }

    /**
     * 返回某次提交版本的信息和此版本的所有文件，非本次提交的内容
     *
     * @param revCommit
     * @return
     */

    public Map<String, Object> parseRevCommit(RevCommit revCommit, boolean containFiles) {
        Map<String, Object> result = new HashMap<>();
        result.put("author", revCommit.getAuthorIdent().getName());
        result.put("committer", revCommit.getCommitterIdent().getName());
        result.put("date", new Date(revCommit.getCommitTime() * 1000L));
        result.put("message", revCommit.getFullMessage());
        result.put("revision", revCommit.getName());
        if (!containFiles) return result;
        List<String> files = new ArrayList<>();
        RevTree revTree = revCommit.getTree();
        try (TreeWalk treeWalk = new TreeWalk(this.repo)) {
            treeWalk.addTree(revTree);
            treeWalk.setRecursive(false);
            while (treeWalk.next()) {
                if (treeWalk.isSubtree()) {
                    treeWalk.enterSubtree();
                } else {
                    files.add(treeWalk.getPathString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("files", files);
        return result;
    }

    public Map<String, Object> parseRevCommit(RevCommit revCommit) {
        return parseRevCommit(revCommit, false);
    }

    /**
     * to test
     *
     * @return
     * @throws GitAPIException
     */
    public FetchResult doFetch(String uri, String remote) throws Exception {
        FetchCommand fetchCmd = git.fetch();
        fetchCmd.setRemote(remote);
        switch (guessAuthentication(uri)) {
            case SSH_PK:
            case SSH_PWD:
                setTransportConfigCallback(fetchCmd, sshSessionFactory);
                break;
            case HTTPS:
            case HTTP:
                System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");     //解决java1.7版本默认使用tlsv1.1协议与github最低支持tls1.2版本不兼容问题。
                setCredentialsProvider(fetchCmd, this.username, this.password);
                break;
            case GIT:
                break;
        }
        return fetchCmd.call();
    }

    public FetchResult doFetch(String uri) throws Exception {
        return doFetch(uri, Constants.DEFAULT_REMOTE_NAME);
    }

    public FetchResult doFetch(File gitDir) throws Exception {
        setRepository(gitDir);
        List<RemoteConfig> remoteConfigs = listRemote();
        String uri = "";
        String remote = "";
        if (remoteConfigs.size() > 0) {
            RemoteConfig remoteConfig = remoteConfigs.get(0);
            uri = remoteConfig.getURIs().get(0).toString();
            remote = remoteConfig.getName();
        }
        return doFetch(uri, remote);
    }

    public PullResult doPull(String uri, String remote, String branch) throws Exception {
        PullCommand pullCmd = git.pull();
        pullCmd.setRemote(remote);
        pullCmd.setRemoteBranchName(branch);
        switch (guessAuthentication(uri)) {
            case SSH_PK:
            case SSH_PWD:
                setTransportConfigCallback(pullCmd, sshSessionFactory);
                break;
            case HTTPS:
            case HTTP:
                System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");     //解决java1.7版本默认使用tlsv1.1协议与github最低支持tls1.2版本不兼容问题。
                setCredentialsProvider(pullCmd, this.username, this.password);
                break;
            case GIT:
                break;
        }
        return pullCmd.call();
    }

    public PullResult doPull(String uri, String remote) throws Exception {
        return doPull(uri, remote, null);
    }

    public PullResult doPull(File gitDir) throws Exception {
        setRepository(gitDir);
        List<RemoteConfig> remoteConfigs = listRemote();
        String uri = "";
        String remote = "";
        if (remoteConfigs.size() > 0) {
            RemoteConfig remoteConfig = remoteConfigs.get(0);
            uri = remoteConfig.getURIs().get(0).toString();
            remote = remoteConfig.getName();
        } else {
            throw new RemoteRepositoryException(new URIish(""), "无远程仓库");
        }
        return doPull(uri, remote);
    }

    /**
     * checkout branch 分支，当不存在branch分支时自动创建
     *
     * @param branch
     * @throws GitAPIException
     */
    public Ref doCheckout(String branch) throws GitAPIException, IOException {
        return doCheckout(branch, false);
    }

    public Ref doCheckout(String branch, boolean forceCreate) throws GitAPIException, IOException {
        return doCheckout(branch, forceCreate, null);
    }

    public Ref doCheckout(String branch, boolean forceCreate, String startPoint) throws GitAPIException, IOException {
        if (git.getRepository().getRepositoryState() != RepositoryState.SAFE) {
            //自旋等待
            int i = 0;
            while (i++ < 5 && git.getRepository().getRepositoryState() != RepositoryState.SAFE) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        }
        CheckoutCommand checkoutCmd = git.checkout();
        checkoutCmd.setName(branch);
        checkoutCmd.setStartPoint(startPoint);
        try {
            return checkoutCmd.call();
        } catch (RefNotFoundException e) {
            Ref ref = this.repo.exactRef(Constants.R_REMOTES + Constants.DEFAULT_REMOTE_NAME + "/" + branch);
            if (ref == null) throw new RefNotFoundException(branch + " not found");
            try {
                return checkoutCmd.setName(ref.getName()).call();
            } catch (RefNotFoundException e2) {
                if (forceCreate) {
                    return checkoutCmd.setName(branch).setCreateBranch(true).call();
                } else {
                    throw e2;
                }
            }
        }
    }

    /**
     * TODO: 暂不需要push功能，需要做权限认证
     *
     * @return
     * @throws GitAPIException
     */
    public Iterable<PushResult> doPush() throws GitAPIException {
        PushCommand push = git.push();
        push.setPushAll();
        return push.call();
    }

    /**
     * TODO: 暂不需要rebase
     *
     * @throws GitAPIException
     */
    public void doRebase() throws GitAPIException {
        RebaseCommand rebase = git.rebase();
        rebase.call();
    }

    /**
     * TODO: 暂不需要stash
     *
     * @throws GitAPIException
     * @deprecated since original
     */
    public void doStash() throws GitAPIException {
        StashApplyCommand stashApplyCommand = git.stashApply();
        StashCreateCommand stashCreateCommand = git.stashCreate();
        StashDropCommand stashDropCommand = git.stashDrop();
        StashListCommand stashListCommand = git.stashList();
    }

    /**
     * 列出某引用下的所有日志
     *
     * @throws GitAPIException
     */
    public List<ReflogEntry> doReflog(String ref) throws GitAPIException {
        ReflogCommand reflogCommand = git.reflog();
        reflogCommand.setRef(ref);
        return new ArrayList<>(reflogCommand.call());
    }

    public List<ReflogEntry> doReflog() throws GitAPIException {
        ReflogCommand reflogCommand = git.reflog();
        return new ArrayList<>(reflogCommand.call());
    }

    /**
     * 版本回退
     *
     * @return
     * @throws GitAPIException
     */
    public Ref doReset(String revision) throws GitAPIException {
        ResetCommand resetCommand = git.reset();
        resetCommand.setMode(ResetCommand.ResetType.HARD);
        resetCommand.setRef(revision);
        return resetCommand.call();
    }

    /**
     * 列出所有remote
     * git remote
     *
     * @return
     * @throws GitAPIException
     */
    public List<RemoteConfig> listRemote() throws GitAPIException {
        RemoteListCommand remoteListCommand = git.remoteList();
        return remoteListCommand.call();
    }

    public RemoteConfig removeRemote(String remotename) throws GitAPIException {
        RemoteRemoveCommand remoteRemoveCommand = git.remoteRemove();
        remoteRemoveCommand.setName(remotename);
        return remoteRemoveCommand.call();
    }

    /**
     * 增加远程库 fetch & push
     * git remote add [-t <branch>] [-m <master>] [-f] [--tags | --no-tags] [--mirror=<fetch|push>] <name> <url>
     *
     * @param uri
     * @param remotename
     * @return
     * @throws GitAPIException
     * @throws URISyntaxException
     */
    public RemoteConfig addRemote(String uri, String remotename) throws GitAPIException, URISyntaxException {
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName(remotename);
        remoteAddCommand.setUri(new URIish(uri));
        return remoteAddCommand.call();
    }

    /**
     * 设置远程库 URI， 若不存在remotename, 则新建
     *
     * @param uri
     * @param remotename
     * @return
     * @throws GitAPIException
     * @throws URISyntaxException
     */
    public RemoteConfig setRemoteUri(String uri, String remotename) throws GitAPIException, URISyntaxException {
        return setRemoteUri(uri, remotename, false);
    }

    /**
     * 设置远程库 URI， 若不存在remotename, 则新建
     *
     * @param uri
     * @param remotename
     * @param pushOrFetch <code>true</code> to set the push url, <code>false</code> to
     *                    set the fetch url
     * @return
     * @throws GitAPIException
     * @throws URISyntaxException
     */
    public RemoteConfig setRemoteUri(String uri, String remotename, boolean pushOrFetch) throws GitAPIException, URISyntaxException {
        RemoteSetUrlCommand remoteSetUrlCommand = git.remoteSetUrl();
        remoteSetUrlCommand.setName(remotename);
        remoteSetUrlCommand.setUri(new URIish(uri));
        remoteSetUrlCommand.setPush(pushOrFetch);
        return remoteSetUrlCommand.call();
    }

    public Map parseRemoteConfig(final RemoteConfig remoteConfig) {
        Map<String, Object> result = new HashMap<>();
        result.put("name", remoteConfig.getName());
        result.put("uris", remoteConfig.getURIs());
        return result;
    }

    /**
     * @throws GitAPIException
     * @deprecated since original
     */
    private void doRemote() throws GitAPIException {
        //TO DO
    }

    /**
     * to test
     * 应用补丁
     * git apply
     *
     * @param in
     * @return
     * @throws GitAPIException
     */
    public ApplyResult doApply(InputStream in) throws GitAPIException {
        ApplyCommand applyCmd = git.apply();
        applyCmd.setPatch(in);
        return applyCmd.call();
    }

    /**
     * to test
     *
     * @throws GitAPIException
     */
    public void doMerge() throws GitAPIException {
        MergeCommand mergeCmd = git.merge();
        mergeCmd.setCommit(false);
        mergeCmd.setMessage("");
        mergeCmd.setStrategy(MergeStrategy.THEIRS);
        mergeCmd.call();
    }

    public CompareResult compareCommit(RevCommit older, RevCommit newer) throws GitAPIException, IOException {
        CompareResult compareResult = new CompareResult();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //DiffFormatter df = new DiffFormatter(out);
        //df.setRepository(this.repo);
        //List<DiffEntry> changed = df.scan(older.getTree(), newer.getTree());
        List<DiffEntry> changed = doDiff(getTreeIteratorByCommit(older), getTreeIteratorByCommit(newer), out);
        int count = 0;
        for (DiffEntry entry : changed) {
            if (this.changeRecordCount != null && count >= this.changeRecordCount) {
                break;
            }
            switch (entry.getChangeType()) {
                case ADD:
                    //新增的文件： entry.getNewPath()
                    compareResult.getCreated().add(entry.getNewPath());
                    break;
                case DELETE:
                    //删除的文件： entry.getOldPath();
                    compareResult.getDeleted().add(entry.getOldPath());
                    break;
                case MODIFY:
                    //修改的文件： entry.getNewPath()
                    compareResult.getUpdated().add(entry.getNewPath());
                    break;
                case COPY:
                    // 从entry.getOldPath()复制到entry.getNewPath();
                    compareResult.getReplica().add(new String[]{entry.getOldPath(), entry.getNewPath()});
                    break;
                case RENAME:
                    // 从entry.getOldPath()移动到entry.getNewPath();
                    compareResult.getRenamed().add(new String[]{entry.getOldPath(), entry.getNewPath()});
                    break;
            }
            count++;
        }
        //进一步分析改动了多少行，只要解析out.toString就好
        // to do
        // countChangedLines(out.toString())
        return compareResult;
    }

    public CompareResult compareCommit(String olderId, String newerId) throws GitAPIException, IOException {

        return compareCommit((RevCommit) this.repo.resolve(olderId), (RevCommit) this.repo.resolve(newerId));
    }

    public byte[] getContentByBranchFile(final String branch, final String revision, final String filename) throws IOException {
        RevWalk walk = new RevWalk(this.repo);
        Ref ref = this.repo.findRef(branch);
        if (ref == null) {
            //此处refs/remotes/origin/ 需列出所有远程库查找判断，但现阶段这情况不会出现，所以懒得写了
            ref = this.repo.exactRef(Constants.R_REMOTES + Constants.DEFAULT_REMOTE_NAME + "/" + branch);
        }
        ObjectId objectId = ref.getObjectId();
        if (!StringUtils.isEmptyOrNull(revision)) {
            objectId = ref.getObjectId();
        }
        //ObjectId objectId = ref.getObjectId();
        RevCommit revCommit = walk.parseCommit(objectId);
        RevTree revTree = revCommit.getTree();
        TreeWalk treeWalk = TreeWalk.forPath(this.repo, filename, revTree);
        if (treeWalk == null) {
            return null;
        }
        ObjectId blobId = treeWalk.getObjectId(0);
        ObjectLoader loader = this.repo.open(blobId);
        return loader.getBytes();
    }

    /**
     * 无分支信息时，遍历本地分支，找到文件
     *
     * @param revision
     * @param filename
     * @return
     * @throws GitAPIException
     */
    public byte[] getContentByRevisionFile(final String revision, final String filename) throws GitAPIException {
        for (Ref ref : listBranch()) {
            try {
                byte[] result = getContentByBranchFile(new File(ref.getName()).getName(), revision, filename);
                if (result == null) continue;
                return result;
            } catch (Exception e) {
                continue;
            }
        }
        return null;
    }

    /**
     * 网上找到的方法，根据commit号找到该commit所属的branches，测试无效
     *
     * @param refs
     * @param commit
     * @return
     * @throws IOException
     * @deprecated
     */
    public List<String> getBranchesByCommit(Map<String, Ref> refs, RevCommit commit) throws IOException {
        List<String> branches = new ArrayList<>();
        RevWalk walk = new RevWalk(this.repo);
        if (refs == null || refs.keySet().size() == 0) refs = repo.getAllRefs();
        for (Map.Entry<String, Ref> e : refs.entrySet())
            if (e.getKey().startsWith(Constants.R_REMOTES)) {
                if (walk.isMergedInto(commit, walk.parseCommit(e.getValue().getObjectId())))
                    branches.add(e.getValue().getName());
            }
        return branches;
    }

    public List<String> getBranchesByCommit(List<Ref> refs, RevCommit commit) throws GitAPIException, IOException {
        List<String> branches = new ArrayList<>();
        RevWalk walk = new RevWalk(this.repo);
        if (refs == null || refs.size() == 0) refs = listBranch();
        for (Ref ref : refs) {
            if (ref.getName().startsWith(Constants.R_HEADS)) {
                RevCommit headCommit = walk.parseCommit(ref.getObjectId());
                if (commit.getName().equals(headCommit.getName())) {
                    branches.add(ref.getName());
                    continue;
                }
                ;
                if (isAncestor(walk, commit, headCommit)) {
                    branches.add(ref.getName());
                }
            }
        }
        return branches;
    }

    public List<Map<String, Object>> listSubFileByCommitAndBranch(String revision, String branch, String path, FileListMode mode, String searchName) throws IOException, GitAPIException {
        List<Map<String, Object>> result = new ArrayList<>();
        RevWalk walk = new RevWalk(this.repo);
        Ref ref = this.repo.findRef(branch);
        if (ref == null) {
            ref = this.repo.exactRef(Constants.R_REMOTES + Constants.DEFAULT_REMOTE_NAME + "/" + branch);
            if (ref == null) {
                throw new RuntimeException("branch not found");
            }
        }
        ObjectId objId;
        if (revision != null) {
            objId = this.repo.resolve(revision);
        } else {
            objId = ref.getObjectId();
        }
        RevCommit revCommit = walk.parseCommit(objId);
        RevTree revTree = revCommit.getTree();
        TreeWalk treeWalk = new TreeWalk(this.repo);
        if (path == null || "".equals(path.trim()) || "/".equals(path.trim())) {
            treeWalk.addTree(revTree);
        } else {
            treeWalk = TreeWalk.forPath(this.repo, path, revTree);
            if (treeWalk == null) {
                throw new RuntimeException("path not found");
            } else if (treeWalk.isSubtree()) {
                treeWalk.enterSubtree();
            }
        }
        if (searchName != null && !"".equals(searchName.trim())) {
            treeWalk.setRecursive(true);
            //treeWalk.setFilter(AndTreeFilter.create(treeWalk.getFilter(), FileNameFilter.create(searchName)));
        }
        while (treeWalk.next()) {
            Map<String, Object> item = new HashMap<>();
            switch (mode) {
                case FILE:
                    if (treeWalk.isSubtree()) {
                        continue;
                    }
                    break;
                case DIR:
                    if (!treeWalk.isSubtree()) {
                        continue;
                    }
                    break;
                case ALL:
                    break;
            }
            if (searchName != null && !treeWalk.getNameString().contains(searchName)) {
                continue;
            }
            String fileName = treeWalk.getNameString();
            String filePath = treeWalk.getPathString();
            Iterable<RevCommit> fileCommits = doLog(objId, null, false, null, filePath, 1);
            Iterator<RevCommit> iterator = fileCommits.iterator();
            RevCommit fileCommit = iterator.next();
            if (fileCommit != null) {
                item.putAll(parseRevCommit(fileCommit));
            }
            item.put("type", treeWalk.isSubtree() ? "DIR" : fileName.substring(fileName.lastIndexOf(".") == -1 ? fileName.length() : fileName.lastIndexOf(".") + 1));
            item.put("file", fileName);
            item.put("path", "/" + filePath);
            result.add(item);
        }
        return result;
    }

    public List<Map<String, Object>> listSubFileByCommitAndBranch(String revision, String branch, String path) throws IOException, GitAPIException {
        return listSubFileByCommitAndBranch(revision, branch, path, FileListMode.FILE, null);
    }

    public List<Map<String, Object>> listSubFileByCommitAndBranch(String branch, String path) throws IOException, GitAPIException {
        return listSubFileByCommitAndBranch(null, branch, path, FileListMode.FILE, null);
    }

    public List<Map<String, Object>> listSubDirByCommitAndBranch(String revision, String branch, String path) throws IOException, GitAPIException {
        return listSubFileByCommitAndBranch(revision, branch, path, FileListMode.DIR, null);
    }

    public List<Map<String, Object>> listSubDirByCommitAndBranch(String branch, String path) throws IOException, GitAPIException {
        return listSubFileByCommitAndBranch(null, branch, path, FileListMode.DIR, null);
    }

    public ObjectId getCurrentRevision(final String branch) throws IOException {
        RevWalk walk = new RevWalk(this.repo);
        Ref ref = this.repo.findRef(branch);
        return ref.getObjectId();
    }

    public boolean isAncestor(RevWalk walk, RevCommit former, RevCommit latter) throws IOException {
        if (latter.getParents() == null) return false;
        for (RevCommit parent : latter.getParents()) {
            if (parent.getName().equals(former.getName())) {
                return true;
            } else {
                parent = walk.parseCommit(parent.getId());
                //如果后一个提交时间早于前一个提交，则不再继续遍历
                if (parent.getCommitTime() < former.getCommitTime()) {
                    return false;
                }
                return isAncestor(walk, former, parent);
            }
        }
        return false;
    }

    public RevCommit getRevCommitByRevision(RevWalk walk, String revision) throws IOException {
        return walk.parseCommit(this.repo.resolve(revision));
    }

    public RevCommit getRevCommitByRevision(String revision) throws IOException {
        RevWalk walk = new RevWalk(this.repo);
        return walk.parseCommit(this.repo.resolve(revision));
    }

    public List<String> getBranchesByCommit(List<Ref> refs, String commit) throws GitAPIException, IOException {
        RevWalk walk = new RevWalk(this.repo);
        RevCommit revCommit = walk.parseCommit(this.repo.resolve(commit));
        return getBranchesByCommit(refs, revCommit);
    }

    public List<String> getBranchesByCommit(String commit) throws GitAPIException, IOException {
        return getBranchesByCommit(null, commit);
    }


    private <T, C extends GitCommand<T>> void setCredentialsProvider(TransportCommand<C, T> transCmd, String username, String password) {
        transCmd.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
    }

    private <T, C extends GitCommand<T>> void setTransportConfigCallback(TransportCommand<C, T> transCmd, final SshSessionFactory sshSessionFactory) {
        transCmd.setTransportConfigCallback(new TransportConfigCallback() {
            @Override
            public void configure(Transport transport) {
                SshTransport sshTransport = (SshTransport) transport;
                sshTransport.setSshSessionFactory(sshSessionFactory);
            }
        });
    }

    private boolean checkAndDeleteGitLock() {
        File lockfile = new File(git.getRepository().getDirectory(), "index.lock");
        boolean lockfileExists = lockfile.exists();
        if (lockfileExists) {
            lockfile.delete();
        }
        return lockfileExists;
    }

    public Git getGit() {
        return git;
    }

    public void setGit(Git git) {
        this.git = git;
    }

    public List<String> getUris() {
        return uris;
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    public Repository getRepo() {
        return repo;
    }

    public void setRepo(Repository repo) {
        this.repo = repo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getChangeRecordCount() {
        return changeRecordCount;
    }

    public void setChangeRecordCount(Integer changeRecordCount) {
        this.changeRecordCount = changeRecordCount;
    }

    public static class CompareResult {

        private final List<String> created = new ArrayList<>();
        private final List<String> deleted = new ArrayList<>();
        private final List<String> updated = new ArrayList<>();
        private final List<String[]> renamed = new ArrayList<>();
        private final List<String[]> replica = new ArrayList<>();

        public List<String> getCreated() {
            return created;
        }

        public List<String> getDeleted() {
            return deleted;
        }

        public List<String> getUpdated() {
            return updated;
        }

        public List<String[]> getRenamed() {
            return renamed;
        }

        public List<String[]> getReplica() {
            return replica;
        }
    }

    //tomcat以服务方式启动时，以system用户启动，无法自动获取rsa文件，需手动指定ras文件地址
    private class MyJschConfigSessionFactory extends JschConfigSessionFactory {

        @Override
        protected JSch getJSch(OpenSshConfig.Host hc, FS fs) throws JSchException {
            JSch jSch = new JSch();
            jSch.removeAllIdentity();
            jSch.addIdentity(privatekeyFile);
            jSch.setKnownHosts(knowHostFile);
            //return jSch;
            return super.getJSch(hc, fs);
        }

        @Override
        protected void configure(OpenSshConfig.Host hc, Session session) {
            session.setConfig("StrictHostKeyChecking", "no");
        }

        @Override
        protected JSch createDefaultJSch(FS fs) throws JSchException {
            JSch defaultJSch = super.createDefaultJSch(fs);
            defaultJSch.addIdentity(privatekeyFile);
            return defaultJSch;
        }
    }

}


