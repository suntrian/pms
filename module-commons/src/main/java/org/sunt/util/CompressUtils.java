package org.sunt.util;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.exception.UnsupportedRarV5Exception;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompressUtils {

    public static final String[] FILE_SUFFIX = {"zip", "tar", "rar", "7z"};

    public static Map<String, byte[]> deCompress(File archive) throws IOException {
        if (!archive.exists()) {
            throw new FileNotFoundException(archive.getName() + "not exists");
        }
        if (archive.isDirectory()) {
            throw new IllegalArgumentException(archive.getName() + "is not archive file");
        }
        FileInputStream fileInputStream = new FileInputStream(archive);
        ArchiveInputStream archiveInputStream = null;
        String suffix = archive.getName().substring(archive.getName().lastIndexOf(".") + 1);
        Map<String, byte[]> result = new HashMap<>();
        ArchiveEntry entry;
        switch (suffix.toLowerCase()) {
            case "zip":
                archiveInputStream = new ZipArchiveInputStream(fileInputStream);
                break;
            case "tar":
                archiveInputStream = new TarArchiveInputStream(fileInputStream);
//        try {
//          archiveInputStream = new ArchiveStreamFactory().createArchiveInputStream(suffix,  fileInputStream);
//        } catch (ArchiveException e) {
//          throw new IllegalStateException("解圧文件"+ archive.getName() + "失败:"+e.getMessage(), e);
//        }
                break;
            case "rar":
                try {
                    Archive rarArchive = null;
                    try {
                        rarArchive = new Archive(fileInputStream);
                    } catch (RarException e) {
                        if (e instanceof UnsupportedRarV5Exception) {
                            throw new IllegalArgumentException("RAR5的算法未公开，暂不支持RAR5及以上的版本，请先解压后再上传");
                        }
                        throw e;
                    } catch (IOException e) {
                        throw e;
                    }
                    if (rarArchive.isEncrypted()) {
                        throw new IllegalStateException("文件被加密");
                    }
                    FileHeader fileHeader;
                    while (null != (fileHeader = rarArchive.nextFileHeader())) {
                        if (fileHeader.isDirectory()) {
                            continue;
                        }
                        String entryName = fileHeader.getFileNameString();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        rarArchive.extractFile(fileHeader, bos);
                        result.put(entryName, bos.toByteArray());
                        bos.close();
                    }
                    rarArchive.close();
                    return result;
                } catch (RarException e) {
                    throw new IllegalStateException("解圧文件" + archive.getName() + "失败:" + e.getMessage(), e);
                }
            case "7z":
                SevenZFile sevenZFile = new SevenZFile(archive);
                while (null != (entry = sevenZFile.getNextEntry())) {
                    if (entry.isDirectory()) {
                        continue;
                    }
                    String entryName = entry.getName();
                    byte[] content = new byte[(int) entry.getSize()];
                    sevenZFile.read(content);
                    result.put(entryName, content);
                }
                sevenZFile.close();
                return result;
            default:
                throw new IllegalStateException("不支持的压缩文件扩展名" + suffix);
        }
        while (null != (entry = archiveInputStream.getNextEntry())) {
            if (entry.isDirectory()) {
                continue;
            }
            String entryName = entry.getName();
//      byte[] content = new byte[(int) entry.getSize()];
//      archiveInputStream.read(content);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            IOUtils.copy(archiveInputStream, bos);
            byte[] content = bos.toByteArray();
            bos.close();
            result.put(entryName, content);
        }
        archiveInputStream.close();
        fileInputStream.close();
        return result;
    }

    public static List<File> deCompress(File archive, File folder, boolean mkdirs) throws IOException {
        if (!mkdirs && !folder.exists()) {
            throw new FileNotFoundException(folder.getName() + "not exists");
        } else if (mkdirs && !folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IllegalArgumentException("can not create folder " + folder.getName());
            }
        }
        Map<String, byte[]> fileContents = deCompress(archive);
        List<File> result = new ArrayList<>(fileContents.size());
        for (Map.Entry<String, byte[]> entry : fileContents.entrySet()) {
            File file = new File(folder, entry.getKey());
            file.getParentFile().mkdirs();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(entry.getValue());
                result.add(file);
            }
        }
        return result;
    }

    public static List<File> deCompress(File archive, File folder) throws IOException {
        return deCompress(archive, folder, false);
    }
}