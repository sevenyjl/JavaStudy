package practise;

import java.util.Scanner;

/**
 * Solo上了大学，对数学很感兴趣，有一天他面对数分三，一个Sequence(数列)摆在了他面前，这可难住他了……
 * 序列如下：S(a,k,n)=a+(k+a)+(2k+a)+…+(nk+a)，题目要他对序列求和，但是a、k、n的取值好多，他不知如何是好，于是他决定写个程序……
 * Can you get it?
 * <p>
 * 题目数据范围：
 * <p>
 * 0<=a<=100.
 * 0<=k<=100.
 * 0<=n<=100.
 */
public class Demo24 {
    public static void main(String[] args) {
        System.out.println(sequence(1, 2, 4));
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        System.out.println(sequence(a, k, n));
    }

    public static int sequence(int a, int k, int n) {
        return (n + 1) * a + sumSum(n) * k;
    }

    public static int sumSum(int i) {
        if (i == 0) {
            return 0;
        } else {
            return i + sumSum(--i);
        }
    }
}
