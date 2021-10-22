package question.Demo04寻找两个正序数组的中位数;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。



 示例 1：


输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2


 示例 2：


输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5


 示例 3：


输入：nums1 = [0,0], nums2 = [0,0]
输出：0.00000


 示例 4：


输入：nums1 = [], nums2 = [1]
输出：1.00000


 示例 5：


输入：nums1 = [2], nums2 = []
输出：2.00000




 提示：


 nums1.length == m
 nums2.length == n
 0 <= m <= 1000
 0 <= n <= 1000
 1 <= m + n <= 2000
 -106 <= nums1[i], nums2[i] <= 106




 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 Related Topics 数组 二分查找 分治
 👍 4360 👎 0*/
public class 寻找两个正序数组的中位数 {
    public static void main(String[] args) {
        int[] int1 = new int[]{1, 3};
        int[] int2 = new int[]{2};//2
//        System.out.println(demo01(int1, int2));
        int1 = new int[]{1, 2};
        int2 = new int[]{3, 4};//2.5
        System.out.println(demo01(int1, int2));
        int1 = new int[]{0, 0};
        int2 = new int[]{0, 0};//0
        System.out.println(demo01(int1, int2));
        int1 = new int[]{};
        int2 = new int[]{1};//1
        System.out.println(demo01(int1, int2));
        int1 = new int[]{2};
        int2 = new int[]{0};
        System.out.println(demo01(int1, int2));
    }

    public static double demo01(int[] ints1, int[] ints2) {
        ArrayList<Integer> integers = new ArrayList<>();
        //排序
        for (int i : ints1) {
            integers.add(i);
        }
        for (int i : ints2) {
            integers.add(i);
        }
        List<Integer> collect = integers.stream().sorted().collect(Collectors.toList());
        int size = collect.size();
        if (collect.isEmpty()) return 0;
        if (size == 1) return collect.get(0);
        if (size % 2 == 0) {
            return (collect.get(size / 2) + collect.get((size / 2 - 1))) / 2D;
        } else {
            return collect.get((size - 1) / 2);
        }
    }
}
