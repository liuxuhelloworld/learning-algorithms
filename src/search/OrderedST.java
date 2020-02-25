package search;

public interface OrderedST<K extends Comparable<K>, V> {
    void put(K key, V val);
    V get(K key);
    int size();
    K min();
    K max();
    K floor(K key);
    K ceiling(K key);
    int rank(K key);
    K select(int k);
    Iterable<K> keys(K lo, K hi);
    void delete(K key);
    boolean contains(K key);

    default boolean isEmpty() {
        return size() == 0;
    }

    default void deleteMin() {
        delete(min());
    }

    default void deleteMax() {
        delete(max());
    };

    default int size(K lo, K hi) {
        if (lo.compareTo(hi) >= 0) {
            return 0;
        }

        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    default Iterable<K> keys() {
        return keys(min(), max());
    }
}