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
}
