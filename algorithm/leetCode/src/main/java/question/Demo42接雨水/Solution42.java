package question.Demo42接雨水;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 * <p>
 * Related Topics 栈 数组 双指针 动态规划 单调栈
 * 👍 2781 👎 0
 */
public class Solution42 {
    public static void main(String[] args) {
        // System.out.println(trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));//6
        // System.out.println(trap(new int[] {4, 2, 0, 3, 2, 5}));//9
        System.out.println(trap(new int[] {4, 2, 3}));//1
    }

    /**
     * 暴力
     * 解答成功: 执行耗时:1 ms,击败了81.90% 的Java用户
     * 内存消耗:37.6 MB,击败了98.74% 的Java用户
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int max = 0;
        int result = 0;
        // 假设最后的墙是无限高，找出能装多少水
        for (int num : height) {
            if (num > max) {
                max = num;
            } else {
                result += max - num;
            }
        }
        int maxTemp = height[height.length - 1];
        // 当最后的墙还没有max高时，倒着遍历将水进行回退
        if (height[height.length - 1] < max) {
            for (int i = height.length - 1; i >= 0; i--) {
                int num = height[i];
                // 退水
                result -= max - num;
                // 退出条件
                if (num == max) {
                    break;
                }
                // 设置倒着遍历的最大值，进行加水操作
                if (num > maxTemp) {
                    maxTemp = num;
                } else {
                    result += maxTemp - num;
                }
            }
        }
        return result;
    }
}