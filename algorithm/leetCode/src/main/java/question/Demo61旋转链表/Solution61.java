package question.Demo61旋转链表;

import bean.ListNode;

import java.util.ArrayList;

public class Solution61 {
    public static void main(String[] args) {
        //2,0,1
        ListNode listNode = rotateRight(new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
//        ListNode listNode = rotateRight(new ListNode(1, new ListNode(2,
//                new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
//        ListNode listNode = rotateRight(new ListNode(1, new ListNode(2)), 0);
//        ListNode listNode = rotateRight(null, 0);
        System.out.println(listNode);

    }


    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37.8 MB,击败了42.89% 的Java用户
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        // 统计个数
        int count = 1;
        ListNode next = head;
        while (next.next != null) {
            count++;
            next = next.next;
        }
        ListNode last = next;
        // 取模
        int size = k % count;
        int i = count - size;
        if (i == 0 || i == count) {
            return head;
        }
        next = head;
        while (i != 1) {
            assert next != null;
            next = next.next;
            i--;
        }
        last.next = head;
        ListNode result = next.next;
        next.next = null;
        return result;
    }
}
