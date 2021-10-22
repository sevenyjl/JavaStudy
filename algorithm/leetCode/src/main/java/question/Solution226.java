package question;

import bean.TreeNode;

/**
 * 226. 翻转二叉树
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 * <p>
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了
 */
public class Solution226 {
    public static void main(String[] args) {

        TreeNode treeNode = new TreeNode(4,
            new TreeNode(2,
                new TreeNode(1), new TreeNode(3)),
            new TreeNode(7,
                new TreeNode(6), new TreeNode(9))
        );
        TreeNode invertTree = invertTree(treeNode);
        System.out.println(invertTree);
    }

    /**
     * 递归
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:35.3 MB,击败了99.87% 的Java用户
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}



