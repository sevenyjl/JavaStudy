package question.Demo43字符串相乘;

import java.math.BigDecimal;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * <p>
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * <p>
 * 说明：
 * <p>
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * Related Topics 数学 字符串 模拟
 * 👍 743 👎 0
 */
public class Solution43 {
    public static void main(String[] args) {
        System.out.println(multiply("2", "3"));//6
        System.out.println(multiply("123", "456"));//56088
    }

    /**
     * 解答成功:
     * 执行耗时:13 ms,击败了37.78% 的Java用户
     * 内存消耗:38.1 MB,击败了95.79% 的Java用户
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        return new BigDecimal(num1).multiply(new BigDecimal(num2)).toString();
    }
}