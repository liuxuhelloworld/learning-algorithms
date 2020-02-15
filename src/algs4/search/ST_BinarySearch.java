package algs4.search;

import algs4.collections.Queue;

import java.util.Arrays;

public class ST_BinarySearch<K extends Comparable<K>, V> implements OrderedST<K, V> {
    private K[] keys;
    private V[] vals;
    private int N = 0;

    public ST_BinarySearch(int capacity) {
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Object[capacity];
    }

    private boolean searchHit(K key, int rank) {
        if (rank < N && keys[rank].compareTo(key) == 0) {
            return true;
        }
        return false;
    }

    private void resize(int newsize) {
        keys = Arrays.copyOf(keys, newsize);
        vals = Arrays.copyOf(vals, newsize);
    }

    @Override
    public void put(K key, V val) {
        assert key != null;

        int i = rank(key);
        if (searchHit(key, i)) {
            vals[i] = val;
        } else {
            if (N == keys.length) {
                resize(N * 2);
            }

            for (int j = N; j > i; j--) {
                keys[j] = keys[j-1];
                vals[j] = vals[j-1];
            }
            keys[i] = key;
            vals[i] = val;
            N++;
        }
    }

    @Override
    public V get(K key) {
        assert key != null;

        int i = rank(key);
        if (searchHit(key, i)) {
            return vals[i];
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        assert key != null;

        int i = rank(key);
        return searchHit(key, i);
    }

    @Override
    public void delete(K key) {
        assert key != null;

        int i = rank(key);
        if (searchHit(key, i)) {
            for (int j = i; j < N-1; j++) {
                keys[j] = keys[j+1];
                vals[j] = vals[j+1];
            }
            keys[N-1] = null;
            vals[N-1] = null;
            N--;

            if (N > 0 && N == keys.length/4) {
                resize(N * 2);
            }
        }
    }

    @Override
    public int size() {
        return N;
    }

    /**
     * return the number of keys less than key (>=0, <=N)
     * @param key
     * @return
     */
    @Override
    public int rank(K key) {
        assert key != null;

        int lo = 0, hi = N-1;
        while (lo <= hi) {
            int mid = (lo + hi)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public K min() {
        return keys[0];
    }

    @Override
    public K max() {
        if (N == 0) {
            return null;
        }
        return keys[N-1];
    }

    @Override
    public K floor(K key) {
        assert key != null;

        int i = rank(key);
        if (searchHit(key, i)) {
            return key;
        } else {
            if (i == 0) {
                return null;
            }
            return keys[i-1];
        }
    }

    @Override
    public K ceiling(K key) {
        assert key != null;

        int i = rank(key);
        if (i == N && N == keys.length) {
            return null;
        }
        return keys[i];
    }

    @Override
    public K select(int k) {
        return keys[k];
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        assert lo != null && hi != null;

        int i = rank(lo), j = rank(hi);

        Queue<K> q = new Queue<>();
        for (int k = i; k < j; k++) {
            q.enqueue(keys[k]);
        }

        if (searchHit(hi, j)) {
            q.enqueue(hi);
        }
        return q;
    }
}
