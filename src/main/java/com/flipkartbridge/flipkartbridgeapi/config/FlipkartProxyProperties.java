package com.flipkartbridge.flipkartbridgeapi.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "proxy.flipkart")
public class FlipkartProxyProperties {
    private String clientId;
    private String clientSecret;
    private String tokenUrl;
    private String host;
    private String tokenPath;
    private String shipmentByOrderIdPath;
    private String readTimeout;
    private String connectTimeout;
}
