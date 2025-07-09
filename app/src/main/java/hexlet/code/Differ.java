package hexlet.code;

import java.util.*;

public final class Differ {
    private Differ() { }

    public static String generate(Map<String, Object> data1, Map<String, Object> data2, String formatName) {
        List<Map<String, Object>> diff = buildDiff(data1, data2);
        return Formatter.format(diff, formatName);
    }

    public static List<Map<String, Object>> buildDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(data1.keySet());
        keys.addAll(data2.keySet());

        List<Map<String, Object>> diff = new ArrayList<>();

        for (String key : keys) {
            Map<String, Object> entry = new HashMap<>();

            if (!data2.containsKey(key)) {
                entry.put("key", key);
                entry.put("status", "removed");
                entry.put("value", data1.get(key));
            } else if (!data1.containsKey(key)) {
                entry.put("key", key);
                entry.put("status", "added");
                entry.put("value", data2.get(key));
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                entry.put("key", key);
                entry.put("status", "unchanged");
                entry.put("value", data1.get(key));
            } else {
                entry.put("key", key);
                entry.put("status", "updated");
                entry.put("oldValue", data1.get(key));
                entry.put("newValue", data2.get(key));
            }

            diff.add(entry);
        }

        return diff;
    }
}