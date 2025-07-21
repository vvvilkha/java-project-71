package hexlet.code.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    public static String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath)).trim();
    }

    public static String getFormat(String filePath) {
        int lastDot = filePath.lastIndexOf(".");
        if (lastDot == -1 || lastDot == filePath.length() - 1) {
            throw new IllegalArgumentException("File has no extension: " + filePath);
        }
        return filePath.substring(lastDot + 1);
    }
}
