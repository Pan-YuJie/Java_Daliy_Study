package www.panyujie.network.UDPdemo1;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-16
 * Time: 20:15
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端
 */
public class ServerDemo2 {
    public static void main(String[] args) throws Exception {
        //1.创建接收端对象,注册端口
        DatagramSocket socket=new DatagramSocket(6666);

        //数据包 盘子
        byte[] buffer=new byte[1024*64];//64KB
        DatagramPacket packet=new DatagramPacket(buffer, buffer.length);

        //接受 数据
        socket.receive(packet);

        //取数据
        int len=packet.getLength();
        String res=new String(buffer,0,len);
        System.out.println(res);

        String id=packet.getSocketAddress().toString();
        System.out.println("对方id是"+id);

        int port=packet.getPort();
        System.out.println("对方端口："+port);

        socket.close();
    }

}
