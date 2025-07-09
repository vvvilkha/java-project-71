package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String format(Object diffObject) {
        List<Map<String, Object>> diff = (List<Map<String, Object>>) diffObject;
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String status = (String) entry.get("status");

            switch (status) {
                case "removed" -> result.append("  - ").append(key).append(": ").append(entry.get("value")).append("\n");
                case "added" -> result.append("  + ").append(key).append(": ").append(entry.get("value")).append("\n");
                case "unchanged" -> result.append("    ").append(key).append(": ").append(entry.get("value")).append("\n");
                case "updated" -> {
                    result.append("  - ").append(key).append(": ").append(entry.get("oldValue")).append("\n");
                    result.append("  + ").append(key).append(": ").append(entry.get("newValue")).append("\n");
                }
                default -> throw new RuntimeException("Unknown status: " + status);
            }
        }

        result.append("}");
        return result.toString();
    }
}
