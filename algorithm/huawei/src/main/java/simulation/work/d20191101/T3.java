package simulation.work.d20191101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;

/**
 * 某地突发一起重大交通事故，热心群众立即播打了 120 急救电话，事发时段正巧是下班高峰，路况不尽如人意。救护车司机打开地图 matrix ，matrix 是给定的一个的矩阵，给定起点S（坐标 startX, startY） 以及终点E（坐标 endX, endY）。地图上所有的 0 都显示通畅路段，1 代表拥堵路段（拥堵路段不可通行）。为了能尽快将伤员送往医院，司机立即求助于市交通部指挥中心，指挥中心使用智慧交通提供的紧急救援功能，可以将地图上的一个拥堵路段（1）开辟出一道绿色通道变为通畅路段（0）。（注意：司机仅有一次开辟绿色通道的机会）司机一次只能往上、往下、往左、往右行驶一公里，请返回他从 S 开始并走到 E 所花的最短公里数。如果一定不能到达，请返回 -1。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[0,0,1,0],[1,0,0,0]], startX = 0, startY = 0, endX = 0, endY = 3
 * <p>
 * 输出：3
 * <p>
 * 解释：司机在智慧交通提供的紧急救援功能的引导下可以将坐标为 (0, 2) 的拥堵路段开辟出一道绿色通道转变为通畅路段。这样的公里数最少，为 3。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：matrix = [[0,1,1,0],[1,0,0,0]], startX = 0, startY = 0, endX = 0, endY = 3
 * <p>
 * 输出：5
 * <p>
 * 解释：司机在智慧交通提供的紧急救援功能的引导下只能将坐标为(1, 0)的拥堵路段开辟出一条绿色通道变为通畅路段。这样的公里数为5。
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：matrix = [[0,1,1,0],[1,1,0,0]], startX = 0, startY = 0, endX = 0, endY = 3
 * <p>
 * 输出：-1
 * <p>
 * 解释：司机被困在了起点。答案为-1。
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * matrix 大小不超过 100 * 100
 * <p>
 * 0 <= 拥堵路段（1）的个数 <= 50
 * <p>
 * 起始点和终点都不是拥堵路段。
 */
public class T3 {
    public static void main(String[] args) {
        // int[][] ints = {{0, 0, 1, 0}, {1, 0, 0, 0}};//3
        // ints = new int[][] {{0, 1, 1, 0}, {1, 0, 0, 0}};//5
        // ints = new int[][] {{0, 1, 1, 0}, {1, 1, 0, 0}};//-1
        // int[][] ints = ArraysRandom.createRandomIntss(10, 10, Arrays.asList(0, 1));
        int[][] ints = new int[][] {
            {0, 0, 1, 0, 0, 0, 1, 1, 1, 1,},
            {0, 0, 1, 1, 1, 1, 0, 0, 1, 0,},
            {0, 1, 1, 1, 0, 0, 1, 1, 0, 0,},
            {1, 1, 0, 1, 0, 0, 0, 0, 1, 1,},
            {0, 0, 0, 1, 0, 1, 1, 1, 1, 0,},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0,},
            {0, 1, 0, 0, 0, 0, 1, 1, 1, 0,},
            {1, 1, 1, 0, 0, 1, 1, 0, 0, 1,},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1,},
            {1, 1, 1, 1, 1, 1, 1, 0, 0, 1,}
        };
        int startX = 0, startY = 0, endX = 9, endY = 9;
        System.out.println(findShortest(ints, startX, startY, endX, endY));
        System.out.println(rescue(ObjectUtil.clone(ints), startX, startY, endX, endY));

    }

    private static int findShortest(int[][] ints, int startX, int startY, int endX, int endY) {
        if (startX == endX && startY == endY) {
            return 0;
        }
        //初始化矩阵
        int[][] init = new int[ints.length][ints[0].length];
        dpValue(ints, init, startX, startY, endX, endY, 0, true);
        for (int[] ints1 : init) {
            System.out.println(Arrays.toString(ints1));
        }
        return init[endX][endY] == 0 ? -1 : init[endX][endY];
    }

    private static int[][] nextStep = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static void dpValue(int[][] ints, int[][] dp, int startX, int startY, int endX, int endY, int step,
        boolean changeFlag) {
        if (startX == endX && startY == endY) {
            return;
        }
        for (int[] ints1 : nextStep) {
            int tempX = startX + ints1[0];
            int tempY = startY + ints1[1];
            if (tempX < ints.length && tempX >= 0 && tempY < ints[0].length && tempY >= 0) {
                if (dp[tempX][tempY] == 0) {
                    if (ints[tempX][tempY] == 0) {
                        dp[tempX][tempY] = step + 1;
                        dpValue(ints, dp, tempX, tempY, endX, endY, step + 1, changeFlag);
                    } else if (ints[tempX][tempY] == 1 && changeFlag) {
                        dp[tempX][tempY] = step + 1;
                        dpValue(ints, dp, tempX, tempY, endX, endY, step + 1, false);
                    }
                } else if (dp[tempX][tempY] > step + 1) {
                    dp[tempX][tempY] = step + 1;
                    dpValue(ints, dp, tempX, tempY, endX, endY, step + 1, changeFlag);
                }
            }
        }
    }

    public static int rescue(int[][] matrix, int startX, int startY, int endX, int endY) {
        List<Integer> res = new ArrayList<>();
        //记录该节点是否已经走过的矩阵，避免重复寻找
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[startX][startY] = true;
        dfs(matrix, startX, startY, endX, endY, 0, false, res, visited);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return -1;
        }
    }

    private static void dfs(int[][] matrix, int startX, int startY, int endX, int endY, int stepNum,
        boolean used, List<Integer> res, boolean[][] visited) {
        //首先判定是否到达终点, 到底终点则比较所用步数
        if (startX == endX && startY == endY) {
            if (res.size() == 0) {
                res.add(stepNum);
            } else {
                if (res.get(0) > stepNum) {
                    res.set(0, stepNum);
                }
            }
        } else {
            //对于结束条件进行剪枝，当已经跑完的路径比现在没跑完的要长才继续寻找
            if (res.size() == 0 || res.get(0) > stepNum) {
                //四个方向进行寻找
                for (int[] next : nextStep) {
                    int nextX = startX + next[0];
                    int nextY = startY + next[1];
                    if (nextX >= 0 && nextX < matrix.length && nextY >= 0 && nextY < matrix[0].length
                        && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        //对于下一步是否为路障进行分别处理
                        if (matrix[nextX][nextY] == 1) {
                            //当该点是障碍点时，只有除障没有用过时才能继续查找
                            if (!used) {
                                dfs(matrix, nextX, nextY, endX, endY, stepNum + 1, true, res, visited);
                            }
                        } else {
                            dfs(matrix, nextX, nextY, endX, endY, stepNum + 1, used, res, visited);
                        }
                        visited[nextX][nextY] = false;
                    }
                }
            }
        }
    }

}