package Demo15三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
}
