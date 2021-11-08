package simulation.professional.d20211104;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author y30016814
 * @since 2021/11/4 16:51
 */
public class T3_NO {
    public static void main(String[] args) {
        System.out.println(less(new int[][] {{0, 1, 250}, {0, 3, 10}, {1, 2, 25}, {1, 3, 80}, {2, 3, 90}}, 2));
        System.out.println(less(new int[][] {{0, 4, 41}, {2, 5, 35}, {1, 3, 49}}, 3));
        System.out.println(cooperativePerformance(3, new int[][] {{0, 4, 41}, {2, 5, 35}, {1, 3, 49}}));
        System.out.println(
            cooperativePerformance(2, new int[][] {{1, 2, 50}, {3, 0, 60}, {0, 1, 25}, {2, 3, 40}, {2, 3, 90}}));
        System.out.println(less(new int[][] {{1, 2, 50}, {3, 0, 60}, {0, 1, 25}, {2, 3, 40}, {2, 3, 90}}, 2));

    }

    private static int less(int[][] program, int num) {
        boolean[] used = new boolean[num * 2];
        ArrayList<Integer> mList = new ArrayList<>();
        extracted(program, used, new ArrayList<>(), mList);
        return mList.stream().max(Comparator.comparingInt(o -> o)).get();
    }

    /**
     * 没过，但是思路有了
     *
     * @param program
     * @param used
     * @param temp
     * @param mList
     */
    private static void extracted(int[][] program, boolean[] used, List<Integer> temp, List<Integer> mList) {
        if (isOver(used)) {
            Collections.sort(temp);
            // System.out.println(temp);
            mList.add(temp.get(temp.size() - 1));
            temp.clear();
            return;
        }
        for (int[] ints : program) {
            if (!used[ints[0]] && !used[ints[1]]) {
                temp.add(ints[2]);
                used[ints[0]] = true;
                used[ints[1]] = true;
                // 递归
                extracted(program, used, temp, mList);
                // 回溯
                used[ints[0]] = false;
                used[ints[1]] = false;
            }
        }
    }

    private static boolean isOver(boolean[] used) {
        boolean isOver = true;
        for (boolean b : used) {
            if (!b) {
                isOver = false;
                break;
            }
        }
        return isOver;
    }

    private static int min = Integer.MAX_VALUE;

    public static int cooperativePerformance(int num, int[][] program) {
        backtrack(num, program, new ArrayList<>(), new HashSet<>(), 0);
        return min;
    }

    private static void backtrack(int num, int[][] program, List<Integer> money, Set<Integer> set, int index) {
        if (set.size() == 2 * num) {
            int temp = Integer.MIN_VALUE;
            for (int ele : money) {
                temp = Math.max(temp, ele);
            }
            min = Math.min(min, temp);
            return;
        }
        for (int i = index; i < program.length; i++) {
            //剪枝的都在这里了
            if (set.contains(program[i][0]) || set.contains(program[i][1]) || program[i][2] > min) {
                continue;
            }
            set.add(program[i][0]);
            set.add(program[i][1]);
            money.add(program[i][2]);
            backtrack(num, program, money, set, i + 1);
            set.remove(program[i][0]);
            set.remove(program[i][1]);
            money.remove(money.size() - 1);
        }
    }

}
