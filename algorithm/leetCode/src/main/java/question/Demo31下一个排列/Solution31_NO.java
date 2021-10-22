package question.Demo31下一个排列;

public class Solution31_NO {
    public static void main(String[] args) {
        toStr(new int[] {4, 3, 2, 1});//[1,4,2,3]
        // toStr(new int[] {1, 2, 3});//[1,3,2]
        // toStr(new int[] {3, 2, 1});//[1,2,3]
        // toStr(new int[] {1, 1, 5});//[1,5,1]
        // toStr(new int[] {1});//1
    }

    public static void toStr(int[] nums) {
        nextPermutation(nums);
        StringBuilder stringBuffer = new StringBuilder("[");
        for (int i = 0; i < nums.length; i++) {
            stringBuffer.append(nums[i]).append(",");
        }
        System.out.println(stringBuffer.substring(0, stringBuffer.length() - 1) + "]");
    }

    public static void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {

        }

    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
