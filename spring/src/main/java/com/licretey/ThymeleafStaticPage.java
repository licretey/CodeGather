package com.licretey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Condition;

/**
 * thymeleaf引擎自动生成静态文件
 */
@Service
public class ThymeleafStaticPage {
    @Autowired
    private TemplateEngine engine;

    @Autowired
    private Map<String, Object> map; // 后端返回的数据


    /**
     * 请求后端商品信息时，
     * 可以通过调用此方法，生成静态商品页面缓存到本地
     * @param spuId
     */
    public void createHtml(Long spuId){
        // 初始化运行上下文
        Context ctx = new Context();
        // 对map进行操作获取出目标数据，然后设置数据模型
        ctx.setVariables(map);
        // 将生成的静态文件保存在本地
        File file = new File("D:\\nginx\\html\\item\\"+spuId+".html"); // 使用nginx下的目录（注意文件夹名为item）
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            this.engine.process("item",ctx,writer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
