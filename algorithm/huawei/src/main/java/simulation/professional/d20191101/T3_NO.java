package simulation.professional.d20191101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import random.ArraysRandom;

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
 * http://3ms.huawei.com/km/blogs/details/7489043
 */
public class T3_NO {
    public static void main(String[] args) {
        // System.out.println(maxSame20211116(new String[] {"leetcode", "leet", "happycode", "lee"}));
        // System.out.println(findMostPrefix(new String[] {"leetcode", "leet", "happycode", "lee"}));
        // System.out.println(maxSame20211116(new String[] {"l", "la", "lb", "lx", "lxa", "lxb"}));
        // System.out.println(maxSame20211116(new String[] {"l", "la", "lb", "lx", "lxa", "lxb"}));
        // System.out.println(maxSame20211116(new String[] {"l", "lx", "lxa", "lbe", "lxe", "a", "b", "c"}));
        // System.out.println(findMostPrefix(new String[] {"l", "lx", "lxa", "lbe", "lxe", "a", "b", "c"}));
        // String[] randomStrings = ArraysRandom.createRandomStrings(20, 10, "abcdefg", true);
        // String[]  randomStrings = new String[] {"gdecgf", "aeaecfcdf", "gfebbagf", "fa", "gg", "b", "dgfbbce", "acfegbede", "bcc", "bdcfc", "a", "db", "fgeaea", "bcafecc", "ddcffcfcf", "aggbcdd", "cfb", };
        // System.out.println(maxSame20211116(randomStrings));
        // System.out.println(findMostPrefix(randomStrings));
        // Arrays.sort(randomStrings);
        // for (String randomString : randomStrings) {
        //     System.out.println(randomString);
        // }

        System.out.println(maxSame20211116(new String[] {
            "la", "lb", "lc",
            "laa", "lba", "lca",
            "lab", "lbb", "lcc",
            "labc", "laba", "lab",
            "ld", "le", "lf"
        }));
        System.out.println(findMostPrefix(new String[] {
            "la", "lb", "lc",
            "laa", "lba", "lca",
            "lab", "lbb", "lcc",
            "ld", "le", "lf"
        }));

    }

    /**
     * 思路，从最长字符串开始sub 然后统计（简单来说：最长优先选择），并记录
     * 缺陷：如果有相同字符串咋办？！
     *
     * @param input
     * @return
     */
    public static int maxSame20211116(String[] input) {
        if (input == null || input.length < 3) {
            return 0;
        }
        // 获取最短长度len
        int maxLen = Arrays.stream(input).max(Comparator.comparingInt(String::length)).get().length();
        // 记录使用过的
        HashSet<String> used = new HashSet<>();
        int count = 0;
        for (int i = maxLen; i > 0; i--) {
            HashMap<String, StringInt> tempMap = new HashMap<>();
            for (String s : input) {
                if (i > s.length() || used.contains(s)) {
                    continue;
                }
                String substring = s.substring(0, i);
                tempMap.put(substring, tempMap.getOrDefault(substring, new StringInt()).addCount(s));
            }
            List<StringInt> collect = tempMap.values().stream().filter(StringInt::isAble).collect(Collectors.toList());
            count += collect.size();
            collect.forEach(stringInt -> used.addAll(stringInt.strList));
        }
        return count;
    }

    static class StringInt {
        private List<String> strList = new ArrayList<>();
        private int count = 0;

        public StringInt addCount(String str) {
            strList.add(str);
            count++;
            return this;
        }

        public boolean isAble() {
            return count >= 3;
        }

    }

    /**
     * 有bug
     * http://3ms.huawei.com/km/blogs/details/7582675
     *
     * @param strings
     * @return
     */
    public static int findMostPrefix(String[] strings) {
        // 求得最长字符串的长度作为纵向遍历时的最大列长
        int maxStringLen = Arrays.stream(strings).map(String::length).max(Integer::compare).get();
        // 记录已选出的字符串组(题目描述不明，此处假定字符串组间不能复用)
        Set<String> recorded = new HashSet<>();
        // 记录每轮遍历时的当前前缀(key)及其对应的完整字符串列表(value)
        Map<String, List<String>> prefixes = new HashMap<>();
        int ans = 0;
        for (int i = 1; i <= maxStringLen; i++) {
            for (String str : strings) {
                if (str.length() < i || recorded.contains(str)) {
                    continue;
                }
                String curPrefix = str.substring(0, i);
                prefixes.putIfAbsent(curPrefix, new ArrayList<>());
                List<String> list = prefixes.get(curPrefix);
                list.add(str);
                if (list.size() == 3) {
                    ans++;
                    recorded.addAll(list);
                    break;
                }
            }
            prefixes.clear();
        }
        return ans;
    }
}
