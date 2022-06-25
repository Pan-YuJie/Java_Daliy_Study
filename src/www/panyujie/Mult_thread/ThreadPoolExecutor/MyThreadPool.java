package www.panyujie.Mult_thread.ThreadPoolExecutor;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import javafx.concurrent.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-05-08
 * Time: 17:06
 */

public class MyThreadPool {

    // 1.描述一个任务 ， 直接使用 Runnable , 不需要创建额外的类
    // 2.使用一个数据结构 组织起来任务
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

    // 3.描述一个线程 , 工作线程的功能就是从任务队列中取到任务执行
    static class Worker extends Thread{
        //当前线程池中与若干个 Worker 线程 ~~ 这些 线程内部 都持有上述的任务队列
        private BlockingQueue<Runnable> queue=null;
        Worker(BlockingQueue<Runnable> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            // 就需要能够拿到上面的队列
            while (true) {
                try {
                    //循环的去拿到任务队列中的任务
                    Runnable runnable=queue.take();
                    //获取之后就可以执行
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //4. 创建一个数据结构来创建组织若干个线程
    private List<Thread> workers=new ArrayList<Thread>();

    public MyThreadPool(int n){
        //创建 n 个线程放到链表中管理
        for(int i = 0; i < n; i++){
            Worker Worker=new Worker(queue);
            Worker.start();
            workers.add(Worker);
        }
    }

    // 5.创建一个方法，让程序员放任务
    public void sumbit(Runnable runnable){
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool=new MyThreadPool(100);
        for(int i = 0; i <1_000;i++){
            myThreadPool.sumbit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hhh");
                }
            });
        }
    }

}
