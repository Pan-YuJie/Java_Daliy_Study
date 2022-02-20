package www.panyujie.network.TCPdemo4;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-17
 * Time: 15:50
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 服务端
 * 使用 线程池
 */
public class Server {

    //建立一个静态的线程池
    private static ExecutorService pool =new ThreadPoolExecutor(3,4, 5,TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2),new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        try {
            System.out.println("==========服务端启动成功=======");
            //1.注册端口号
            ServerSocket serverSocket=new ServerSocket(8888);


            while (true) {
                //2.建立管道，等待接受客户端连接 accept方法
                Socket socket=serverSocket.accept();
                System.out.println(socket.getRemoteSocketAddress() + "它上线了");

                //创建任务
                Runnable runnable=new ServerReaderRunnable(socket);
                //扔给 线程池
                pool.execute(runnable);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

