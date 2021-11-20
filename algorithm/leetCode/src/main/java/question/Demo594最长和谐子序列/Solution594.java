package question.Demo594最长和谐子序列;

import random.ArraysRandom;

import java.util.*;
import java.util.stream.Collectors;

public class Solution594 {
    public static void main(String[] args) {
//        System.out.println(findLHS20211120100725(new int[]{1, 3, 2, 2, 5, 2, 3, 7}));
        System.out.println(findLHS20211120100725(new int[]{1, 2, 3, 4}));
        System.out.println(findLHS20211120100725(new int[]{1, 1, 1, 1, 1,}));
        System.out.println(findLHS20211120100725(new int[]{1}));
        System.out.println(findLHS20211120100725(new int[]{}));
//        System.out.println(findLHS20211120100725(ArraysRandom.createRandomInts(100, 1000, true)));

    }

    public int findLHS(int[] nums) {
        return 0;
    }


    public static int findLHS20211120100725YouHua(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            Integer orDefault = map.getOrDefault(num, 0);
            map.put(num, ++orDefault);
        }
        // 寻找连续且最大的字符串
        int max = 0;
        for (Integer integer : map.keySet()) {
            
        }
        return max;
    }

    /**
     * 2021/11/20 10:28
     * 解答成功:
     * 执行耗时:36 ms,击败了7.16% 的Java用户
     * 内存消耗:42.9 MB,击败了5.07% 的Java用户
     * 优化：只输出长度
     * 1. 所以可以不用sort
     * 2. map中可以放int 而不是List
     *
     * @param nums
     * @return
     */
    public static int findLHS20211120100725(int[] nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> orDefault = map.getOrDefault(num, new ArrayList<>());
            orDefault.add(i);
            map.put(num, orDefault);
        }
        // 寻找连续且最大的字符串
        Integer temp = null;
        int max = 0;
        for (Integer integer : map.keySet().stream().sorted().collect(Collectors.toList())) {
            if (temp != null && integer - temp == 1) {
                max = Math.max(map.get(temp).size() + map.get(integer).size(), max);
            }
            temp = integer;
        }
        return max;
    }


}
