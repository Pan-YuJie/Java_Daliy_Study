package www.panyujie.network.TCPdemo3;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-17
 * Time: 15:50
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 使用 多线程 每一个Socket占一个线程
 */
public class Server {
    public static void main(String[] args) {

        try {
            System.out.println("==========服务端启动成功=======");
            //1.注册端口号
            ServerSocket serverSocket=new ServerSocket(8888);

            while (true) {
                //2.建立管道，等待接受客户端连接 accept方法
                Socket socket=serverSocket.accept();

                System.out.println(socket.getRemoteSocketAddress() + "它上线了");

                //开始线程
                new Thread(new ServerReaderThread(socket)).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

