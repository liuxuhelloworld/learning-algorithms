package algs4.search;

import algs4.collections.Queue;

public class ST_SequentialSearch<K, V> implements ST<K, V> {
    private Node first;

    private class Node {
        K key;
        V val;
        Node next;

        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(K key, V val) {
        for (Node p = first; p != null; p = p.next) {
            if (key.equals(p.key)) {
                p.val = val;
                return ;
            }
        }
        first = new Node(key, val, first);
    }

    @Override
    public V get(K key) {
        for (Node p = first; p != null; p = p.next) {
            if (key.equals(p.key)) {
                return p.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        int cnt = 0;
        for (Node p = first; p != null; p = p.next) {
            cnt++;
        }
        return cnt;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        for (Node p = first; p != null; p = p.next) {
            q.enqueue(p.key);
        }
        return q;
    }
}
