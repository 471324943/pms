package cn.czxy.Utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by 花开自来 on 2019/4/10.
 */
public class DBCPUtil {

    public static BasicDataSource basicDataSource = new BasicDataSource();

    static {
        InputStream resourceAsStream = DBCPUtil.class.getClassLoader().getResourceAsStream("DBCPConfig.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        basicDataSource.setDriverClassName(properties.getProperty("driver"));
        basicDataSource.setUrl(properties.getProperty("url"));
        basicDataSource.setUsername(properties.getProperty("username"));
        basicDataSource.setPassword(properties.getProperty("password"));
    }

    public void Demo1() {
        System.out.println(basicDataSource);
    }
}
