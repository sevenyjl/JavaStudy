package question.Demo50Pow;

import java.math.BigDecimal;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * -104 <= xn <= 104
 * <p>
 * Related Topics 递归 数学
 * 👍 767 👎 0
 *
 * @author y30016814
 * @since 2021/10/29 16:37
 */
public class Solution50 {
    public static void main(String[] args) {
        System.out.println(myPow(2.00000, 10));
        System.out.println(myPow(2.00000, 1));
        System.out.println(myPow(2.00000, -2));
        // 解答失败: 测试用例:34.00515 -3 测试结果:34.00515 期望结果:3e-05 stdout:
        System.out.println(myPow(2, -3));
        System.out.println(myPow(2, -1));
        System.out.println(myPow(2, 0));
        System.out.println(myPow(1.00001, 123456));
        System.out.println(myPow(1.00000, 2147483647));
        System.out.println(myPow(0.00001, 2147483647));
    }

    public static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public static double myPow5(double x, int n) {
        //2^10=2^5*2^5?
        //2^5=2*2*2*2*2=2^2*2^2*2^1
        if (n < 0) {
            n = -n;
            x = 1d / x;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == 2) {
            return x * x;
        }
        if (n % 2 == 0) {
            return myPow5(x, n / 2) * myPow5(x, n / 2);
        } else {
            return myPow5(x, n / 2) * myPow5(x, n / 2) * x;
        }
    }

    /**
     * n太大就要超时
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow4(double x, int n) {
        if (x == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            n = -n;
            x = 1d / x;
        }
        double step = x;
        while (n != 1) {
            x *= step;
            n--;
        }
        return x;
    }

    /**
     * 超时~
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow3(double x, int n) {
        if (n >= 0) {
            return new BigDecimal(x).pow(n).doubleValue();
        } else {
            return myPow3(1 / x, -n);
        }
    }

    /**
     * n<0时不行
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow1(double x, int n) {
        return new BigDecimal(x).pow(n).doubleValue();
    }

    /**
     * n太大递归栈溢出
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow2(double x, int n) {
        if (n >= 1) {
            return x * myPow2(x, --n);
        } else if (n < 0) {
            n = -n;
            return myPow2((1d / x), n);
        } else {
            return 1;
        }
    }
}
