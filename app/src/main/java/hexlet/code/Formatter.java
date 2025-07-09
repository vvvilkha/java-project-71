package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

public class Formatter {
    public static String format(Object diff, String formatName) {
        return switch (formatName) {
            case "plain" -> PlainFormatter.format(diff);
            case "stylish" -> StylishFormatter.format(diff);
            default -> throw new RuntimeException("Unknown format: " + formatName);
        };
    }
}
