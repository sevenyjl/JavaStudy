package practise;

import java.util.Scanner;

/**
 * 题目描述
 * Solo小学一年级的时候做数学题很莫名奇妙，经常把算术表达式加上很多空格（如：7+ 31  -2），让老师很是头大，于是老师决定雇用你编写一个程序来独立计算Solo的答案。Can you help the teacher?
 * <p>
 * 解答要求
 * 时间限制：5000ms, 内存限制：100MB
 * 输入
 * 输入只有一行，即一个长度不超过100的字符串S，表示Solo的算术表达式，且S只包含数字和”+”、”-”两种运算符，以及Solo加上的一大堆空格（我们保证输入都是合法的）。
 * 注意：S中不一定包含运算符，且我们保证S中不会出现大于100000的数。
 * <p>
 * 输出
 * 输出表达式的运算结果。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 1+2 + 3 +   4
 * 输出样例 1
 * <p>
 * 10
 * 提示样例 1
 */
public class Demo27 {
    public static void main(String[] args) {
        System.out.println(calculator("7+ 31  -2"));//36
        System.out.println(calculator("1+2 + 3 +   4"));//10

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        System.out.println(calculator(str));

    }

    public static int calculator(String str) {
        char[] chars = str.toCharArray();
        int result = 0;
        StringBuilder temp = new StringBuilder();
        for (char aChar : chars) {
            if (aChar >= '0' && aChar <= '9') {
                temp.append(aChar);
            } else if (aChar == '+' || aChar == '-') {
                result += Integer.parseInt(temp.toString());
                temp = new StringBuilder();
                temp.append(aChar);
            }
        }
        if (temp.length()>0){
            result += Integer.parseInt(temp.toString());
        }
        return result;
    }
}
