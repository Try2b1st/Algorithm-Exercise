package org.ex;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;

    private static class Node {
        int key;
        int value;

        Node next, pre;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<Integer, Node> keyToNode = new HashMap<>();
    private final Node dump = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dump.next = dump;
        dump.pre = dump;
    }

    public void put(int key, int value) {
        Node node = keyToNode.get(key);

        if (node != null) {
            node.value = value;
            return;
        }

        Node newOne = new Node(key, value);

        keyToNode.put(key, newOne);

        if(keyToNode.size() > capacity){

        }
    }

    public int get(int key) {
        Node node = getNode(key);
        return node == null ? -1 : node.value;
    }

    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) {
            return null;
        }

        Node node = keyToNode.get(key);

        removeNode(node);
        putNode(node);

        return node;
    }

    private void putNode(Node node) {
        node.next = dump.next;
        node.pre = dump;

        node.pre.next = node;
        node.next.pre = node;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}
