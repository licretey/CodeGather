package com.serialization;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.serialization.bean.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Json03Jackson {
    // Jackson利用ObjectMapper（线程安全）序列化
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static {
        /**
         * 序列化配置
         */
        // 全局配置：配置序列化时只包含非空属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 全局配置：自动发现spi发现jackson的model并注册生效
        objectMapper.findAndRegisterModules();
        // 全局配置：手动配置时间module并注册
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        objectMapper.registerModule(javaTimeModule); // 注册
        // 也可对Date配置SimpleDateFormat，但不建议，因为SimpleDateFormat线程不安全（而objectMapper安全）

        // 序列化梅花输出
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    /**
     * Jackson序列化
     */
    @Test
    public void test01() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        // jacksson默认对空值进行序列化
        // user.setName("licretey");
        user.setPwd("123");
        user.setAddr("河南");
        user.setWebsiteUrl("www.baidu.com");
        user.setRegisterDate(new Date()); // long类型时间戳
        user.setBirtyday(LocalDateTime.now()); // 格式一般
        // 序列化
        String jsonStr = objectMapper.writeValueAsString(user);
        System.out.println(jsonStr);
    }
}
