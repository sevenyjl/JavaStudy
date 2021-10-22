package simulation.professional.d20211020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 有 N 个由小写字母组成的非空字符串列表 strings。你需要在这些字符串中选出 M 组，每组三个，记为S[0], S[1], ... S[3M-1]，满足：每组字符串存在公共前缀，且该前缀非空。每组前缀互不相同。请找出 M 的最大值。
 * <p>
 * 例 1:
 * <p>
 * 输入: strings = ["leetcode", "leet", "happycode", "lee"]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 我们可以取["leetcode", "leet","lee"]，并令公共前缀为"l"或"le"或"lee"。
 * <p>
 * 例 2:
 * <p>
 * 输入: strings = ["l", "la", "lb", "lx", "lxa", "lxb"]
 * <p>
 * 输出: 2
 * <p>
 * 解释: 我们可以取["l", "la", "lb"]，并令它们公共前缀为"l";然后取["lx", "lxa", "lxb"]，并令它们公共前缀为"lx"。故最多是两组。
 * <p>
 * 例 3:
 * <p>
 * 输入: strings = ["l", "la", "lb", "lc", "ld", "le"]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 这些字符串两两都只有一个公共前缀"l"。因为公共前缀需要保证唯一，我们只能取出一组。
 * <p>
 * 限制:
 * 1 <= N <= 1000
 * 1 <= strings 中每个字符串的长度 <= 50
 */
public class T3_NO {
    public static void main(String[] args) {
        System.out.println(maxSame(new String[] {"leetcode", "leet", "happycode", "lee"}));
        System.out.println(maxSame(new String[] {"l", "la", "lb", "lx", "lxa", "lxb"}));

    }

    static int result = 0;

    public static int maxSame(String[] input) {
        result = 0;
        int index = 1;
        HashMap<String, Integer> map = new HashMap<>();
        int maxStrLen = Arrays.stream(input).max(Comparator.comparingInt(String::length)).get().length();
        while (index < maxStrLen) {
            String temp = null;
            int count = 0;
            for (String s : input) {
                if (index > s.length()) {
                } else if (temp == null) {
                    temp = s.substring(0, index);
                    count++;
                } else if (temp.equals(s.substring(0, index))) {
                    count++;
                }
            }
            map.put(temp, count);
            index++;
        }
        boolean[] booleans = new boolean[input.length];
        map.forEach((k, v) -> {
            if (v >= 3) {
                boolean isEdit = false;
                for (int i = 0; i < input.length; i++) {
                    if (!booleans[i] && input[i].startsWith(k)) {
                        booleans[i] = true;
                        isEdit = true;
                    }
                }
                if (isEdit) {
                    result++;
                }
            }
        });
        return result;
    }

    public static int findMostPrefix(String[] strings) {
        //首先拿到所有字符串的最大长度
        int mostLength = 0;
        for (String s : strings) {
            if (s.length() > mostLength) {
                mostLength = s.length();
            }
        }
        int count = 0;
        Set<String> used = new HashSet<>();
        for (int i = mostLength; i > 0; i--) {
            //记录字符串前缀相同的字符串
            Map<String, List<String>> map = new HashMap<>();
            for (String s : strings) {
                if (s.length() >= i && !used.contains(s)) {
                    List<String> list = map.getOrDefault(s.substring(0, i), new ArrayList<>());
                    list.add(s);
                    if (list.size() == 3) {
                        count++;
                        used.addAll(list);
                        break;
                    }
                    map.put(s.substring(0, i), list);
                }
            }
        }
        return count;
    }
}
