package practise;

import java.util.Scanner;

/**
 * http://oj.rnd.huawei.com/problems/8/details
 * 题目描述
 * solo发现他参加Online Judge的比赛表现很不稳定，于是他翻开历史记录，发现他在每一轮的比赛中他的排名R都能整除参赛人数N(包括solo)，于是他每次参赛都会先预测他的排名情况，以给自己更大的自信。
 * <p>
 * 解答要求
 * 时间限制：15000ms, 内存限制：100MB
 * 输入
 * 输入只有一个整数N(0<N<109)，代表参赛人数。
 * <p>
 * 输出
 * 在一行输出solo参赛可能的获得的排名数S以及由小到大输出各个排名Ri (0<i ≤ S)，用空格隔开。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 10
 * 输出样例 1
 * <p>
 * 4 1 2 5 10
 */
public class Demo08 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int i1 = scanner.nextInt();
        int n = 10;
        String s = "";
        int count = 1;
        for (int i = 1; i <= n/2; i++) {
            if (n % i == 0) {
                s += " " + i;
                count++;
            }
        }
        s += " " + n;
        System.out.println(count + s);
    }
}
