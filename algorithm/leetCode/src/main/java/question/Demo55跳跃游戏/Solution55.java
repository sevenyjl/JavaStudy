package question.Demo55跳跃游戏;

/**
 * @author y30016814
 * @since 2021/11/1 16:49
 */
public class Solution55 {
    public static void main(String[] args) {
        // System.out.println(canJump(new int[] {1, 0, 0, 0, 4}));
        // System.out.println(canJump(new int[] {0, 0, 0, 0, 4}));
        // System.out.println(canJump(new int[] {1, 2, 0, 0, 4}));
        // System.out.println(canJump(new int[] {1, 2, 3, 0, 4}));
        // System.out.println(canJump(new int[] {4, 0, 0, 0, 4}));
        // System.out.println(canJump(new int[] {2, 3, 1, 1, 4}));
        // System.out.println(canJump(new int[] {2, 0, 0}));
        // System.out.println(canJump(new int[] {1, 1, 0, 1}));
        // System.out.println(canJump(new int[] {0}));
        // System.out.println(canJump(new int[] {2, 0}));
        System.out.println(canJump(new int[] {3, 3, 0, 0, 0, 2, 4, 0, 1}));
        // System.out.println(canJump(new int[] {3,0,8,2,0,0,1}));
    }

    /**
     * 解答成功:
     * 执行耗时:2 ms,击败了95.57% 的Java用户
     * 内存消耗:39.2 MB,击败了95.21% 的Java用户
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            // 当移动到了i 都大于了最大的可达距离，您觉得还有机会到达后面吗？
            if (i <= rightmost) {
                // 计算能达到的最远可达距离：max(当前最大距离,i+nums[i])i+nums[i]从当前出发移动nums[i]步
                rightmost = Math.max(rightmost, i + nums[i]);
                // 如果最大可达距离都大于num.len-1了，那么久直接可达
                if (rightmost >= n - 1) {
                    return true;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 递归输出 超时~
     *
     * @param nums
     * @return
     */
    public static boolean canJump1(int[] nums) {
        if (nums.length < 2) {
            return true;
        }
        return move(nums, 0);
    }

    /**
     * 每次只走1步，如果1步走不通再走2步... 直到step=nums[index]，明细这样超时了
     * 思路扭转，贪心先尝试最大步数... 仍然超时
     *
     * @param nums
     * @param now
     * @return
     */
    private static boolean move(int[] nums, int now) {
        if (now >= nums.length - 1) {
            return true;
        }
        if (nums[now] == 0) {
            return false;
        }
        for (int i = nums[now]; i > 0; i--) {
            if (move(nums, now + i)) {
                return true;
            }
        }
        return false;
    }

}
