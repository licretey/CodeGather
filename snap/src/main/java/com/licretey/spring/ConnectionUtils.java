package com.licretey.spring;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 链接工具类，从数据源获取一个连接，并绑定到一个线程上
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     */
    public Connection getThreadConnection(){
        Connection connection = t1.get(); // 1 先从 threadlocal 中获取
        try {
            // 2 没有则从数据原处获取，并存入 threadlocal
            if(connection == null) {
                connection = dataSource.getConnection();
                t1.set(connection);
            }
            // 3 返回当前线程上的连接
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        t1.remove();
    }
}
