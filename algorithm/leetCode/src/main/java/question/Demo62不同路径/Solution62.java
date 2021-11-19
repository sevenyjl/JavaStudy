package question.Demo62不同路径;

import java.util.Arrays;
import java.util.HashMap;

import cn.hutool.core.util.RandomUtil;

/**
 * @author y30016814
 * @since 2021/11/18 17:04
 */
public class Solution62 {
    public static void main(String[] args) {
        int m = 34;
        int n = 7;
        //解答失败: 测试用例:36 7 测试结果:5224219 期望结果:4496388 stdout:
        System.out.println(uniquePaths20211118172004(m, n));
        System.out.println(uniquePaths20211118171539(m, n));
        // int tempM = RandomUtil.randomInt(n, m);
        // n = RandomUtil.randomInt(n, m);
        // m = tempM;
        // System.out.printf("m=%s,n=%s\n", m, n);
        // System.out.println(uniquePaths20211118172004(m, n));
        // System.out.println(uniquePaths20211118171539(m, n));
        cache.forEach((k, v) -> {
            String[] split = k.split(" ");
            if (uniquePaths20211118171539(Integer.parseInt(split[0]), Integer.parseInt(split[1])) != v) {
                System.out.printf("不匹配k=%s, v=%s\n", k, v);
            }
        });
        uniquePaths1(4,4);
    }

    static HashMap<String, Integer> cache = new HashMap<>();

    /**
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 动态规划
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:35.3 MB,击败了21.23% 的Java用户
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths1(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }

    /**
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 组合数学 这nm谁想得到呀~~~~~~！！！！！
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:35.2 MB,击败了61.27% 的Java用户
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    /**
     * 解答成功:
     * 执行耗时:9 ms,击败了100.00% 的Java用户
     * 内存消耗:35.8 MB,击败了5.31% 的Java用户
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths20211118172004(int m, int n) {
        if (m == 1 || n == 1) {
            cache.put(m + " " + n, 1);
            return 1;
        } else if (n == 2 || m == 2) {
            int max = Math.max(n, m);
            cache.put(m + " " + n, max);
            return max;
        } else {
            Integer integer = cache.getOrDefault(m + " " + n, cache.get(n + " " + m));
            if (integer == null) {
                integer = uniquePaths20211118172004(m - 1, n) + uniquePaths20211118172004(m, n - 1);
                cache.put(m + " " + n, integer);
            }
            return integer;
        }
    }

    /**
     * 暴力递归 一定超时
     */
    public static int uniquePaths20211118171539(int m, int n) {
        return run(m, n, 0, 0);
    }

    private static int run(int m, int n, int x, int y) {
        int count = 0;
        // 判断是否到达终点
        if (x == m - 1 && y == n - 1) {
            count++;
        } else {
            if (x < m - 1) {
                count += run(m, n, x + 1, y);
            }
            if (y < n - 1) {
                count += run(m, n, x, y + 1);
            }
        }
        return count;
    }
}
