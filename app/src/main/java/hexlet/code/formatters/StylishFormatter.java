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
            String fieldStatus = (String) diffLine.get("STATUS");
            String fieldName = (String) diffLine.get("FIELD");
            Object oldFieldValue = diffLine.get("OLD_VALUE");
            Object newFieldValue = diffLine.get("NEW_VALUE");

            switch (fieldStatus) {
                case Constants.REMOVED -> builder.append(REMOVED_LINE_FORMAT.formatted(fieldName, oldFieldValue));
                case Constants.ADDED -> builder.append(ADDED_LINE_FORMAT.formatted(fieldName, newFieldValue));
                case Constants.UNCHANGED -> builder.append(SAME_LINE_FORMAT.formatted(fieldName, oldFieldValue));
                case Constants.CHANGED -> {
                    builder.append(UPDATED_LINE_FORMAT_REMOVED.formatted(fieldName, oldFieldValue));
                    builder.append(UPDATED_LINE_FORMAT_ADDED.formatted(fieldName, newFieldValue));
                }
                default -> { }
            }
        }

        builder.append("}");
        return builder.toString();
    }
}

