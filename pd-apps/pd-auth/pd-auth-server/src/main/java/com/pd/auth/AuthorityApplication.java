package com.pd.auth;

import com.pd.auth.server.EnableAuthServer;
import com.pd.user.annotation.EnableLoginArgResolver;
import com.pd.valid.config.EnableFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 1.8
 */
@Slf4j
@EnableAuthServer
@EnableFormValidator
@SpringBootApplication
@EnableDiscoveryClient
@EnableLoginArgResolver
@EnableFeignClients(value = {"com.pd"})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class AuthorityApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(AuthorityApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("应用 '{}' 运行成功, Swagger文档: http://{}:{}/doc.html",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }
}
