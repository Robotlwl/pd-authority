package com.pd.zuul;

import com.pd.auth.client.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@EnableZuulProxy
@EnableAuthClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.pd"})
public class ZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
