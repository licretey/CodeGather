package com.sourceLearn.a04;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 解析java.home, java.version
 *
 * @date 2024-08-08 17:21:19
 */
@ConfigurationProperties(prefix = "java")
public class Bean4 {
    private String home;
    private String version;

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Bean4{" +
                "home='" + home + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}