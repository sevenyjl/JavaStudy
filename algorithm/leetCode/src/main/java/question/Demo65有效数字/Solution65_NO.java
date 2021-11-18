package question.Demo65有效数字;

import java.math.BigDecimal;

public class Solution65_NO {
    public static void main(String[] args) {
        System.out.println("-----------以下应该都是true----------------");
//        System.out.println(isNumber20211118203958("2"));
//        System.out.println(isNumber20211118203958("00892"));
//        System.out.println(isNumber20211118203958("-0.1"));
//        System.out.println(isNumber20211118203958("+3.14"));
//        System.out.println(isNumber20211118203958("4."));
//        System.out.println(isNumber20211118203958("-.9"));
//        System.out.println(isNumber20211118203958("-90E3"));
//        System.out.println(isNumber20211118203958("3e+7"));
//        System.out.println(isNumber20211118203958("-123.456e789"));
//        System.out.println(isNumber20211118203958("44e016912630333"));
//        System.out.println(isNumber20211118203958(".1"));
//        System.out.println("-----------以下应该都是false----------------");
//        System.out.println(isNumber20211118203958("abc"));
//        System.out.println(isNumber20211118203958("1e"));
//        System.out.println(isNumber20211118203958("99e2.5"));
//        System.out.println(isNumber20211118203958("--6"));
//        System.out.println(isNumber20211118203958("-+6"));
        System.out.println(isNumber20211118203958("."));
    }


    /**
     * 几个规则：
     * 1、不能存在连续符合 -+ --（++ +-）？
     * 2、e不能打头，且后面必须是整数（就是不能有.）
     * 3、.不能打头，且后面必须有数字
     *
     * @param s
     * @return
     */
    public static boolean isNumber20211118203958(String s) {
        boolean haveSymbol = false;
        boolean haveE = false;
        boolean havePoint = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isNumber(c)) {
            } else if (c == '.') {
                if (haveE) {
                    return false;
                }
                if (!havePoint) {
                    if (i + 1 < s.length() && isNumber(s.charAt(i + 1))) {
                    } else {
                        return false;
                    }
                    havePoint = true;
                } else {
                    return false;
                }
            } else if (c == 'E' || c == 'e') {
                if (i != 0) {
                    if (!haveE && i != s.length() - 1) {
                        haveE = true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (c == '+' || c == '-') {
                if (!haveSymbol && i == 0) {
                    haveSymbol = true;
                } else {
                    char c1 = s.charAt(i - 1);
                    if (c1 == 'E' || c1 == 'e') {
                        continue;
                    }
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 直接javaAPi
     * 有用例不通过
     *
     * @param s
     * @return
     */
    public static boolean isNumber20211118203405(String s) {
        try {
            new BigDecimal(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
