package com.jxd.annotation;

import java.lang.annotation.*;

/**
 * Copyright © 2019西游计. All rights reserved.
 *
 * @Project：wt分布式微服务平台
 * @FileName: JxdAno.java
 * @Description:
 * @Author: jxd
 * @Date: 2019/6/17 17:55
 * @Version: V1.0
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface JxdAno {
//    String value() default "3600";
    public int time() default 3600;
    public boolean flat() default false;
}
