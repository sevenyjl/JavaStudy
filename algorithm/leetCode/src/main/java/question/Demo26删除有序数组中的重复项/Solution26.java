package question.Demo26删除有序数组中的重复项;

public class Solution26 {
    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[] {1, 1, 2}));//2
        System.out.println(removeDuplicates(new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));//5
    }

    /**
     * 解答成功:
     * 执行耗时:1 ms,击败了98.94% 的Java用户
     * 内存消耗:39.4 MB,击败了94.71% 的Java用户
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }
}
