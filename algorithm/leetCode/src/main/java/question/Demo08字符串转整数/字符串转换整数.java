package question.Demo08字符串转整数;

public class 字符串转换整数 {
    public static void main(String[] args) {
        System.out.println(myAtoi("42"));//42
        System.out.println(myAtoi("-42"));//-42
        System.out.println(myAtoi(" -42"));//-42
        System.out.println(myAtoi(" -+42"));//0
        System.out.println(myAtoi("+-12"));//0
        System.out.println(myAtoi("4193 with words"));//4193
        System.out.println(myAtoi("words and 987"));//0
        System.out.println(myAtoi("-91283472332"));//-2147483648
        System.out.println(myAtoi("2000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"));
        //2147483647
    }

    /**
     * 	执行耗时:5 ms,击败了13.13% 的Java用户
     * 	内存消耗:39.3 MB,击败了5.05% 的Java用户
     * @param s
     * @return
     */
    public static int myAtoi(String s) {
        String tempStr = s.trim();
        char[] chars = tempStr.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char a = chars[i];
            if (a == '-' && result.length() == 0) {
                result.append(a);
            } else if (a == '+'&& result.length() == 0) {
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
