package simulation.professional.d20211022;

import cn.hutool.core.util.RandomUtil;
import random.ArraysRandom;

/**
 * @author y30016814
 * @since 2021/11/10 15:09
 * 20211022-Java科目一专业级
 * http://3ms.huawei.com/km/blogs/details/11142343
 */
public class T3 {
    public static void main(String[] args) {
        int[] departing = {2, 4, 0, 5, 1, 1};
        int[] returning = {5, 3, 0, 2, 1, 4};
        int minDays = 1;
        int maxDays = 5;
        departing = ArraysRandom.createRandomInts(1000000, 10000, true);
        returning = ArraysRandom.createRandomInts(1000000, 10000, true);
        minDays = RandomUtil.randomInt(500, 5000);
        maxDays = RandomUtil.randomInt(5000, 10000);
        System.out.printf("minDays=%s    maxDays=%s\n", minDays, maxDays);
        System.out.println("*******************************");
        long start = System.currentTimeMillis();
        //do something
        System.out.println(T3_Lc102203_minPrice.minPrice(departing, returning, minDays, maxDays));
        System.out.printf("T3_Lc102203_minPrice 执行耗时%s毫秒\n", System.currentTimeMillis() - start);

         start = System.currentTimeMillis();
        //do something
        System.out.println(minPrice(departing, returning, minDays, maxDays));
        System.out.printf("minPrice 执行耗时%s毫秒\n", System.currentTimeMillis() - start);
    }

    /**
     * 暴力
     *
     * @param departing
     * @param returning
     * @param minDays
     * @param maxDays
     * @return
     */
    public static int minPrice(int[] departing, int[] returning, int minDays, int maxDays) {
        int min = Integer.MAX_VALUE;
        // 循环开始起始到最后-minDays
        for (int i = 0; i < departing.length - minDays; i++) {
            // 寻找从当前日出发，最小的开销
            min = Math.min(min, departing[i] + findMinPrice(i, returning, minDays, maxDays));
        }
        return min;
    }

    private static int findMinPrice(int start, int[] returning, int minDays, int maxDays) {
        int result = returning[start + minDays];
        for (int i = start + minDays + 1; i <= start + maxDays; i++) {
            if (i < returning.length) {
                result = Math.min(result, returning[i]);
            } else {
                return result;
            }
        }
        return result;
    }

}
