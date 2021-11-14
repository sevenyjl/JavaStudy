package question.Demo677键值映射;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution677 {
    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("app"));
        mapSum.insert("apple", 2);
        System.out.println(mapSum.sum("ap"));
    }
}

/**
 * 解答成功:
 * 执行耗时:15 ms,击败了12.20% 的Java用户
 * 内存消耗:38.1 MB,击败了94.26% 的Java用户
 */
class MapSum {
    HashMap<String, Integer> temp;

    public MapSum() {
        temp = new HashMap<>();
    }

    public void insert(String key, int val) {
        temp.put(key, val);
    }

    public int sum(String prefix) {
        return temp.entrySet().stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getKey().startsWith(prefix))
                .mapToInt(Map.Entry::getValue).sum();
    }
}
