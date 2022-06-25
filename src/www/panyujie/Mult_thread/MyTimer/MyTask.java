package www.panyujie.Mult_thread.MyTimer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-05-08
 * Time: 17:54
 */

// 创建一个类，来描述一个具体的任务
class MyTask implements Comparable<MyTask> {
    // 任务具体要做什么
    private Runnable runnable;

    // 任务具体的执行时间:保存任务要执行的毫秒级时间戳
    private long time;

    // after 是一个时间间隙，不是绝对的时间戳的值
    public MyTask(Runnable runnable, long after) {
        this.runnable = runnable;
        // 很简单，意思就是从当前时间开始， after 秒之后，这个任务被执行。
        this.time = System.currentTimeMillis() + after;
    }

    // 通过调用这里 run方法，来执行我们任务具体要做什么
    public void run() {
        runnable.run();
    }

    public long getTime() {
        return time;
    }

    @Override
    public int compareTo(MyTask o) {
        return (int) (this.time - o.time);
    }
}
