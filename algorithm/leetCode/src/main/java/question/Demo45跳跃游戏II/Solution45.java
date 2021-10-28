package question.Demo45跳跃游戏II;

/**
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示:
 * <p>
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 * <p>
 * Related Topics 贪心 数组 动态规划
 * 👍 1240 👎 0
 */
public class Solution45 {
    public static void main(String[] args) {
        // System.out.println(jump(new int[] {2, 3, 1, 1, 4}));
        // System.out.println(jump(new int[] {2, 3, 0, 1, 4}));
        // System.out.println(jump(new int[] {1, 2, 3, 4, 5, 6, 7, 8}));
        // System.out.println(jump(new int[] {0, 0, 0, 0, 0, 0, 0, 0}));
        System.out.println(jump(new int[] {4, 1, 1, 3, 1, 1, 1}));//2
        System.out.println(jump2(new int[] {4, 1, 1, 3, 1, 1, 1}));//2
    }

    /**
     * 不满足 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int min = nums.length;
        for (int i = 0; i < nums.length - 1; i++) {
            int jumpNow = i;
            int count = i;
            while (jumpNow < nums.length - 1) {
                if (nums[jumpNow] == 0) {
                    count++;
                    jumpNow++;
                } else {
                    jumpNow += nums[jumpNow];
                    count++;
                }
            }
            min = Math.min(count, min);
        }
        return min;
    }

    public static int jump2(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
