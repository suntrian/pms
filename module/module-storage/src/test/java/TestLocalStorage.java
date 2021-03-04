import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sunt.storage.repo.impl.LocalStorage;

import java.io.File;
import java.io.IOException;

public class TestLocalStorage {

    private final String tempDir = System.getProperty("java.io.tmpdir");

    @Test
    public void testLocalFile() throws IOException {
        LocalStorage storage = new LocalStorage();
        String path = new File(tempDir, "/abc").getPath();
        if (storage.write(path, "abc".getBytes()) > 0) {
            String abc = new String(storage.readBytes(path));
            System.out.println(abc);
            storage.delete(path);
            boolean exists = storage.exists(path);
            Assertions.assertFalse(exists);
        } else {
            throw new IllegalStateException("未写入");
        }
    }

}
