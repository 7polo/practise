import java.util.*;

/**
 * @author baoqianyong
 * @date 2021/5/25
 */
public class Lru2<K, V> {

    private Map<K, Node<K, V>> map = new HashMap<>();

    private int cacheSize;

    private Node<K, V> first;
    private Node<K, V> last;

    public Lru2(int size) {
        this.cacheSize = size;
    }

    public void put(K k, V v) {
        if (first == null) {
            this.first = new Node(k, v);
            this.last = this.first;

            map.put(k, this.first);
            return;
        }

        if (first.k == k) {
            return;
        }
        Node<K, V> node = map.get(k);
        if (node == null) {
            node = new Node(k, v);
        }

        Node<K, V> pre = node.pre;
        Node<K, V> next = node.next;

        if (pre != null) {
            pre.next = next;
        }
        if (next != null) {
            next.pre = pre;
        }
        if (node == this.last) {
            this.last = pre;
        }

        node.pre = null;
        node.next = this.first;
        this.first.pre = node;
        this.first = node;
        map.put(k, node);

        // 把最后一个淘汰
        if (map.size() > this.cacheSize) {
            map.remove(this.last.k);
            this.last = this.last.pre;
            this.last.next = null;
        }
    }

    class Node<K, V> {

        private Node<K, V> pre = null;
        private Node<K, V> next = null;

        private K k;
        private V v;

        public Node(K k, V v, Node<K, V> pre, Node<K, V> next) {
            this.k = k;
            this.v = v;
            this.pre = pre;
            this.next = next;
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return k.equals(node.k);
        }

        @Override
        public int hashCode() {
            return Objects.hash(k);
        }
    }

    public static void main(String[] args) {
        Lru2 lru = new Lru2(3);

        lru.put("a", "a");
        lru.put("b", "b");
        lru.put("c", "c");
        lru.put("a", "a");
        lru.put("d", "d");
        System.out.println();
    }
}
