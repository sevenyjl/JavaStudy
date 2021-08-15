package Demo03无重复字符的最长子串;

import java.util.HashMap;

/*给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。



 示例 1:


输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。


 示例 2:


输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。


 示例 3:


输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。


 示例 4:


输入: s = ""
输出: 0




 提示：


 0 <= s.length <= 5 * 104
 s 由英文字母、数字、符号和空格组成

 Related Topics 哈希表 字符串 滑动窗口
 👍 5921 👎 0*/
public class 无重复字符的最长子串 {
    public static void main(String[] args) {
        String s = "abcabcbb";//3
        System.out.println(demo01(s));
        s = "bbbbb";//1
        System.out.println(demo01(s));
        s = "pwwkew";//3
        System.out.println(demo01(s));
        s = "";//0
        System.out.println(demo01(s));
    }

    /**
     * 利用重复hashMap来进行记录并回溯
     *
     * @param s
     * @return
     */
    public static int demo01(String s) {
        char[] chars = s.toCharArray();
        HashMap<String, Integer> temp = new HashMap<>();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            String a = chars[i] + "";
            Integer integer = temp.get(a);
            if (integer == null) {
                temp.put(a, i);
            } else {
                int max1 = Math.max(max, i - integer);
                max = max1;
                i = integer;
                temp.clear();
            }
        }
        return max;
    }
}
