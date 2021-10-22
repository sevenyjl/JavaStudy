package simulation.professional.d20211019;

/**
 * 给定一个闭区间范围[m, n]，1<=m<=n<=10^9，求[m,n]区间上位数为偶数的整数有多少个？
 * <p>
 * <p>
 * 输入样例：
 * <p>
 * m = 1, n = 100
 * <p>
 * 输出样例：
 * <p>
 * 90
 * <p>
 * 输出样例说明：[1,100]区间上，有10~99这些整数的位数是2位，是偶数，所以答案是10~99这些整数的数量，即90
 */
public class T2 {
    public static void main(String[] args) {
        System.out.println(solution(1, 1000000000-10) + "=" + mostDouble(1, 1000000000-10));
    }

    public static int solution(int m, int n) {
        if (m > n) {
            return 0;
        }
        if (n < 10) {
            return 0;
        } else if (n < 100) {
            return m > 9 ? n - m + 1 : n - 9;
        } else if (n < 1000) {
            return solution(m, 99);
        } else if (n < 10000) {
            return (m > 999 ? n - m + 1 : n - 999) + solution(m, 99);
        } else if (n < 100000) {
            return solution(m, 9999);
        } else if (n < 1000000) {
            return (m > 99999 ? n - m + 1 : n - 99999) + solution(m, 9999);
        } else if (n < 10000000) {
            return solution(m, 999999);
        } else if (n < 100000000) {
            return (m > 9999999 ? n - m + 1 : n - 9999999) + solution(m, 9999999);
        } else if (n < 1000000000) {
            return solution(m, 99999999);
        } else {
            return (n - 999999999) + solution(m, 999999999);
        }
    }


    public static int mostDouble(int m, int n) {
        int count = 0;
        for (int i = m; i <= n; i++) {
            int length = String.valueOf(i).length();
            //当循环到10的倍数时，直接进行跳过操作
            if (i % 10 == 0) {
                //当是偶数时直接将连续的偶数全部计算在内
                int finish = (int) Math.pow(10, length) - 1;
                if (length % 2 == 0) {
                    count += finish - i + 1;
                }

                //当跳过了结束值，需减掉多出来的偶数
                if (finish > n && length % 2 == 0) {
                    count -= (finish - n);
                }
                i = finish;

            } else {
                if (length % 2 == 0) {
                    count++;
                }
            }
        }
        return count;
    }

}