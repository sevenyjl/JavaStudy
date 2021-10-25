package simulation.newcomers.d20211025;

import java.util.Comparator;
import java.util.HashMap;

/**
 * 给你一个整数 num 。请你先求出从 1 到 num 的每个整数 的「权重」（权重是指10 进制下每一位上的数字累加值，如整数259，权重 = 2 + 5 + 9 = 16），然后把权重相等的整数放到同一个组中。
 * <p>
 * 请找出整数个数最多的组，并返回这些组的数量。
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 12
 * 输出：3
 * 解释：将 1 到 12 的每个整数计算权重后，总共有 9 个组，分别是：
 * 权重为 1 的组是 [1,10]，1 的权重 = 1, 10 的权重 = 1 + 0
 * 权重为 2 的组是 [2,11]，2 的权重 = 2, 11 的权重 = 1 + 1
 * 权重为 3 的组是 [3,12]，3 的权重 = 3, 12 的权重 = 1 + 2
 * 依此类推，所有组如下:
 * [1,10], [2,11], [3,12], [4], [5], [6], [7], [8], [9]。
 * 所有组中整数个数最多是 2，符合条件的组有 3 个，分别是[1,10], [2,11], [3,12]，因此返回 3。
 * 示例 2：
 * <p>
 * 输入：num = 1000
 * 输出：2
 * 解释：有 2 个组的成员个数是 75，并且是最多的。
 * 提示：
 * <p>
 * 1 <= num <= 10^4
 */
public class T2 {
    public static void main(String[] args) {
        System.out.println(259 % 10);
        System.out.println(getMaxGroupNum(12));
        System.out.println(getMaxGroupNum(1000));
    }

    public static int getMaxGroupNum(int num) {
        // 权重：个数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= num; i++) {
            int temp = i;
            int sum = 0;
            while (temp > 9) {
                sum += temp % 10;
                temp = temp / 10;
            }
            sum += temp;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        Integer max = map.values().stream().max(Comparator.comparingInt(o -> o)).orElse(0);
        return (int) map.values().stream().filter(s -> s >= max).count();
    }
}
