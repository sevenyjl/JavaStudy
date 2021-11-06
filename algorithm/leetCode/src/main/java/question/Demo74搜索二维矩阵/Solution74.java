package question.Demo74搜索二维矩阵;

public class Solution74 {
    public static void main(String[] args) {
//        System.out.println(searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 2));
        System.out.println(searchMatrix(new int[][]{{1},{3}}, 3));
    }


    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        } else if (matrix.length == 1 && matrix[0].length == 1) {
            return target == matrix[0][0];
        }
        return binarySearch(matrix, 0, matrix.length * matrix[0].length-1, target);
    }

    /**
     * todo 有bug
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

    /**
     * 先判断那行，在快速获取
     * <p>
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38 MB,击败了30.50% 的Java用户
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix02(int[][] matrix, int target) {
        int n = matrix[0].length;
        for (int[] ints : matrix) {
            if (ints[n - 1] >= target) {
                for (int num : ints) {
                    if (num == target) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }


    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37.2 MB,击败了99.84% 的Java用户
     * 暴力遍历
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix01(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if (target == anInt) {
                    return true;
                }
            }
        }
        return false;
    }
}