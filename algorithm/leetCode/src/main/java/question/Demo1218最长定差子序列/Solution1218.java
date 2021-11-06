package question.Demo1218最长定差子序列;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Solution1218 {
    public static void main(String[] args) {
        System.out.println(longestSubsequence(new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1}, -2));
    }

    /**
     * 解答成功:
     * 执行耗时:51 ms,击败了14.21% 的Java用户
     * 内存消耗:48.5 MB,击败了80.71% 的Java用户
     * @param arr
     * @param difference
     * @return
     */
    public static int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> preCountMap = new HashMap<>();
        for (int i : arr) {
            Integer integer = preCountMap.get(i - difference);
            if (integer == null) {
                preCountMap.put(i, preCountMap.getOrDefault(i, 1));
            } else {
//                preCountMap.remove(i - difference);
                integer++;
                preCountMap.put(i, Math.max(integer, preCountMap.getOrDefault(i, 0)));
            }
        }
//        System.out.println(preCountMap);
        return preCountMap.values().stream().max(Comparator.comparingInt(o -> o)).orElse(1);
    }

    /**
     * 超时
     *
     * @param arr
     * @param difference
     * @return
     */
    public static int longestSubsequence1(int[] arr, int difference) {
        int max = 0;
        boolean[] used = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                int pre = arr[i];
                int count = 1;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] - pre == difference) {
                        pre = arr[j];
                        count++;
                        used[j] = true;
                    }
                }
                max = Math.max(count, max);
            }
        }
        return max;
    }
}
