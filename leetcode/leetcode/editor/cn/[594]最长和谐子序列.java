//和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。 
//
// 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。 
//
// 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,2,2,5,2,3,7]
//输出：5
//解释：最长的和谐子序列是 [3,2,2,2,3]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,1,1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
// Related Topics 数组 哈希表 排序 👍 221 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findLHS(int[] nums) {
        return findLHS20211120100725(nums);
    }

    public static int findLHS20211120100725(int[] nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> orDefault = map.getOrDefault(num, new ArrayList<>());
            orDefault.add(i);
            map.put(num, orDefault);
        }
        // 寻找连续且最大的字符串
        Integer temp = null;
        int max = 0;
        for (Integer integer : map.keySet().stream().sorted().collect(Collectors.toList())) {
            if (temp != null && integer - temp == 1) {
                max = Math.max(map.get(temp).size() + map.get(integer).size(), max);
            }
            temp = integer;
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
