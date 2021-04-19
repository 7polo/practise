import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baoqianyong
 * @date 2021/2/14
 */
public class Demo {

    public static void main(String[] args) {
        int maxLength = ( 1 << 30) + 2;
        HashMap<Integer, Integer> map = new HashMap<>(maxLength);
        for (int i = 0; i <= maxLength; i++) {
            map.put(i, i);
        }
        System.out.println(maxLength);
        System.out.println(map.size());
        System.out.println(maxLength == map.size());
    }


    public static void concurrent() {
        Map<String, Object> map = new ConcurrentHashMap<>();
    }
}
