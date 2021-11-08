package question.Demo60排列序列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author y30016814
 * @since 2021/11/4 14:44
 */
public class Solution60 {
    public static void main(String[] args) {
        System.out.println(getPermutation2(4, 9));
    }

    /**
     * 记录数字是否使用过
     */
    private static boolean[] used;

    /**
     * 阶乘数组
     */
    private static int[] factorial;

    private static int n;
    private static int k;

    public static String getPermutation2(int n, int k) {
        Solution60.n = n;
        Solution60.k = k;
        calculateFactorial(n);
        // 查找全排列需要的布尔数组
        used = new boolean[n + 1];
        Arrays.fill(used, false);

        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }

    /**
     * @param index 在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
     * @param path
     */
    private static void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }

        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 计算阶乘数组
     *
     * @param n
     */
    private static void calculateFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    /**
     * 解答成功:
     * 执行耗时:2 ms,击败了45.75% 的Java用户
     * 内存消耗:35.8 MB,击败了63.95% 的Java用户
     */
    public static String getPermutation(int n, int k) {
        if (n == 1) {
            return "1";
        }
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            integers.add(i + 1);
        }
        return get(integers, k - 1);
    }

    private static String get(List<Integer> list, int target) {
        if (list.size() == 2) {
            return target == 1 ? "" + list.get(1) + list.get(0) : "" + list.get(0) + list.get(1);
        }
        int length = list.size();
        int num1 = getNum(length - 1);
        int hang = target / num1;
        int ge = target % num1;
        StringBuilder sb = new StringBuilder();
        Integer remove = list.remove(hang);
        sb.append(remove).append(get(list, ge));
        return sb.toString();
    }

    static HashMap<Integer, Integer> map = new HashMap<>();

    static {
        map.put(1, 1);
        map.put(2, 2);
    }

    private static int getNum(int length) {
        Integer integer = map.get(length);
        if (integer != null) {
            return integer;
        }
        int i = length * getNum(length - 1);
        map.put(length, i);
        return i;
    }
}
