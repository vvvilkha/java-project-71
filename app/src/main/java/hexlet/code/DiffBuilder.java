package hexlet.code;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Objects;

public class DiffBuilder {
    public static List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> result = new ArrayList<>();

        TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            result.add(buildLine(key, value1, value2, map1, map2));
        }

        return result;
    }

    private static Map<String, Object> buildLine(String key, Object value1, Object value2,
                                                 Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> diffLine = new HashMap<>();
        diffLine.put("FIELD", key);

        if (!map2.containsKey(key)) {
            diffLine.put("STATUS", Constants.REMOVED);
            diffLine.put("OLD_VALUE", value1);
        } else if (!map1.containsKey(key)) {
            diffLine.put("STATUS", Constants.ADDED);
            diffLine.put("NEW_VALUE", value2);
        } else if (Objects.equals(value1, value2)) {
            diffLine.put("STATUS", Constants.UNCHANGED);
            diffLine.put("OLD_VALUE", value1);
        } else {
            diffLine.put("STATUS", Constants.CHANGED);
            diffLine.put("OLD_VALUE", value1);
            diffLine.put("NEW_VALUE", value2);
        }

        return diffLine;
    }
}


