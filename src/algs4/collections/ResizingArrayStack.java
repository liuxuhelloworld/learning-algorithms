package algs4.collections;

import java.util.Iterator;

public class ResizingArrayStack<T> implements Iterable<T> {
    private T[] a = (T[]) new Object[1];
    private int N = 0;

    private void resize(int max) {
        T[] tmp = (T[]) new Object[max];
        for (int i = 0; i < N; i++) {
            tmp[i] = a[i];
        }
        a = tmp;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(T s) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = s;
    }

    public T pop() {
        if (N == 0) {
            return null;
        }

        T ret = a[--N];

        a[N] = null; 

        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return ret;
    }

    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public T next() {
            return a[--i];
        }

        public void remove() {}
    }
}
