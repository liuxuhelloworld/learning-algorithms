package sort;

import edu.princeton.cs.algs4.In;

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 1; i < N; i++) {
            Comparable cur = a[i];

            int j;
            for (j = i - 1; j >= 0; j--) {
                if (Util.less(cur, a[j])) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = cur;
        }
    }

    public static void stepSort(Comparable[] a, int step) {
        int N = a.length;

        for (int i = step; i < N; i++) {
            Comparable cur = a[i];

            int j;
            for (j = i - step; j >= 0; j = j - step) {
                if (Util.less(cur, a[j])) {
                    a[j + step] = a[j];
                } else {
                    break;
                }
            }
            a[j + step] = cur;
        }
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        sort(a);
        assert Util.isSorted(a);
        Util.show(a);
    }
}
