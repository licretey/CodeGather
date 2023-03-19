package com.serialization.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Date;

//设置只对本类序列化时，序列化非空属性
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Long id;
    private String name;
    private String pwd;
    @JsonProperty("address") // 指定属性名用对应的key代替，即用address代替addr
    private String addr;
    @JsonIgnore // 序列化忽略属性
    private String websiteUrl;
    // 格式化日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date registerDate;
    /**
     * 新版本报错，需要配置model并注册 ：
     *     方式1 可以配置基于spi自动发现jackson的model并注册功能
     *     objectMapper.findAndRegisterModules();
     *     方式2 可以手动配置module并注册
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
