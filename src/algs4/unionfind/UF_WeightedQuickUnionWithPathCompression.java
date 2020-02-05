package algs4.unionfind;

import edu.princeton.cs.algs4.*;

public class UF_WeightedQuickUnionWithPathCompression {
    private int[] id;
    private int[] weight;
    private int count;

    public UF_WeightedQuickUnionWithPathCompression(int N) {
        count = N;

        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }

        weight = new int[N];
        for (int i = 0; i < N; i++) {
            weight[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (weight[pRoot] < weight[qRoot]) {
            id[pRoot] = qRoot;
            weight[qRoot] += weight[pRoot];
        } else {
            id[qRoot] = pRoot;
            weight[pRoot] += weight[qRoot];
        }

        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        while (p != id[p]) {
            int q = id[p];
            id[p] = root;
            p = q;
        }

        return root;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF_WeightedQuickUnionWithPathCompression uf = new UF_WeightedQuickUnionWithPathCompression(N);

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) {
                continue;
            }
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
