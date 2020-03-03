package edu.gduf.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-02-29 18:13
 **/
public class MyBatisUtil {

    static private SqlSessionFactory factory;

    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis_config.xml");
            factory = new SqlSessionFactoryBuilder().build(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory() {
        return factory;
    }
}
