//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 3741 👎 0
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public  List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if (nums.length < 2) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        //-4 -1 -1 0 1 2
        for (int i = 0; i < nums.length - 1; i++) {
            List<List<Integer>> sum = isSum(nums, i + 1, nums.length - 1, i);
            if (!sum.isEmpty()) {
                result.addAll(sum);
            }
        }
        return new ArrayList<>(result);
    }

    public static List<List<Integer>> isSum(int[] nums, int left, int right, int target) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        while (left < right && left > 0 && right < nums.length) {
            if (nums[left] + nums[right] == -nums[target]) {
                result.add(Arrays.asList(nums[left], nums[right], nums[target]));
                right--;
            } else if (nums[left] + nums[right] < -nums[target]) {
                left++;
            } else if (nums[left] + nums[right] > -nums[target]) {
                right--;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
