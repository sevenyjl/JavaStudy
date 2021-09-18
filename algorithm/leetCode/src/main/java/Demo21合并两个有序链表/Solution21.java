package Demo21合并两个有序链表;


import Demo02两数相加.ListNode;

public class Solution21 {
    public static void main(String[] args) {
//[1,1,2,3,4,4]
        System.out.println(mergeTwoLists(new ListNode(1, new ListNode(2, new ListNode(4))), new ListNode(1, new ListNode(3, new ListNode(4)))));
//[]
        System.out.println(mergeTwoLists(null, null));
        //[0]
        System.out.println(mergeTwoLists(null, new ListNode(0)));
    }

    /**
     * 解答成功:
     * 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:37.9 MB,击败了26.50% 的Java用户
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode next1 = l1;
        ListNode next2 = l2;
        ListNode result = new ListNode();
        ListNode next = result;
        while (true) {
            if (next1 == null && next2 == null) {
                break;
            } else if (next1 == null) {
                next.next = next2;
                next2 = next2.next;
            } else if (next2 == null) {
                next.next = next1;
                next1 = next1.next;
            } else {
                if (next1.val < next2.val) {
                    next.next = next1;
                    next1 = next1.next;
                } else {
                    next.next = next2;
                    next2 = next2.next;
                }
            }
            next = next.next;
        }
        return result.next;

    }
}
