package www.panyujie.network.UDPdemo4;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-16
 * Time: 20:15
 */

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 接收端
 */
public class ServerDemo2 {
    public static void main(String[] args) throws Exception {
        //1.创建接收端对象,注册端口
        MulticastSocket socket=new MulticastSocket(6666);

        //2.把当前的接收端绑定到一个组播组中，绑定对应的组播消息的IP
        socket.joinGroup(InetAddress.getByName("224.0.1.1"));

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
