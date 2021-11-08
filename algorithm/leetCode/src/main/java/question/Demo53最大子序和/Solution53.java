package question.Demo53最大子序和;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * <p>
 * 输入：nums = [-1]
 * 输出：-1
 * <p>
 * <p>
 * 示例 5：
 * <p>
 * <p>
 * 输入：nums = [-100000]
 * 输出：-100000
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * Related Topics 数组 分治 动态规划
 * 👍 3896 👎 0
 *
 * @author y30016814
 * @since 2021/10/30 9:23
 */
public class Solution53 {
    public static void main(String[] args) {
        // System.out.println(maxSubArray1(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(maxSubArray1(new int[] {-3, -2, -1}));
    }

    /**
     * 暴力滑动~ 直接超时
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max);
            for (int j = 0; j < nums.length - i; j++) {
                int num = nums[j];
                for (int k = j + 1; k <= i + j; k++) {
                    num += nums[k];
                }
                max = Math.max(num, max);
            }
        }
        return max;
    }

    /**
     * 特点：如果当前sum仍然大于0，那么尝试加上当前数（想找更大），否则当前sum就等于当前数
     * eg：
     * - sum=1 next=2  ——>当前sum大于0，sum=1+2=3更大了
     * - sum=1 next=-2 ——>当前sum大于0，sum=-2+1虽然小了，但是后面的sum赋值都是当前数了（简而言之，如果是负数，就找最大的负数）
     * 最后再从sum中和max中比较出最大值
     *
     * @param nums
     * @return
     */
    public static int maxSubArray1(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
