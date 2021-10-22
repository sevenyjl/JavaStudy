package practise;

import java.util.Arrays;

/**
 * 题目描述
 * 给定一个正整数，我们可以定义出下面的公式:
 * N=a[1]+a[2]+a[3]+…+a[m];
 * a[i]>0,1<=m<=N;
 * 对于一个正整数，求解满足上面公式的所有算式组合，如，对于整数 4 :
 * <p>
 * 4 = 4;
 * 4 = 3 + 1;
 * 4 = 2 + 2;
 * 4 = 2 + 1 + 1;
 * 4 = 1 + 1 + 1 + 1;
 * 所以上面的结果是 5 。
 * 注意：对于 “4 = 3 + 1” 和 “4 = 1 + 3” ，这两处算式实际上是同一个组合!
 * <p>
 * 解答要求
 * 时间限制：1000ms, 内存限制：100MB
 * 输入
 * 每个用例中，会有多行输入，每行输入一个正整数，表示要求解的正整数N(1 ≤ N ≤ 120) 。
 * <p>
 * 输出
 * 对输入中的每个整数求解答案，并输出一行(回车换行);
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 4
 * 10
 * 20
 * 输出样例 1
 * <p>
 * 5
 * 42
 * 627
 * 提示样例 1
 */
public class Demo31_NO {
    public static void main(String[] args) {
        // System.out.println(cal(5));
        System.out.println(dp(5));
    }

    public static int cal(int num) {
        return cal(num, num);
    }

    public static int cal(int m, int n) {
        if (m <= 1 || n == 1) {
            return 1;
        }
        if (m < n) {
            return cal(m, m);
        } else {
            return cal(m - n, n) + cal(m, n - 1);
        }
    }

    public static int dp(int num) {
        return dp(num, num);
    }

    private static int dp(int m, int n) {
        int[][] arr = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i <= 1 || j <= 1) {
                    arr[i][j] = 1;
                } else if (i < j) {
                    arr[i][j] = arr[i][i];
                } else {
                    arr[i][j] = arr[i - j][j] + arr[i][j - 1];
                }
            }
        }
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
        return arr[m][n];
    }
}
