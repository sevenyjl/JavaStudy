package question.Demo19删除链表的倒数第N个结点;

public class Solution19 {
    public static void main(String[] args) {
        //[1,2,3,5]
        System.out.println(removeNthFromEnd(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
        //[]
        System.out.println(removeNthFromEnd(new ListNode(1), 1));
        //[1]
        System.out.println(removeNthFromEnd(new ListNode(1, new ListNode(2)), 1));
    }

    /**
     * 解答成功: 执行耗时:0 ms,击败了100.00% 的Java用户
     * 内存消耗:36.4 MB,击败了42.09% 的Java用户
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode te = head;
        while (te != null) {
            len++;
            te = te.next;
        }
        ListNode temp = head;
        ListNode pre = null;
        int index = len - n;
        while (temp != null) {
            if (index == 0) {
                if (pre == null) {
                    return head.next;
                } else {
                    pre.next = temp.next;
                    break;
                }
            }
            index--;
            pre = temp;
            temp = temp.next;
        }
        return head;
    }
}


class ListNode {
    @Override
    public String toString() {
        if (val == 0) {
            return "";
        }
        return val + "," + (next == null ? "" : next);
    }

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

