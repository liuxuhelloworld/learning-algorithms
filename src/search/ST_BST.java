package search;

import collections.Queue;

public class ST_BST<K extends Comparable<K>, V> implements OrderedST<K, V> {
    private Node root;

    private class Node {
        K key;
        V val;
        Node left, right;
        int N;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.N = 1;
        }
    }

    @Override
    public void put(K key, V val) {
        if (key == null) {
            return;
        }
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
    }

    private Node put(Node p, K key, V val) {
        if (p == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = put(p.left, key, val);
        } else if (cmp > 0) {
            p.right = put(p.right, key, val);
        } else {
            p.val = val;
        }
        p.N = size(p.left) + size(p.right) + 1;
        return p;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }

        Node p = root;
        while (p != null) {
            int cmp = key.compareTo(p.key);
            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                return p.val;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            return false;
        }

        Node p = root;
        while (p != null) {
            int cmp = key.compareTo(p.key);
            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node p) {
        if (p == null) {
            return 0;
        }
        return p.N;
    }

    @Override
    public K min() {
        Node p = min(root);
        if (p == null) {
            return null;
        }
        return p.key;
    }

    private Node min(Node p) {
        if (p == null) {
            return null;
        }

        if (p.left == null) {
            return p;
        }
        return min(p.left);
    }

    @Override
    public K max() {
        Node p = max(root);
        if (p == null) {
            return null;
        }
        return p.key;
    }

    private Node max(Node p) {
        if (p == null) {
            return null;
        }

        if (p.right == null) {
            return p;
        }
        return max(p.right);
    }

    @Override
    public K floor(K key) {
        if (key == null) {
            return null;
        }

        Node p = floor(root, key);
        if (p == null) {
            return null;
        }
        return p.key;
    }

    private Node floor(Node p, K key) {
        if (p == null) {
            return null;
        }

        int cmp = key.compareTo(p.key);
        if (cmp == 0) {
            return p;
        } else if (cmp < 0) {
            return floor(p.left, key);
        } else {
            Node q = floor(p.right, key);
            if (q != null) {
                return q;
            } else {
                return p;
            }
        }
    }

    @Override
    public K ceiling(K key) {
        if (key == null) {
            return null;
        }

        Node p = ceiling(root, key);
        if (p == null) {
            return null;
        }
        return p.key;
    }

    private Node ceiling(Node p, K key) {
        if (p == null) {
            return null;
        }

        int cmp = key.compareTo(p.key);
        if (cmp == 0) {
            return p;
        } else if (cmp > 0) {
            return ceiling(p.right, key);
        } else {
            Node q = ceiling(p.left, key);
            if (q != null) {
                return q;
            } else {
                return p;
            }
        }
    }

    @Override
    public int rank(K key) {
        if (key == null) {
            return -1;
        }

        return rank(root, key);
    }

    private int rank(Node p, K key) {
        if (p == null) {
            return 0;
        }

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return rank(p.left, key);
        } else if (cmp > 0) {
            return size(p.left) + 1 + rank(p.right, key);
        } else {
            return size(p.left);
        }
    }

    @Override
    public K select(int k) {
       Node p = select(root, k);
       if (p == null) {
           return null;
       }
       return p.key;
    }

    private Node select(Node p, int k) {
        if (p == null) {
            return null;
        }
        if (k < 0 || k >= size(p)) {
            return null;
        }

        int x = size(p.left);
        if (k < x) {
            return select(p.left, k);
        } else if (k > x) {
            return select(p.right, k - x - 1);
        } else {
            return p;
        }
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node p, Queue<K> queue, K lo, K hi) {
        if (p == null) {
            return;
        }

        int cmplo = lo.compareTo(p.key);
        int cmphi = hi.compareTo(p.key);
        if (cmplo < 0) {
            keys(p.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(p.key);
        }
        if (cmphi > 0) {
            keys(p.right, queue, lo, hi);
        }
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            return;
        }
        delete(root, key);
    }

    private Node delete(Node p, K key) {
        if (p == null) {
            return null;
        }

        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = delete(p.left, key);
        } else if (cmp > 0) {
            p.right = delete(p.right, key);
        } else {
            if (p.left == null) {
                return p.right;
            }
            if (p.right == null) {
                return p.left;
            }

            Node q = p;
            p = min(q.right);
            p.right = deleteMin(q.right);
            p.left = q.left;
        }
        p.N = size(p.left) + 1 + size(p.right);
        return p;
    }

    @Override
    public void deleteMin() {
        deleteMin(root);
    }

    private Node deleteMin(Node p) {
        if (p == null) {
            return null;
        }
        if (p.left == null) {
            return p.right;
        }
        p.left = deleteMin(p.left);
        p.N = size(p.left) + 1 + size(p.right);
        return p;
    }

    @Override
    public void deleteMax() {
        deleteMax(root);
    }

    private Node deleteMax(Node p) {
        if (p == null) {
            return null;
        }
        if (p.right == null) {
            return p.left;
        }
        p.right = deleteMax(p.right);
        p.N = size(p.left) + 1 + size(p.right);
        return p;
    }
}
