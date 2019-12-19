package com.jxd.test;

import com.jxd.config.PhotoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@RestController
public class testController {

    @Autowired
    PhotoConfig photoConfig;

    private static String result;
    @RequestMapping("/test")
//    @TimeAno(flat = true)
    public String test() throws IllegalAccessException, InstantiationException, InvocationTargetException {

        return result;
    }

    @RequestMapping("/test222")
//    @TimeAno(flat = true)
    public String test222() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> clazz =PhotoGGHandler.class;
        Map<String, Method> map =photoConfig.getMapMethod();
        System.out.println(map.get("actionWK").invoke(clazz.newInstance(), new Object[]{}));
        return result;
    }
}
