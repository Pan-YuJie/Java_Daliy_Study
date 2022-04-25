package www.panyujie.JDBC.jdbcconetion2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-21
 * Time: 17:23
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {



        //使用反射加载 driver类
        //在加载 driver类时中 DriverManager.registerDriver 在静态代码块中 已经完成了注册 所以不用在注册了
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong";
        String user="root";
        String password="pyj285464711";

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);

    }
}
