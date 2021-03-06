package question.Demo25K个一组翻转链表;

import bean.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 */
public class Solution25 {

    public static void main(String[] args) {
        // //[2,1,4,3,5]
        System.out.println(
            reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
        // //[3,2,1,4,5]
        System.out.println(
            reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 3));
        // //[1,2,3,4,5]
        System.out.println(
            reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 1));
        // //[1]
        System.out.println(reverseKGroup(new ListNode(1), 1));

    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        int count = 1;
        ListNode result = new ListNode(0, head);
        ListNode start = result;
        ListNode next = result.next;
        while (next != null) {
            if (count % k == 0) {
                ListNode temp = next.next;
                next.next = start.next;
                ListNode[] reverse = reverse(next);
                start.next = reverse[0];
                next = reverse[reverse.length - 1];
                reverse[reverse.length - 1].next = temp;
                start = next;
            }
            count++;
            next = next.next;
        }
        return result.next;
    }

    public static ListNode[] reverse(ListNode head) {
        ListNode[] listNodes = new ListNode[2];
        listNodes[0] = head;
        listNodes[1] = head.next;
        ListNode next = head;
        ListNode now = head;
        while (true) {
            //退出条件
            if (next.next == now && now == next) {
                now.next = null;
                break;
            }
            if (next.next == now) {
                next.next = now.next;
                now.next = next;
                now = next;
            } else {
                next = next.next;
            }
        }
        return listNodes;
    }

}
