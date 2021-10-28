package question.Demo44通配符匹配;

import java.util.Arrays;

/**
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * <p>
 * <p>
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * <p>
 * 示例 4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * <p>
 * 示例 5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 * Related Topics 贪心 递归 字符串 动态规划
 * 👍 766 👎 0
 */
public class Solution44 {
    public static void main(String[] args) {
        // System.out.println(isMatch("aa", "a"));//false
        // System.out.println(isMatch("aa", "*"));//true
        // System.out.println(isMatch("cb", "?a"));//false
        // System.out.println(isMatch("adceb", "*a*b"));//true
        // System.out.println(isMatch("acdcb", "a*c?b"));//false
        // System.out.println(isMatch("aab", "c*a*b"));//false
        // System.out.println(isMatch("", "******"));//false
        System.out.println(isMatch("b", "*?*?"));//false
    }

    /**
     * 解答成功:
     * 执行耗时:19 ms,击败了77.27% 的Java用户
     * 内存消耗:38.4 MB,击败了93.55% 的Java用户
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*' && dp[i - 1][0]) {
                dp[i][0] = true;
            }
        }
        for (int i = 1; i < p.length() + 1; i++) {
            char cp = p.charAt(i - 1);
            for (int j = 1; j < s.length() + 1; j++) {
                char cs = s.charAt(j - 1);
                if (dp[i - 1][j - 1]) {
                    dp[i][j] = cp == '*' || cp == cs || cp == '?';
                } else if (cp == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        for (boolean[] aBoolean : dp) {
            System.out.println(Arrays.toString(aBoolean));
        }
        return dp[p.length()][s.length()];
    }
}
