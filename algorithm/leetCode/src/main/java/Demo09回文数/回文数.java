package Demo09回文数;

public class 回文数 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));//true
        System.out.println(isPalindrome(-121));//false
        System.out.println(isPalindrome(10));//false
        System.out.println(isPalindrome(-101));//false
        System.out.println(isPalindrome(-0));//false
    }


    /**
     * 解答成功: 执行耗时:17 ms,击败了10.38% 的Java用户
     * 内存消耗:37.7 MB,击败了83.15% 的Java用户
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        String s = x + "";
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 执行耗时:19 ms,击败了7.24% 的Java用户
     * 内存消耗:38.4 MB,击败了6.46% 的Java用户
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome01(int x) {
        //转换为str
        StringBuilder stringBuilder = new StringBuilder(x + "");
        String s = stringBuilder.toString();
        String reverse = stringBuilder.reverse().toString();
        return s.equals(reverse);
    }
}
