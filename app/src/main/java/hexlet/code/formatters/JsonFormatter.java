package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.Constants;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonFormatter {
    public static String formatJson(List<Map<String, Object>> diff) throws Exception {
        List<Map<String, Object>> mapped = diff.stream().map(line -> {
            Map<String, Object> mappedLine = new LinkedHashMap<>();
            mappedLine.put("key", line.get("FIELD"));

            String status = (String) line.get("STATUS");

            switch (status) {
                case "SAME" -> {
                    mappedLine.put("status", Constants.UNCHANGED);
                    mappedLine.put("value", line.get("OLD_VALUE"));
                }
                case "REMOVED" -> {
                    mappedLine.put("status", Constants.REMOVED);
                    mappedLine.put("value", line.get("OLD_VALUE"));
                }
                case "ADDED" -> {
                    mappedLine.put("status", Constants.ADDED);
                    mappedLine.put("value", line.get("NEW_VALUE"));
                }
                case "UPDATED" -> {
                    mappedLine.put("status", Constants.CHANGED);
                    mappedLine.put("oldValue", line.get("OLD_VALUE"));
                    mappedLine.put("newValue", line.get("NEW_VALUE"));
                }
                default -> throw new IllegalStateException("Unknown status: " + status);
            }
            return mappedLine;
        }).collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(mapped);
    }
}

