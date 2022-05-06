package www.panyujie.JDBC.jdbcconetion1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-21
 * Time: 16:11
 */

public class Test {
    public static void main(String[] args) throws SQLException {

        //前置工作 导入 Jar包 在lib目录下 并加入到依赖库中
        //1.注册驱动
        // com.mysql.cj.jdbc 8.0+以上版本这样写
        Driver driver=new com.mysql.cj.jdbc.Driver();

        //2.得到连接
        //(1) jdbc:mysql:// 规定好的协议，通过JDBC连接mysql
        //(2) localhost 主机，IP地址
        //（3） 3306 mysql端口
        String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong";
        //MySQL在高版本需要指明是否进行SSL连接。解决方案： 在mysql连接字符串url中加入ssl=true或者false即可，如下所示
        //"jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong"

        //将用户名密码封装到Properties 对象
        //user 和 password 别写错
        Properties properties =new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","pyj285464711");

        //String name="root";
        //String password="pyj285464711";

        Connection connection=driver.connect(url,properties);

        //3.执行sql
        String sql="insert into account values (null,'六六',2000)";
        //用于执行静态的sql语句并返回生成的结果
        Statement statement = connection.createStatement();
        int rows=statement.executeUpdate(sql);//返回的结果是受影响的行数
        System.out.println(rows);

        //4.关闭连接
        statement.close();
        connection.close();

    }

}
