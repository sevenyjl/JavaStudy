package question.Demo563二叉树的坡度;

import java.util.ArrayList;
import java.util.List;

import bean.TreeNode;

/**
 * @author y30016814
 * @since 2021/11/18 9:07
 */
public class Solution563 {

    public static void main(String[] args) {
        // TODO: 2021/11/18 数组转二叉树
        TreeNode treeNode = new TreeNode(4,
            new TreeNode(2, new TreeNode(3), new TreeNode(5)),
            new TreeNode(9, null, new TreeNode(7)));
        System.out.println(findTilt(treeNode));
    }

    static int ans = 0;

    public static int findTilt(TreeNode root) {
        dfs(root);
        return ans;
    }

    public static int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sumLeft = dfs(node.left);
        int sumRight = dfs(node.right);
        ans += Math.abs(sumLeft - sumRight);
        return sumLeft + sumRight + node.val;
    }

    /**
     * 解答成功:
     * 执行耗时:1 ms,击败了30.55% 的Java用户
     * 内存消耗:39.1 MB,击败了5.13% 的Java用户
     *
     * @param root 二叉树
     * @return 坡度
     */
    public static int findTilt1(TreeNode root) {
        return core(root).count;
    }

    public static Three core(TreeNode root) {
        Three three = new Three();
        if (root == null) {
            return three;
        }
        Three tilt;
        if (root.left != null) {
            // 加入left
            three.left += root.left.val;
            // 递归
            tilt = core(root.left);
            three.left += tilt.value;
            three.count += tilt.count;
        }
        if (root.right != null) {
            three.right += root.right.val;
            // 递归
            tilt = core(root.right);
            three.right += tilt.value;
            three.count += tilt.count;
        }
        three.sumValue();
        return three;
    }

    static class Three {
        int left = 0;
        int right = 0;
        int value = 0;
        int count = 0;

        public void sumValue() {
            this.value = this.left + this.right;
            this.count += Math.abs(this.left - this.right);
        }
    }

}
