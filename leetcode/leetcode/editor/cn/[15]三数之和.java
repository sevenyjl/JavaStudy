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
    public List<List<Integer>> threeSum(int[] nums) {
        //-4,-1,-1,0,1,2
        //取得数组中的最大数，并取得位数
        HashSet<List<Integer>> result = new HashSet<>();
        int[] sort = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < sort.length - 1; i++) {
            result.addAll(towSum(i + 1, sort.length - 1, i, sort));
        }
        return new ArrayList<>(result);
    }

    public static List<List<Integer>> towSum(int left, int right, int sumIndex, int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        while (true) {
            //退出条件
            if (left >= right) {
                break;
            }
//            System.out.println(nums[left] + "+" + nums[right] + "=" + nums[sumIndex]);
            //计算
            int target = nums[left] + nums[right];
            if (target + nums[sumIndex] == 0) {
                result.add(Arrays.asList(nums[sumIndex], nums[left], nums[right]));
                left++;
            } else if (target < nums[sumIndex]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
