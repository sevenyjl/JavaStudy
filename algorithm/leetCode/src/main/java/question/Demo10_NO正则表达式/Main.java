package question.Demo10_NO正则表达式;

/**
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));//false
        System.out.println(isMatch("aa", "a*"));//true
        System.out.println(isMatch("ab", ".*"));//true
        System.out.println(isMatch("aab", "c*a*b"));//true
        System.out.println(isMatch("mississippi", "mis*is*p*."));//false
    }

    public static boolean isMatch(String s, String p) {
        if (".*".equals(p)) return true;
        //解析p
        char[] pChars = p.toCharArray();
        for (int i = 0; i < pChars.length; i++) {
            char pChar = pChars[i];
            if (pChar == '.') {

            }
        }
        return false;
    }
}
