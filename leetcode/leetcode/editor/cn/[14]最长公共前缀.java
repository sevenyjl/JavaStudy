//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1774 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        String next = "";
        if (strs != null && strs.length > 1) {
            if (strs[0] != null) {
                if (strs[0].length()!=0){
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
            if (strs[0].length()!=0){
                next += strs[0].charAt(next.length()) + "";
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
