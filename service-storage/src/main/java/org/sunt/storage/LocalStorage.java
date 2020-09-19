package org.sunt.storage;

import java.io.*;
import java.net.URL;

public class LocalStorage implements IStorage {

    @Override
    public int write(String path, byte[] bytes) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(bytes);
            fos.flush();
        }
        return bytes.length;
    }

    @Override
    public int write(String path, InputStream inputStream) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            int x, y = 0;
            while ((x = inputStream.read()) != -1) {
                y++;
                fos.write(x);
            }
            fos.flush();
            return y;
        }
    }

    @Override
    public int write(URL url, InputStream inputStream) throws IOException {
        return write(url.getPath(), inputStream);
    }

    @Override
    public byte[] readBytes(String path) throws IOException {
        return new FileInputStream(path).readAllBytes();
    }

    @Override
    public InputStream readInputStream(String path) throws IOException {
        return new URL(path).openStream();
    }

    @Override
    public boolean delete(String path) throws IOException {
        return new File(path).delete();
    }

    @Override
    public boolean delete(URL url) throws IOException {
        return new File(url.getFile()).delete();
    }

    @Override
    public boolean mkdir(String path) throws IOException {
        return false;
    }

    @Override
    public boolean exists(String path) throws IOException {
        return new File(path).exists();
    }
}
