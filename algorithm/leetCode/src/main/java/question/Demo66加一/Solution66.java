package question.Demo66加一;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author y30016814
 * @since 2021/11/19 14:48
 */
public class Solution66 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne20211119150539(new int[] {1, 2, 3, 4})));
        System.out.println(Arrays.toString(plusOne20211119150539(new int[] {0})));
        System.out.println(Arrays.toString(plusOne20211119150539(new int[] {9})));
    }

    public int[] plusOne(int[] digits) {
        return null;
    }

    /**
     * 投机取巧
     * 思路：先转str 在par Int 再toString 再封装为int[]
     * 缺陷：digits的长度为 1~100，而long的长度 只有19
     */
    public static int[] plusOne20211119150539(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            sb.append(digit);
        }
        long l = Long.parseLong(sb.toString()) + 1;
        int length = (l + "").length();
        int temp = length;
        int[] result = new int[length];
        while (length != 0) {
            double pow = Math.pow(10, length - 1);
            result[temp - length] = (int) (l / pow);
            l %= pow;
            length--;
        }
        return result;
    }

    /**
     * 直接解，貌似不难
     * 第一次失败：没考虑进位问题
     * <p>
     * 2021/11/19 15:03:45	解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37 MB,击败了34.91% 的Java用户
     */

    public static int[] plusOne20211119144938(int[] digits) {
        if (digits[0] == 0) {
            digits[0] += 1;
        } else {
            int last = digits[digits.length - 1];
            if (last == 9) {
                // 从尾部到头找到9
                int index = -1;
                for (int i = digits.length - 1; i >= 0; i--) {
                    if (digits[i] != 9) {
                        index = i;
                        digits[i] += 1;
                        break;
                    } else {
                        digits[i] = 0;
                    }
                }
                if (index == -1) {
                    // 得扩展数组了
                    int[] newArrays = new int[digits.length + 1];
                    newArrays[0] = 1;
                    return newArrays;
                }
                return digits;
            } else {
                digits[digits.length - 1] = last + 1;
            }
        }
        return digits;
    }

}
