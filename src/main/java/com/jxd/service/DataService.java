package com.jxd.service;

import com.jxd.utils.JxdProperties;
import org.springframework.beans.factory.annotation.Autowired;


public class DataService {


    @Autowired
    private JxdProperties jxdProperties;

    public void getToken(){
        for(String queue:jxdProperties.getMqQueen()){
            System.out.println(queue+",");
        }
        System.out.println("模拟生成"+jxdProperties.getHandlerPackage());
    }

}
