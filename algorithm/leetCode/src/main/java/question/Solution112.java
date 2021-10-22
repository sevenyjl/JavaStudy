package question;

import bean.TreeNode;

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
 * targetSum 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * <p>
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class Solution112 {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5,
            new TreeNode(4,
                new TreeNode(11, new TreeNode(7), new TreeNode(2)), null),
            new TreeNode(8,
                new TreeNode(13), new TreeNode(4, new TreeNode(1), null))
        );
        System.out.println(hasPathSum(treeNode, 22));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.2 MB,击败了71.53% 的Java用户
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum = targetSum - root.val;
        if (targetSum == 0 && root.left == null && root.right == null) {
            return true;
        }
        boolean leftB = hasPathSum(root.left, targetSum);
        boolean rightB = hasPathSum(root.right, targetSum);
        if (!leftB && !rightB) {
            return false;
        }
        return true;
    }
}
