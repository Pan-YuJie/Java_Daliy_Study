package www.panyujie.network.TCP_Echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-06-25
 * Time: 19:10
 */


public class TcpEchoClient {
    // 用普通的 Socket 即可，不用 ServerSocket 了
    private Socket socket = null;

    //此处也不用手动给客户端指定端口号，由系统自动分配(隐式)
    public TcpEchoClient(String serverIP,int serverPort) throws IOException {
        // 其实这里是可以给定端口号的，但是这里给了之后，含义是不同的。
        // 这里传入的 IP 与 端口号 的 含义： 表示的不是自己绑定，而是表示 和 这个IP 端口 建立连接
        socket = new Socket(serverIP,serverPort);// 这里表示 与 IP 为serverIP的主机上的 端口为9090的程序，建立连接。
    }
    public void start(){
        System.out.println("和进服务器连接成功！");
        Scanner sc = new Scanner(System.in);
        try(InputStream inputStream = socket.getInputStream()){
            try (OutputStream outputStream = socket.getOutputStream()){
                while(true){
                    //1、从控制台读取字符串
                    System.out.println("->");
                    String request = sc.next();
                    //2、根据读取的自妇产，构造请求，把请求发送给服务器
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.println(request);// 看似是一个输出语句，其实已经将数据写到服务器里面去了
                    printWriter.flush();// 记得 立即刷新缓冲区，确保 服务器 第一时间 感知到 请求。
                    //3、从服务器读取响应，并解析
                    Scanner scanner = new Scanner(inputStream);
                    String response = scanner.next();
                    //4、把结果显示到控制台上。
                    System.out.printf("request:%s,response:%s\n ",request,response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        TcpEchoClient client = new TcpEchoClient("127.0.0.1",9090);
        client.start();
    }
}