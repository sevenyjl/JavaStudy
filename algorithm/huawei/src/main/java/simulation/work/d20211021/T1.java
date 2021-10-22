package simulation.work.d20211021;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 给定一个整数 n ，查找其二进制表示中最长的连续数字0或1的个数。
 * 注：二进制表示无须在高位补 0 。
 * <p>
 * 输入
 * 一个整数 n ，取值范围 [0, 2^32) 。
 * <p>
 * 输出
 * 一个整数，表示最长连续数字的个数。
 * <p>
 * 样例
 * 输入样例1 复制
 * 3
 * 输出样例1 复制
 * 2
 * 提示样例1
 * 3的二进制表示为11，最长连续数字为 11，个数为2。
 * <p>
 * 输入样例2 复制
 * 17
 * 输出样例2 复制
 * 3
 * 提示样例2
 * 17的二进制表示为10001，最长连续数字为 000，个数为3。
 * <p>
 * 输入样例3 复制
 * 0
 * 输出样例3 复制
 * 1
 * 提示样例3
 * 0的二进制表示为0，最长连续数字的个数为1。
 * <p>
 * 提示
 */
public class T1 {
    // 待实现函数，在此函数中填入答题代码
    private static int getLongestNum(long num) {
        String string = Long.toBinaryString(num);
        String[] split0 = string.split("0");
        String[] split1 = string.split("1");
        return Math.max(Arrays.stream(split0).max(Comparator.comparingInt(String::length)).orElse("").length(),
            Arrays.stream(split1).max(Comparator.comparingInt(String::length)).orElse("").length());
    }

    /**
     * main入口由OJ平台调用
     */
    public static void main(String[] args) {
        // Scanner cin = new Scanner(System.in, StandardCharsets.UTF_8.name());
        // long num = cin.nextLong();
        // cin.close();
        // int result = getLongestNum(num);
        // System.out.println(result);
        System.out.println(getLongestNum(17));
    }
}
