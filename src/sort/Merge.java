package sort;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Merge {
    private static Comparable[] copy;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            copy[k] = a[k];
        }

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = copy[j++];
            } else if (j > hi) {
                a[k] = copy[i++];
            } else if (Util.less(copy[j], copy[i])) {
                a[k] = copy[j++];
            } else {
                a[k] = copy[i++];
            }
        }
    }

    public static void sort(Comparable[] a) {
        copy = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }

    public static void bottomUpSort(Comparable[] a) {
        int N = a.length;

        copy = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz*2) {
            for (int i = 0; i < N-sz; i += sz*2) {
                merge(a, i, i+sz-1, Math.min(i+sz-1+sz, N-1));
            }
        }
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        sort(a);
        assert Util.isSorted(a);
        Util.show(a);

        List<String> alist = Arrays.asList(a);
        Collections.shuffle(alist);
        a = alist.toArray(new String[0]);
        Util.show(a);
        bottomUpSort(a);
        assert Util.isSorted(a);
    }
 }
