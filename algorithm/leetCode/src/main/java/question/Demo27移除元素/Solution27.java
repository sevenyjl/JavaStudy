package question.Demo27移除元素;

public class Solution27 {
    public static void main(String[] args) {
        toStr(new int[] {3, 2, 2, 3}, 3);//2  [2,2]
        toStr(new int[] {0, 1, 2, 2, 3, 0, 4, 2}, 2);//5, nums = [0,1,4,0,3]
    }

    public static void toStr(int[] nums, int val) {
        int removeElement = removeElement(nums, val);
        System.out.println(removeElement);
        StringBuilder stringBuffer = new StringBuilder("[");
        for (int i = 0; i < removeElement; i++) {
            stringBuffer.append(nums[i]).append(",");
        }
        System.out.println(stringBuffer.substring(0, stringBuffer.length() - 1) + "]");
    }

    /**
     * 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.9 MB,击败了68.95% 的Java用户
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        int index = 0;
        for (int num : nums) {
            if (num != val) {
                nums[index] = num;
                index++;
            }
        }
        return index;
    }
}
