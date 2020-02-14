package algs4.search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class TestClient {
    public static void main(String[] args) {
        ST<String, Integer> st = new ST_SequentialSearch<>();
        OrderedST<String, Integer> ost = new ST_BinarySearch<>(20);

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
            ost.put(key, i);
        }

        for (String s : st.keys()) {
            StdOut.println(s + " " + st.get(s));
            StdOut.println(s + " " + ost.get(s));
        }

        for (String s : ost.keys()) {
            StdOut.println(s + " " + ost.get(s));
            StdOut.println(s + " " + st.get(s));
        }
    }
}
