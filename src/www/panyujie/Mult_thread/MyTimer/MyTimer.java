package www.panyujie.Mult_thread.MyTimer;


import java.util.concurrent.PriorityBlockingQueue;

class MyTimer{
    // 定时器内部要能够存放多个任务
    private PriorityBlockingQueue<MyTask> queue = new PriorityBlockingQueue<>();

    public void schedule(Runnable runnable,long after){
        MyTask task = new MyTask(runnable,after);
        queue.put(task);
        synchronized(locker){
            locker.notify();
        }
    }
    private Object locker = new Object();

    public MyTimer(){
        Thread t = new Thread(()->{
            while(true){
                try {
                    // 取出队首元素
                    MyTask task =queue.take();

                    //再比较一下看看当前这个任务时间到了没
                    long curTime = System.currentTimeMillis();

                    // 拿当前时间 和 任务执行时间进行比较
                    if(curTime < task.getTime()){
                        //时间还没到，把任务再塞回到队列中
                        queue.put(task);
                        // 指定一个等待时间
                        synchronized(locker){
                            locker.wait(task.getTime() - curTime);
                        }
                    }else{
                        // 时间到了，执行这个任务
                        task.run();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
    }

}
