package com.jxd.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Map<String, Method> map = new HashMap<>();
        Class<?> clazz =PhotoGGHandler.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            map.put(method.getName(),method);
//            System.out.println(method.getName());
//            method.invoke(clazz.newInstance());
        }
        //actionGooglePhoto有参数
        //actionWK
        System.out.println(map.get("actionWK").invoke(clazz.newInstance(), new Object[]{}));
//        System.out.println(map.get("actionGooglePhoto").getParameterCount());//获取方法中参数的个数
    }
}
