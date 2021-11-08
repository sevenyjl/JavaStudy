package simulation.work.d20211030;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * http://3ms.huawei.com/km/blogs/details/7725323?l=zh-cn
 *
 * @author y30016814
 * @since 2021/10/30 15:58
 */
public class T1 {
    public static void main(String[] args) {
        System.out.println(
            findNumber2(new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 5, 7, 9}, new int[] {1, 3, 4, 5, 8}));
    }

    /**
     * hash
     * 时间复杂度O(n)
     *
     * @param array1
     * @param array2
     * @param array3
     * @return
     */
    public static List<Integer> findNumber2(int[] array1, int[] array2, int[] array3) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : array1) {
            map.put(i, 1);
        }
        for (int i : array2) {
            Integer orDefault = map.getOrDefault(i, 0);
            orDefault++;
            map.put(i, orDefault);
        }
        for (int i : array3) {
            Integer orDefault = map.getOrDefault(i, 0);
            orDefault++;
            map.put(i, orDefault);
        }
        return map.entrySet()
            .stream()
            .filter(integerIntegerEntry -> integerIntegerEntry.getValue() == 3)
            .map(Map.Entry::getKey)
            .sorted()
            .collect(
                Collectors.toList());
    }

    /**
     * 暴力破解
     * 时间复杂度O(n^3)
     *
     * @param array1
     * @param array2
     * @param array3
     * @return
     */
    public static List<Integer> findNumber(int[] array1, int[] array2, int[] array3) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i : array1) {
            for (int i1 : array2) {
                for (int i2 : array3) {
                    if (i == i1 && i == i2) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }
}
