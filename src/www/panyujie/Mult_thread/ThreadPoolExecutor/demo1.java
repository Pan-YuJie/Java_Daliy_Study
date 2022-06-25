package www.panyujie.Mult_thread.ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-05-08
 * Time: 16:44
 */


public class demo1 {

    public static void main(String[] args) {
        //固定数目的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //创建一个自动扩容的线程池 ， 会根据任务量进行扩容
        // Executors.newCachedThreadPool();
        // 创建一个只有一个线程的线程池
        //Executors.newSingleThreadExecutor();
        // 创建一个带有定时器功能的线程池， 类实 Timer
        //Executors.newScheduledThreadPool();

        for(int i = 0; i < 1000; i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("111");
                }
            });
        }

    }

}
