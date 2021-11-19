package question.Demo67二进制求和;

import java.math.BigInteger;

/**
 * @author y30016814
 * @since 2021/11/19 15:56
 */
public class Solution67 {
    public static void main(String[] args) {
        System.out.println(addBinary20211119155730("1011", "111"));
        System.out.println(addBinary20211119164224("1011", "111"));

    }

    public String addBinary(String a, String b) {
        return null;
    }

    /**
     * 利用APi 先转int再转二进制
     * todo 问：Integer.paresInt radix表示：？
     * 缺陷：
     * 如果字符串超过 3333 位，不能转化为 Integer
     * 如果字符串超过 6565 位，不能转化为 Long
     * 如果字符串超过 500000001500000001 位，不能转化为 BigInteger
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static String addBinary20211119164224(String a, String b) {
        return Integer.toBinaryString((Integer.parseInt(a, 2) + Integer.parseInt(b, 2)));
    }

    /**
     * 解答成功:
     * 执行耗时:7 ms,击败了7.35% 的Java用户
     * 内存消耗:38.7 MB,击败了5.13% 的Java用户
     *
     */
    public static String addBinary20211119164802(String a, String b) {
        return new BigInteger(a, 2).add(new BigInteger(b, 2)).toString(2);
    }

    /**
     * 直接破解
     * 待优化~
     */
    public static String addBinary20211119155730(String a, String b) {
        // 1.找最短
        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        boolean needCarry = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            int result = (a.charAt(a.length() - i - 1) - '0') + (b.charAt(b.length() - i - 1) - '0');
            if (needCarry) {
                result++;
                needCarry = false;
            }
            if (result >= 2) {
                // 进位
                result -= 2;
                needCarry = true;
            }
            sb.insert(0, result);
        }
        if (needCarry) {
            if (a.length() < b.length()) {
                // 找到最后的1
                int index = -1;
                for (int i = b.length() - 1; i >= a.length(); i--) {
                    char c = b.charAt(i);
                    if (c != '1') {
                        index = i;
                        break;
                    } else {
                        sb.insert(0, "0");
                    }
                }
                if (index == -1) {
                    sb.insert(0, "1");
                } else {
                    sb.insert(0, b.substring(0, index - 1) + "1");
                }
            } else {
                sb.insert(0, "1");
            }
        }
        return sb.toString();
    }

}
