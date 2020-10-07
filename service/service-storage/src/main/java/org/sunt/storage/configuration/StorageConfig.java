package org.sunt.storage.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sunt.storage.FTPStorage;
import org.sunt.storage.HdfsStorage;
import org.sunt.storage.IStorage;
import org.sunt.storage.LocalStorage;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.UnsupportedAddressTypeException;

@Configuration
public class StorageConfig {

    @Bean
    public IStorage storage(@Value("") String rootPath) throws IOException {
        URL url = new URL(rootPath);
        switch (url.getProtocol()) {
            case "file":
                return new LocalStorage();
            case "hdfs":
                return new HdfsStorage(rootPath);
            case "ftp":
                return new FTPStorage(rootPath, null, null);
            default:
                throw new UnsupportedAddressTypeException();
        }
    }

}