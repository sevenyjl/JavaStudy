package question.Demo40组合总和II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution40 {
    public static void main(String[] args) {
        System.out.println(combinationSum1(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));
        //[1, 1, 2, 5, 6, 7, 10]
        //[[1,1,6],[1,2,5],[1,7],[2,6]]
        // System.out.println(combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));
        //[1, 2, 2, 2, 5]
        //[[1,2,2],[5]]
    }


    /**
     * 暴力递归
     * 解答成功:
     * 执行耗时:951 ms,击败了5.15% 的Java用户
     * 内存消耗:38.5 MB,击败了74.74% 的Java用户
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        System.out.println(Arrays.toString(candidates));
        // 定义结果集
        Set<List<Integer>> result = new HashSet<>();
        // 定义能求和到target的集合（临时集合）
        ArrayList<Integer> integers = new ArrayList<>();
        // 定义一个是被使用过
        Set<Integer> used = new HashSet<>();
        extracted(candidates, target, 0, integers, result, used);
        return new ArrayList<>(result);
    }

    private static void extracted(int[] candidates, int target, int start, List<Integer> integers,
        Set<List<Integer>> result, Set<Integer> used) {
        // 从头开始遍历（start就是从哪个数字开始）
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            // 假装这个数就是需要的，进行预计算
            int temp = target - candidate;
            // 如果大于0，那么继续递归
            if (temp > 0) {
                if (!used.contains(candidate)) {
                    // 收集到集合中
                    integers.add(candidate);
                    // 递归，找目标target=temp的值
                    extracted(candidates, temp, i + 1, integers, result, used);
                    // 回溯
                    integers.remove((Integer) candidate);
                }
            } else if (temp == 0) {
                // 收集到集合中
                integers.add(candidate);
                // 找到解并存入结果集合
                result.add(new ArrayList<>(integers));
                used.add(integers.get(0));
                // 回溯
                integers.remove((Integer) candidate);
                return;
            } else {
                return;
            }
        }
    }
}
