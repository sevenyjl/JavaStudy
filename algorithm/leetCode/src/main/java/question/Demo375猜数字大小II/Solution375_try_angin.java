package question.Demo375猜数字大小II;

import java.util.Arrays;

import cn.hutool.poi.excel.ExcelUtil;

/**
 * @author y30016814
 * @since 2021/11/12 16:25
 * 提示：从大到小的猜
 */
public class Solution375_try_angin {
    public static void main(String[] args) {
        System.out.println(getMoneyAmount(10));

    }

    public static int getMoneyAmount(int n) {
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = k + Math.max(f[i][k - 1], f[k + 1][j]);
                    minCost = Math.min(minCost, cost);
                }
                f[i][j] = minCost;
            }
        }
        for (int[] ints : f) {
            System.out.println(Arrays.toString(ints));
        }
        return f[1][n];

    }

}
