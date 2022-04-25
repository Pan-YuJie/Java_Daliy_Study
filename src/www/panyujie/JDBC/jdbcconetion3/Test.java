package www.panyujie.JDBC.jdbcconetion3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Submerge
 * Date: 2022-02-21
 * Time: 17:34
 */

//增加配置文件 Properties
public class Test {


    public static void main(String[] args) throws Exception {
        Properties properties =new Properties();

        properties.load(new FileInputStream("src\\jdbc.properties"));

        //获取相关的值

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection);

    }
}
