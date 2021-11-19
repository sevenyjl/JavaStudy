package simulation.professional.d20211105;

import java.util.Arrays;

import cn.hutool.core.util.RandomUtil;
import random.ArraysRandom;

/**
 * @author y30016814
 * @since 2021/11/10 16:27
 * JAVA专业级科目一1105
 * http://3ms.huawei.com/km/blogs/details/11249843
 */
public class T1 {

    public static void main(String[] args) {
        int[] ropeList = ArraysRandom.createRandomInts(1000000, 10000, false);
        int len = RandomUtil.randomInt(20000, 200000);
        ropeList = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        len = 30;
        System.out.println(len);
        long start = System.currentTimeMillis();
        //do something
        System.out.println(T1_Answer.getMaxRopeLength(len, ropeList));
        System.out.printf("T1_Answer 执行耗时%s毫秒\n", System.currentTimeMillis() - start);
        System.out.println("************over*************");
        start = System.currentTimeMillis();
        //do something
        System.out.println(T1_Answer2.getMaxRopeLength(len, ropeList));
        System.out.printf("T1_Answer2 执行耗时%s毫秒\n", System.currentTimeMillis() - start);
        System.out.println("************over*************");
        start = System.currentTimeMillis();
        //do something
        System.out.println(ge(len, ropeList));
        System.out.printf("my 执行耗时%s毫秒\n", System.currentTimeMillis() - start);
        System.out.println("************over*************");
        start = System.currentTimeMillis();
        //do something
        System.out.println(T1_Answer3.getMaxRopeLength(len, ropeList));
        System.out.printf("T1_Answer3 执行耗时%s毫秒\n", System.currentTimeMillis() - start);
        System.out.println("************over*************");

    }

    /**
     * 暴力
     * 找可能的最大值，找可能的最小值
     * 最大值：sum(repoList)/len
     * eg:sum(803,732,520,110)/11=196
     * 最小值：max(repoList)/len
     * eg:max(803,732,520,110)/11=73
     *
     * @param len
     * @param ropeList
     * @return
     */
    public static int ge(int len, int[] ropeList) {
        Arrays.sort(ropeList);
        int minTemp = ropeList[ropeList.length - 1] / len;
        // 1.当分割的长度小于rope.len 就直接排序，返回第rope.len-len个
        if (len <= ropeList.length && ropeList[ropeList.length - len] > minTemp) {
            return ropeList[ropeList.length - len];
        }
        // 2. 定位可能的最大值，最小值，二分查询
        int maxTemp = Arrays.stream(ropeList).sum() / len;

        return erFen(maxTemp, minTemp, len, ropeList);
    }

    private static int erFen(int maxTemp, int minTemp, int len, int[] ropeList) {
        while (maxTemp > minTemp + 1) {
            int er = (maxTemp + minTemp) / 2;
            if (isOver(er, ropeList, len)) {
                minTemp = er;
            } else {
                maxTemp = er;
            }
        }
        return isOver(maxTemp,ropeList,len)?maxTemp:minTemp;
    }

    private static boolean isOver(int step, int[] ropeList, int len) {
        int count = 0;
        for (int j : ropeList) {
            int temp = j;
            while (temp >= step) {
                count++;
                temp -= step;
            }
            if (count >= len) {
                return true;
            }
        }
        return false;
    }
}


