package edu.gduf.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Demo class
 *
 * @author 古市
 * @date 2020/02/04 13:24
 */
public class DruidUtil {
    static private DataSource dataSource;
    static{
        Properties properties = new Properties();
        try {
            properties.load(DruidUtil.class.getClassLoader().getResourceAsStream("druid_config.properties"));
            System.out.println("success");
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 关闭各种流
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(stmt, conn);
    }

    /**
     * 关闭各种流
     * @param stmt
     * @param conn
     */
    public static void close(Statement stmt, Connection conn){
        if (stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回一个数据库连接池
     * @return 数据库连接池
     */
    public DataSource getDataSource(){
        return dataSource;
    }

}
