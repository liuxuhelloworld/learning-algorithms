package algs4.collections;

import java.util.Iterator;

public class Bag<T> implements Iterable<T> {
    private Node head = null;
    private int N = 0;

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void add(T item) {
        Node oldHead = head;

        head = new Node();
        head.item = item;
        head.next = oldHead;

        N++;
    }

    public Iterator<T> iterator() {
        return new BagIterator();
    }

    private class BagIterator implements Iterator<T> {
        private Node cur = head;

        public boolean hasNext() {
            return cur != null;
        }

        public T next() {
            T item = cur.item;

            cur = cur.next;

            return item;
        }
    }
}
