/**
 * @author baoqianyong
 * @date 2021/2/15
 */
public class Demo {
    public static void main(String[] args) {
//        String s1 = "123";
//        String s2 = "123";
//        System.out.println(s1 == s2);  // 结果： true
//        System.out.println(s1.equals(s2));   // 结果： true
//
//        String s3 = new String("123");
//        String s4 = new String("123");
//        System.out.println(s3 == s4);      // 结果： false
//        System.out.println(s3.equals(s4)); // 结果： true
//
//        System.out.println(s1 == s3);      // 结果： false
//        System.out.println(s1.equals(s3)); // 结果： true

        // 第一组
        int i1 = 127;
        Integer i2 = 127;
        Integer i3 = 127;
        System.out.println(i1 == i2);
        System.out.println(i2 == i3);

        // 第二组
        int i4 = 129;
        Integer i5 = 129;
        Integer i6 = 129;
        System.out.println(i4 == i5);
        System.out.println(i5 == i6);
    }
}
