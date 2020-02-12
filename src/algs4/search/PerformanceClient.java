package algs4.search;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PerformanceClient {
    public static void main(String[] args) {
        int minlen = Integer.parseInt(args[0]);

        ST<String, Integer> st = new ST_SequentialSearch<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minlen) {
                continue;
            }
            if (st.contains(word)) {
                st.put(word, st.get(word)+1);
            } else {
                st.put(word, 1);
            }
        }

        String max = "";
        int maxcnt = 0;
        for (String s : st.keys()) {
            int cnt = st.get(s);
            if (cnt > maxcnt) {
                max = s;
                maxcnt = cnt;
            }
        }
        StdOut.println(max + " " + maxcnt);
    }
}