package collections;

import java.util.Iterator;

import edu.princeton.cs.algs4.*;

public class Queue<T> implements Iterable<T> {
    private Node head = null;
    private Node tail = null;
    private int N;

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

    public void enqueue(T item) {
        Node oldTail = tail;

        tail = new Node();
        tail.item = item;
        tail.next = null;

        if (head == null) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        N++;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        T tmp = head.item;

        head = head.next;
        if (head == null) {
            tail = null;
        }
        N--;

        return tmp;
    }

    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<T> {
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

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (item.equals("-")) {
                q.enqueue(item);
            } else {
                if (!q.isEmpty()) {
                    StdOut.print(q.dequeue() + " ");
                }
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}
