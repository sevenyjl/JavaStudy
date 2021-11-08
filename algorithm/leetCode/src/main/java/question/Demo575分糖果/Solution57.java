package question.Demo575分糖果;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author y30016814
 * @since 2021/11/1 17:21
 */
public class Solution57 {
    public static void main(String[] args) {
        System.out.println(distributeCandies(new int[] {1, 1, 2, 2, 3, 3}));
        System.out.println(distributeCandies(new int[] {1, 1, 2, 3}));
        System.out.println(distributeCandies(new int[] {1, 1, 1, 3}));
        System.out.println(distributeCandies(new int[] {1, 1, 1, 1}));
        System.out.println(distributeCandies(new int[] {}));
    }

    /**
     * 解答成功:
     * 执行耗时:32 ms,击败了90.94% 的Java用户
     * 内存消耗:40.3 MB,击败了59.92% 的Java用户
     *
     * @param candyType
     * @return
     */
    public static int distributeCandies(int[] candyType) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        return Math.min(candyType.length / 2, set.size());
    }
}
