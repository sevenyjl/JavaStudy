package commond.doublePointer;

import java.util.*;

/**
 * 使用双指针解决，三数之和
 * https://leetcode-cn.com/problems/3sum
 * 总结：
 * 1. 排序
 * 2. 定义left，right
 */
public class Demo01ThreeNumberSum {
    public static void main(String[] args) {
//        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));//[[-1,-1,2],[-1,0,1]]
//        System.out.println(threeSum(new int[]{-1, 0, 1, 2, 2, -1, -4}));//[[-1,-1,2],[-1,0,1],[2,2,4]]
        System.out.println(threeSum(new int[]{-2, 0, 1, 1, 2}));//[[-1,-1,2],[-1,0,1],[2,2,4]]
    }

    /**
     * 解答成功: 执行耗时:850 ms,击败了5.22% 的Java用户
     * 内存消耗: 42.7 MB,击败了14.06% 的Java用户
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
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
