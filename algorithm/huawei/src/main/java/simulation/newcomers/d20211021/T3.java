package simulation.newcomers.d20211021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 请你按照指定的规则对一个非空字符串 input 中的字符进行重新组合，并返回重新组合后的结果。
 * 规则如下：
 * <p>
 * 字符串 input 都是由小写字母组成。
 * 给定一个整数 interval，使得重新组合后的字符串中相同字母的位置间隔距离至少为 interval。
 * 如果无法完成重新组合，则返回一个空字符串 ""。
 * 注意：答案不一定唯一。如果有多个满足要求的答案，返回任意一个都算正确。
 * <p>
 * 示例 1：
 * <p>
 * 输入: input = "xxyyzz", interval = 3
 * <p>
 * 输出: "xyzxyz"
 * <p>
 * 解释: 相同的字母在新的字符串中间隔至少 3 个单位距离。
 * <p>
 * 示例 2:
 * <p>
 * 输入: input = "xxxyyz", interval = 3
 * <p>
 * 输出: ""
 * <p>
 * 解释: 没有办法找到可能的重排结果。
 * <p>
 * 示例 3:
 * <p>
 * 输入: input = "aaazxxyy", interval = 2
 * <p>
 * 输出: "axayaxyz"
 * <p>
 * 解释: 输出为其中一种答案，相同的字母在新的字符串中间隔至少 2 个单位距离。
 * <p>
 * 提示：
 * <p>
 * 0 <= input.length <= 150000
 * 0 <= interval <= 26
 * <p>
 * 总结核心思想：优先使用次数最少的字符。关键点就在排序
 * <p>
 * 类似题目：https://leetcode-cn.com/problems/reorganize-string/
 */
public class T3 {
    public static void main(String[] args) {
        // System.out.println(combineChars("xxyyzz", 3)+"="+combineChars2("xxyyzz", 3));
        // System.out.println(combineChars("xxxyyz", 3)+"="+combineChars2("xxxyyz", 3));
        // System.out.println(combineChars("xxxyyzz", 2) + "=" + combineChars2("xxxyyzz", 2));
        // System.out.println(combineChars("xxxyyzz", 3) + "=" + combineChars2("xxxyyzz", 3));
        // System.out.println(combineChars("xxxyyzzbb", 4) + "=" + combineChars2("xxxyyzzbb", 4));
        // System.out.println(combineChars("abcdabcdzz", 4) + "=" + combineChars2("abcdabcdzz", 4));
        // System.out.println(combineChars("aaabbbcccdd", 3) + "=" + combineChars2("aaabbbcccdd", 3));
        // System.out.println(combineChars("aaabbbcccdddddd", 3) + "=" + combineChars2("aaabbbcccdddddd", 3));
        // System.out.println(combineChars("zaabb", 3) + "=" + combineChars2("zaabb", 3));
        System.out.println(combineChars("aaab", 2) + "=" + combineChars2("aaab", 2));
    }

    /**
     * 使用map 代替队列，排序不友好【不推荐,有bug】
     *
     * @param s
     * @param k
     * @return
     */
    public static String combineChars2(String s, int k) {
        if (k <= 1 || s.length() <= 1) {
            return s;
        }
        if (s.length() <= k) {
            return "";
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            Integer orDefault = map.getOrDefault(c, 0);
            map.put(c, ++orDefault);
        }
        StringBuilder sb = new StringBuilder();
        while (map.values().stream().noneMatch(v -> v == 0)) {
            for (int i = 0; i < k; i++) {
                int count = 0;
                for (Character character : map.entrySet()
                    .stream()
                    .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                    .map(Map.Entry::getKey)
                    .collect(
                        Collectors.toList())) {
                    Integer integer = map.get(character);
                    if (integer > 0) {
                        count++;
                        sb.append(character);
                        map.put(character, --integer);
                    }
                    if (count >= k) {
                        break;
                    }
                }
                if (count != k) {
                    if (i != k - 1 && sb.length() != s.length()) {
                        return "";
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 队列处理（初始化队列时指定排序）
     *
     * @param s
     * @param k
     * @return
     */
    public static String combineChars(String s, int k) {
        if (k <= 1 || s.length() <= 1) {
            return s;
        }
        if (s.length() <= k) {
            return "";
        }
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        //最大堆，每次取出来的都是个数最多的字符
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[1] == b[1] ? a[0] - b[0] : b[1] - a[1]);
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                heap.offer(new int[] {i, map[i]});
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            //间隔为k，则每k个为一组，作为一次选择的轮询
            //每次优先选择数量最多的那个字符
            List<Integer> polled = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                int[] cur = heap.poll();
                sb.append((char) (cur[0] + 'a'));
                polled.add(cur[0]);
                if (heap.size() == 0) {
                    if (i != k - 1 && sb.length() != s.length()) {
                        return "";
                    }
                    break;
                }
            }
            for (int i : polled) {
                if (--map[i] > 0) {
                    heap.offer(new int[] {i, map[i]});
                }
            }
        }
        return sb.toString();
    }

}

