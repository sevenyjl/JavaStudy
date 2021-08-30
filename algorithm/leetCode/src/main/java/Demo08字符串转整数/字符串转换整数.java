package Demo08字符串转整数;

public class 字符串转换整数 {
    public static void main(String[] args) {
//        System.out.println(myAtoi("42"));
//        System.out.println(myAtoi("-42"));
//        System.out.println(myAtoi(" -42"));
//        System.out.println(myAtoi(" -+42"));
//        System.out.println(myAtoi("+-12"));
//        System.out.println(myAtoi("4193 with words"));
//        System.out.println(myAtoi("words and 987"));
//        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
    }

    public static int myAtoi(String s) {
        char[] chars = s.trim().toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char a = chars[i];
            if (a == '-' || a == '+') {
                result.append(a);
            } else if (a >= '0' && a <= ('9')) {
                result.append(a);
            } else {
                break;
            }
        }
        double temp = 0;
        try {
            temp = Double.parseDouble(result.toString());
        } catch (Exception e) {
            return 0;
        }
        if (temp > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (temp < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) temp;
        }

    }
}
