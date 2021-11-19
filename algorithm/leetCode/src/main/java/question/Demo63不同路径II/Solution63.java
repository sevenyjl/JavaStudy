package question.Demo63不同路径II;

import java.util.Arrays;

/**
 * @author y30016814
 * @since 2021/11/19 13:48
 */
public class Solution63 {

    public static void main(String[] args) {
        int[][] ints = {
            {1, 0},
        };
        System.out.println(uniquePathsWithObstacles20211119140337(ints));
        System.out.println(uniquePathsWithObstacles20211119141020(ints));
        System.out.println(uniquePathsWithObstacles20211119140337(new int[][] {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
        }));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return 0;
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37.7 MB,击败了64.15% 的Java用户
     * 动态规划
     */
    public static int uniquePathsWithObstacles20211119141020(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        if (obstacleGrid[0][0] != 1) {
            dp[0][0] = 1;
        } else {
            return 0;
        }
        for (int i = 1; i < dp.length; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < dp[0].length; i++) {
            if (obstacleGrid[0][i] == 1) {
                break;
            } else {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < dp.length; ++i) {
            for (int j = 1; j < dp[0].length; ++j) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    /**
     * 暴力破解 一定超时
     * 用例 {{1,0}} 有问题
     */
    public static int uniquePathsWithObstacles20211119140337(int[][] obstacleGrid) {
        return run(obstacleGrid, 0, 0);
    }

    private static int run(int[][] obstacleGrid, int x, int y) {
        int count = 0;
        // 判断是否到达终点
        if (x == obstacleGrid.length - 1 && y == obstacleGrid[0].length - 1) {
            count++;
        } else {
            if (x < obstacleGrid.length - 1 && obstacleGrid[x + 1][y] != 1) {
                count += run(obstacleGrid, x + 1, y);
            }
            if (y < obstacleGrid[0].length - 1 && obstacleGrid[x][y + 1] != 1) {
                count += run(obstacleGrid, x, y + 1);
            }
        }
        return count;
    }

}
