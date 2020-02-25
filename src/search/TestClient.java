package search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestClient {
    public static void testST(ST<String, Integer> st) {
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

    private static void testOrderedSTBasics(OrderedST<String, Integer> ost) {
        System.out.println("size = " + ost.size());
        System.out.println("minkey = " + ost.min());
        System.out.println("maxkey = " + ost.max());
        System.out.println("floor of minkey = " + ost.floor(ost.min()));
        System.out.println("floor of maxkey = " + ost.floor(ost.max()));
        System.out.println("ceiling of minkey = " + ost.ceiling(ost.min()));
        System.out.println("ceiling of maxkey = " + ost.ceiling(ost.max()));
        String mid = ost.select(ost.size()/2);
        System.out.println("floor of midkey = " + ost.floor(mid));
        System.out.println("ceiling of midkey = " + ost.ceiling(mid));
        System.out.println("contains 'liuxu'? = " + ost.contains("liuxu"));
        System.out.println("rank of 'liuxu' = " + ost.rank("liuxu"));
        System.out.println("floor of 'liuxu' = " + ost.floor("liuxu"));
        System.out.println("ceiling of 'liuxu' = " + ost.ceiling("liuxu"));
        System.out.println("select the rank of 'liuxu' = " + ost.select(ost.rank("liuxu")));
        ost.delete(mid);
        ost.deleteMin();
        ost.deleteMax();
    }

    private static void testOrderedST(OrderedST<String, Integer> ost) {
        testOrderedSTBasics(ost);

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            ost.put(key, i);
        }

        for (String s : ost.keys()) {
            StdOut.println(s + " " + ost.get(s));
        }

        testOrderedSTBasics(ost);
    }

    public static void main(String[] args) {
        // OrderedST<String, Integer> ost = new ST_BinarySearch<>(16);
        OrderedST<String, Integer> ost = new ST_BST<>();
        testOrderedST(ost);
    }
}
