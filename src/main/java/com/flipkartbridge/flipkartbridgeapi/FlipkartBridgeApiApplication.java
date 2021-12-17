package com.flipkartbridge.flipkartbridgeapi;

import com.flipkartbridge.flipkartbridgeapi.config.FlipkartProxyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class FlipkartBridgeApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlipkartBridgeApiApplication.class, args);
    }

}
