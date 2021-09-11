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
        String suffix = "";
        for (int i = 0; i < strs[0].length(); i++) {
            String temp = strs[0].substring(0, i+1);
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
//leetcode submit region end(Prohibit modification and deletion)
