package question.Demo559N叉树的最大深度;

import bean.Node;

import java.util.Arrays;

public class Solution559 {
    public static void main(String[] args) {
        Node root = new Node(1,
                Arrays.asList(new Node(2), new Node(3,
                                Arrays.asList(new Node(6), new Node(7, new Node(11, new Node(14))))),
                        new Node(4, new Node(8, new Node(12))),
                        new Node(5,
                                Arrays.asList(new Node(9, new Node(13)), new Node(10)))
                ));
        System.out.println(maxDepth20211121205937(root));

    }

    public int maxDepth(Node root) {
        return 0;
    }

    /**
     * 基于暴力遍历 {@link Solution559#maxDepth20211121200711} 的优化
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:38.5 MB,击败了55.13% 的Java用户
     * @param root
     * @return
     */
    public static int maxDepth20211121205937(Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        if (root.children != null) {
            for (Node child : root.children) {
                max = Math.max(maxDepth20211121205937(child), max);
            }
        }
        return max + 1;
    }


    /**
     * 暴力遍历
     * 解答成功:
     * 执行耗时:5 ms,击败了6.62% 的Java用户
     * 内存消耗:39.4 MB,击败了5.17% 的Java用户
     *
     * @param root
     * @return
     */
    public static int maxDepth20211121200711(Node root) {
        if (root != null) {
            int asInt = 0;
            if (root.children != null) {
                asInt = root.children.stream().mapToInt(Solution559::maxDepth20211121200711).max().orElse(0);
            }
            return asInt + 1;
        }
        return 0;
    }


}
