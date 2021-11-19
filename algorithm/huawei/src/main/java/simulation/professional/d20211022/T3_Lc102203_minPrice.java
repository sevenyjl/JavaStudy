package simulation.professional.d20211022;

/**
 * 功能说明
 *
 * @author x00501103
 * @version 2021-2021 v1.0
 * @since 2021/10/22 18:34
 * C版权所有 (c) 华为技术有限公司 2021-2021
 */
public class T3_Lc102203_minPrice {
    public static void main(String[] args) {
        int[] departing = {5, 4, 3, 2, 1, 0};
        int[] returning = {0, 1, 2, 3, 4, 5};
        int minDays = 1;
        int maxDays = 4;
        int result = minPrice(departing, returning, minDays, maxDays);
        System.out.println(result);
    }

    public static int minPrice(int[] departing, int[] returning, int minDays, int maxDays) {
        int length = returning.length;
        // 初始化滑窗
        int minCurr = returning[minDays];
        for (int i = minDays; i <= maxDays; i++) {
            minCurr = Math.min(returning[i], minCurr);
        }
        // 记录左侧值
        int left = returning[minDays];
        int minResult = departing[0] + minCurr;
        // 滑窗开始移动
        for (int i = 1; i < length - minDays; i++) {
            // 更新滑窗的内容（最小值，最左值）
            // 滑窗删除的值是原始最左值left，增加的是return[i+maxDays]
            if (minCurr == left) {
                minCurr = returning[i + minDays];
                for (int j = i + minDays; j <= i + maxDays & j < length; j++) {
                    minCurr = Math.min(minCurr, returning[j]);
                }
            } else {
                if (i + maxDays <= length - 1) {
                    minCurr = Math.min(minCurr, returning[i + maxDays]);
                }
            }
            minResult = Math.min(minResult, minCurr + departing[i]);
            left = returning[minDays + i];
        }
        return minResult;
    }
}