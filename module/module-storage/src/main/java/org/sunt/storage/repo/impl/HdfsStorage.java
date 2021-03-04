package org.sunt.storage.repo.impl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.sunt.storage.repo.IStorage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class HdfsStorage implements IStorage {

    private static final String PROTOCOL_PREFIX = "hdfs:";
    private final Configuration configuration;

    public HdfsStorage(Configuration configuration) {
        this.configuration = configuration;
    }

    public HdfsStorage(String url) {
        this.configuration = new Configuration();
        this.configuration.set("fs.defaultFS", url);
    }

    @Override
    public boolean acceptURL(URL url) {
        return url.getProtocol() != null && url.getProtocol().toLowerCase().startsWith(PROTOCOL_PREFIX);
    }

    @Override
    public int write(String path, byte[] bytes) throws IOException {
        Path filePath = new Path(path);
        try (FileSystem fs = FileSystem.get(URI.create(path), this.configuration);
             FSDataOutputStream outputStream = fs.create(filePath)) {
            outputStream.write(bytes);
            outputStream.flush();
        }
        return bytes.length;
    }

    @Override
    public int write(String path, InputStream inputStream) throws IOException {
        try (FileSystem fs = FileSystem.get(URI.create(path), this.configuration);
             FSDataOutputStream outputStream = fs.create(new Path(path))) {
            int x, y = 0;
            while ((x = inputStream.read()) != -1) {
                y++;
                outputStream.write(x);
            }
            outputStream.flush();
            return y;
        }
    }

    @Override
    public int write(URL url, InputStream inputStream) throws IOException {
        return write(url.getPath(), inputStream);
    }

    @Override
    public byte[] readBytes(String path) throws IOException {
        try (FileSystem fileSystem = FileSystem.get(URI.create(path), configuration);
             FSDataInputStream in = fileSystem.open(new Path(path));
             ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            IOUtils.copyBytes(in, out, configuration);
            return out.toByteArray();
        }
    }

    @Override
    public InputStream readInputStream(String path) throws IOException {
        FileSystem fileSystem = FileSystem.get(URI.create(path), configuration);
        return fileSystem.open(new Path(path));
    }

    @Override
    public boolean delete(String path) throws IOException {
        try (FileSystem fileSystem = FileSystem.get(URI.create(path), configuration)) {
            return fileSystem.delete(new Path(path), true);
        }
    }

    @Override
    public boolean delete(URL url) throws IOException {
        return delete(url.getPath());
    }

    @Override
    public boolean mkdir(String path) throws IOException {
        try (FileSystem fileSystem = FileSystem.get(URI.create(path), configuration)) {
            return fileSystem.mkdirs(new Path(path));
        }
    }

    @Override
    public boolean exists(String path) throws IOException {
        try (FileSystem fileSystem = FileSystem.get(URI.create(path), configuration)) {
            return fileSystem.exists(new Path(path));
        }
    }
}
