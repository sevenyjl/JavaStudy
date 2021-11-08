package question.Demo49字母异位词分组;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <p>
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: strs = [""]
 * 输出: [[""]]
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * <p>
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 * <p>
 * Related Topics 哈希表 字符串 排序
 * 👍 891 👎 0
 *
 * @author y30016814
 * @since 2021/10/29 15:59
 */
public class Solution49 {

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    /**
     * 解答成功:
     * 执行耗时:63 ms,击败了5.72% 的Java用户
     * 内存消耗:45.6 MB,击败了5.02% 的Java用户
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Lettering, List<String>> map = new HashMap<>();
        for (String str : strs) {
            Lettering key = new Lettering(str);
            List<String> orDefault = map.getOrDefault(key, new ArrayList<>());
            orDefault.add(str);
            map.put(key, orDefault);
        }
        return new ArrayList<>(map.values());
    }

    static class Lettering {
        private String str;
        private Map<Character, Integer> map;

        public Lettering(String str) {
            map = new HashMap<>();
            for (char c : str.toCharArray()) {
                Integer orDefault = map.getOrDefault(c, 0);
                map.getOrDefault(c, 0);
                map.put(c, ++orDefault);
            }
            this.str = str;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Lettering o1 = (Lettering) o;
            return o1.map.equals(map);
        }

        @Override
        public int hashCode() {
            return map.hashCode();
        }

    }
}
