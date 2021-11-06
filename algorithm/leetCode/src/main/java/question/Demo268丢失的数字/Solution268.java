package question.Demo268丢失的数字;

import java.util.Arrays;

public class Solution268 {
    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{3, 0, 1}));
        System.out.println(missingNumber(new int[]{0, 1}));
        System.out.println(missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.4 MB,击败了95.18% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int[] ints = new int[nums.length + 1];
        Arrays.fill(ints, -1);
        for (int num : nums) {
            ints[num] = num;
        }
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == -1) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 解答成功:
     * 执行耗时:5 ms,击败了34.81% 的Java用户
     * 内存消耗:38.4 MB,击败了93.56% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int missingNumber1(int[] nums) {
        Arrays.sort(nums);
        int n = 0;
        for (int num : nums) {
            if (n == num) {
                n++;
            } else {
                return n;
            }
        }
        return n;
    }
}
