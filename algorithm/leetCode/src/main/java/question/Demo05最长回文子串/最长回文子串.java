package question.Demo05最长回文子串;

import java.util.HashMap;

/*给你一个字符串 s，找到 s 中最长的回文子串。



 示例 1：


输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。


 示例 2：


输入：s = "cbbd"
输出："bb"


 示例 3：


输入：s = "a"
输出："a"


 示例 4：


输入：s = "ac"
输出："a"




 提示：


 1 <= s.length <= 1000
 s 仅由数字和英文字母（大写和/或小写）组成

 Related Topics 字符串 动态规划
 👍 3958 👎 0*/
public class 最长回文子串 {
    public static void main(String[] args) {
        String s = "babad";//bab
        System.out.println(demo01(s));
        s = "cbbd";//bb
        System.out.println(demo01(s));
        s = "a";//a
        System.out.println(demo01(s));
        s = "ac";//a
        System.out.println(demo01(s));
    }

    public static String demo01(String s) {
        char[] chars = s.toCharArray();
        HashMap<String, Integer> temp = new HashMap<>();
        int max = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < chars.length; i++) {
            String str = chars[i] + "";
            Integer integer = temp.get(str);
            if (integer == null) {
                temp.put(str, i);
            } else if ((i - integer) > max) {
                max = i - integer;
                start = integer;
                end = i;
                i = integer;
                temp.clear();
            }
        }
        return s.substring(start, end + 1);
    }
}
