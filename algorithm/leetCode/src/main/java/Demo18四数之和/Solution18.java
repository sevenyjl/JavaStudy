package Demo18四数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution18 {
    public static void main(String[] args) {
        //[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//        System.out.println(fourSum2(new int[]{1, 0, -1, 0, -2, 2}, 0));
        //[[2,2,2,2]]
//        System.out.println(fourSum2(new int[]{2, 2, 2, 2}, 8));
//        System.out.println(fourSum2(null, 8));
        System.out.println(fourSum2(new int[]{2, 2, 2, 2, 2}, 8));

    }

    /**
     * 双指针遍历+暴力遍历
     * 解答成功:
     * 执行耗时:99 ms,击败了7.62% 的Java用户
     * 内存消耗:39.6 MB,击败了5.08% 的Java用户
     */
    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        HashSet<List<Integer>> result = new HashSet<>();
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        int[] ints = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < ints.length; i++) {
            for (int i1 = i + 1; i1 < ints.length; i1++) {
                List<List<Integer>> arrayLists = towSum(ints, target - ints[i] - ints[i1], i1 + 1, ints.length - 1);
                if (!arrayLists.isEmpty()) {
                    for (List<Integer> list : arrayLists) {
                        list.add(ints[i]);
                        list.add(ints[i1]);
                        result.add(list);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    public static List<List<Integer>> towSum(int[] nums, int target, int start, int end) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum > target) {
                end--;
            } else if (sum < target) {
                start++;
            } else {
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(nums[start]);
                integers.add(nums[end]);
                result.add(integers);
                start++;
                end--;
            }
        }
        return result;
    }

    /**
     * 暴力遍历
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = i + 1; i1 < nums.length; i1++) {
                for (int i2 = i1 + 1; i2 < nums.length; i2++) {
                    for (int i3 = i2 + 1; i3 < nums.length; i3++) {
                        if (nums[i] + nums[i1] + nums[i2] + nums[i3] == target) {
                            result.add(Arrays.asList(nums[i], nums[i1], nums[i2], nums[i3]));
                        }
                    }
                }
            }
        }
        return result;
    }
}
