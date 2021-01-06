package org.sunt.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SshStorage implements IStorage {

    @Override
    public boolean acceptURL(URL url) {
        return false;
    }

    @Override
    public int write(String path, byte[] bytes) throws IOException {
        return 0;
    }

    @Override
    public int write(String path, InputStream inputStream) throws IOException {
        return 0;
    }

    @Override
    public int write(URL url, InputStream inputStream) throws IOException {
        return 0;
    }

    @Override
    public byte[] readBytes(String path) throws IOException {
        return new byte[0];
    }

    @Override
    public InputStream readInputStream(String path) throws IOException {
        return null;
    }

    @Override
    public boolean delete(String path) throws IOException {
        return false;
    }

    @Override
    public boolean delete(URL url) throws IOException {
        return false;
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
