package practise;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * http://oj.rnd.huawei.com/problems/15/details
 * 输入
 * The first line of the input contains an integer T(1≤T≤20) which means the number of test cases.
 * Then T lines follow, each line consists of two positive integers, A and B. Notice that the integers are very large,that means you should not process them by using 32-bit integer.You may assume the length of each integer will not exceed 1000.
 * 输入的第一行包含整数T(1≤T≤20)，表示测试用例的数量。然后T行紧随其后，每行由两个正整数A和B组成。请注意，整数非常大，这意味着您不应该使用32位整数来处理它们。您可以假设每个整数的长度不会超过1000。
 * 输出
 * For each test case, you should output two lines. The first line is "Case #:", # means the number of the test case. The second line is the an equation "A + B = Sum", Sum means the result of A + B.Note there are some spaces int the equation. Output a blank line between two test cases.
 * <p>
 * 样例
 * 输入样例 1 复制
 * <p>
 * 2
 * 1 2
 * 112233445566778899 998877665544332211
 */
public class Demo15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
        for (int i = 1; i <= count; i++) {
            String s = scanner.nextLine();
            String[] split = s.split(" ");
            System.out.println("Case " + i + ":");
            BigDecimal b1 = new BigDecimal(split[0]);
            BigDecimal b2 = new BigDecimal(split[1]);
            System.out.printf("%s + %s = %s\n\n", b1, b2, b1.add(b2));
        }
        scanner.close();
    }
}
