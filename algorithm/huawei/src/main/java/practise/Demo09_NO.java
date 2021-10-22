package practise;

import java.util.Scanner;

/**
 * http://oj.rnd.huawei.com/problems/9/details
 * 题目描述
 * 2005年的百度之星初赛有这么一道题，一个正整数有可能可以被表示为 m(m>1) 个连续正整数之和，如：
 * <p>
 * 15=1+2+3+4+5
 * 15=4+5+6
 * 15=7+8
 * 但现在你的任务是判断给定的整数n能否表示成连续的m(m>1)个正整数之和。
 * <p>
 * 解答要求
 * 时间限制：1000ms, 内存限制：100MB
 * 输入
 * 输入只有一个整数n (1<n<230 +1)。
 * <p>
 * 输出
 * 若n能表示成连续的m(m>1)个正整数之和则输出“YES”，否则输出“NO”。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 15
 * 输出样例 1
 * <p>
 * YES
 */
public class Demo09_NO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = 0;
        while (scanner.hasNext()) {
            n = scanner.nextLong();
            if (check(n)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    /**
     * http://oj.rnd.huawei.com/discuss/problems/discussions/1c80bb64-5e51-4553-8e99-70ac0c18dc5c?navName=Java%E7%AE%80%E5%8D%95%E8%A7%A3%E6%B3%95
     * @param n
     * @return
     */
    public static boolean check(long n) {
        int num = 1;
        int m = 2;
        while (num < n) {
            if ((n - num) % m == 0) {
                return true;
            }
            num += m;
            m++;
        }
        return false;
    }
}