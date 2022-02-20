package www.panyujie.network.TCPdemo3;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-17
 * Time: 15:49
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args)  {

        try {
            System.out.println("==========客户端启动成功=======");
            //1.创建Socket通信管道请求与服务器连接
            //Socket(InetAddress address, int port)
            //创建流套接字并将其连接到指定IP地址的指定端口号。
            //服务器是本所以写本机IP地址 服务器端口自己注册
            Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress().toString(),8888);

            //2.从Socket得到一个字节输出流发送数据
            OutputStream os=socket.getOutputStream();

            //3.使用高级的打印流
            PrintStream ps=new PrintStream(os);

            Scanner sc=new Scanner(System.in);

            //4.发送消息
            while (true) {
                System.out.println("请说：");
                String msg=sc.nextLine();
                ps.println(msg);
                ps.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
