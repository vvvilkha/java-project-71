package hexlet.code.formatters;

import hexlet.code.Constants;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static final String REMOVED_LINE_FORMAT = "Property '%s' was removed";
    public static final String ADDED_LINE_FORMAT = "Property '%s' was added with value: %s";
    public static final String UPDATED_LINE_FORMAT = "Property '%s' was updated. From %s to %s";

    public static String formatPlain(List<Map<String, Object>> diff) {
        StringBuilder builder = new StringBuilder();

        for (Map<String, Object> diffLine : diff) {
            String line = buildPlainLine(diffLine);
            if (!line.isEmpty()) {
                builder.append(line).append("\n");
            }
        }

        return builder.toString().trim();
    }

    private static String buildPlainLine(Map<String, Object> line) {
        String fieldStatus = (String) line.get("STATUS");
        String fieldName = (String) line.get("FIELD");
        String oldValue = getValue(line.get("OLD_VALUE"));
        String newValue = getValue(line.get("NEW_VALUE"));

        return switch (fieldStatus) {
            case Constants.REMOVED -> REMOVED_LINE_FORMAT.formatted(fieldName);
            case Constants.ADDED -> ADDED_LINE_FORMAT.formatted(fieldName, newValue);
            case Constants.CHANGED -> UPDATED_LINE_FORMAT.formatted(fieldName, oldValue, newValue);
            case Constants.UNCHANGED -> "";
            default -> throw new IllegalStateException("Unexpected status: " + fieldStatus);
        };
    }

    private static String getValue(Object obj) {
        if (obj instanceof String) {
            return "'" + obj + "'";
        } else if (obj instanceof Map || obj instanceof List) {
            return "[complex value]";
        }
        return String.valueOf(obj);
    }
}

