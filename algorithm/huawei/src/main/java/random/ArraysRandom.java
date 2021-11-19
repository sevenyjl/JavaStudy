package random;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

public class ArraysRandom {

    public static String[] createRandomStrings(int size, int strLimit, String bastStr, boolean isPrint) {
        int randomSize = RandomUtil.randomInt(size);
        String[] strings = new String[randomSize];
        StringBuilder print = new StringBuilder("[");
        for (int i = 0; i < randomSize; i++) {
            if (StrUtil.isNotEmpty(bastStr)) {
                strings[i] = RandomUtil.randomString(bastStr, RandomUtil.randomInt(1, strLimit));
            } else {
                strings[i] = RandomUtil.randomString(RandomUtil.randomInt(1, strLimit));
            }
            if (isPrint) {
                print.append("\"").append(strings[i]).append("\"").append(", ");
            }
        }
        if (isPrint) {
            System.out.println(print.append("]").toString());
        }
        return strings;
    }

    public static int[][] createRandomIntss(int lie, int hang, List<Integer> ele) {
        int[][] ints = new int[lie][hang];
        StringBuilder stringBuilder = new StringBuilder("{");
        for (int i = 0; i < lie; i++) {
            for (int j = 0; j < hang; j++) {
                Integer integer = RandomUtil.randomEle(ele);
                ints[i][j] = integer;
                stringBuilder.append(getSevenStr(integer, 3)).append(",");
            }
            stringBuilder.append("},\n{");
        }
        System.out.println("----------create ints----------");
        System.out.println(stringBuilder);
        System.out.println("-------------------------------");
        return ints;
    }

    public static String getSevenStr(Object o, int len) {
        String s = o.toString();
        int length = s.length();
        for (int i = length; i < len; i++) {
            s = " " + s;
        }
        return s;
    }

    public static int[] createRandomInts(int size, int limit, boolean isPrint) {
        int[] ints = new int[size];
        for (int i = 0; i < size; i++) {
            ints[i] = RandomUtil.randomInt(limit);
        }
        if (isPrint) {
            System.out.println("----------create ints----------");
            System.out.println(Arrays.toString(ints).replace("[", "{").replace("]", "}"));
            System.out.println("-------------------------------");
        }
        return ints;
    }
}
