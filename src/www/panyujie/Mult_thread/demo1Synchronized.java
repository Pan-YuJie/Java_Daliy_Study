package www.panyujie.Mult_thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-05-06
 * Time: 19:44
 */

//class Counter{
//    // 这个变量 就是 两个线程要去自增的变量
//    public int count;
//    public void increase(){
//        count++;
//    }
//}

class CounterSyc{
    // 这个变量 就是 两个线程要去自增的变量
    public int count;
    synchronized public void increase(){ //synchronized 自动给方法上锁 持续到方法执行完
        count++;
    }
}

public class demo1Synchronized {
        private  static CounterSyc counter = new CounterSyc();

        public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(()->{
                for (int i = 0; i < 5_0000; i++) {
                    counter.increase();
                }
            });
            Thread t2 = new Thread(()->{
                for (int i = 0; i < 5_0000; i++) {
                    counter.increase();
                }
            });
            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(counter.count);
        }
}
