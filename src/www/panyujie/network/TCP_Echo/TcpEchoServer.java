package www.panyujie.network.TCP_Echo;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-06-25
 * Time: 19:10
 */

//public class TcpEchoServer {
//    // listen 的 中文意思是 监听
//    // 但是，在Java socket 中是体现不出来 “监听”的含义
//    // 之所以这么叫，其实是 操作系统原生的 API 里有一个操作叫做 listen
//    // 而 ServerSocket 确实起到了一个监听的效果
//    // 所以，取个 listenSocket 的名字
//    private ServerSocket listenSocket = null;
//
//    public TcpEchoServer(int port) throws IOException {
//        listenSocket = new ServerSocket(port);
//    }
//    // 启动服务器
//    public void start() throws IOException {
//        System.out.println("服务器启动！");
//        while(true){
//            //由于 TCP 是有连接的、因此，不能一上来就读取数据，需要先建立连接
//            // accept 就是在“接电话”，接电话的前提是：有人给你打电话【有客户端发送请求】
//            Socket clientSocket = listenSocket.accept();
//            processConnection(clientSocket);// 处理连接成功的客户端请求
//        }
//    }
//
//    private void processConnection(Socket clientSocket) throws IOException {
//        System.out.printf("[%s,%d] 客户端建立连接\n",clientSocket.getInetAddress().toString(),// 获取客户端IP地址
//                clientSocket.getPort());//获取客户端端口
//        //接下来，就可以来处理请求 和 响应
//        // 这里的针对 TCP socket 的读写  和 文件操作 的读取一模一样！
//        try(InputStream inputStream = clientSocket.getInputStream()){
//            try(OutputStream outputStream = clientSocket.getOutputStream()){
//                Scanner sc = new Scanner(inputStream);
//                // 循环处理每个请求，分别返回响应
//                while(true){
//                    //1、读取请求
//                    // 如果没有下一个结果，直接结束循环。
//                    if(!sc.hasNext()){
//                        System.out.printf("[%s:%d] 客户端断开连接！\n",clientSocket.getInetAddress().toString(),
//                                clientSocket.getPort());
//                        break;
//                    }
//                    //此处使用 Scanner 更方便
//                    // 如果不用 Scanner，而使用原生的 InputStream 的 read 也是可以的。
//                    // 但是很麻烦！它需要构造一个 字节数组 来存储 read 读取的数据。
//                    //  read 还会返回字节的个数，如果为-1，即为没有后续数据了，读完了。
//                    String request = sc.next();
//
//                    //2、根据请求，计算响应
//                    String response = process(request);
//
//                    //3、将响应返回给客户端
//                    //为了方便起见，可以使用 PrintWriter 把 OutputStream 包裹一下
//                    PrintWriter printWriter =new PrintWriter(outputStream);
//                    printWriter.println(response);
//                    printWriter.flush();// 刷新缓冲区。
//                    // 如果没有这个 flush，可能 客户端就不能第一时间看到响应的结果
//
//                    System.out.printf("[%s:%d] request:%s,response:%s\n",clientSocket.getInetAddress(),// IP
//                            clientSocket.getPort(),// 端口
//                            request,//请求
//                            response);// 响应
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            clientSocket.close();
//        }
//    }
//    // 因为是回显服务，不涉及业务。只需要直接返货就可以了
//    private String process(String request) {
//        return request;
//    }
//    public static void main(String[] args) throws IOException {
//        TcpEchoServer server = new TcpEchoServer(9090);
//        server.start();
//    }
//}

public class TcpEchoServer {

    public ServerSocket serverSocket =null;

    public TcpEchoServer (int port) throws IOException {
        serverSocket =new ServerSocket(port);
    }

    public void start() throws IOException {

        System.out.println("服务器启动");

        while (true) {
            // 由于 TCP 是有连接的，不能一上来就读数据， 要先建立连接 (接电话)
            // accept 就是在”接电话“，接电话的前提是，有人给你打~~， 如果没有客户端尝试建立连接， accept 就会阻塞
            // accept 返回一个 socket 对象， 称为 clientSocket , 后续和客户端之间的沟通， 都是通过 clientSocket 完成的
            Socket cilentSocket = serverSocket.accept();

            processConnection(cilentSocket);

        }

    }

    private void processConnection(Socket cilentSocket) throws IOException {
        //打印客户端信息
        System.out.printf("[%s:%d] 客户端建立连接！！\n",cilentSocket.getInetAddress().toString(),cilentSocket.getPort());

        //处理请求和响应 全双工
        try( InputStream inputStream = cilentSocket.getInputStream()) {
            try(OutputStream outputStream = cilentSocket.getOutputStream()){

                //循环处理每个请求，返回响应
                Scanner scanner =new Scanner(inputStream);
                while(true) {
                    //读取请求
                    if(!scanner.hasNext()){
                        System.out.printf("[%s:%d] 客户端断开链接！！",cilentSocket.getInetAddress().toString(),cilentSocket.getPort());
                        break;
                    }
                    // 此处用Scanner 更方便
                    String request = scanner.next();
                    //根据请求计算响应
                    String response=process(request);
                    //为了方便使用 用 PrintWrite 把 OutputStream 包裹
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.println(response);
                    //刷新缓冲区，没有可能不能第一时间看见响应结果
                    printWriter.flush();

                    System.out.printf("[%s,%d] req:%s , resp:%s\n",cilentSocket.getInetAddress().toString(),
                            cilentSocket.getPort(),request,response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

     }

    private String process(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException {

        TcpEchoServer tcpEchoServer = new TcpEchoServer(9090);

        tcpEchoServer.start();

    }

}
