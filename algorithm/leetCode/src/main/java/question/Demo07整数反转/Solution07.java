package question.Demo07整数反转;

public class Solution07 {

    public static void main(String[] args) {
        System.out.println(demo01(-231));
        System.out.println(demo01(Integer.MIN_VALUE));
        System.out.println(demo01(Integer.MAX_VALUE));
    }

    public static int demo01(int x) {
        String s = String.valueOf(x);
        boolean flag = false;
        if (s.startsWith("-")) {
            flag = true;
            s = s.substring(1);
        }
        char[] chars = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = chars.length - 1; i >= 0; i--) {
            stringBuilder.append(chars[i]);
        }
        long l = Long.parseLong(flag ? "-" + stringBuilder : stringBuilder.toString());
        if (l > Integer.MAX_VALUE) {
            return 0;
        }

        if (l < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) l;
    }
}
