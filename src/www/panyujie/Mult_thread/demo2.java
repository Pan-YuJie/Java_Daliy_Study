package www.panyujie.Mult_thread;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-05-07
 * Time: 20:25
 */
public class demo2 {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1=new Thread(()->{
           //进行 wait
            synchronized (lock){
                System.out.println("wait1之前");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait1之后");
            }
        });

        Thread t3=new Thread(()->{
            //进行 wait
            synchronized (lock){
                System.out.println("wait3之前");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait3之后");
            }
        });

        Thread t4=new Thread(()->{
            //进行 wait
            synchronized (lock){
                System.out.println("wait4之前");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait4之后");
            }
        });

        t1.start();
        t3.start();
        t4.start();

        Thread.sleep(3000);

        Thread t2=new Thread(()->{
            //进行 notify
            synchronized (lock) {
                System.out.println("notify之前");
                lock.notify();
                System.out.println("notify之后");
            }
        });

        t2.start();

    }

}
