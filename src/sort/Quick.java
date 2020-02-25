package sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return ;
        }

        int k = partition(a, lo, hi);
        sort(a, lo, k-1);
        sort(a, k+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        Comparable pivot = a[lo];

        int i = lo, j = hi + 1;
        while (true) {
            while (Util.less(a[++i], pivot)) {
                if (i == hi) {
                    break;
                }
            }
            while (Util.less(pivot, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            Util.exch(a, i, j);
        }
        Util.exch(a, lo, j);

        return j;
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        sort(a);
        assert Util.isSorted(a);
        Util.show(a);
    }
}
