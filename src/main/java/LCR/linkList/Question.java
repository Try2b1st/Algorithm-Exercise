package LCR.linkList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {
    public class ListNode {
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


    /**
     * LCR 123. 图书整理 I
     *
     * @param head
     * @return
     */
    List<Integer> list = new ArrayList<>();

    public int[] reverseBookList(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ref(head);
        int[] ans = new int[list.size()];
        int count = 0;
        for (int x : list) {
            ans[count++] = x;
        }
        return ans;
    }

    public void ref(ListNode head) {
        if (head != null) {
            ref(head.next);
            list.add(head.val);
        }
    }


    /**
     * LCR 136. 删除链表的节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head;
        ListNode current = head.next;
        while (true) {
            if (current.val != val) {
                pre = current;
                current = current.next;
            } else {
                pre.next = current.next;
                break;
            }
        }
        return head;
    }


    /**
     * LCR 140. 训练计划 II
     *
     * @param head
     * @param cnt
     * @return
     */
    public ListNode trainingPlan(ListNode head, int cnt) {
        ListNode slow = head;
        ListNode fast = head;

        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }

        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
