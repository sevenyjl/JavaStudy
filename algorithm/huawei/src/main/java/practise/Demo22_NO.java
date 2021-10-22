package practise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * http://oj.rnd.huawei.com/problems/22/details
 * 题目描述
 * Write a program which reads an integer n and prints the number of prime numbers which are less than or equal to n. A prime number is a natural number which has exactly two distinct natural number divisors: 1 and itself. For example, the first four prime numbers are: 2, 3, 5, 7.
 * 编写一个程序，读取整数n并打印小于或等于n的素数。素数是一个自然数，它正好有两个不同的自然数除数：1和本身。例如，前四个素数是：2、3、5、7。
 * 输入
 * Input only has an integer n (0<n<100000001) in a line
 * 输入在一行中仅有整数n (0<n<100000001)
 * <p>
 * 输出
 * Prints the number of prime numbers
 * 打印素数的数量
 */
public class Demo22_NO {

    final static ArrayList<Integer> integers = new ArrayList<>();

    public static void main(String[] args) {
        // System.out.println(3);//6  123
        // System.out.println(4);//10 1357
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (!integers.isEmpty()) {
                List<Integer> collect = integers.stream().filter(s -> s < n).collect(Collectors.toList());
                if (!collect.isEmpty()) {
                    // System.out.println(collect);
                    System.out.println(collect.size());
                    return;
                }
            }
            //是否为质数
            boolean isPrime;
            for (int i = 2; i <= n; i++) {
                isPrime = true;
                for (int j = 2; j < i; j++) {
                    //若能除尽，则不为质数
                    if ((i % j) == 0) {
                        isPrime = false;
                        break;
                    }
                }
                //如果是质数，则打印
                if (isPrime) {
                    integers.add(i);
                }
            }
            System.out.println(integers.size());
        }
        scanner.close();
    }
}
