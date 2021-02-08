package x.snowroller.fileutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {

    public static byte[] readFromFile(File file) {
        byte[] content = new byte[0];
        System.out.println("Does file exists: " + file.exists());
        if (file.exists() && file.canRead()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                content = new byte[(int) file.length()];
                int count = fileInputStream.read(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
