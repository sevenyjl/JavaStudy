package question.Demo36有效的数独;

import java.util.Arrays;

public class Solution36 {
    public static void main(String[] args) {
        System.out.println(isValidSudoku(new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        }));
        System.out.println(isValidSudoku(new char[][]{
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        }));
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 63.43% 的用户
     * 内存消耗： 37.7 MB , 在所有 Java 提交中击败了 99.65% 的用户
     *
     * @param board
     * @return
     */
    public static boolean isValidSudoku(char[][] board) {
        boolean[] used = new boolean[10];
        // 验证行
        for (int i = 0; i < board.length; i++) {
            // 重新赋值
            Arrays.fill(used, false);
            for (int j = 0; j < board.length; j++) {
                if (extracted(board, used, i, j)) return false;
            }
        }
        // 验证列
        for (int i = 0; i < board.length; i++) {
            // 重新赋值
            Arrays.fill(used, false);
            for (int j = 0; j < board.length; j++) {
                if (extracted(board, used, j, i)) return false;
            }
        }
        // 验证块
        for (int i = 0; i < board.length; i++) {
            int chen = i / 3;
            int lie = i % 3;
            Arrays.fill(used, false);
            for (int j = lie * 3; j < (lie + 1) * 3; j++) {
                for (int k = chen * 3; k < (chen + 1) * 3; k++) {
                    if (extracted(board, used, k, j)) return false;
                }
            }
        }
        return true;
    }

    private static boolean extracted(char[][] board, boolean[] used, int j, int k) {
        if (board[j][k] != '.') {
            if (!used[board[j][k] - 48]) {
                used[board[j][k] - 48] = true;
            } else {
                return true;
            }
        }
        return false;
    }
}
