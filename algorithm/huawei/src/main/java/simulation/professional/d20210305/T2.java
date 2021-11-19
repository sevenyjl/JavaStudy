package simulation.professional.d20210305;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import random.ArraysRandom;

/**
 * @author y30016814
 * @since 2021/11/5 9:55
 * http://3ms.huawei.com/km/blogs/details/10437493
 * http://3ms.huawei.com/km/blogs/details/9959105
 */
public class T2 {
    public static void main(String[] args) {
        int[][] persons = {{0, 10, 2}, {8, 34, 5}, {2, 15, 2}};
        persons = ArraysRandom.createRandomIntss(10, 3, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        persons = new int[][] {
            {5, 9, 7,},
            {5, 5, 6,},
            {0, 4, 9,},
            {1, 2, 1,},
            {4, 8, 3,},
            {1, 9, 9,},
            {3, 3, 2,},
            {1, 6, 1,},
            {5, 6, 5,},
            {1, 8, 7,}
        };
        for (int[] period : new T_02FindPeriods().findPeriods(persons)) {
            System.out.println(Arrays.toString(period));
        }
        System.out.println("my");
        for (int[] period : solution(persons)) {
            System.out.println(Arrays.toString(period));
        }
    }

    /**
     * 不可行~
     *
     * @param persons
     * @return
     */
    private static int[][] solution(int[][] persons) {
        // 排序
        Arrays.sort(persons, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) {
                    return o1[2] - o2[2];
                }
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });
        // for (int[] person : persons) {
        //     System.out.println(Arrays.toString(person));
        // }
        ArrayList<int[]> temp = new ArrayList<>();
        int max = 0;
        // 合并区间
        for (int i = 0; i < persons.length; i++) {
            int[] tempPerson = persons[i];
            int left = tempPerson[0];
            int right = tempPerson[1];
            int value = tempPerson[2];
            for (int j = i + 1; j < persons.length; j++) {
                int[] personJ = persons[j];
                if (right >= personJ[0] && right <= personJ[1]) {
                    left = Math.max(left, personJ[0]);
                    right = Math.min(right, personJ[1]);
                    value += personJ[2];
                }
                if (personJ[0] > right) {
                    break;
                }
            }
            max = Math.max(max, value);
            temp.add(new int[] {left, right, value});
        }
        temp.forEach(ints -> System.out.println(Arrays.toString(ints)));
        int finalMax = max;
        int[][] tempInts = temp.stream()
            .filter(ints -> ints[2] == finalMax)
            .sorted(Comparator.comparingInt(o -> o[0])).toArray(int[][]::new);
        int[][] result = new int[tempInts.length][2];
        for (int i = 0; i < tempInts.length; i++) {
            result[i][0] = tempInts[i][0];
            result[i][1] = tempInts[i][1];
        }
        return result;
    }

}

/**
 * Created by l00426456 on 2021/7/7.
 * // 给你一个二维数组，{{0,10,2},{12,34,5},{2,15,2}}，
 * // 标识每一个项目在每个时间段投入的人力，例如：0， 10, 2 表示在0点- 10点投入两个人
 * // 输出：
 * // 输出最高人力投入的区间，如果有多个这样的区间，则按照start升序输出
 */
class T_02FindPeriods {
    //用个集合来保存满足最大人力的区间
    List<int[]> periodList = new ArrayList<>();

    public int[][] findPeriods(int[][] persons) {
        int[] deltaPerson = new int[100002];
        int lastPeriod = -1;
        for (int i = 0; i < persons.length; i++) {
            deltaPerson[persons[i][0]] = deltaPerson[persons[i][0]] + persons[i][2];
            deltaPerson[persons[i][1] + 1] = deltaPerson[persons[i][1] + 1] - persons[i][2];
            lastPeriod = Math.max(persons[i][1], lastPeriod);
        }

        int[] periods = new int[lastPeriod + 1];
        periods[0] = deltaPerson[0];
        //求出每个时间点的人力
        for (int i = 1; i < lastPeriod + 1; i++) {
            periods[i] = deltaPerson[i] + periods[i - 1];
        }

        int maxPersonNum = getMaxPersonNum(periods);
        getMaxPeriod(periods, maxPersonNum);

        return compareToPeriod();
    }

    //求出最大时间点的人力数
    private int getMaxPersonNum(int[] nums) {
        int max = 0;
        for (int i : nums) {
            // System.out.println(i);
            max = Math.max(i, max);
        }
        return max;
    }

    //采用双指针来求最大区间
    private void getMaxPeriod(int[] nums, int maxCount) {
        int left = 1;
        int right = 1;
        while (right < nums.length) {
            if (nums[left] != maxCount) {
                left++;
                right++;
                continue;
            }
            if (nums[left] == maxCount) {
                if (nums[right] != maxCount) {
                    periodList.add(new int[] {left, right - 1});
                    left = right;
                }
            }
            right++;
        }
    }

    //进行排序
    private int[][] compareToPeriod() {
        Collections.sort(periodList, (o1, o2) -> o1[0] - o2[0]);
        int[][] result = new int[periodList.size()][2];
        for (int i = 0; i < periodList.size(); i++) {
            result[i] = periodList.get(i);
        }
        return result;
    }

}
