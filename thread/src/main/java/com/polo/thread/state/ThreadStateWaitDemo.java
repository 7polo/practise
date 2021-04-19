package com.polo.thread.state;

/**
 * @author baoqianyong
 * @date 2021/3/30
 */
public class ThreadStateWaitDemo {


    public static class Resource {
        int count = 0;

        public synchronized int increase() {
            if (count % 2==0) {
                try {
                    this.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.notifyAll();
            count++;
            System.out.println(count);
            return count;
        }
    }

    public static class Thread1 extends Thread {

        private Resource resource;

        public Thread1(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while (true) {
//                try {
//                    Thread.sleep(1000);
                resource.increase();

//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Resource resource = new Resource();
        Thread1 t1 = new Thread1(resource);
        Thread1 t2 = new Thread1(resource);
        t1.start();
        t2.start();
        while (true) {
            Thread.sleep(100);
            System.out.println("t1:" + t1.getState()+" "+"t2:" + t2.getState());
        }
    }
}
