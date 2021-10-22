package question.Demo13罗马数字转整数;


public class Solution {

    public static void main(String[] args) {
//        System.out.println(romanToInt("III"));//3
        System.out.println(romanToInt("IV"));//4
        System.out.println(romanToInt("IX"));//9
        System.out.println(romanToInt("LVIII"));//58
        System.out.println(romanToInt("MCMXCIV"));//1994
    }

    /**
     * 解答成功:
     * 执行耗时:4 ms,击败了99.56% 的Java用户
     * 内存消耗:38.5 MB,击败了80.09% 的Java用户
     * 待优化：重复代码优化点
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == 'M') {
                count += 1000;
            } else if (aChar == 'D') {
                count += 500;
            } else if (aChar == 'C') {
                if (i != chars.length - 1 && chars[i + 1] == 'M') {
                    count += 900;
                    i++;
                } else if (i != chars.length - 1 && chars[i + 1] == 'D') {
                    count += 400;
                    i++;
                } else {
                    count += 100;
                }
            } else if (aChar == 'L') {
                count += 50;
            } else if (aChar == 'X') {
                if (i != chars.length - 1 && chars[i + 1] == 'C') {
                    count += 90;
                    i++;
                } else if (i != chars.length - 1 && chars[i + 1] == 'L') {
                    count += 40;
                    i++;
                } else {
                    count += 10;
                }
            } else if (aChar == 'V') {
                count += 5;
            } else if (aChar == 'I') {
                if (i != chars.length - 1 && chars[i + 1] == 'X') {
                    count += 9;
                    i++;
                } else if (i != chars.length - 1 && chars[i + 1] == 'V') {
                    count += 4;
                    i++;
                } else {
                    count += 1;
                }
            } else {
                throw new RuntimeException("不支持字符串：" + aChar);
            }
        }
        return count;
    }
}
