package collections;

import edu.princeton.cs.algs4.*;

public class QueueTest {
    public static int[] readInts(String src) {
        In in = new In(src);
        
        Queue<Integer> q = new Queue<>();
        while (!in.isEmpty()) {
            q.enqueue(in.readInt());
        }

        int N = q.size();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = q.dequeue();
        }

        return a;
    }

    public static void main(String[] args) {
        int[] arr = readInts("123 456 789 0");
        for (int a : arr) {
            System.out.println(a);
        }
    }
}
