package org.linkedList;

import java.util.HashMap;

/**
 * @author 下水道的小老鼠
 */
public class LinkedList {
    /**
     * 基本定义
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 203. 移除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode nonHead = new ListNode(-1, head);
        ListNode pred = nonHead;
        while (head != null) {
            if (head.val == val) {
                pred.next = head.next;
            } else {
                pred = head;
            }
            head = head.next;
        }
        return nonHead.next;
    }

    /**
     * 707. 设计链表
     */
    public static class MyLinkedList {
        //size存储链表元素的个数
        int size;
        //虚拟头结点
        ListNode head;

        //初始化
        public MyLinkedList() {
            size = 0;
            head = new ListNode(0);
        }

        //获取第index个节点的数值，注意index是从0开始的，第0个节点就是头结点
        public int get(int index) {
            if (index < 0 || size <= index) {
                return -1;
            }
            ListNode currentHead = head;
            for (int i = 0; i <= index; i++) {
                currentHead = currentHead.next;
            }
            return currentHead.val;
        }

        public void addAtHead(int val) {
            addAtIndex(0, val);
        }

        //在链表的最后插入一个节点，等价于在(末尾+1)个元素前添加
        public void addAtTail(int val) {
            addAtIndex(size, val);
        }

        // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
        // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
        // 如果 index 大于链表的长度，则返回空
        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            size++;

            ListNode pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            ListNode newNode = new ListNode(val);
            newNode.next = pred.next;
            pred.next = newNode;
        }

        //删除第index个节点
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            size--;
            if (index == 0) {
                head = head.next;
                return;
            }

            ListNode pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            pred.next = pred.next.next;
        }
    }

    /**
     * 206. 反转链表
     * 双指针法
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode temp;
        ListNode cur = head;
        ListNode pred = null;

        while (cur != null) {
            temp = cur.next;
            cur.next = pred;
            pred = cur;
            cur = temp;
        }
        return pred;
    }

    /**
     * 206. 反转链表
     * 递归算法
     *
     * @param pred
     * @param cur
     * @return
     */
    public ListNode reverse(ListNode pred, ListNode cur) {
        if (cur != null) {
            ListNode temp = cur.next;
            cur.next = pred;
            pred = cur;
            cur = temp;
            return reverse(pred, cur);
        }
        return pred;
    }

    /**
     * 24. 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode nonHead = new ListNode(-1, head);
        ListNode pred = nonHead;
        ListNode left = head;
        ListNode right = head.next;

        while (true) {
            ListNode temp = right.next;
            pred.next = right;
            right.next = left;
            left.next = temp;

            if (temp == null || temp.next == null) {
                break;
            }

            //更新状态
            pred = left;
            left = temp;
            right = temp.next;
        }
        return nonHead.next;
    }


    /**
     * 19. 删除链表的倒数第 N 个结点
     * 用反转链表方法
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndByReverse(ListNode head, int n) {
        ListNode reverseList = reverse(null, head);
        ListNode start = new ListNode(-1, reverseList);
        ListNode temp = start;
        for (int i = 1; i < n; i++) {
            start = start.next;
        }
        start.next = start.next.next;
        return reverse(null, temp.next);
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 用双指针法
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nonHead = new ListNode(-1, head);
        ListNode fast = nonHead;
        ListNode slow = nonHead;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return nonHead.next;
    }


    /**
     * 面试题 02.07. 链表相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;

        int lenA = 0, lenB = 0;

        while (curA != null) {
            lenA++;
            curA = curA.next;
        }
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }


        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                headA = headA.next;
            }
            while (headA != headB && headA != null && headB != null) {
                headA = headA.next;
                headB = headB.next;
            }
            if (headA == headB) {
                return headA;
            } else {
                return null;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                headB = headB.next;
            }
            while (headA != headB && headA != null && headB != null) {
                headA = headA.next;
                headB = headB.next;
            }
            if (headA == headB) {
                return headB;
            } else {
                return null;
            }
        }
    }

    /**
     * 142. 环形链表 II
     * 双指针法
     * 相遇时： slow指针走过的节点数为: x + y，
     * fast指针走过的节点数：x + y + n (y + z)，
     * n为fast指针在环内走了n圈才遇到slow指针，
     * （y+z）为 一圈内节点的个数A。
     * <p>
     * (x + y) * 2 = x + y + n (y + z)
     * x = (n - 1)(y+z)+z
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            //判断该链表有环
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}
