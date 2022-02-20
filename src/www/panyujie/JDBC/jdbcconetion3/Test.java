package www.panyujie.JDBC.jdbcconetion3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        properties.load(new FileInputStream("/src/www/panyujie/JDBC/jdbcconetion3/jdbc.properties"));

        //获取相关的值



    }
}
