package org.sunt.storage.repo.impl;

import org.apache.commons.net.ftp.FTPClient;
import org.sunt.storage.repo.IStorage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;

public class FTPStorage implements IStorage {

    private final FTPClient client;
    private static final String PROTOCOL_PREFIX = "ftp:";

    @Override
    public boolean acceptURL(URL url) {
        return url.getProtocol() != null && url.getProtocol().toLowerCase().startsWith(PROTOCOL_PREFIX);
    }

    public FTPStorage(String ftpServer, String username, String password) throws IOException {
        this(ftpServer, 0, username, password);
    }

    public FTPStorage(String host, int port, String username, String password) throws IOException {
        this(InetAddress.getByName(host), port, username, password);
    }

    public FTPStorage(InetAddress ftpServer, int port, String username, String password) throws IOException {
        this.client = new FTPClient();
        this.client.connect(ftpServer, port);
        boolean succeed = this.client.login(username, password);
        if (!succeed) {
            this.client.disconnect();
        }
    }

    public FTPStorage(FTPClient client) {
        this.client = client;
    }


    @Override
    public int write(String path, byte[] bytes) throws IOException {
        try (InputStream bis = new ByteArrayInputStream(bytes)) {
            client.storeFile(path, bis);
            return bytes.length;
        }
    }

    @Override
    public int write(String path, InputStream inputStream) throws IOException {
        return client.storeFile(path, inputStream) ? 1 : 0;
    }

    @Override
    public int write(URL url, InputStream inputStream) throws IOException {
        return client.storeFile(url.getPath(), inputStream) ? 1 : 0;
    }

    @Override
    public byte[] readBytes(String path) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            boolean succeed = client.retrieveFile(path, bos);
            if (succeed) {
                return bos.toByteArray();
            }
        }
        return new byte[0];
    }

    @Override
    public InputStream readInputStream(String path) throws IOException {
        return this.client.retrieveFileStream(path);
    }

    @Override
    public boolean delete(String path) throws IOException {
        return this.client.deleteFile(path);
    }

    @Override
    public boolean delete(URL url) throws IOException {
        return this.client.deleteFile(url.getPath());
    }

    @Override
    public boolean mkdir(String path) throws IOException {
        return false;
    }

    @Override
    public boolean exists(String path) throws IOException {
        return false;
    }
}
