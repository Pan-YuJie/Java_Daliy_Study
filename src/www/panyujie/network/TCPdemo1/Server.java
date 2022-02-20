package www.panyujie.network.TCPdemo1;

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
 */
public class Server {
    public static void main(String[] args) {

        try {
            System.out.println("==========服务端启动成功=======");
            //1.注册端口号
            ServerSocket serverSocket=new ServerSocket(8888);

            //2.建立管道，等待接受客户端连接 accept方法
            Socket socket=serverSocket.accept();

            //3.从管道中得到字节输入流
            InputStream is=socket.getInputStream();

            //包装成高级流 缓冲字节输入流 BufferReader
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            //按行读取消息
            String msg;
            //发一行收一行 用 if
            if((msg=br.readLine())!=null){
                System.out.println(socket.getRemoteSocketAddress()+" 说了: " +msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
