package LCR.linkList;

import java.util.*;

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

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * LCR 141. 训练计划 III
     *
     * @param head
     * @return
     */
    public ListNode trainningPlan(ListNode head) {
        return reverseLinked(null, head);
    }

    public ListNode reverseLinked(ListNode pre, ListNode current) {
        if (current == null) {
            return pre;
        }

        ListNode next = current.next;
        current.next = pre;

        return reverseLinked(current, next);
    }


    /**
     * LCR 142. 训练计划 IV
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode trainningPlan(ListNode l1, ListNode l2) {
        ListNode current1 = l1;
        ListNode current2 = l2;

        if (current1 == null && current2 == null) {
            return null;
        }
        if (current1 == null) {
            return current2;
        }
        if (current2 == null) {
            return current1;
        }

        ListNode head = new ListNode();
        ListNode ans = new ListNode();
        if (current2.val <= current1.val) {
            ans = current2;
        } else {
            ans = current1;
        }

        while (current1 != null || current2 != null) {
            if (current1 == null || current2 != null && current2.val <= current1.val) {
                head.next = current2;
                head = head.next;
                current2 = current2.next;
            } else {
                head.next = current1;
                head = head.next;
                current1 = current1.next;
            }
        }
        return ans;
    }

    public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * LCR 154. 复杂链表的复制
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node node = new Node(head.val);
        Node myHead = node;
        Map<Node, Node> map = new HashMap<>();
        map.put(head, node);
        Node x = head;
        head = head.next;

        while (head != null) {
            Node next = new Node(head.val);
            node.next = next;
            node = next;
            map.put(head, next);
            head = head.next;
        }

        while (x != null) {
            if (map.containsKey(x)) {
                map.get(x).random = map.get(x.random);
            }
            x = x.next;
        }

        return myHead;
    }
}
