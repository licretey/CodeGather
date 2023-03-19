package com.serialization;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.serialization.bean.User;
import com.serialization.bean.UserVO;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Json04JacksonReverse {
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
        // 通用配置：驼峰转下划线（当前版本已过时）
        // objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
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
     * Jackson反序列化
     */
    @Test
    public void test01() throws JsonProcessingException {
        String jsonStr = "{\"id\":1,\"pwd\":\"123\",\"addr\":\"河南\",\"websiteUrl\":\"www.baidu.com\",\"registerDate\":\"2023-02-13 23:07:40\",\"birtyday\":\"2023-02-13 23:07:40\"}";
        String jsonStr2 = "{\"id\":1,\"age\":20,\"pwd\":\"123\",\"addr\":\"河南\",\"websiteUrl\":\"www.baidu.com\",\"registerDate\":\"2023-02-13 23:07:40\",\"birtyday\":\"2023-02-13 23:07:40\"}";
        // 反序列化
        User user = objectMapper.readValue(jsonStr, User.class);
        System.out.println(user);
        User user2 = objectMapper.readValue(jsonStr2, User.class);
        System.out.println(user2);
    }

    /**
     * 泛型处理
     * @throws JsonProcessingException
     */
    @Test
    public void test02() throws JsonProcessingException {
        User user = new User();
        user.setName("licreteyJack");
        user.setWebsiteUrl("11122.com");
        UserVO<User> userVO = UserVO.buildSuccess(user);
        String dtoStr = objectMapper.writeValueAsString(userVO);
        System.out.println(dtoStr);
        // 反序列化为UserVO<User>
        UserVO<User> userUserVO = objectMapper.readValue(dtoStr, new TypeReference<UserVO<User>>() {
        });
        System.out.println("反序列化泛型："+userUserVO);
        System.out.println("反序列化泛型中的data："+userUserVO.getData());
    }
}
