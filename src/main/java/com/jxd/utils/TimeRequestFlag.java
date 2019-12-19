package com.jxd.utils;

import com.jxd.annotation.JxdAno;
import com.jxd.config.PhotoConfig;
import com.rabbitmq.http.client.Client;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Copyright © 2019西游计. All rights reserved.
 *
 * @Project：wt分布式微服务平台
 * @FileName: TimeRequestFlag.java
 * @Description:
 * @Author: jxd
 * @Date: 2019/6/17 17:56
 * @Version: V1.0
 */

@Aspect // FOR AOP
@Order() // 控制多个Aspect的执行顺序，越小越先执行, 当然也可以不写这注解, 对于写和不写@order的两个切面, 有@order的优先于无@order的执行; 都有@order时, 越小越执先执行
public class TimeRequestFlag {


    @Autowired
    PhotoConfig photoConfig;

    @Autowired
    private JxdProperties jxdProperties;

    //        前置通知（Before advice）- 在目标方便调用前执行通知
    //        后置通知（After advice）- 在目标方法完成后执行通知
    //        返回通知（After returning advice）- 在目标方法执行成功后，调用通知
    //        异常通知（After throwing advice）- 在目标方法抛出异常后，执行通知
    //        环绕通知（Around advice）- 在目标方法调用前后均可执行自定义逻辑
    @Before("@annotation(jxdAno)")
    public synchronized void beforeRequestFlat(JoinPoint joinPoint, JxdAno jxdAno) throws Exception {
        Map<String, Method> maps = photoConfig.getMapMethod();
        Object[] args = joinPoint.getArgs();//获取目标方法的参数值
        Class<?> clazz = joinPoint.getTarget().getClass();
        Class<?> clazzHandler = Class.forName(jxdProperties.getHandlerPackage());
        String methodName = joinPoint.getSignature().getName();
        int count = 0;
        if (0 != args.length)
            count = Integer.parseInt(null == args[0] ? "0" : args[0].toString());//阈值
        Object result;
        if (jxdAno.flat()) {
            if (this.isTimeAno(methodName, jxdAno) && isMqMessageCount()) {
                result = actionMethod(maps.get(methodName), new Object[]{count}, clazzHandler);
                this.reflection(clazz, result);
            }
        } else {
            result = actionMethod(maps.get(methodName), new Object[]{count}, clazzHandler);
            this.reflection(clazz, result);
        }
    }

    private Object actionMethod(Method method, Object[] objects, Class<?> clazz) {
        Object result;
        try {
            if (method.getParameterCount() == 0) {
                result = method.invoke(clazz.newInstance());
            } else {
                result = method.invoke(clazz.newInstance(), objects);
            }
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于判断接口是否在一个时间段内只调用了1次
     */
    private boolean isTimeAno(String methodName, JxdAno jxdAno) {
        long start = System.currentTimeMillis();
        if (null != System.getProperty(methodName)) {
            long timeFlag = Long.parseLong(System.getProperty(methodName));
            if (timeFlag + Long.parseLong(jxdAno.time() + "") < start) {//之内只允许调用一次
                System.setProperty(methodName, start + "");
            } else {
                return false;
            }
        } else {
            System.setProperty(methodName, start + "");
        }
        return true;
    }

    /**
     * 用于判断Mq中jxd_queueGG,jxd_queueWK,jxd_queueFK是否有消息必须没有消息才能执行
     *
     * @return
     */
    private boolean isMqMessageCount() {
        Client client = null;
        try {
            client = new Client(jxdProperties.getMqUrl(), jxdProperties.getMqUserName(), jxdProperties.getMqPwd());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String queens[] = jxdProperties.getMqQueen();
        for (int i = 0; i < queens.length; i++) {
            if (client.getQueue("/", queens[i]).getTotalMessages() > 0) return false;
        }
        return true;
    }

    /**
     * 反射值
     *
     * @param clazz
     * @param value
     */

    public void reflection(Class<?> clazz, Object value) {
        if (null == value) return;
        try {
            Field f = clazz.getDeclaredField("result");
            f.setAccessible(true);//私有变空权限
            f.set(clazz.newInstance(), value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
