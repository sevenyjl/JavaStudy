package question.Demo57插入区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author y30016814
 * @since 2021/11/3 16:22
 */
public class Solution57 {
    public static void main(String[] args) {
        for (int[] ints : insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {7, 10}, {12, 16}}, new int[] {4, 8})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {{1, 3}, {6, 9}}, new int[] {4, 8})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {}, new int[] {4, 8})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {{1, 5}}, new int[] {2, 3})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {{1, 5}}, new int[] {2, 7})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {}, new int[] {2, 7})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {{1, 3}, {6, 9}}, new int[] {2, 5})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {{1, 5}}, new int[] {6, 8})) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("-----------------");
        for (int[] ints : insert(new int[][] {{1, 5}}, new int[] {0, 0})) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        ArrayList<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        return ansList.toArray(new int[ansList.size()][]);
    }

    /**
     * 解答成功:
     * 执行耗时:4 ms,击败了15.88% 的Java用户
     * 内存消耗:40.9 MB,击败了5.42% 的Java用户
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert1(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][] {newInterval};
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        ArrayList<int[]> result = new ArrayList<>();
        boolean isStart = false;
        int overIndex = intervals.length - 1;
        int[] temp = new int[] {-1, -1};
        for (int i = 0; i < intervals.length; i++) {
            overIndex = i;
            int[] interval = intervals[i];
            if (interval[1] >= newInterval[0] && newInterval[1] >= interval[0] && !isStart) {
                isStart = true;
                temp[0] = Math.min(interval[0], newInterval[0]);
            } else if (interval[0] > newInterval[1] && isStart) {
                temp[1] = Math.max(newInterval[1], intervals[i - 1][1]);
                break;
            } else if (!isStart) {
                result.add(interval);
            }
        }
        if (temp[0] == -1) {
            result.add(newInterval);
        } else if (temp[1] == -1) {
            temp[1] = Math.max(newInterval[1], intervals[intervals.length - 1][1]);
            result.add(temp);
        } else {
            result.add(temp);
            for (int j = overIndex; j < intervals.length; j++) {
                result.add(intervals[j]);
            }
        }
        result.sort(Comparator.comparingInt(o -> o[0]));
        return result.toArray(new int[result.size()][]);
    }
}
