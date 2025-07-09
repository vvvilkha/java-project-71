package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {

    private Differ() {
    }

    public static String generate(final Map<String, Object> data1,
                                  final Map<String, Object> data2) {
        Set<String> allKeys = new TreeSet<>(data1.keySet());
        allKeys.addAll(data2.keySet());

        StringBuilder result = new StringBuilder("{\n");

        for (String key : allKeys) {
            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ")
                        .append(data1.get(key)).append("\n");
            } else if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ")
                        .append(data2.get(key)).append("\n");
            } else if (!data1.get(key).equals(data2.get(key))) {
                result.append("  - ").append(key).append(": ")
                        .append(data1.get(key)).append("\n");
                result.append("  + ").append(key).append(": ")
                        .append(data2.get(key)).append("\n");
            } else {
                result.append("    ").append(key).append(": ")
                        .append(data1.get(key)).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}
