package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class Parser {

    public static Map<String, Object> parse(String filePath) throws Exception {
            Path path = Path.of(filePath).toAbsolutePath();
            String content = Files.readString(path);
            String fileName = path.getFileName().toString();

            if (fileName.endsWith(".json")) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(content, Map.class);
            } else if (fileName.endsWith(".yml") || fileName.endsWith(".yaml")) {
                ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                return objectMapper.readValue(content, Map.class);
            } else {
                throw new RuntimeException("Unsupported file format: " + fileName);
        }
    }
}

