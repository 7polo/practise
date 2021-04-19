package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author baoqianyong
 * @date 2021/2/14
 */
public class Demo {

    public static void main(String[] args) {

        final RealSubject realSubject = new RealSubject();

        // 1. 创建Enhancer
        Enhancer enhancer = new Enhancer();

        // 2. 设置父类
        enhancer.setSuperclass(Subject.class);

        // 3. 设置 MethodInterceptor
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run..."+method.getName());
                // 代理类时可调用
                // Object result = proxy.invokeSuper(obj, args);

                // 代理接口时调用
                Object result = method.invoke(realSubject, args);
                System.out.println("after method run...");
                return result;
            }
        });
        // 4. 生成代理类
        Subject proxy = (Subject) enhancer.create();
        proxy.doAction();
    }
}
