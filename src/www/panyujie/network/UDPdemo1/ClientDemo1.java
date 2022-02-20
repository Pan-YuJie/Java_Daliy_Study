package www.panyujie.network.UDPdemo1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-16
 * Time: 20:14
 */

/*
   发送端
 */
public class ClientDemo1 {
    public static void main(String[] args) throws Exception {

        //1.创建发送端对象 ; 发送端 默认端口号
        DatagramSocket socket=new DatagramSocket(7777);

        //2.创建一个数据包封装数据
        //    public DatagramPacket(byte buf[], int length, netAddress address, int port);
        //参数一：封装要发送的数据（韭菜）
        //参数二：发送数据大小
        //参数三：服务端的IP地址
        //参数四：服务端的端口
        byte[] buffer="我是你爸爸".getBytes();
        DatagramPacket packet=new DatagramPacket(buffer, buffer.length,InetAddress.getLocalHost(),6666);

        //发送数据出去
        socket.send(packet);
        socket.close();
    }
}
