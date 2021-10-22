package simulation.work.d20211019;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class T1 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode = mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode next = head;
        ListNode next1 = l1;
        ListNode next2 = l2;
        while (next1 != null && next2 != null) {
            if (next1.value > next2.value) {
                next.next = next2;
                next2 = next2.next;
            } else {
                next.next = next1;
                next1 = next1.next;
            }
            next = next.next;
        }
        if (next1 != null) {
            next.next = next1;
        }
        if (next2 != null) {
            next.next = next2;
        }
        return head.next;
    }

    static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

}

