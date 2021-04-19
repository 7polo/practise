package proxy.static1;

/**
 * @author baoqianyong
 * @date 2021/2/13
 */
public class Proxy implements Subject{

    private RealSubject realSubject = new RealSubject();

    @Override
    public void doAction() {
        System.out.println("代理开启---");
        realSubject.doAction();
        System.out.println("代理结束----");
    }


    public static void main(String[] args) {
        Subject proxy = new Proxy();
        proxy.doAction();
    }
}
