package question.Demo56合并区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author y30016814
 * @since 2021/11/2 17:05
 */
public class Solution56 {
    public static void main(String[] args) {
        for (int[] ints : merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------------------------------");
        for (int[] ints : merge(new int[][] {{1, 4}, {4, 5}, {8, 10}, {15, 18}})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------------------------------");
        for (int[] ints : merge(new int[][] {{5, 6}, {2, 3}, {1, 10}, {3, 18}})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------------------------------");
        for (int[] ints : merge(new int[][] {{1, 4}, {0, 1}})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------------------------------");
        for (int[] ints : merge(new int[][] {{1, 4}, {0, 4}})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------------------------------");
        for (int[] ints : merge(new int[][] {{1, 4}, {0, 2}, {3, 5}})) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 解答成功:
     * 执行耗时:7 ms,击败了49.05% 的Java用户
     * 内存消耗:40.8 MB,击败了77.20% 的Java用户
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 先排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        int minL = intervals[0][0];
        int minR = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (minR >= intervals[i][0]) {
                minR = Math.max(intervals[i][1], minR);
            } else {
                result.add(new int[] {minL, minR});
                minL = intervals[i][0];
                minR = intervals[i][1];
            }
        }
        result.add(new int[] {minL, minR});
        return result.toArray(new int[result.size()][2]);
    }

    public static int[][] merge_NO(int[][] intervals) {
        // 定义一个数组用于记录是否拜访过
        boolean[] isVisited = new boolean[intervals.length];
        int minIndex = 0;
        int min = intervals[minIndex][0];
        isVisited[0] = true;
        ArrayList<int[]> result = new ArrayList<>();
        extracted(intervals, isVisited, minIndex, min, intervals[minIndex][1], result);
        return result.toArray(new int[result.size()][]);
    }

    private static void extracted(int[][] intervals, boolean[] isVisited, int minIndex, int min, int max,
        List<int[]> result) {
        for (int i = 1; i < isVisited.length; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                if (intervals[minIndex][1] >= intervals[i][0] && intervals[minIndex][0] <= intervals[i][1]) {
                    minIndex = i;
                    min = Math.min(intervals[i][0], min);
                    max = Math.max(intervals[i][1], max);
                    // 递归
                    extracted(intervals, isVisited, minIndex, min, max, result);
                    return;
                } else {
                    int[] ints = {min, max};
                    result.add(ints);
                    minIndex = i;
                    min = intervals[minIndex][0];
                    max = intervals[minIndex][1];
                }
            }
        }
        result.add(new int[] {Math.min(min, intervals[minIndex][0]), Math.max(intervals[minIndex][1], max)});
    }
}
