//有效数字（按顺序）可以分成以下几个部分： 
//
// 
// 一个 小数 或者 整数 
// （可选）一个 'e' 或 'E' ，后面跟着一个 整数 
// 
//
// 小数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 下述格式之一：
// 
// 至少一位数字，后面跟着一个点 '.' 
// 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字 
// 一个点 '.' ，后面跟着至少一位数字 
// 
// 
// 
//
// 整数（按顺序）可以分成以下几个部分： 
//
// 
// （可选）一个符号字符（'+' 或 '-'） 
// 至少一位数字 
// 
//
// 部分有效数字列举如下： 
//
// 
// ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1",
// "53.5e93", "-123.456e789"] 
// 
//
// 部分无效数字列举如下： 
//
// 
// ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"] 
// 
//
// 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "0"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "e"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s = "."
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = ".1"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，或者点 '.' 。 
// 
// Related Topics 字符串 👍 287 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isNumber(String s) {
        boolean haveSymbol = false;
        boolean haveE = false;
        boolean havePoint = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isNumber(c)) {
            } else if (c == '.') {
                if (haveE) {
                    return false;
                }
                if (!havePoint) {
                    if (i + 1 < s.length() && isNumber(s.charAt(i + 1))) {
                    } else {
                        return false;
                    }
                    havePoint = true;
                } else {
                    return false;
                }
            } else if (c == 'E' || c == 'e') {
                if (i != 0) {
                    if (!haveE && i != s.length() - 1) {
                        haveE = true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (c == '+' || c == '-') {
                if (!haveSymbol && i == 0) {
                    haveSymbol = true;
                } else {
                    char c1 = s.charAt(i - 1);
                    if (c1 == 'E' || c1 == 'e') {
                        continue;
                    }
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
}
//leetcode submit region end(Prohibit modification and deletion)
