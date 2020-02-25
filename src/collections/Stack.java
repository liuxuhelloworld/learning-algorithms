package collections;

import java.util.Iterator;

import edu.princeton.cs.algs4.*;

public class Stack<T> implements Iterable<T> {
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

    public void push(T item) {
        Node oldHead = head;

        head = new Node();
        head.item = item;
        head.next = oldHead;

        N++;
    }

    public T pop() {
        if (head == null) {
            return null;
        }

        T tmp = head.item;
        head = head.next;
        N--;
        return tmp;
    }

    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private Node cur = head;

        public boolean hasNext() {
            return cur != null;
        }

        public T next() {
            T item = cur.item; 

            cur = cur.next;

            return item;
        }

        public void remove() {}
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (!item.equals("-")) {
                s.push(item);
            } else {
                if (!s.isEmpty()) {
                    StdOut.print(s.pop() + " ");
                }
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
