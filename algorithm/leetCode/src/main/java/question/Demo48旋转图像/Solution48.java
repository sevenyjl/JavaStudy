package question.Demo48旋转图像;

import java.util.Arrays;

public class Solution48 {
    public static void main(String[] args) {
//        show(new int[][]{
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9}
//        });
        show(new int[][]{
                {5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}
        });
    }

    private static void show(int[][] matrix) {
        rotate(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 旋转矩阵
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.7 MB,击败了16.32% 的Java用户
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        change(matrix, 0, matrix.length);
    }

    private static void change(int[][] matrix, int start, int len) {
        if (len < 2) {
            return;
        }
        for (int i = 0; i < len - 1; i++) {
            int temp = matrix[start][start + i];
            matrix[start][start + i] = matrix[start + len - 1 - i][start];
            matrix[start + len - 1 - i][start] = matrix[start + len - 1][start + len - 1 - i];
            matrix[start + len - 1][start + len - 1 - i] = matrix[start + i][start + len - 1];
            matrix[start + i][start + len - 1] = temp;
        }
        change(matrix, ++start, len - 2);
    }
}
