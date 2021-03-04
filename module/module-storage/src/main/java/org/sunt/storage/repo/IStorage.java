package org.sunt.storage.repo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface IStorage {

    boolean acceptURL(URL url);

    int write(String path, byte[] bytes) throws IOException;

    int write(String path, InputStream inputStream) throws IOException;

    int write(URL url, InputStream inputStream) throws IOException;

    byte[] readBytes(String path) throws IOException;

    InputStream readInputStream(String path) throws IOException;

    boolean delete(String path) throws IOException;

    boolean delete(URL url) throws IOException;

    boolean mkdir(String path) throws IOException;

    boolean exists(String path) throws IOException;

}
