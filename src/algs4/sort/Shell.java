package algs4.sort;

import edu.princeton.cs.algs4.In;

public class Shell {
    public static void sort(Comparable[] a) {
        int N = a.length;

        int h = 1;
        while (h < N/3) {
            h = 3 * h + 1; // 1, 4, 13, 40, 121,...
        }

        while (h >= 1) {
            Insertion.stepSort(a, h);
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        String[] a = new In().readAllStrings();
        sort(a);
        assert Util.isSorted(a);
        Util.show(a);
    }
}
