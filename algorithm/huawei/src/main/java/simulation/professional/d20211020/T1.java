package simulation.professional.d20211020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 8 间牢房排成一排，每间牢房不是有人住就是空着。
 * <p>
 * 每天，无论牢房是被占用或空置，都会根据以下规则进行更改：
 * <p>
 * 如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。
 * 否则，它就会被空置。
 * （请注意，由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。）
 * <p>
 * 我们用以下方式描述监狱的当前状态：如果第 i 间牢房被占用，则 cell[i]==1，否则 cell[i]==0。
 * <p>
 * 根据监狱的初始状态，在 N 天后返回监狱的状况（和上述 N 种变化）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：cells = [0,1,0,1,1,0,0,1], N = 7
 * 输出：[0,0,1,1,0,0,0,0]
 * 解释：
 * 下表概述了监狱每天的状况：
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * Day 8: [0, 0, 0, 0, 0, 1, 1, 0]
 * Day 9: [0, 1, 1, 1, 0, 0, 0, 0]
 * Day10: [0, 0, 1, 0, 0, 1, 1, 0]
 * Day11: [0, 0, 1, 0, 0, 0, 0, 0]
 * Day12: [0, 0, 1, 0, 1, 1, 1, 0]
 * Day13: [0, 0, 1, 1, 0, 1, 0, 0]
 * Day14: [0, 0, 0, 0, 1, 1, 0, 0]
 * Day15: [0, 1, 1, 0, 0, 0, 0, 0]
 * <p>
 * 示例 2：
 * <p>
 * 输入：cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * 输出：[0,0,1,1,1,1,1,0]
 * <p>
 * 提示：
 * <p>
 * cells.length == 8
 * cells[i] 的值为 0 或 1 
 * 1 <= N <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/prison-cells-after-n-days
 * <p>
 */
public class T1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(prisonAfterNDays(new int[] {0, 1, 0, 1, 1, 0, 0, 1}, 7)));//[0,0,1,1,0,0,0,0]
        System.out.println(
            Arrays.toString(prisonAfterNDays(new int[] {1, 0, 0, 1, 0, 0, 1, 0}, 1000000000)));//[0,0,1,1,1,1,1,0]
    }

    /**
     * 解答成功:
     * 执行耗时:11 ms,击败了6.57% 的Java用户
     * 内存消耗:38.9 MB,击败了5.19% 的Java用户
     * @param cells
     * @param n
     * @return
     */
    public static int[] prisonAfterNDays(int[] cells, int n) {
        exchange(cells);
        if (cells[0] != 0) {
            cells[0] = 0;
        }
        if (cells[cells.length - 1] != 0) {
            cells[cells.length - 1] = 0;
        }
        ArrayList<List<Integer>> cache = new ArrayList<>();
        isIn(cells, cache);
        if (n - 1 < cache.size()) {
            return cache.get(n - 1).stream().mapToInt(Integer::valueOf).toArray();
        } else {
            return cache.get((n - 1) % cache.size()).stream().mapToInt(Integer::valueOf).toArray();
        }
    }

    private static int[] exchange(int[] cells) {
        int pre = cells[0];
        for (int i = 1; i < cells.length - 1; i++) {
            if (pre == cells[i + 1]) {
                pre = cells[i];
                cells[i] = 1;
            } else {
                pre = cells[i];
                cells[i] = 0;
            }
        }
        return cells;
    }

    private static void isIn(int[] cells, List<List<Integer>> result) {
        List<Integer> collect = Arrays.stream(cells).boxed().collect(Collectors.toList());
        if (!result.contains(collect)) {
            result.add(collect);
            int[] exchange = exchange(cells);
            // System.out.println(Arrays.toString(exchange));
            isIn(exchange, result);
        }
    }

}
