package com.serialization.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.time.LocalDateTime;
import java.util.Date;

public class Person {
    private Long id;
    private String name;
    // serialize = false 序列化时不参与 deserialize = false 反序列化时不参与
    @JSONField(serialize = false, deserialize = false)
    private String pwd;
    @JSONField(name = "address") // 指定属性名用对应的key代替，即用address代替addr
    private String addr;
    private String websiteUrl;
    // 指定日期序列化时的格式
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birtyday;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String add) {
        this.addr = add;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDateTime getBirtyday() {
        return birtyday;
    }

    public void setBirtyday(LocalDateTime birtyday) {
        this.birtyday = birtyday;
    }
}
