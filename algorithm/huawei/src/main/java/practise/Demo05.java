package practise;

import java.util.Scanner;

/**
 * http://oj.rnd.huawei.com/problems/5/details
 * 题目描述
 * 编写程序实现将任意10进制正小数m转换成n进制的正小数，小数点后保留10位小数。
 * <p>
 * 解答要求
 * 时间限制：1000ms, 内存限制：100MB
 * 输入
 * 输入包含两个数m,n，用空格隔开。输入包含多组测试，当m,n都为0时输入结束。
 * <p>
 * Limits:
 * <p>
 * 0.0000009<m<1
 * 1<n<10
 * 输出
 * 输出10进制正小数m的n进制小数。结果保留10位小数。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 0.795 3
 * 0 0
 * 输出样例 1
 * <p>
 * 0.2101101122
 */
public class Demo05 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String next = in.next();
            if ("0 0".equals(next)) {
                break;
            }
            String[] s = next.split(" ");

        }

    }
}
