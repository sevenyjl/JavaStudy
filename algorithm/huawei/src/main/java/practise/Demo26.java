package practise;

import java.util.Scanner;

/**
 * 题目描述
 * 输入一个英文句子，句子中仅包含英文字母，数字，空格和标点符号，其中数字、空格和标点符号将句子划分成一个个独立的单词，除去句子中的数字、空格和标点符号，
 * 将句子中的每个单词的首字母大写，然后输出句子，输出时各个单词之间以一个空格隔开，句子以“.”结束
 * <p>
 * 解答要求
 * 时间限制：1000ms, 内存限制：100MB
 * 输入
 * 输入只有一行，包含一个长度都不超过100的字符串S，表示英文句子。
 * <p>
 * 输出
 * 输出只有一行，即按要求输出处理后的英文句子，若句子中不含任何单词，则输出一个“.”。
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * Who love?, Solo..
 * 输出样例 1
 * <p>
 * Who Love Solo.
 * 提示样例 1
 * <p>
 * <p>
 * 输入样例 2 复制
 * <p>
 * ----Who,love???Solo
 * 输出样例 2
 * <p>
 * Who Love Solo.
 * 提示样例 2
 * <p>
 * <p>
 * 输入样例 3 复制
 * <p>
 * 66666666664123+Who-32didn't love? Solo32..
 * 输出样例 3
 * <p>
 * Who Didn T Love Solo.
 * 提示样例 3
 */
public class Demo26 {
    public static void main(String[] args) {
        System.out.println();
        System.out.println(whoLoveSoloAgain("Who love?, Solo..\n"));//Who Love Solo.
        System.out.println(whoLoveSoloAgain("----Who,love???Solo"));//Who Love Solo.
        System.out.println(whoLoveSoloAgain("66666666664123+Who-32didn't love? Solo32.."));//Who Didn T Love Solo.

        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        System.out.println(whoLoveSoloAgain(str));
    }

    public static String whoLoveSoloAgain(String str) {
        char[] chars = str.toCharArray();
        boolean isFirst = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (char aChar : chars) {
            if ((aChar >= 'a' && aChar <= 'z') || (aChar >= 'A' && aChar <= 'Z')) {
                if (isFirst) {
                    stringBuilder.append(" ")
                        .append(Character.toUpperCase(aChar));
                } else {
                    stringBuilder.append(aChar);
                }
                isFirst = false;
            } else {
                isFirst = true;
            }
        }
        return stringBuilder.append(".").toString().trim();
    }
}
