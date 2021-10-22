package question.Demo10_NO正则表达式;

import java.util.regex.Pattern;

public class Solution10 {
    public static void main(String[] args) {
        isMatch("ab", "abc*");
    }

    /**
     * 解答成功:
     * 执行耗时:39 ms,击败了10.64% 的Java用户
     * 内存消耗:38.7 MB,击败了6.86% 的Java用户
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch2(String s, String p) {
        if (s == null) {
            return false;
        }
        if (p == null) {
            return false;
        }
        return Pattern.matches(p, s);
    }

    /**
     * 解答成功:
     * 执行耗时:2 ms,击败了89.53% 的Java用户
     * 内存消耗:37.1 MB,击败了38.56% 的Java用户
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        int lengthS = s.length();
        int lengthP = p.length();
        boolean[][] dp = new boolean[lengthS + 1][lengthP + 1];
        dp[0][0] = true;

        for (int i = 1; i < lengthP + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i < lengthS + 1; i++) {
            for (int j = 1; j < lengthP + 1; j++) {
                if (p.charAt(j - 1) == '*') {
                    boolean b = dp[i][j - 2];// 当字符为0个时的匹配状态
                    boolean b1 = dp[i][j - 1];// 当字符为1个时的匹配状态
                    boolean b2 = dp[i - 1][j];// todo 待研究这里的逻辑
                    boolean b3 = s.charAt(i - 1) == p.charAt(j - 2);
                    boolean b4 = p.charAt(j - 2) == '.';
                    dp[i][j] = b || b1 || (b2 && (b3 || b4));
                } else {
                    // 如果f[i-1][j-1]能匹配，且当前字符s[i-1]=p[i-1]或者p[i-1]=='.'
                    boolean b = dp[i - 1][j - 1];
                    boolean b1 = s.charAt(i - 1) == p.charAt(j - 1);
                    boolean b2 = p.charAt(j - 1) == '.';
                    dp[i][j] = b && (b1 || b2);
                }
            }
        }
        // for (boolean[] booleans : dp) {
        //     System.out.println(Arrays.toString(booleans));
        // }
        return dp[lengthS][lengthP];
    }
}
