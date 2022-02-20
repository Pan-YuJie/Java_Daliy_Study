package www.panyujie.network.UDPdemo4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-16
 * Time: 20:14
 */

/*
   发送端
   组播
 */
public class ClientDemo1 {
    public static void main(String[] args) throws Exception {
        //1.创建发送端对象
        DatagramSocket socket=new DatagramSocket(7777);

        byte[] buffer="我是组播消息".getBytes();
        //客户端地址时 组播地址 224.0.0.0至239.255.255.255
        DatagramPacket packet=new DatagramPacket(buffer, buffer.length,InetAddress.getByName("224.0.1.1"),6666);

        //发送数据出去
        socket.send(packet);
        socket.close();
    }
}
