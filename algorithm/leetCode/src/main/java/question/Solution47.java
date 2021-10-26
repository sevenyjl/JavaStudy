package question.Demo47全排列II;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution47 {
    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[] {1, 2, 3}));
        System.out.println(permuteUnique(new int[] {1, 2, 1}));
    }

    /**
     * 解答成功:
     * 执行耗时:28 ms,击败了13.46% 的Java用户
     * 内存消耗:38.5 MB,击败了99.36% 的Java用户
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> lists = new HashSet<>();
        ArrayList<Integer> integers = new ArrayList<>();
        for (int num : nums) {
            integers.add(num);
        }
        di(lists, integers, nums.length, 0);
        return new ArrayList<>(lists);
    }

    public static void di(Set<List<Integer>> lists, List<Integer> integers, int n, int start) {
        if (start == n) {
            lists.add(new ArrayList<>(integers));
        }
        for (int i = start; i < n; i++) {
            Collections.swap(integers, start, i);
            di(lists, integers, n, start + 1);
            Collections.swap(integers, i, start);
        }
    }
}
