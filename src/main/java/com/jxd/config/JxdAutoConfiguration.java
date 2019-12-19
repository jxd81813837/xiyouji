package com.jxd.config;


import com.jxd.service.JxdService;
import com.jxd.service.DataService;
import com.jxd.utils.JxdProperties;
import com.jxd.utils.TimeRequestFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties({JxdProperties.class})
public class JxdAutoConfiguration  {

    @Autowired
    JxdProperties jxdProperties;

    @Bean
    public JxdService jxdService(){
        return new JxdService();
    }

    @Bean
    public DataService testService(){
        return new DataService();
    }

    @Bean
    public PhotoConfig photoConfig(){
        return new PhotoConfig();
    }

    @Bean
    public TimeRequestFlag timeRequestFlag(){
        return new TimeRequestFlag();
    }
}
