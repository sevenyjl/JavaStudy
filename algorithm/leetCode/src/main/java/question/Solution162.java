package question;

import java.util.Arrays;

/**
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * <p>
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * <p>
 * Related Topics 数组 二分查找
 * 👍 620 👎 0
 */
public class Solution162 {
    public static void main(String[] args) {
        System.out.println(findPeakElement2(new int[] {1, 2, 3, 1}));//2
        System.out.println(findPeakElement2(new int[] {1, 2, 1, 3, 5, 6, 4}));//1 或 5
        System.out.println(findPeakElement2(new int[] {1, 2, 5, 4, 2, 4, 1}));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.2 MB,击败了30.49% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.2 MB,击败了35.45% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int findPeakElement2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int indexMid = left + (right - left) / 2;
            if (nums[indexMid] > nums[indexMid + 1]) {
                right = indexMid;
            } else {
                left = indexMid + 1;
            }
        }
        return left;
    }
}
