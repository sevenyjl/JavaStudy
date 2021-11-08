package question.Demo598范围求和;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Solution598 {
    public static void main(String[] args) {
        System.out.println(maxCount1(3, 4, new int[][]{}));
        System.out.println(maxCount(3, 4, new int[][]{}));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.7 MB,击败了7.87% 的Java用户
     *
     * 思路：找最小行、最小列，并取其乘积
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public static int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) {
            return m * n;
        }
        int lie = ops[0][0];
        int hang = ops[0][1];
        for (int[] op : ops) {
            lie = Math.min(op[0], lie);
            hang = Math.min(op[1], hang);
        }
        return lie * hang;
    }

    /**
     * 暴力
     * 超时
     *
     * @param m
     * @param n
     * @param ops
     * @return
     */
    public static int maxCount1(int m, int n, int[][] ops) {
        int max = 0;
        // 定义二维数组
        int[][] ints = new int[m][n];
        for (int[] op : ops) {
            max++;
            for (int i = 0; i < op[0]; i++) {
                for (int j = 0; j < op[1]; j++) {
                    ints[i][j] += 1;
                }
            }
        }
        int count = 0;
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
            for (int i : anInt) {
                if (i == max) {
                    count++;
                }
            }
        }
        return count;
    }
}
