package com.licretey.spring;

import java.sql.SQLException;

/**
 * 与事务相关工具类，包含开启、提交、回滚、释放事务
 */
public class TransactionManagerUtils {

    private ConnectionUtils connectionUtils;

    public ConnectionUtils getConnectionUtils() {
        return connectionUtils;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启
     */
    public void begin(){
        try {
            // 默认开启，自动提交事物；当关闭后意味着事务的手动管理即被打开
            connectionUtils.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交
     */
    public void commit(){
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚
     */
    public void rollback(){
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放
     */
    public void release(){
        try {
            connectionUtils.getThreadConnection().close();  // 还回连接池
            connectionUtils.removeConnection();             // 解绑
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
