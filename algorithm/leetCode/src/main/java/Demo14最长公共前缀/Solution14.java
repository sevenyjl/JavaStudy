package Demo14最长公共前缀;

public class Solution14 {
    public static void main(String[] args) {
//        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
//        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
//        System.out.println(longestCommonPrefix(new String[]{"a"}));
        System.out.println(longestCommonPrefix(new String[]{"", ""}));

    }

    public static String longestCommonPrefix(String[] strs) {
        String suffix = "";
        String next = "";
        if (strs != null && strs.length > 1) {
            if (strs[0] != null) {
                if (strs[0].length() != 0) {
                    next = strs[0].charAt(0) + "";
                }
            }
        } else if (strs.length == 1) {
            return strs[0];
        } else {
            return "";
        }
        //判断是否为空
        int count = strs.length;
        for (String str : strs) {
            if (str == null || "".equals(str)) {
                count--;
            }
        }
        if (count == 0) {
            return "";
        }
        while (true) {
            //退出条件：任意一个前缀不符合
            for (String str : strs) {
                if (!str.startsWith(next)) {
                    return suffix;
                }
            }
            suffix = next;
            if (strs[0].length() != 0) {
                next += strs[0].charAt(next.length()) + "";
            }
        }
    }
}
