package question.Demo52N皇后II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author y30016814
 * @since 2021/11/1 11:12
 */
public class Solution52 {
    public static void main(String[] args) {
        System.out.println(totalNQueens(4));
    }

    /**
     * 解答成功:
     * 执行耗时:1 ms,击败了79.17% 的Java用户
     * 内存消耗:35.3 MB,击败了39.00% 的Java用户
     *
     * @param n
     * @return
     */
    public static int totalNQueens(int n) {
        // 这里定义int Integer都可以 Integer是为了方便一点点~ 这个的函数意义是：index=row value=col代替了二维矩阵
        int[] queens = new int[n];
        // 设置没放皇后的值为-1（因为有可能queens[0]=0的情况）
        Arrays.fill(queens, -1);
        // 放置皇后
        return putQueen(queens, 0);
    }

    private static int putQueen(int[] queens, int row) {
        int count = 0;
        // 从col=0 开始 对当前row的所有位置进行放queen尝试
        t:
        for (int i = 0; i < queens.length; i++) {
            // 遍历已经放在了的皇后。目的：判断当前皇后师傅同列，是否同对角线
            for (int j = 0; j < row; j++) {
                // 同列判断
                if (queens[j] == i) {
                    continue t;
                }
                // 同对角线判断 同一条斜线上的每个位置满足行下标与列下标之差相等
                else if (Math.abs(row - j) == Math.abs(i - queens[j])) {
                    continue t;
                }
            }
            // 放置皇后：index表示行，value表示列
            queens[row] = i;
            // 判断是否全部完成放置，判断依据row为最后一行且最后一行有皇后放置即queens[row]!=0;
            if (row == queens.length - 1 && queens[row] != -1) {
                queens[row] = -1;
                return 1;
            }
            // 放置下一行
            count += putQueen(queens, row + 1);
            // 回溯
            queens[row] = 0;
        }
        return count;
    }
}
