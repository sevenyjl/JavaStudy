package question.Demo64最下路径和;

public class Solution64 {
    public static void main(String[] args) {
        System.out.println(minPathSum20211118194048(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    /**
     * 暴力破解
     * 解答成功:
     * 执行耗时:5 ms,击败了5.06% 的Java用户
     * 内存消耗:41.3 MB,击败了18.04% 的Java用户
     *
     * @param grid
     * @return
     */
    public static int minPathSum20211118194048(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int[] ints = grid[i];
            for (int j = 0; j < ints.length; j++) {
                boolean flagX = false;
                boolean flagY = false;
                int valueX = grid[i][j], valueY = grid[i][j];
                dp[i][j] = grid[i][j];
                if (i - 1 >= 0) {
                    valueX += dp[i - 1][j];
                    dp[i][j] = valueX;
                    flagX = true;
                }
                if (j - 1 >= 0) {
                    valueY += dp[i][j - 1];
                    dp[i][j] = valueY;
                    flagY = true;
                }
                if (flagX && flagY) {
                    dp[i][j] = Math.min(valueX, valueY);
                }
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

}
