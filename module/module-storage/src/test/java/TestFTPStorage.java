import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sunt.storage.repo.impl.FTPStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFTPStorage {

    public static final String PASS = "123456";
    private static final int PORT = 2121;
    private static final String USER = "root";

    @BeforeAll
    public static void ftpServer() throws FtpException {
        FtpServerFactory ftpServerFactory = new FtpServerFactory();

        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(PORT);

        ftpServerFactory.addListener("default", listenerFactory.createListener());

        BaseUser user = new BaseUser();
        user.setName(USER);
        user.setPassword(PASS);
        user.setHomeDirectory(System.getProperty("java.io.tmpdir"));

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new WritePermission());
        user.setAuthorities(authorities);

        ftpServerFactory.getUserManager().save(user);

        FtpServer server = ftpServerFactory.createServer();
        server.start();
    }

    @Test
    public void testWriteAndReadAndDeleteFile() throws IOException {
        FTPStorage ftpStorage = new FTPStorage("localhost", PORT, USER, PASS);
        if (ftpStorage.write("/abc", "abc".getBytes()) > 0) {
            String abc = new String(ftpStorage.readBytes("/abc"));
            System.out.println(abc);
            ftpStorage.delete("/abc");
            Assertions.assertFalse(ftpStorage.exists("/abc"));
        }
    }

}
