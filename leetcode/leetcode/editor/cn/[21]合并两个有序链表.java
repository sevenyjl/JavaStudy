//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1920 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
//leetcode submit region end(Prohibit modification and deletion)
