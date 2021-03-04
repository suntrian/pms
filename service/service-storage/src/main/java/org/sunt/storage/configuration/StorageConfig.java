package org.sunt.storage.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sunt.storage.repo.IStorage;
import org.sunt.storage.repo.impl.FTPStorage;
import org.sunt.storage.repo.impl.LocalStorage;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.UnsupportedAddressTypeException;

@Configuration
public class StorageConfig {

    @Bean
    public IStorage storage(@Value("${pms.baseDir}") String rootPath) throws IOException {
        URL url = new URL(rootPath);
        switch (url.getProtocol()) {
            case "file":
                return new LocalStorage();
//            case "hdfs":
//                return new HdfsStorage(rootPath);
            case "ftp":
                return new FTPStorage(rootPath, null, null);
//            case "ssh":
//                return new SshStorage();
            default:
                throw new UnsupportedAddressTypeException();
        }
    }

}
