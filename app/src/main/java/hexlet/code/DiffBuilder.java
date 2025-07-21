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

            Map<String, Object> keyCompareResults = new HashMap<>();
            keyCompareResults.put("FIELD", key);

            if (!map2.containsKey(key)) {
                keyCompareResults.put("STATUS", Constants.REMOVED);
                keyCompareResults.put("OLD_VALUE", value1);
            } else if (!map1.containsKey(key)) {
                keyCompareResults.put("STATUS", Constants.ADDED);
                keyCompareResults.put("NEW_VALUE", value2);
            } else if (Objects.equals(value1, value2)) {
                keyCompareResults.put("STATUS", Constants.UNCHANGED);
                keyCompareResults.put("OLD_VALUE", value1);
            } else {
                keyCompareResults.put("STATUS", Constants.CHANGED);
                keyCompareResults.put("OLD_VALUE", value1);
                keyCompareResults.put("NEW_VALUE", value2);
            }

            result.add(keyCompareResults);
        }

        return result;
    }
}

