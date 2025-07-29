package hexlet.code.formatters;

import hexlet.code.Constants;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static final String REMOVED_LINE_FORMAT = "  - %s: %s\n";
    public static final String ADDED_LINE_FORMAT = "  + %s: %s\n";
    public static final String SAME_LINE_FORMAT = "    %s: %s\n";
    public static final String UPDATED_LINE_FORMAT_REMOVED = "  - %s: %s\n";
    public static final String UPDATED_LINE_FORMAT_ADDED = "  + %s: %s\n";

    public static String formatStylish(List<Map<String, Object>> result) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");

        for (Map<String, Object> diffLine : result) {
            builder.append(buildStylishLine(diffLine));
        }

        builder.append("}");
        return builder.toString();
    }

    private static String buildStylishLine(Map<String, Object> line) {
        String status = (String) line.get("STATUS");
        String key = (String) line.get("FIELD");
        Object oldValue = line.get("OLD_VALUE");
        Object newValue = line.get("NEW_VALUE");

        return switch (status) {
            case Constants.REMOVED -> REMOVED_LINE_FORMAT.formatted(key, oldValue);
            case Constants.ADDED -> ADDED_LINE_FORMAT.formatted(key, newValue);
            case Constants.UNCHANGED -> SAME_LINE_FORMAT.formatted(key, oldValue);
            case Constants.CHANGED -> UPDATED_LINE_FORMAT_REMOVED.formatted(key, oldValue)
                    + UPDATED_LINE_FORMAT_ADDED.formatted(key, newValue);
            default -> throw new IllegalStateException("Unexpected status: " + status);
        };
    }
}

