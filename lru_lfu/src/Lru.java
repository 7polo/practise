import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author baoqianyong
 * @date 2021/5/25
 */
public class Lru<K, V> {

    private LinkedHashMap<K, V> cachedMap;

    private int cacheSize;

    public Lru(int size) {
        this.cacheSize = size;

        cachedMap = new LinkedHashMap<K, V>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > Lru.this.cacheSize;
            }
        };
    }

    public void put(K k, V v) {
        cachedMap.put(k, v);
    }

    public V get(K k) {
        return cachedMap.get(k);
    }

    public static void main(String[] args) {
        Lru lru = new Lru(3);

        lru.put("a", "a");
        lru.put("b", "b");
        lru.put("c", "c");
        lru.put("a", "a");
        lru.put("d", "d");
        System.out.println();
    }
}
