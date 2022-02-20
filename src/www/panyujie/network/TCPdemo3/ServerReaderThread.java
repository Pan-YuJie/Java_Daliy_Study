package www.panyujie.network.TCPdemo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-17
 * Time: 16:42
 */
public class ServerReaderThread extends Thread {

    private Socket socket;

    public ServerReaderThread() {
    }

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {

            //从管道中得到字节输入流
            InputStream is = socket.getInputStream();
            //包装成高级流 缓冲字节输入流 BufferReader
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            //按行读取消息
            String msg;
            //发一行收一行 用 if
            while((msg=br.readLine())!=null){
                System.out.println(socket.getRemoteSocketAddress()+" 说了: " +msg);
            }

        } catch (IOException e) {
            //下线逻辑
            System.out.println(socket.getRemoteSocketAddress() + "下线了");
        }
    }
}
