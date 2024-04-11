package Hot100;

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
     *
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
        ListNode dummy = new ListNode(0,head);
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
}
