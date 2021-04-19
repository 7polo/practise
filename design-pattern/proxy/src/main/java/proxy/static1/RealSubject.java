package proxy.static1;

/**
 * @author baoqianyong
 * @date 2021/2/13
 */
public class RealSubject implements Subject{

    public void doAction() {
        System.out.println(this.getClass().getSimpleName()+" : doAction");
    }
}
