package hexlet.code.formatters;

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
            var fieldStatus = (String) diffLine.get("STATUS");
            var fieldName = (String) diffLine.get("FIELD");
            var oldFieldValue = diffLine.get("OLD_VALUE");
            var newFieldValue = diffLine.get("NEW_VALUE");

            switch (fieldStatus) {
                case "REMOVED" -> builder.append(REMOVED_LINE_FORMAT.formatted(fieldName, oldFieldValue));
                case "ADDED" -> builder.append(ADDED_LINE_FORMAT.formatted(fieldName, newFieldValue));
                case "SAME" -> builder.append(SAME_LINE_FORMAT.formatted(fieldName, oldFieldValue));
                case "UPDATED" -> {
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
