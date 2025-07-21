package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String content, String format) throws IOException {
        ObjectMapper mapper = switch (format) {
            case Constants.FORMAT_JSON -> new ObjectMapper();
            case Constants.FORMAT_YML -> new YAMLMapper();
            default -> throw new RuntimeException("Unsupported format: " + format);
        };
        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}


