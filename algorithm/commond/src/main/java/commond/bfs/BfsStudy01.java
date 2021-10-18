package commonly.bfs;/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

import java.util.*;

/**
 * 实战题目（2020/4/24 真题）：
 * <p>
 * 现将一个实验室平面图视作n*m的二维矩阵，左上角坐标为[0,0]，在某些格子里面放置了噪音源。
 * - 噪音沿8个方向传播，传播过程中，噪音值逐级递减1，直到为0。
 * - 若同一格被多个噪音覆盖，则监测结果不叠加，保留较大值。
 * <p>
 * 输入：
 * <p>
 * n ， m， 噪音源[所在行，所在列，噪音值]
 * <p>
 * 输出：
 * <p>
 * 计算每格噪音分贝数的总和。
 * <p>
 * 分析：
 * <p>
 * 题目是个图（二维矩阵），需要计算噪音的每个相邻格子噪音值，特征很明显，直接BFS开搞。
 * <p>
 * 思路：
 * <p>
 * 1. 对每个输入的噪音源节点，进行BFS，计算出所有格子的噪音值；
 * <p>
 * 2. 加起来输出即为结果。
 * BFS解决噪音问题
 *
 * @author l00389208
 * @since 2020-4-24
 */
public class BfsStudy01 {

    public static void main(String[] args) {
        int n = 5;
        int m = 6;
        int[][] noise = new int[][] {{3, 4, 3}, {1, 1, 4}}; // 测试用例，输出应该为63

        System.out.println(spreadNoise(n, m, noise));
    }

    private static int[][] sMatrix; // 定义矩阵

    private static int sRow, sCol;

    private static List<int[]> sDirections = Arrays.asList(
        new int[] {1, 0}, // 右
        new int[] {-1, 0}, // 左
        new int[] {0, 1}, // 下
        new int[] {0, -1}, // 上
        new int[] {1, -1}, // 右上
        new int[] {-1, -1}, // 左上
        new int[] {1, 1}, // 右下
        new int[] {-1, 1} // 左下
    );

    public static int spreadNoise(int n, int m, int[][] noise) {
        sMatrix = new int[n][m]; // 初始化矩阵
        sRow = n;
        sCol = m;

        int noiseNodesNum = noise.length; // 噪音源的个数
        for (int i = 0; i < noiseNodesNum; i++) {
            bfs(noise[i][0], noise[i][1], noise[i][2]);
        }

        return getResult();
    }

    private static int getResult() {
        int result = 0;
        for (int i = 0; i < sRow; i++) {
            for (int j = 0; j < sCol; j++) {
                result += sMatrix[i][j];
            }
        }
        return result;
    }

    private static void bfs(int x, int y, int level) {
        // 1.初始化:遍历需要的队列queue
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[sRow][sCol];
        // 2.起始节点入队列
        int[] startNode = {x, y, level};
        queue.offer(startNode);
        // 3.标志起始节点赋值， 并标记为已访问
        sMatrix[x][y] = level;
        visited[x][y] = 1;

        // 4.遍历邻节点
        while (!queue.isEmpty()) {
            int[] node = queue.poll();

            for (int[] direction : sDirections) {
                // 计算每个相邻节点的位置和噪音值
                int row = node[0] + direction[0];
                int col = node[1] + direction[1];
                int noiseLevel = node[2] - 1;

                // 噪音大于0，且未出边界，且未访问过
                if (noiseLevel > 0 && row >= 0 && row < sRow && col >= 0 && col < sCol && visited[row][col] == 0) {
                    queue.offer(new int[] {row, col, noiseLevel});
                    visited[row][col] = 1;
                    sMatrix[row][col] = Math.max(sMatrix[row][col], noiseLevel);
                }
            }
        }
    }
}
