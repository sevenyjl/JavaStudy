package question.Demo15三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Solution15 {
    public static void main(String[] args) {
//        System.out.println(threeSum2(new int[]{-1, 0, 1, 2, -1, -4}));
//        System.out.println(threeSum2(new int[]{}));
//        System.out.println(threeSum2(new int[]{0}));
        System.out.println(threeSum2(new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4}));
        //[-4, -3, -2, -1, -1, 0, 0, 1, 2, 3, 4]
    }


    public static List<List<Integer>> threeSum2(int[] nums) {
        return new ArrayList<>();
    }



    /**
     * 暴力遍历
     * 超时~
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]).stream().sorted().collect(Collectors.toList()));
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }




    public List<List<Integer>> threeSum3(int[] nums) {
        //-4,-1,-1,0,1,2
        //取得数组中的最大数，并取得位数
        HashSet<List<Integer>> result = new HashSet<>();
        int[] sort = Arrays.stream(nums).sorted().toArray();
        for (int i = 0; i < sort.length - 1; i++) {
            result.addAll(towSum(i + 1, sort.length - 1, i, sort));
        }
        return new ArrayList<>(result);
    }

    public static List<List<Integer>> towSum(int left, int right, int sumIndex, int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        while (true) {
            //退出条件
            if (left >= right) {
                break;
            }
//            System.out.println(nums[left] + "+" + nums[right] + "=" + nums[sumIndex]);
            //计算
            int target = nums[left] + nums[right];
            if (target + nums[sumIndex] == 0) {
                result.add(Arrays.asList(nums[sumIndex], nums[left], nums[right]));
                left++;
            } else if (target < nums[sumIndex]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}
