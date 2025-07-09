package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");

        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            if (data1.containsKey(key) && data2.containsKey(key)) {
                Object value1 = data1.get(key);
                Object value2 = data2.get(key);
                if (value1.equals(value2)) {
                    result.append("    ").append(key).append(": ").append(toString(value1)).append("\n");
                } else {
                    result.append("  - ").append(key).append(": ").append(toString(value1)).append("\n");
                    result.append("  + ").append(key).append(": ").append(toString(value2)).append("\n");
                }
            } else if (data1.containsKey(key)) {
                result.append("  - ").append(key).append(": ").append(toString(data1.get(key))).append("\n");
            } else {
                result.append("  + ").append(key).append(": ").append(toString(data2.get(key))).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static String toString(Object value) {
        return value instanceof Boolean || value instanceof Integer ? value.toString() : value.toString();
    }
}