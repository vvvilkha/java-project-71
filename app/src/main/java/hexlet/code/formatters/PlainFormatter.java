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
            String fieldStatus = (String) diffLine.get("STATUS");
            String fieldName = (String) diffLine.get("FIELD");
            String oldValue = getValue(diffLine.get("OLD_VALUE"));
            String newValue = getValue(diffLine.get("NEW_VALUE"));

            switch (fieldStatus) {
                case Constants.REMOVED -> builder.append(REMOVED_LINE_FORMAT.formatted(fieldName)).append("\n");
                case Constants.ADDED -> builder.append(ADDED_LINE_FORMAT.formatted(fieldName, newValue)).append("\n");
                case Constants.CHANGED -> builder.append(UPDATED_LINE_FORMAT.formatted(fieldName, oldValue, newValue))
                        .append("\n");
                default -> { }
            }
        }

        return builder.toString().trim();
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
