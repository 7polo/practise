package con.polo.collection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baoqianyong
 * @date 2021/5/21
 */
public class ArrayListDemo {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>(1);
        for (int i = 0; i < 4; i++) {
            list.add(new Integer(i));
        }
        List<Integer> clone = (List<Integer>) list.clone();
        System.out.println();
    }

    private static <E> void printCapacity(ArrayList<E> list) {
        try {
            Field field = list.getClass().getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] elementData = (Object[]) field.get(list);
            System.out.println("Capacity:" + elementData.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
