package practise;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 题目描述
 * Given an integer N(0 ≤ N ≤ 10000), your task is to calculate N!.
 * 给定一个整数N(0 ≤ N ≤ 10000)，您的任务是计算N!。
 * 输入
 * One N in one line.
 * 一行一个N。
 * <p>
 * 输出
 * For each N, output N! in one line.
 * 对于每个N，输出N!在一行中。
 */
public class Demo18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigDecimal bigDecimal = new BigDecimal(scanner.nextLine());
        if (bigDecimal.intValue() == 0) {
            System.out.println("1");
            return;
        }
        BigDecimal one = new BigDecimal(1);
        BigDecimal result = new BigDecimal(1);
        while (bigDecimal.intValue() != 1) {
            result = result.multiply(bigDecimal);
            bigDecimal = bigDecimal.subtract(one);
        }
        System.out.println(result);
        scanner.close();
    }
}
