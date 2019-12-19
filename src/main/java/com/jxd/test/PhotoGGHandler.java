package com.jxd.test;

import org.springframework.stereotype.Component;

/**
 * Copyright © 2019西游计. All rights reserved.
 *
 * @Project：wt分布式微服务平台
 * @FileName: PhotoGGHandler.java
 * @Description:
 * @Author: jxd
 * @Date: 2019/6/18 14:14
 * @Version: V1.0
 */

@Component
public class PhotoGGHandler {


    /**
     * 抓取google图片程序
     */
    public void actionGooglePhoto(String str) {
        System.out.println("actionGooglePhoto"+"----"+str);
    }

    /**
     * 抓取目的地表新增的GooglePlaceId数据
     * 抓取景点表新增的GooglePlaceId数据
     */
    public void actionDSAndDB() {
        System.out.println("actionDSAndDB");

    }

    /**
     * 抓取WK图片的
     */
    public String actionWK() {
        System.out.println("actionWK");
        return "aaaaaa";
    }

}
