package question.Demo39组合总和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution39 {
    public static void main(String[] args) {
        System.out.println(combinationSum(new int[] {2, 3, 6, 7}, 7));
        System.out.println(combinationSum(new int[] {2, 3, 5}, 8));
        System.out.println(combinationSum(new int[] {2}, 1));
    }

    /**
     * 暴力递归遍历
     * 解答成功:
     * 执行耗时:2 ms,击败了99.68% 的Java用户
     * 内存消耗:38.6 MB,击败了61.73% 的Java用户
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 排序
        Arrays.sort(candidates);
        // 定义结果集
        ArrayList<List<Integer>> result = new ArrayList<>();
        // 定义能求和到target的集合（临时集合）
        ArrayList<Integer> integers = new ArrayList<>();
        extracted(candidates, target, 0, integers, result);
        return result;
    }

    private static void extracted(int[] candidates, int target, int start, List<Integer> integers,
        List<List<Integer>> result) {
        // 从头开始遍历（start就是从哪个数字开始）
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            // 假装这个数就是需要的，进行预计算
            int temp = target - candidate;
            // 如果大于0，那么继续递归
            if (temp > 0) {
                // 收集到集合中
                integers.add(candidate);
                // 递归，找目标target=temp的值
                extracted(candidates, temp, i, integers, result);
                // 回溯
                integers.remove((Integer) candidate);
            } else if (temp == 0) {
                // 收集到集合中
                integers.add(candidate);
                // 找到解并存入结果集合
                result.add(new ArrayList<>(integers));
                // 回溯
                integers.remove((Integer) candidate);
                return;
            } else {
                return;
            }
        }
    }

    /**
     * 解答成功:
     * 执行耗时:2 ms,击败了99.68% 的Java用户
     * 内存消耗:38.4 MB,击败了88.06% 的Java用户
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    /**
     * @param candidates 基数
     * @param target 目标和
     * @param ans 结果集
     * @param combine 已经选择的数
     * @param idx 当前数的下标
     */
    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 从index开始找
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
