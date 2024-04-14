package Hot100;

import java.util.List;
import java.util.PriorityQueue;

public class Link {
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
     * 25. K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            n++;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode dummy = new ListNode(0, head);
        ListNode p = dummy;

        for (; n >= k; n -= k) {
            for (int i = 0; i < k; i++) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }


            ListNode nx = p.next;
            p.next.next = cur;
            p.next = pre;
            p = nx;
        }
        return dummy.next;
    }

    /**
     * 23. 合并 K 个升序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }

        ListNode head = new ListNode();
        ListNode cur = head;

        while (!pq.isEmpty()) {
            ListNode temp = pq.poll();
            if (temp.next != null) {
                pq.offer(temp.next);
            }
            cur.next = temp;
            cur = cur.next;
        }

        return head.next;
    }


    /**
     * 2. 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;

        int carry = 0;
        while (l1 != null || l2 != null) {
            if (l2 == null) {
                cur.val = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
                l1 = l1.next;
            } else if (l1 == null) {
                cur.val = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
                l2 = l2.next;
            } else {
                cur.val = (l2.val + l1.val + carry) % 10;
                carry = (l2.val + l1.val + carry) / 10;
                l1 = l1.next;
                l2=l2.next;
            }
            if (l1 != null || l2 != null) {
                cur.next = new ListNode(0);
                cur = cur.next;
            }
        }

        return res;
    }

    
}
