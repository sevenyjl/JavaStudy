package question;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：[3,2,3]
 * 输出：3
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * <p>
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 * <p>
 * Related Topics 数组 哈希表 分治 计数 排序
 * 👍 1148 👎 0
 */
public class Solution169 {
    public static void main(String[] args) {
        System.out.println(majorityElement3(new int[] {3, 2, 3}));
        System.out.println(majorityElement3(new int[] {1,1,1,2,2,2,2,2,2,3,3,3}));
    }

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private static int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public static int majorityElement3(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    public static int majorityElement2(int[] nums) {
        //先排序
        Arrays.sort(nums);
        //直接取中间
        return nums[nums.length / 2];
    }

    /**
     * 解答成功: 执行耗时:14 ms,击败了16.29% 的Java用户
     * 内存消耗:44 MB,击败了71.27% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int avg = nums.length / 2;
        for (int num : nums) {
            Integer orDefault = map.getOrDefault(num, 0);
            orDefault++;
            map.put(num, orDefault);
        }
        Map.Entry<Integer, Integer> integerIntegerEntry = map.entrySet()
            .stream()
            .filter(s -> s.getValue() > avg)
            .max(Comparator.comparingInt(Map.Entry::getKey))
            .orElse(null);
        return integerIntegerEntry == null ? 0 : integerIntegerEntry.getKey();
    }
}
