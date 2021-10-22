package question;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/split-array-largest-sum/solution/fen-ge-shu-zu-de-zui-da-zhi-by-leetcode-solution/
 * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * <p>
 * 设计一个算法使得这 m 个子数组各自和的最大值最小。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [7,2,5,10,8], m = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1,2,3,4,5], m = 2
 * 输出：9
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [1,4,4], m = 3
 * 输出：4
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 * <p>
 * Related Topics 贪心 数组 二分查找 动态规划
 * 👍 554 👎 0
 */
public class Solution410_NO {
    public static void main(String[] args) {
        System.out.println(splitArray(new int[] {7, 2, 5, 10, 8}, 2));//18
        System.out.println(splitArray(new int[] {1, 2, 3, 4, 5}, 2));//9
        System.out.println(splitArray(new int[] {1, 4, 4}, 3));//4
        System.out.println(splitArray(new int[] {2, 16, 14, 15}, 2));//29
    }

    public static int splitArray(int[] nums, int m) {
        System.out.println("我是废物");
        return -1;
    }
}
