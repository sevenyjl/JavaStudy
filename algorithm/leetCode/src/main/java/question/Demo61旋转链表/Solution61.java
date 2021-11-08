package question.Demo61旋转链表;

import bean.ListNode;

import java.util.ArrayList;

public class Solution61 {
    public static void main(String[] args) {
        //2,0,1
        ListNode listNode = rotateRight(new ListNode(0, new ListNode(1,
                new ListNode(2))), 4);
//        ListNode listNode = rotateRight(new ListNode(1, new ListNode(2,
//                new ListNode(3, new ListNode(4, new ListNode(5))))), 2);
//        ListNode listNode = rotateRight(new ListNode(1, new ListNode(2)), 0);
//        ListNode listNode = rotateRight(null, 0);
        System.out.println(listNode);

    }

    
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int count = 1;
        ListNode next = head;
        while (next.next != null) {
            count++;
            next = next.next;
        }
        next.next = head;
        ListNode pre = next;
        next = next.next;
        int index = k % count + 1;
        // TODO: 2021/11/7 这里有bug 
        while (true) {
            if (index == 0) {
                break;
            }
            pre = next;
            next = next.next;
            index--;
        }
        pre.next = null;
        return next;
    }
}
