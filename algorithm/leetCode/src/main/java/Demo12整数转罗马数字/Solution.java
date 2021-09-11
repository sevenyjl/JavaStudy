package Demo12整数转罗马数字;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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