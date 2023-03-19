package com.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.serialization.bean.Person;
import com.serialization.bean.ResultVO;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;


/**
 * fastjson反序列化
 */
public class Json02FastJsonReverse {
    // 通用配置
    @Test
    public void test03() {
        Person person = new Person();
        person.setId(1L);
        person.setName("licretey");
        person.setPwd("123");
        person.setAddr("河南");
        person.setWebsiteUrl("www.baidu.com");
        person.setRegisterDate(new Date());
        person.setBirtyday(LocalDateTime.now());
        // 序列化
        String jsonString = JSON.toJSONString(person, true); // prettyFormat： 格式化输出字符串（非一行）
        System.out.println(jsonString);
    }

    // 处理泛型
    @Test
    public void test02() {
        String jsonStr = "{\"addr\":\"河南\",\"birtyday\":\"2023-02-13 21:50:52\",\"id\":1,\"name\":null,\"pwd\":\"123\",\"registerDate\":\"2023-02-13 21:50:52\",\"websiteUrl\":\"www.baidu.com\"}";
        Person person = JSON.parseObject(jsonStr, Person.class);
        ResultVO<Person> personResultVO = ResultVO.buildSuccess(person);
        String voJsonStr = JSON.toJSONString(personResultVO);
        // 反序列化泛型
        ResultVO resultVO = JSON.parseObject(voJsonStr, ResultVO.class);
        System.out.println("返序列化泛型：" + resultVO);
        // 能正常打印，但resultVO.getData()不能获取到泛型类型（需要强转）
        Object data = resultVO.getData();
        // 解决：TypeReference中传入想要反序列化出的类型
        ResultVO<Person> resultVO2 = JSON.parseObject(voJsonStr, new TypeReference<ResultVO<Person>>() {
        });
        System.out.println("反序列化泛型2：" + resultVO2);
        // 获取的data无需强转
        Person person2 = resultVO2.getData();
        System.out.println("data:" + data);
    }

    @Test
    public void test01() {
        String jsonStr = "{\"addr\":\"河南\",\"birtyday\":\"2023-02-13 21:50:52\",\"id\":1,\"name\":null,\"pwd\":\"123\",\"registerDate\":\"2023-02-13 21:50:52\",\"websiteUrl\":\"www.baidu.com\"}";
        // 反序列化
        Person person = JSON.parseObject(jsonStr, Person.class);
        System.out.println(person);
    }
}
