package Demo14最长公共前缀;

public class Solution14 {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(longestCommonPrefix(new String[]{"a"}));
        System.out.println(longestCommonPrefix(new String[]{"", ""}));

    }

    /**
     * 暴力破解
     * 解答成功:
     * 执行耗时:2 ms,击败了24.66% 的Java用户
     * 内存消耗:36.3 MB,击败了89.64% 的Java用户
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        String suffix = "";
        for (int i = 0; i < strs[0].length(); i++) {
            String temp = strs[0].substring(0, i + 1);
            for (int j = 1; j < strs.length; j++) {
                String str = strs[j];
                if (!str.startsWith(temp)) {
                    return suffix;
                }
            }
            suffix = temp;
        }
        return suffix;
    }
}
