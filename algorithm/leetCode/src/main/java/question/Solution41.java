package question.Demo41缺失的第一个正数;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：3
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * Related Topics 数组 哈希表
 * 👍 1241 👎 0
 */
public class Solution41 {
    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[] {1, 2, 0}));//3
        System.out.println(firstMissingPositive(new int[] {3, 4, -1, 1}));//2
        System.out.println(firstMissingPositive(new int[] {7, 8, 9, 11, 12}));//1
    }

    /**
     * 解答成功:
     * 执行耗时:4 ms,击败了21.38% 的Java用户
     * 内存消耗:94.4 MB,击败了77.10% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int start = 1;
        for (int num : nums) {
            if (num > 0) {
                if (num == start) {
                    start++;
                } else if (num > start) {
                    return start;
                }
            }
        }
        return start;
    }

}