package question.Demo01两数之和;

import java.util.*;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * 提示：
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 * Related Topics 数组 哈希表
 * 👍 11828 👎 0
 */
public class Solution01 {
    public static void main(String[] args) {
        int[] ints = new int[]{2, 7, 11, 15};
        int target = 9;
        //0,1
//        System.out.println(Arrays.toString(demo01(ints, target)));
        System.out.println(Arrays.toString(demo02(ints, target)));
        ints = new int[]{3, 2, 4};
        target = 6;
        //1,2
//        System.out.println(Arrays.toString(demo01(ints, target)));
        System.out.println(Arrays.toString(demo02(ints, target)));
        ints = new int[]{3, 3};
        target = 6;
        //0,1
//        System.out.println(Arrays.toString(demo01(ints, target)));
        System.out.println(Arrays.toString(demo02(ints, target)));
    }


    /**
     * 简单粗暴些，两重循环，遍历所有情况看相加是否等于⽬标和，如果符合直接输出。
     *
     * @param ints
     * @param target
     * @return
     */
    public static int[] demo01(int[] ints, int target) {
        for (int i = 0; i < ints.length; i++) {
            int anInt = ints[i];
            for (int j = i + 1; j < ints.length; j++) {
                int anInt1 = ints[j];
                if (anInt + anInt1 == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


    /**
     * 使用hashMap来减少循环
     *
     * @param ints
     * @param target
     * @return
     */
    public static int[] demo02(int[] ints, int target) {
        HashMap<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < ints.length; i++) {
            int anInt = ints[i];
            Integer integer = temp.get(target - anInt);
            if (integer == null) {
                temp.put(anInt, i);
            } else {
                return new int[]{integer, i};
            }
        }
        return null;
    }
}
