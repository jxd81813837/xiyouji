package com.jxd.config;

import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Map;


public  class PhotoConfig    {

    private Map<String, Method> mapMethod;

    public Map<String, Method> getMapMethod() {
        return mapMethod;
    }

    public void setMapMethod(Map<String, Method> mapMethod) {
        this.mapMethod = mapMethod;
    }
}
