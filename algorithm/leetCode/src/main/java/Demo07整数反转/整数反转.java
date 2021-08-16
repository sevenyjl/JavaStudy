package Demo07整数反转;

/**
 * //给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * //
 * // 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。
 * //假设环境不允许存储 64 位整数（有符号或无符号）。
 * //
 * //
 * //
 * // 示例 1：
 * //
 * //
 * //输入：x = 123
 * //输出：321
 * //
 * //
 * // 示例 2：
 * //
 * //
 * //输入：x = -123
 * //输出：-321
 * //
 * //
 * // 示例 3：
 * //
 * //
 * //输入：x = 120
 * //输出：21
 * //
 * //
 * // 示例 4：
 * //
 * //
 * //输入：x = 0
 * //输出：0
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // -231 <= x <= 231 - 1
 * //
 * // Related Topics 数学
 * // 👍 2999 👎 0
 */

public class 整数反转 {
    public static void main(String[] args) {
//        System.out.println(demo02(321));//123
//        System.out.println(demo01(120));//21
//        System.out.println(demo02(0));//0
        System.out.println(demo02(1534236469));
        System.out.println(demo02(900000));
    }

    /**
     * 优
     * 解答成功: 执行耗时:1 ms,击败了100.00% 的Java用户 内存消耗:35.6 MB,击败了33.78% 的Java用户
     *
     * @param x
     * @return
     */
    public static int demo02(int x) {
        int count = 1;
        double result = 0;
        int length = String.valueOf(Math.abs(x)).length();
        while (true) {
            int i = x % (int) Math.pow(10, count);
            result += i / Math.pow(10, count - 1) * Math.pow(10, (length - count));
            x = x - i;
            count++;
            if (x == 0) {
                if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                    return 0;
                }
                return (int) result;
            }
        }
    }

    /**
     * 有问题！！！
     *
     * @param x
     * @return
     */
    public static int demo01(int x) {
        String s = String.valueOf(x);
        boolean flag = false;
        if (s.startsWith("-")) {
            flag = true;
            s = s.substring(1);
        }
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuilder.append(chars[i]);
        }
        return Integer.parseInt(flag ? "-" + stringBuilder : stringBuilder.toString());
    }
}
