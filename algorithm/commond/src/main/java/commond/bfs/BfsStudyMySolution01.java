package commonly.bfs;/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import random.ArraysRandom;

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
public class BfsStudyMySolution01 {

    public static void main(String[] args) {
        int n = 100;
        int m = 100;
        long start = System.currentTimeMillis();
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(RandomUtil.randomInt(10));
        }
        int[][] randomIntss = ArraysRandom.createRandomIntss(10, 3, integers);
        int[][] clone = ObjectUtil.clone(randomIntss);
        System.out.println(randomIntss == clone);
        //do something
        System.out.println(spreadNoise(n, m,
            randomIntss)); // 测试用例，输出应该为63
        System.out.printf("执行耗时%s毫秒\n", System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        //do something
        System.out.println(BfsStudy01.spreadNoise(n, m,
            clone));
        System.out.printf("执行耗时%s毫秒\n", System.currentTimeMillis() - start);
    }

    /**
     * 暴力扩散
     * 思路：每个都去扩散，留下最大值
     *
     * @param n
     * @param m
     * @param noise
     * @return
     */
    public static int spreadNoise(int n, int m, int[][] noise) {
        //定义二维矩阵 n*m
        int[][] initCanvas = new int[n][m];
        for (int[] ints : noise) {
            spreads(initCanvas, ints[0], ints[1], ints[2]);
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result += initCanvas[i][j];
            }
        }
        return result;
    }

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

    public static void spreads(int[][] initCanvas, int lie, int hang, int value) {
        //赋值
        initCanvas[lie][hang] = Math.max(initCanvas[lie][hang], value);
        //扩散
        for (int[] direction : sDirections) {
            // 计算每个相邻节点的位置和噪音值
            int row = lie + direction[0];
            int col = hang + direction[1];
            int noiseLevel = value - 1;
            if (noiseLevel > 0 && row >= 0 && row < initCanvas.length && col >= 0
                && col < initCanvas[0].length && noiseLevel > initCanvas[row][col]) {
                initCanvas[row][col] = noiseLevel;
                spreads(initCanvas, row, col, noiseLevel);
            }
        }

    }

}
