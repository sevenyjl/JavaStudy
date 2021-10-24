package question.Demo38外观数列;

import java.util.HashMap;

public class Solution38 {
    public static void main(String[] args) {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(4));
    }

    static HashMap<Integer, String> cache = new HashMap<Integer, String>();

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 62.30% 的用户
     * 内存消耗： 37.8 MB , 在所有 Java 提交中击败了 32.55% 的用户
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        String start = "1";
        for (int i = 1; i < n; i++) {
            String s = cache.get(start);
            if (s == null) {
                start = outStr(start);
                cache.put(i, start);
            } else {
                return s;
            }
        }
        return start;
    }

    public static String outStr(String s) {
        Character temp = null;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (temp == null) {
                count = 1;
                temp = s.charAt(i);
            } else if (temp == s.charAt(i)) {
                count++;
            } else {
                sb.append(count).append(temp);
                temp = s.charAt(i);
                count = 1;
            }
        }
        if (temp != null) {
            sb.append(count).append(temp);
        }
        return sb.toString();
    }
}
