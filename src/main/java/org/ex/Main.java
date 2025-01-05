package org.ex;


import Hot100.Dp;
import Hot100.Skill;
import org.apache.commons.codec.digest.DigestUtils;
import org.ex.graphTheory.Connection;
import org.ex.graphTheory.Question;

import java.util.*;

/**
 * @author 下水道的小老鼠
 */
public class Main {
    public static void main(String[] args) {
    }

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

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = 0;

        for (ListNode cur = head; cur != null; cur = cur.next) {
            len++;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode p = dummy;
        ListNode pre = null;
        ListNode cur = head;

        while (len > k) {
            for (int i = 0; i < k; i++) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next.next;
            }

            ListNode nx = p.next;
            p.next.next = cur;
            p.next = pre;
            p = nx;
        }
        return dummy.next;
    }

}




