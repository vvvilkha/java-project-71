package hexlet.code.formatters;

import hexlet.code.Constants;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(String format, List<Map<String, Object>> result) throws Exception {
        return switch (format) {
            case Constants.FORMAT_JSON_OUTPUT -> JsonFormatter.formatJson(result);
            case Constants.FORMAT_PLAIN -> PlainFormatter.formatPlain(result);
            case Constants.FORMAT_STYLISH -> StylishFormatter.formatStylish(result);
            default -> throw new IllegalArgumentException("Unknown format: " + format);
        };
    }
}
