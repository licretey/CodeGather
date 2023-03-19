package com.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.serialization.bean.Person;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * DateTime
 * https://www.cnblogs.com/xichji/p/11777214.html
 * https://www.bilibili.com/video/BV1RG411K7CX/?spm_id_from=333.337.search-card.all.click&vd_source=d51ab707a572e170499eec2667dc994e
 * Java集合
 * https://blog.csdn.net/feiyanaffection/article/details/81394745
 * https://blog.csdn.net/gd_yuzhe/article/details/119141963
 */
public class Json01FastJson {
    /**
     * SerializeFilter定制化处理
     * 如要求:
     *  json字符串的key要大写，驼峰或者驼峰转下划线
     */
    @Test
    public void test03(){
        Person person = new Person();
        person.setId(1L);
        person.setName("licretey");
        person.setPwd("123");
        person.setAddr("河南");
        person.setWebsiteUrl("www.baidu.com");
        person.setRegisterDate(new Date());
        person.setBirtyday(LocalDateTime.now());
        // 定制序列化
        // SerializeFilter serializeFilter = new NameFilter() {
        //     @Override
        //     public String process(Object o, String s, Object o1) {
        //         return s.toUpperCase();
        //     }
        // };
        // 等效写法
        /**
         * object 待序列化对象
         * name value 属性与对应的值
         */
        NameFilter nameFilter = (object, name, value) -> name.toUpperCase();

        String jsonString = JSON.toJSONString(person, nameFilter);
        System.out.println(jsonString);
    }

    /**
     * 测试fastjson的引用探测(奇怪的$ref)
     * $ref: 对象中多次引用了同一个其它对象时会出现
     */
    @Test
    public void test02(){
        List<Person> list = new ArrayList<>();
        Person person = new Person();
        person.setId(1L);
        person.setName("licretey");
        person.setPwd("123");
        person.setAddr("河南");
        // 添加了多次
        list.add(person);
        list.add(person);
        list.add(person);
        // 序列化: [{"addr":"河南","id":1,"name":"licretey","pwd":"123"},{"$ref":"$[0]"},{"$ref":"$[0]"}]
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);
        // SerializerFeature.DisableCircularReferenceDetect: 开启引用探测
        String jsonString2 = JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(jsonString2);
    }


    @Test
    public void test01(){
        Person person = new Person();
        person.setId(1L);
        // fastjson默认对空值字段不进行序列化
        // person.setName("licretey");
        person.setPwd("123");
        person.setAddr("河南");
        person.setWebsiteUrl("www.baidu.com");
        person.setRegisterDate(new Date());
        person.setBirtyday(LocalDateTime.now());
        // 序列化
        /**
         * SerializerFeature.WriteMapNullValue 序列化时会包含null
         */
        String jsonString = JSON.toJSONString(person, SerializerFeature.WriteMapNullValue);
        System.out.println(jsonString);
    }
}
