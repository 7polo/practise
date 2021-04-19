package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author baoqianyong
 * @date 2021/2/13
 */
public class Demo {

    public static void main(String[] args) {
        //在硬盘上保存动态生成代理类的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        // 目标对象
        final RealSubject target = new RealSubject();

        // 调用处理
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理开启---");
                Object result = method.invoke(target, args);
                System.out.println("代理结束----");
                return result;
            }
        };
        Subject proxy = (Subject) Proxy.newProxyInstance(classLoader, target.getClass().getInterfaces(), invocationHandler);
        proxy.doAction();
    }
}
