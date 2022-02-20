package www.panyujie.network.InteAdress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-16
 * Time: 19:07
 * @author Submerge
 */

public class demo1 {
    public static void main(String[] args) throws Exception {
        InetAddress ip1 = InetAddress.getLocalHost();
        //System.out.println(Arrays.toString(ip1.getAddress()));

        System.out.println(ip1.getHostName());
        System.out.println(ip1.getHostAddress());

        InetAddress ip2=InetAddress.getByName("www.baidu.com");
        System.out.println(ip2.getHostAddress());
        System.out.println(ip2.getHostName());

        System.out.println(ip2.isReachable(200));

    }
}
