package simulation.professional.d20191101;

import java.util.Arrays;

/**
 * 某地突发一起重大交通事故，热心群众立即播打了120急救电话，事发时段正巧是下班高峰，路况不尽如人意。救护车司机打开地图matrix，matrix是给定的一个的矩阵，给定起点S（坐标 startX, startY）以及终点E（坐标 endX, endY）。地图上所有的0 都显示通畅路段，1 代表拥堵路段（拥堵路段不可通行）。为了能尽快将伤员送往医院，司机立即求助于市交通部指挥中心，指挥中心使用智慧交通提供的紧急救援功能，可以将地图上的一个拥堵路段（1）开辟出一道绿色通道变为通畅路段（0）。（注意：司机仅有一次开辟绿色通道的机会）司机一次只能往上、往下、往左、往右行驶一公里，请返回他从S开始并走到E所花的最短公里数。如果一定不能到达，请返回-1。
 * 示例 1：
 * 输入：matrix = [[0,0,1,0],[1,0,0,0]], startX = 0, startY = 0, endX = 0, endY = 3
 * 输出：3
 * 解释：司机在智慧交通提供的紧急救援功能的引导下可以将坐标为 (0, 2) 的拥堵路段开辟出一道绿色通道转变为通畅路段。这样的公里数最少，为 3。
 * 示例 2：
 * 输入：matrix = [[0,1,1,0],[1,0,0,0]], startX = 0, startY = 0, endX = 0, endY = 3
 * 输出：5
 * 解释：司机在智慧交通提供的紧急救援功能的引导下只能将坐标为(1, 0)的拥堵路段开辟出一条绿色通道变为通畅路段。这样的公里数为5。
 * 示例 3：
 * 输入：matrix = [[0,1,1,0],[1,1,0,0]], startX = 0, startY = 0, endX = 0, endY = 3
 * 输出：-1
 * 解释：司机被困在了起点。答案为-1。
 * 限制：
 * <p>
 * matrix 大小不超过 100 * 100
 * 0 <= 拥堵路段（1）的个数 <= 50
 * 起始点和终点都不是拥堵路段。
 *
 * @author y30016814
 * @since 2021/11/16 17:28
 * http://3ms.huawei.com/km/blogs/details/7488887
 */
public class T2 {
    public static void main(String[] args) {
        System.out.println(solution20211116(new int[][] {
            {0, 0, 1, 1, 0},
            {1, 0, 1, 0, 0},
            {1, 0, 1, 1, 0},
            {1, 0, 0, 0, 0},
        }, 1, 3, 0, 0));
    }

    static int[][] direction = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static int solution20211116(int[][] matrix, int startX, int startY, int endX, int endY) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        dp[startX][startY] += 1;
        extracted(matrix, startX, startY, endX, endY, dp, true);
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[endX][endY];
    }

    private static void extracted(int[][] matrix, int startX, int startY, int endX, int endY, int[][] dp,
        boolean canChange) {
        for (int[] ints : direction) {
            int x = startX + ints[0];
            int y = startY + ints[1];
            if (x < dp.length && x >= 0 && y < dp[0].length && y >= 0) {
                if (matrix[x][y] == 1) {
                    if (canChange) {
                        matrix[x][y] = 0;
                        extracted2(matrix, startX, startY, endX, endY, dp, false, x, y);
                        matrix[x][y] = 1;
                    }
                } else {
                    extracted2(matrix, startX, startY, endX, endY, dp, canChange, x, y);
                }
            }
        }
    }

    private static void extracted2(int[][] matrix, int startX, int startY, int endX, int endY, int[][] dp,
        boolean canChange, int x, int y) {
        if (dp[x][y] == -1) {
            dp[x][y] = dp[startX][startY] + 1;
            extracted(matrix, x, y, endX, endY, dp, canChange);
        } else {
            if (dp[startX][startY] + 1 <= dp[x][y]) {
                dp[x][y] = dp[startX][startY] + 1;
                extracted(matrix, x, y, endX, endY, dp, canChange);
            }
        }
    }
}
