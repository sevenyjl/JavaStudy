package question.Demo54螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

/**
 * @author y30016814
 * @since 2021/11/1 14:21
 */
public class Solution54 {
    public static void main(String[] args) {
        // System.out.println(spiralOrder(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
        // System.out.println(spiralOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
        System.out.println(
            spiralOrder(new int[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {17, 18, 19, 20}}));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.5 MB,击败了60.48% 的Java用户
     * 利用辅助矩阵visited
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        boolean[][] visited = new boolean[rows][columns];
        int total = rows * columns;
        int row = 0, column = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        for (int i = 0; i < total; i++) {
            order.add(matrix[row][column]);
            visited[row][column] = true;
            int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
            // 当nextRow或者nextColumn到底边界值，或者是已经visited的值就开启转向
            if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns
                || visited[nextRow][nextColumn]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            column += directions[directionIndex][1];
        }
        return order;
    }

    /**
     * 更具模型进行暴力遍历
     * <p>
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.4 MB,击败了71.57% 的Java用户
     *
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder1(int[][] matrix) {
        ArrayList<Integer> integers = new ArrayList<>(matrix.length * matrix[0].length);
        addIn(integers, matrix, 0);
        return integers;
    }

    private static void addIn(List<Integer> integers, int[][] matrix, int step) {
        for (int i = step; i < matrix[0].length - step; i++) {
            integers.add(matrix[step][i]);
        }
        if (integers.size() == matrix.length * matrix[0].length) {
            return;
        }
        for (int i = 1 + step; i < matrix.length - step; i++) {
            integers.add(matrix[i][matrix[0].length - step - 1]);
        }
        if (integers.size() == matrix.length * matrix[0].length) {
            return;
        }
        for (int i = matrix[0].length - 2 - step; i >= step; i--) {
            integers.add(matrix[matrix.length - 1 - step][i]);
        }
        for (int i = matrix.length - step - 2; i >= 1 + step; i--) {
            integers.add(matrix[i][step]);
        }
        if (integers.size() == matrix.length * matrix[0].length) {
            return;
        }
        addIn(integers, matrix, ++step);
    }
}
