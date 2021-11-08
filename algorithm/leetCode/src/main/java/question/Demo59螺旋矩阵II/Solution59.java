package question.Demo59螺旋矩阵II;

import java.util.Arrays;

/**
 * @author y30016814
 * @since 2021/11/4 14:25
 */
public class Solution59 {
    public static void main(String[] args) {
        for (int[] ints : generateMatrix(5)) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[][] generateMatrix2(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while (num <= tar) {
            for (int i = l; i <= r; i++) {
                mat[t][i] = num++; // left to right.
            }
            t++;
            for (int i = t; i <= b; i++) {
                mat[i][r] = num++; // top to bottom.
            }
            r--;
            for (int i = r; i >= l; i--) {
                mat[b][i] = num++; // right to left.
            }
            b--;
            for (int i = b; i >= t; i--) {
                mat[i][l] = num++; // bottom to top.
            }
            l++;
        }
        return mat;
    }

    /**
     * 暴力
     * <p>
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.5 MB,击败了30.95% 的Java用户
     *
     * @param n
     * @return
     */
    public static int[][] generateMatrix(int n) {
        int[][] ints = new int[n][n];
        int now = 0;
        int index = 0;
        while (now != n * n) {
            for (int i = index; i < n - index; i++) {
                now++;
                ints[index][i] = now;
            }
            for (int i = 1 + index; i < n - index; i++) {
                now++;
                ints[i][n - 1 - index] = now;
            }
            for (int i = n - 2 - index; i >= index; i--) {
                now++;
                ints[n - 1 - index][i] = now;
            }
            for (int i = n - 2 - index; i >= 1 + index; i--) {
                now++;
                ints[i][index] = now;
            }
            index++;
        }
        return ints;
    }
}
