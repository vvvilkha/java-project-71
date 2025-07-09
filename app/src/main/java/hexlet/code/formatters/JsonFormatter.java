package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String formatJson(List<Map<String, Object>> diff) throws Exception {
        List<Map<String, Object>> mapped = diff.stream().map(line -> {
            Map<String, Object> mappedLine = new LinkedHashMap<>();
            mappedLine.put("key", line.get("FIELD"));

            String status = (String) line.get("STATUS");
            if (status.equals("SAME")) {
                mappedLine.put("status", "unchanged");
                mappedLine.put("value", line.get("OLD_VALUE"));
            } else if (status.equals("REMOVED")) {
                mappedLine.put("status", "removed");
                mappedLine.put("value", line.get("OLD_VALUE"));
            } else if (status.equals("ADDED")) {
                mappedLine.put("status", "added");
                mappedLine.put("value", line.get("NEW_VALUE"));
            } else if (status.equals("UPDATED")) {
                mappedLine.put("status", "changed");
                mappedLine.put("oldValue", line.get("OLD_VALUE"));
                mappedLine.put("newValue", line.get("NEW_VALUE"));
            }
            return mappedLine;
        }).toList();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(mapped);
    }
}
