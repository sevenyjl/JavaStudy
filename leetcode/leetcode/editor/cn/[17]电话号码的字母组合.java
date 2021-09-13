//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 👍 1506 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    static HashMap<String, String[]> map = new HashMap<>();

    static {
        map.put("2", new String[]{"a", "b", "c"});
        map.put("3", new String[]{"d", "e", "f"});
        map.put("4", new String[]{"g", "h", "i"});
        map.put("5", new String[]{"j", "k", "l"});
        map.put("6", new String[]{"m", "n", "o"});
        map.put("7", new String[]{"p", "q", "r", "s"});
        map.put("8", new String[]{"t", "u", "v"});
        map.put("9", new String[]{"w", "x", "y", "z"});
    }
    public List<String> letterCombinations(String digits) {


        ArrayList<String> result = new ArrayList<>();
        if (digits == null) {
            return result;
        }
        char[] chars = digits.toCharArray();
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            String[] strings = map.get(chars[i] + "");
            if (strings == null) {
                return new ArrayList<>();
            }
            if (i == 0) {
                result.addAll(Arrays.asList(strings));
            } else {
                for (String s : result) {
                    for (String string : strings) {
                        temp.add(s + string);
                    }
                }
                result.clear();
                result.addAll(temp);
                temp.clear();
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
