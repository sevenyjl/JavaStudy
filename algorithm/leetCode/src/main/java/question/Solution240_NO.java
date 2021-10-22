package question;

/**
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-2/
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
 * ,23,26,30]], target = 5
 * 输出：true
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
 * ,23,26,30]], target = 20
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 * <p>
 * Related Topics 数组 二分查找 分治 矩阵
 * 👍 735 👎 0
 */
public class Solution240_NO {
    public static void main(String[] args) {
        // System.out.println(searchMatrix(new int[][] {
        //     {1, 4, 7, 11, 15},
        //     {2, 5, 8, 12, 19},
        //     {3, 6, 9, 16, 22},
        //     {10, 13, 14, 17, 24},
        //     {18, 21, 23, 26, 30}
        // }, 5));//true
        // System.out.println(searchMatrix(new int[][] {
        //     {1, 4, 7, 11, 15, 16},
        //     {2, 5, 8, 12, 19, 20},
        //     {3, 6, 9, 16, 21, 23},
        //     {10, 13, 14, 17, 24, 25},
        //     {18, 21, 23, 26, 30, 31}
        // }, 20));//false
        // System.out.println(searchMatrix(new int[][] {
        //     {-5}
        // }, -5));//false
        System.out.println(searchMatrix(new int[][] {
            {1, 4}, {2, 5}
        }, 2));//false

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        return false;
    }

    /**
     * 暴力遍历【不可取】
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix0(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (anInt == target) {
                    return true;
                }
            }
        }
        return false;
    }

}
