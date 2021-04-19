package proxy.cglib;


/**
 * @author baoqianyong
 * @date 2021/2/13
 */
public class RealSubject implements Subject {

    @Override
    public void doAction() {
        System.out.println(this.getClass().getSimpleName()+" : doAction");
    }
}
