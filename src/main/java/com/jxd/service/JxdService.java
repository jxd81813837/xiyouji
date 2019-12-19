package com.jxd.service;

import com.jxd.config.PhotoConfig;
import com.jxd.utils.JxdProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class JxdService implements ApplicationRunner {

    @Autowired
    private JxdProperties JxdProperties;

    @Autowired
    private PhotoConfig photoConfig;

    private Map<String, Method> mapMethod;

    public void setMapMethod(Map<String, Method> mapMethod) {
        mapMethod = mapMethod;
    }

    public Map<String, Method> getPhotoMapMethod() {
        return mapMethod;
    }


    public void actionPhotoConfig() {

        Class<?> clazz = null;
        try {
            clazz = Class.forName(JxdProperties.getHandlerPackage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Method> map = new HashMap<>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Method put = map.put(method.getName(), method);
        }
        photoConfig.setMapMethod(map);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.actionPhotoConfig();
//        System.out.println("22222222222222");
    }

}
