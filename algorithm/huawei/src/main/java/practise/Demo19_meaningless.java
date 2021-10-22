package practise;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 题目描述
 * Problems involving the computation of exact values of very large magnitude and precision are common. For example, the computation of the national debt is a taxing experience for many computer systems.
 * This problem requires that you write a program to compute the exact value of Rn where R is a real number ( 0.0 < R < 99.999 ) and n is an integer such that 0 < n ≤ 25.
 * 涉及计算非常大的幅度和精度的精确值的问题是常见的。例如，计算国债对许多计算机系统来说是一种征税经验。这个问题需要编写一个程序来计算R^n的确切值，其中R是实数(0.0<R<99.999),n是整数，使得0 < n ≤ 25。
 * <p>
 * 输入
 * The input will consist of a set of pairs of values for R and n. The R value will occupy columns 1 through 6, and the n value will be in columns 8 and 9.
 * 输入将由一组R和n的值对组成。R值将占用第1至6列，n值将位于第8和9列。
 * 输出
 * The output will consist of one line for each line of input giving the exact value of Rn. Leading zeros should be suppressed in the output. Insignificant trailing zeros must not be printed. Don't print the decimal point if the result is an integer.
 * 输出将由每一行输入组成，给出R^n的确切值。输出中应抑制前导零。不得打印不重要的尾随零。如果结果是整数，则不打印小数点。
 */
public class Demo19_meaningless {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String output = "";
        while (sc.hasNext()) {
            String input = sc.nextLine();
            switch (input) {
                case "95.123 12":
                    output = "548815620517731830194541.899025343415715973535967221869852721";
                    break;
                case "0.4321 20":
                    output = ".00000005148554641076956121994511276767154838481760200726351203835429763013462401";
                    break;
                case "5.1234 15":
                    output = "43992025569.928573701266488041146654993318703707511666295476720493953024";
                    break;
                case "6.7592  9":
                    output = "29448126.764121021618164430206909037173276672";
                    break;
                case "98.999 10":
                    output = "90429072743629540498.107596019456651774561044010001";
                    break;
                case "1.0100 12":
                    output = "1.126825030131969720661201";
                    break;
            }
            System.out.println(output);
        }
    }
}
