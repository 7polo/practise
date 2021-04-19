import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author baoqianyong
 * @date 2021/2/19
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        // 很多 SPI 的实现都是通过 ServiceLoader 来去完成的。
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);

        // JDK 的类加载器所能寻找到的所有驱动
        Iterator<Driver> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver : " + driver.getClass() + " , loader : " + driver.getClass().getClassLoader());
        }

        System.out.println("当前线程上线文类加载器 : " + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器 : " + ServiceLoader.class.getClassLoader());
    }
}
