package question.Demo24两两交换链表中的节点;

import bean.ListNode;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 */
public class Solution24 {
    public static void main(String[] args) {
        System.out.println(swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))))));
        System.out.println(
            swapPairs(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))))));
        System.out.println(swapPairs(new ListNode(1)));
        System.out.println(swapPairs(null));
    }

    /**
     * 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.1 MB,击败了42.86% 的Java用户
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode result = new ListNode(0, head);
        ListNode next = result;
        while (next.next != null) {
            if (next.next.next == null) {
                break;
            }
            ListNode temp = next.next;
            next.next = temp.next;
            temp.next = next.next.next;
            next.next.next = temp;
            next = next.next.next;
        }
        return result.next;
    }
}
