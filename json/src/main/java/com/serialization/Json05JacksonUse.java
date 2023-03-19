package com.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.serialization.bean.User;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Json05JacksonUse {
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
        // 通用配置：驼峰转下划线
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        // 全局配置：手动配置时间module并注册
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        objectMapper.registerModule(javaTimeModule); // 注册
        // 也可对Date配置SimpleDateFormat，但不建议，因为SimpleDateFormat线程不安全（而objectMapper安全）


        /**
         * 反序列化配置
         */
        // 全局配置：对象中不存在的属性不序列化
        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); // 与上行等价
    }
    /**
     * 对象更新，对象合并，如果后者的属性有值则使用后者，否则前者的值不变
     */
    @Test
    public void test() throws JsonMappingException {
        User originUser = new User();
        originUser.setId(1L);
        originUser.setName("licreteyJack");
        originUser.setWebsiteUrl("11122.com");

        User newUser = new User();
        newUser.setId(2L);
        // newUser.setName("licreteyJack");
        newUser.setWebsiteUrl("333444.com");
        User updatedUser = objectMapper.updateValue(originUser, newUser);
        // 能省下许多判断
        System.out.println(updatedUser.toString()); // {id:2,name:'licreteyJack',websiteurl:'333444.com'}
    }
}
