package www.panyujie.Mult_thread;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-05-06
 * Time: 19:26
 */


class Counter{
    // 这个变量 就是 两个线程要去自增的变量
    public int count;
    public void increase(){
        count++;
    }
}

public class demo1 {

    private  static Counter counter = new Counter();

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

        // 为了保证得到的count的结果，是两个线程执行完毕后的结果
        // 我们需要使用 join 来等待 线程 执行结束
        // 这样在 main 线程中，打印的 count 的 结果，才是两个线程对 count 的 自增最终结果
        // 因为 三个线程之间关系 为 并发关系。
        // 如果不使用 join， main下城压根就不会等 t1 和 t2 自增完，直接输出count。
        // 使用 join 之后，只有 t1 和 t2 线程都结束了之后，main线程才能结束。

        t1.join();// 先执行 t1.join，然后等待 t1 结束
        t2.join();// 与 t1.join同理，再等待 t2 结束。

        // 这两个join，谁在前，谁在后，都无所谓！
        // 由于 这个线程调度 是随机的，我们也不能确定 是 t1 先结束，还是t2先结束。
        // 就算 是 t2 先结束，t2.join也要等t1结束之后，t2.join才能返回。
        // 当这两个线程结束后，main线程才会执行sout语句。

        // 在 main 线程中 打印一下两个线程自增完成之后，得到的count的结果
        System.out.println(counter.count);
    }

}
