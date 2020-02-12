package algs4.search;

public interface ST<K, V> {
    void put(K key, V val);
    V get(K key);
    int size();
    Iterable<K> keys();

    default void delete(K key) {
        put(key, null);
    }

    default boolean contains(K key) {
        return get(key) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }
}
