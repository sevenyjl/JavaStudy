//整数反转

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
//	获取当前时间: 2021-08-16 21:23:07
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
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
}
//leetcode submit region end(Prohibit modification and deletion)



