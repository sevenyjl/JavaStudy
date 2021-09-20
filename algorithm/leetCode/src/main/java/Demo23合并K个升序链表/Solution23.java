package Demo23合并K个升序链表;

import Demo02两数相加.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Solution23 {
    public static void main(String[] args) {
        //[1,1,2,3,4,4,5,6]
        System.out.println(mergeKLists(new ListNode[]{new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))}));
        System.out.println(mergeKLists(new ListNode[]{}));
        System.out.println(mergeKLists(new ListNode[]{null}));
    }

    /**
     * 拆分重排
     * 解答成功:
     * 执行耗时:13 ms,击败了31.12% 的Java用户
     * 内存消耗:39 MB,击败了99.22% 的Java用户
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        ListNode result = new ListNode();
        //拆了再合
        ArrayList<Integer> integers = new ArrayList<>();
        for (ListNode list : lists) {
            while (list != null) {
                integers.add(list.val);
                list = list.next;
            }
        }
        ListNode next = result;
        Iterator<Integer> iterator = integers.stream().sorted().iterator();
        while (iterator.hasNext()) {
            Integer v = iterator.next();
            next.next = new ListNode(v);
            next = next.next;
        }
        return result.next;
    }
}
