package Demo12整数转罗马数字;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V +
 * II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
 * 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * <p>
 * <p>
 * 给你一个整数，将其转为罗马数字。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: num = 3
 * 输出: "III"
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: num = 4
 * 输出: "IV"
 * <p>
 * 示例 3:
 * <p>
 * <p>
 * 输入: num = 9
 * 输出: "IX"
 * <p>
 * 示例 4:
 * <p>
 * <p>
 * 输入: num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * <p>
 * <p>
 * 示例 5:
 * <p>
 * <p>
 * 输入: num = 1994
 * 输出: "MCMXCIV"
 * MDMXCIV
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= num <= 3999
 * <p>
 * Related Topics 哈希表 数学 字符串 👍 681 👎 0
 */
class Solution {
    public static void main(String[] args) {
//        System.out.println(intToRoman(1));
//        System.out.println(intToRoman(100));
//        System.out.println(intToRoman(4));
//        System.out.println(intToRoman(6));
//        System.out.println(intToRoman(9));
//        System.out.println(intToRoman(11));
//        System.out.println(intToRoman(44));
//        System.out.println(intToRoman(49));
//        System.out.println(intToRoman(59));
//        System.out.println(intToRoman(99));
//        System.out.println(intToRoman(399));
//        System.out.println(intToRoman(499));
//        System.out.println(intToRoman(3999));
//        System.out.println(intToRoman(1994));

    }
    /**
     * 暴力判断法
     * 解答成功: 执行耗时:4 ms,击败了99.97% 的Java用户
     * 内存消耗:38.4 MB,击败了17.95% 的Java用户
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        if (num < 4) {
            for (int i = 0; i < num; i++) {
                sb.append("I");
            }
        } else if (num < 5) {
            return "IV";
        } else if (num < 9) {
            sb.append("V").append(intToRoman(num - 5));
        } else if (num < 10) {
            return "IX";
        } else if (num < 40) {
            for (int i = 0; i < num / 10; i++) {
                sb.append("X");
            }
            sb.append(intToRoman(num % 10));
        } else if (num < 50) {
            sb.append("XL").append(intToRoman(num - 40));
        } else if (num < 90) {
            for (int i = 0; i < num / 50; i++) {
                sb.append("L");
            }
            sb.append(intToRoman(num % 50));
        } else if (num < 100) {
            sb.append("XC").append(intToRoman(num - 90));
        } else if (num < 400) {
            for (int i = 0; i < num / 100; i++) {
                sb.append("C");
            }
            sb.append(intToRoman(num % 100));
        } else if (num < 500) {
            sb.append("CD").append(intToRoman(num - 400));
        } else if (num < 900) {
            for (int i = 0; i < num / 500; i++) {
                sb.append("D");
            }
            sb.append(intToRoman(num % 500));
        } else if (num < 1000) {
            sb.append("CM").append(intToRoman(num - 900));
        } else if (num < 4000) {
            for (int i = 0; i < num / 1000; i++) {
                sb.append("M");
            }
            sb.append(intToRoman(num % 1000));
        } else {
            throw new RuntimeException(num + ">4000 暂时不支持大于4000");
        }
        return sb.toString();
    }
}