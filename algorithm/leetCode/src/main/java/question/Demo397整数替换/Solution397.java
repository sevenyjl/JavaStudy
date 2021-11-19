package question.Demo397整数替换;

import java.util.HashMap;
import java.util.Optional;

import cn.hutool.core.util.StrUtil;

/**
 * @author y30016814
 * @since 2021/11/19 9:42
 */
public class Solution397 {
    public static void main(String[] args) {
        // for (int i = 1000; i < 10000; i++) {
        //     System.out.println("method1 " + i + "需要：" + integerReplacement20211119094313(i));
        // System.out.println("method2 " + i + "需要：" + integerReplacement20211119095043(i));
        // System.out.println("method3 " + i + "需要：" + integerReplacement20211119095440(i));
        // }
        // System.out.println(integerReplacement20211119095440(2147483647));// 超时
        System.out.println(integerReplacement20211119120316(2147483647));// 超时
        // System.out.println(integerReplacement20211119095043(6));
    }

    static HashMap<Integer, Integer> cache = new HashMap<>();

    static HashMap<Long, Long> cacheLong = new HashMap<>();

    static {
        cache.put(1, 0);
        cache.put(2, 1);
        cache.put(3, 2);
        cacheLong.put(1L, 0L);
    }

    /**
     * 解答成功:
     * 执行耗时:1 ms,击败了70.52% 的Java用户
     * 内存消耗:35.3 MB,击败了23.55% 的Java用户
     *
     * 这太恶心了，这题 换成long就可以了 md
     */
    public static long integerReplacement20211119120316(long n) {
        Long aLong = cacheLong.get(n);
        if (aLong == null) {
            aLong = 1L;
            if (n % 2 == 0) {
                aLong += integerReplacement20211119120316(n / 2L);
            } else {
                aLong += Math.min(integerReplacement20211119120316(n + 1),
                    integerReplacement20211119120316(n - 1));
            }
            cacheLong.put(n, aLong);
        }
        return aLong;
    }

    /* --------------- 简单总结：好像是int溢出导致的 死循环超时！！！！---------------*/

    /**
     * 缓存+循环（替换递归）
     * 超时~~！！！
     * 崩溃     2021/11/19/12/00/31
     */
    public static int integerReplacement20211119095440(int n) {
        int tempN = n;
        // 1. 看缓存有没有
        Integer integer = cache.get(n);
        if (integer != null) {
            return integer;
        }
        // 2. 定义一个简单缓存
        HashMap<Integer, Integer> temp = new HashMap<>();
        // 3. 定义count
        int count = 0;
        while (true) {
            if (n == 1) {
                break;
            } else if (n == 3) {
                count += 2;
                break;
            }
            // 1. 先判断奇数/偶数
            if (n % 2 == 0) {
                count++;
                n = n / 2;
            } else {
                count++;
                if ((n + 1) % 4 == 0) {
                    n++;
                } else {
                    n--;
                }
            }
            integer = cache.get(n);
            if (integer == null) {
                temp.put(n, count);
            } else {
                count += integer;
                break;
            }
        }
        int finalCount = count;
        temp.forEach((k, v) -> cache.put(k, finalCount - v));
        cache.put(tempN, count);
        return count;
    }

    /**
     * 缓存递归
     * 递归栈溢出
     */
    public static int integerReplacement20211119095043(int n) {
        Integer integer = cache.get(n);
        if (integer == null) {
            integer = 1;
            if (n % 2 == 0) {
                integer += integerReplacement20211119095043(n / 2);
            } else {
                integer += Math.min(integerReplacement20211119095043(n + 1),
                    integerReplacement20211119095043(n - 1));
            }
            cache.put(n, integer);
        }
        return integer;
    }

    /**
     * 暴力递归
     * 一定超时/递归栈溢出
     */
    public static int integerReplacement20211119094313(int n) {
        if (n == 1) {
            return 0;
        }
        int count = 1;
        if (n % 2 == 0) {
            count += integerReplacement20211119094313(n / 2);
        } else {
            count += Math.min(integerReplacement20211119094313(n + 1), integerReplacement20211119094313(n - 1));
        }
        return count;
    }
}
