package practise;

import java.util.Scanner;

/**
 * A+B问题
 * 输入
 * Input two integers A and B, process to the end of the file. (Watch the Sample Input)
 *
 * 输出
 * For each case, output A + B in one line.(Watch the Sample Output)
 */
public class Demo01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
    }
}
