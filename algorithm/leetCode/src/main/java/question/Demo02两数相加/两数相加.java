package question.Demo02两数相加;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * <p>
 * Related Topics 递归 链表 数学
 * 👍 6590 👎 0
 */
public class 两数相加 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
//        System.out.println(demo01(l1, l2));
        l1 = new ListNode(0);
        l2 = new ListNode(0);
//        System.out.println(demo01(l1, l2));
        l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));
        l2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        System.out.println(demo01(l1, l2));
    }

    /**
     * 自己的思路
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode demo01(ListNode l1, ListNode l2) {
        boolean flag = false;
        ListNode head = new ListNode(0);
        ListNode temp = head;
        ListNode next1 = l1;
        ListNode next2 = l2;
        while (next1 != null) {
            if (next2 == null) {
                break;
            }
            int i = next1.val + next2.val;
            if (flag) {
                i++;
                flag = false;
            }
            if (i >= 10) {
                flag = true;
                i = i - 10;
            }
            next1 = next1.next;
            next2 = next2.next;
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        temp = nextTest(flag, temp, next1);
        temp = nextTest(flag, temp, next2);
        if (flag) {
            temp.next = new ListNode(1);
        }
        return head.next;
    }


    private static ListNode nextTest(boolean flag, ListNode temp, ListNode next) {
        while (next != null) {
            int val = next.val;
            if (flag) {
                val++;
                flag = false;
            }
            if (val >= 10) {
                flag = true;
                val = val - 10;
            }
            temp.next = new ListNode(val);
            next = next.next;
            temp = temp.next;
        }
        return temp;
    }

}

