package com.jxd.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jxd")
public class JxdProperties {
    private String mqUrl;
    private String mqUserName;
    private String mqPwd;
    private String[] mqQueen;

    public String getMqUrl() {
        return mqUrl;
    }

    public void setMqUrl(String mqUrl) {
        this.mqUrl = mqUrl;
    }

    public String getMqUserName() {
        return mqUserName;
    }

    public void setMqUserName(String mqUserName) {
        this.mqUserName = mqUserName;
    }

    public String getMqPwd() {
        return mqPwd;
    }

    public void setMqPwd(String mqPwd) {
        this.mqPwd = mqPwd;
    }

    public String[] getMqQueen() {
        return mqQueen;
    }

    public void setMqQueen(String[] mqQueen) {
        this.mqQueen = mqQueen;
    }

    public String getHandlerPackage() {
        return handlerPackage;
    }

    public void setHandlerPackage(String handlerPackage) {
        this.handlerPackage = handlerPackage;
    }

    private String handlerPackage;
}
