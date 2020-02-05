package algs4.collections;

import edu.princeton.cs.algs4.*;

public class FixedCapacityStack<T> {
    private T[] a;
    private int N;

    public FixedCapacityStack(int cap) {
        a = (T[]) new Object[cap];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(T s) {
        a[N++] = s;
    }

    public T pop() {
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> s = new FixedCapacityStack<>(100);

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
