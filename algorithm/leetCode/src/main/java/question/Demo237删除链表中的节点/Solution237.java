package question.Demo237删除链表中的节点;

import bean.ListNode;

/**
 * @author y30016814
 * @since 2021/11/2 15:16
 */
public class Solution237 {
    public static void main(String[] args) {
        // 4,5,1,9  5
        ListNode node = new ListNode(5, new ListNode(1, new ListNode(9)));
        ListNode listNode = new ListNode(4, node);
        deleteNode(node, listNode);
        System.out.println(listNode);
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37.8 MB,击败了58.31% 的Java用户
     *
     * @param node
     * @param listNode
     */
    public static void deleteNode(ListNode node, ListNode listNode) {
        ListNode next = node;
        ListNode pre = node;
        while (next.next != null) {
            ListNode temp = next.next;
            next.value = temp.value;
            pre = next;
            next = temp;
        }
        pre.next = null;
    }
}
