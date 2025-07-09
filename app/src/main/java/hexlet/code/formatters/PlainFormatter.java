package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String format(Object diffObject) {
        List<Map<String, Object>> diff = (List<Map<String, Object>>) diffObject;
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> entry : diff) {
            String key = (String) entry.get("key");
            String status = (String) entry.get("status");

            switch (status) {
                case "removed" -> result.append("Property '").append(key).append("' was removed\n");
                case "added" -> result.append("Property '").append(key).append("' was added with value: ").append(formatValue(entry.get("value"))).append("\n");
                case "updated" -> result.append("Property '").append(key).append("' was updated. From ")
                        .append(formatValue(entry.get("oldValue"))).append(" to ").append(formatValue(entry.get("newValue"))).append("\n");
                default -> {}
            }
        }

        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        } else {
            return value.toString();
        }
    }
}