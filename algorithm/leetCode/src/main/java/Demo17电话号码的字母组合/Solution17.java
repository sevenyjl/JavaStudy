package Demo17电话号码的字母组合;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution17 {
    public static void main(String[] args) {
        System.out.println(letterCombinations(null));
        //["ad","ae","af","bd","be","bf","cd","ce","cf"]
        System.out.println(letterCombinations("23"));
        //[]
        System.out.println(letterCombinations(""));
        //["a","b","c"]
        System.out.println(letterCombinations("2"));

    }

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


    /**
     * 暴力遍历
     * 解答成功:
     * 执行耗时:3 ms,击败了22.76% 的Java用户
     * 内存消耗:38.6 MB,击败了18.26% 的Java用户
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
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
