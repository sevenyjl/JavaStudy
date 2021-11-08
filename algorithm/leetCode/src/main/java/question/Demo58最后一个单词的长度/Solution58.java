package question.Demo58最后一个单词的长度;

/**
 * @author y30016814
 * @since 2021/11/4 14:15
 */
public class Solution58 {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(lengthOfLastWord("luffy is still joyboy"));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.8 MB,击败了27.42% 的Java用户
     *
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        String trim = s.trim();
        return trim.length() - trim.lastIndexOf(" ") - 1;
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.8 MB,击败了27.03% 的Java用户
     *
     * @param s
     * @return
     */
    public static int lengthOfLastWord1(String s) {
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ' && count != 0) {
                return count;
            } else if (s.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }
}
