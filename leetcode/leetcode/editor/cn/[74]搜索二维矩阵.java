//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -10⁴ <= matrix[i][j], target <= 10⁴ 
// 
// Related Topics 数组 二分查找 矩阵 👍 529 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        } else if (matrix.length == 1 && matrix[0].length == 1) {
            return target == matrix[0][0];
        }
        return binarySearch(matrix, 0, matrix.length * matrix[0].length-1, target);
    }

    /**
     * len 长度=row*col
     * 第i个数在 [x][y]=4*x+y=i-->[(i-i%col)/row][i%col]
     * -   eg: [1 ,2 ,3 ,4 ,5 ]
     * -       [7 ,8 ,9 ,10,11]
     * -       [15,16,17,18,19]
     * -       第i=8 ->matrix[(8-3)/3][8%5]=matrix[1][3]
     *
     * @param matrix
     * @param start
     * @param end
     * @param target
     * @return
     */
    private static boolean binarySearch(int[][] matrix, int start, int end, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        // 当还剩两个值的时候 就直接返回了
        if (end - start == 1) {
            return matrix[(start - start % col) / row][start % col] == target ||
                    matrix[(end - end % col) / row][end % col] == target;
        }
        // 取中间
        int mindIndex = (start + end) / 2;
        int mindValue = matrix[(mindIndex - mindIndex % col) / row][mindIndex % col];
        if (mindValue == target) {
            return true;
        }
        if (mindValue > target) {
            return binarySearch(matrix, 0, mindIndex, target);
        } else {
            return binarySearch(matrix, mindIndex, end, target);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
