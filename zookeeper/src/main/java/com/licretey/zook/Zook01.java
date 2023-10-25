package com.licretey.zook;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Zook01 {
    CuratorFramework curator = null;

    public void createConnection() {
        // 方式1
        RetryPolicy rt = new ExponentialBackoffRetry(3000, 10);
        // CuratorFramework curator = CuratorFrameworkFactory.newClient("localhost:2181,192.168.0.3:2181", 60 * 1000, 15 * 1000, rt);
        // curator.start();
        // 方式2
        CuratorFramework curator2 = CuratorFrameworkFactory.builder()
                .connectString("")
                .sessionTimeoutMs(60 * 1000)
                .sessionTimeoutMs(15 * 1000)
                .retryPolicy(rt)
                // 不必要，设置命名空间
                .namespace("licretey")
                .build();
        curator = curator2;
        curator2.start();
    }

    public void createNode() throws Exception {
        this.createConnection();
        // 1 基本的创建 若未指定数据，会将当前ip作为数据存储
        curator.create().forPath("/licretey1");
        // 2 带有数据的创建
        curator.create().forPath("/licretey2", "heihei".getBytes(StandardCharsets.UTF_8));
        // 3 设置节点类型 默认持久化
        curator.create().withMode(CreateMode.EPHEMERAL).forPath("/licretey3");
        // 4 多级节点创建
        curator.create().creatingParentsIfNeeded().forPath("/licretey4/n1");
    }

    public void getNode() throws Exception {
        this.createConnection();
        // 查询数据 get
        byte[] bytes = curator.getData().forPath("/licretey1");
        System.out.println(new String(bytes));
        // 查询子节点 ls
        List<String> childrens = curator.getChildren().forPath("/licretey1");
        // 查询节点状态信息 ls -s
        Stat status = new Stat();
        curator.getData().storingStatIn(status).forPath("/licretey1");
        System.out.println(status);
    }

    public void setData() throws Exception {
        this.createConnection();
        // 修改数据
        curator.setData().forPath("/licretey1", "yang".getBytes(StandardCharsets.UTF_8));
        // 根据版本去修改数据
        int version = 0;
        Stat status = new Stat();
        curator.getData().storingStatIn(status).forPath("/licretey1");
        version = status.getVersion();
        curator.setData().withVersion(version).forPath("/licretey1", "yang".getBytes(StandardCharsets.UTF_8));
    }

    public void deleteNode() throws Exception {
        this.createConnection();
        // 删除单个节点
        curator.delete().forPath("/licretey1");
        // 递归删除节点
        curator.delete().deletingChildrenIfNeeded().forPath("/licretey1");
        // 必须删除成功的操作
        curator.delete().guaranteed().forPath("/licretey1");
        // 删除后执行回调
        curator.delete().guaranteed().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                System.out.println("删除了\n" + event);
            }
        }).forPath("/licretey1");
    }
}
