package org.linkedList;

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

            if(temp == null || temp.next == null){
                break;
            }

            //更新状态
            pred = left;
            left = temp;
            right = temp.next;
        }
        return nonHead.next;
    }
}
