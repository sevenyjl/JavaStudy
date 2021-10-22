package question.Demo29两数相除;

public class Solution29 {

    public static void main(String[] args) {
        System.out.println(divide(10, 3));//3
        System.out.println(divide(7, -3));//-2
        System.out.println(divide(-2147483648, -1));//2147483647
    }

    /**
     * 解答成功: 执行耗时:1 ms,击败了100.00% 的Java用户
     * 内存消耗:35.7 MB,击败了19.16% 的Java用户
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        if (dividend == -2147483648 && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        return dividend / divisor;
    }
}
