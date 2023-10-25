package com.licretey.zook;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Arrays;
import java.util.List;

public class ZKWatcher {
    CuratorFramework curator = null;

    public void createConnection() {
        // 方式1
        RetryPolicy rt = new ExponentialBackoffRetry(3000, 10);
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

    public void zkNodeCache() throws Exception {
        // 创建nodeCache
        NodeCache nodeCache = new NodeCache(curator, "/licretey1", false);
        // 注册监听
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点变化了");
                // 获取修改后的数据
                byte[] data = nodeCache.getCurrentData().getData();
                System.out.println(new String(data));
            }
        });
        // 开启监听 参数true表示开启监听时，加载缓存数据
        nodeCache.start(true);
    }

    public void zkPathChildrenCache() throws Exception {
        // 创建 PathChildrenCache
        PathChildrenCache childrenCache = new PathChildrenCache(curator, "/licretey1", true);
        // 注册监听
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                System.out.println("节点变化了\n"+event);
                // 仅监听数据的变更的事件
                List<PathChildrenCacheEvent.Type> types = Arrays.asList(PathChildrenCacheEvent.Type.CHILD_UPDATED);
                if (types.contains(event.getType())) {
                    // 获取修改后的数据
                    byte[] data = event.getData().getData();
                    System.out.println(new String(data));
                }
            }
        });
        // 开启监听 参数true表示开启监听时，加载缓存数据
        childrenCache.start(true);
    }

    public void zkTreeCache() throws Exception {
        // 创建 TreeCache
        TreeCache treeCache = new TreeCache(curator, "/licretey1");
        // 注册监听
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                System.out.println("节点变化了\n"+event);
                // 仅监听数据的变更的事件
                List<PathChildrenCacheEvent.Type> types = Arrays.asList(PathChildrenCacheEvent.Type.CHILD_UPDATED);
                if (types.contains(event.getType())) {
                    // 获取修改后的数据
                    byte[] data = event.getData().getData();
                    System.out.println(new String(data));
                }
            }
        });
        // 开启监听 参数true表示开启监听时，加载缓存数据
        treeCache.start();
    }
}
