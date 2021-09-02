package Demo11盛最多水的容器;

public class Solution {
    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));//49
        System.out.println(maxArea(new int[]{1, 1}));//1
        System.out.println(maxArea(new int[]{4, 3, 2, 1, 4}));//16
    }

    public static int maxArea(int[] height) {
        if (height==null)return 0;
        if (height.length<=3)return 0;
        int result = 0;
        int x = 0;
        int y = 0;
        for (int i = 1; i < height.length; i++) {
            int area = (i - x) * Math.min(height[i], height[x]);
            if (area > result) {
                y = height[x];
            }else {

            }
        }
        return 0;
    }
}