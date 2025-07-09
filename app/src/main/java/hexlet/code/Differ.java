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
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (!data2.containsKey(key)) {
                result.append("  - ").append(key).append(": ")
                        .append(value1).append("\n");
            } else if (!data1.containsKey(key)) {
                result.append("  + ").append(key).append(": ")
                        .append(value2).append("\n");
            } else if (value1 == null && value2 != null
                    || value1 != null && value2 == null
                    || (value1 != null && !value1.equals(value2))) {
                result.append("  - ").append(key).append(": ")
                        .append(value1).append("\n");
                result.append("  + ").append(key).append(": ")
                        .append(value2).append("\n");
            } else {
                result.append("    ").append(key).append(": ")
                        .append(value1).append("\n");
            }
        }
        result.append("}");
        return result.toString();
    }
}