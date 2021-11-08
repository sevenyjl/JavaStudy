package question.Demo51N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author y30016814
 * @since 2021/10/29 17:48
 */
public class Solution51 {
    public static void main(String[] args) {
        System.out.println(solveNQueens(1));
    }

    /**
     * 解答成功:
     * 执行耗时:6 ms,击败了22.51% 的Java用户
     * 内存消耗:38.1 MB,击败了99.33% 的Java用户
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        // 这里定义int Integer都可以 Integer是为了方便一点点~ 这个的函数意义是：index=row value=col代替了二维矩阵
        Integer[] queens = new Integer[n];
        // 设置没放皇后的值为-1（因为有可能queens[0]=0的情况）
        Arrays.fill(queens, -1);
        // 获得所有皇后的解法，用来存储
        ArrayList<List<Integer>> lists = new ArrayList<>();
        // 放置皇后
        putQueen(lists, queens, 0);
        return showQueen(lists, n);
    }

    private static void putQueen(List<List<Integer>> lists, Integer[] queens, int row) {
        // 从col=0 开始 对当前row的所有位置进行放queen尝试
        t:
        for (int i = 0; i < queens.length; i++) {
            // 遍历已经放在了的皇后。目的：判断当前皇后师傅同列，是否同对角线
            // TODO: 2021/11/1 优化：将同列判断和同斜线判断放置为两个list中 （以空间换时间？）
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
                lists.add(Arrays.stream(queens).collect(Collectors.toList()));
                queens[row] = -1;
                return;
            }
            // 放置下一行
            putQueen(lists, queens, row + 1);
            // 回溯
            queens[row] = 0;
        }
    }

    /**
     * just show the queens
     *
     * @param lists
     * @param len
     * @return
     */
    private static List<List<String>> showQueen(List<List<Integer>> lists, int len) {
        ArrayList<List<String>> result = new ArrayList<>();
        lists.forEach(l -> {
            ArrayList<String> temp = new ArrayList<>();
            l.forEach(integer -> {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < len; i++) {
                    if (i == integer) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                temp.add(sb.toString());
            });
            result.add(temp);
        });
        return result;
    }

    public static List<List<String>> solveNQueens1(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();// 列上的皇后
        Set<Integer> diagonals1 = new HashSet<Integer>();// 对角线1
        Set<Integer> diagonals2 = new HashSet<Integer>();// 对角线2
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public static void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns,
        Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
