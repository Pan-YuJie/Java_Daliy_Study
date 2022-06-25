package www.panyujie.Mult_thread.MyBlockingQueue;

class MyBlockingQueue{
    // 保存数据的本体
    private int[] data = new int[1000];
    // 有效元素个数
    private int usedSize;
    // 队头下标位置
    private int head;
    // 队尾下标位置
    private int rear;

    private Object locker = new Object();// 专门的锁对象

    // 入队列
    public void put(int value) throws InterruptedException {
        synchronized(locker){
            if(usedSize == this.data.length){
                // 如果队列满了，暂时先返回。
                //return;
                locker.wait();
            }
            data[rear++] = value;
            //处理 rear 到达数组末尾的情况。
            if(rear >= data.length){
                rear = 0;
            }
            usedSize++;// 入队成功，元素个数加一。
            locker.notify();
        }
    }
    // 出队列
    public Integer take() throws InterruptedException {
        synchronized(locker){
            if(usedSize == 0){
                // 如果队列为空，就返回一个 非法值
                // return null;
                locker.wait();
            }
            int tmp = data[head];
            head++;
            if(head == data.length){
                head = 0;
            }
            usedSize--;
            // 在 take成功之后，唤醒put中的等待。
            locker.notify();
            return tmp;
        }
    }

    public static class Test22 {
        private  static MyBlockingQueue queue = new MyBlockingQueue();

        public static void main(String[] args) {
            // 实现一个 生产者消费者模型
            Thread producer = new Thread(()->{
                int num = 0;
                while (true){
                    try {
                        System.out.println("生产了" + num);
                        queue.put(num);
                        num++;
    //                   Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            producer.start();

            Thread customer = new Thread(()->{
                while (true){
                    try {
                        int num = queue.take();
                        System.out.println("消费了"+num);
                        //Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            customer.start();
        }
    }
}

