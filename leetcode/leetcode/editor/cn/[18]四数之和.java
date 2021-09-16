//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] ： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
// Related Topics 数组 双指针 排序 👍 953 👎 0
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     * 双指针遍历
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
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

    public List<List<Integer>> towSum(int[] nums, int target, int start, int end) {
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

}
//leetcode submit region end(Prohibit modification and deletion)
