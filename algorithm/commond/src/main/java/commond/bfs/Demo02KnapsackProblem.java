package commond.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http://3ms.huawei.com/km/groups/2912737/blogs/details/10011993
 * 0-1背包问题
 * <p>
 * 有 N 件物品和一个容量为 C 的背包。第 i 件物品的重量是 w[i]，价值是 v[i]。求解将哪些物品装入背包可使价值总和最大。注意这里不要求把物品整个装入，可以只装入一个物品的部分。
 * <p>
 * Input:  n = 8, prices = [3,2], weights = [300,160], amounts = [1,6]
 * Output:  640
 */
public class Demo02KnapsackProblem {
    public static void main(String[] args) {
        demo01();
    }

    private static void demo01() {
        int total = 10;//背包容量
        int[][] mv = new int[][] {
            {2, 3},
            {3, 4},
            {4, 5},
            {1, 6},
            {2, 4},
        };
        int[][] dp = new int[mv.length + 1][total + 1];
        for (int i = 0; i < mv.length; i++) {
            for (int j = 0; j < total + 1; j++) {
                if (j >= mv[i][0]) {
                    dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - mv[i][0]] + mv[i][1]);
                } else {
                    dp[i + 1][j] = dp[i][j];
                }
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
    }

}
