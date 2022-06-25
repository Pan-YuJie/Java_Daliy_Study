package www.panyujie.network.UDP_Echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-06-25
 * Time: 16:20
 */


// 实现翻译回显C/S
public class MyUDPServer {

    // 对于一个服务器程序来说, 核心流程也是要分成两步.
    // 1. 进行初始化操作 (实例化 Socket 对象)
    // 2. 进入主循环, 接受并处理请求. (主循环就是一个 "死循环")
    //   a) 读取数据并解析
    //   b) 根据请求计算响应
    //   c) 把响应结果写回到客户端.

    private DatagramSocket socket =null;

    //map去存储我们的汉译英数据
    private Map<String, String> map = new HashMap<>();

    public  MyUDPServer(int port) throws SocketException {
        //添加数据
        initDates();
        //服务器new socket对象的时候需要和一个ip地址和端口号绑定起来
        //如果没有写ip 则默认时0.0.0.0 (一个特殊的ip会关联到这个主机的国有网卡的ip)
        //socket对象本省就是一个文件
        socket = new DatagramSocket(port);
    }

    // map初始化
    private void initDates() {
        map.put("猫", "cat");
        map.put("猪", "pig");
        map.put("狗", "dog");
        map.put("人", "people");
        map.put("笔", "pen");
        map.put("坐", "sit");
        map.put("手", "hand");
        map.put("腿", "leg");
    }

    //启动服务器
    public void start() throws IOException {

        System.out.println("服务器启动");
        // UDP 不用建立连接， 接受盛怒据即可
        while(true){
            //1. 接受客户端的请求
            //2. 根据请求计算相应
            //3. 把响应写回客户端
            //这是一个接受数据的缓冲区 地址是接受数据的时候有内存填充
            DatagramPacket datagramPacket= new DatagramPacket(new byte[4096],4096);
            //程序启动会很快到达receive操作 如果客户端没有发送任何数据 此时receive操作会阻塞直到有客户端发送数据过来
            //1.当整的有哭换端发送过来数据时 receive就会将数据保证到DategramPAcket对象的缓冲区里
            socket.receive(datagramPacket);
            //原本请求的数据时byte[]需要将其转换成String 并且如果发来的数据小于我们缓冲区的大小就会默认添加空格 我们得去掉无用空格
            String request = new String(datagramPacket.getData(), 0, datagramPacket.getLength(),"UTF-8").trim();
            //2.请求计算相应
            String respond = process(request);

            //把响应写回给客户端, 响应数据就是 response, 需要包装成一个 DatagramPacket 对象
            //此时这个用于send 不仅需要指定缓冲区还不要忘记在Packet对象的最后加上请求数据包里的Socket地址
            //填写ip和port还可以自己手动设置将ip和port分开写(如下面案例) 还可以直接定义InetAddress对象(里面包含ip和port)
            DatagramPacket respondPacket = new DatagramPacket(respond.getBytes(),
                    respond.getBytes().length, datagramPacket.getSocketAddress());

            // 3。发送数据
            socket.send(respondPacket);

            //打印请求访问日志
            System.out.println(respondPacket.getAddress().toString() + "  " + respondPacket.getPort() + "  request: "
                    + request + "  respond: " + respond);

        }

    }

    private String process(String request) {
        return map.getOrDefault(request, "未学习");
    }

    //一个主函数去设置该服务器的端口 并让其开始执行
    public static void main(String[] args) {
        try {
            MyUDPServer myUDPServer = new MyUDPServer(9090);
            try {
                myUDPServer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
