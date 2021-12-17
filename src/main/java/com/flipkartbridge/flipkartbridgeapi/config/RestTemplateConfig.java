package com.flipkartbridge.flipkartbridgeapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableConfigurationProperties(FlipkartProxyProperties.class)
public class RestTemplateConfig {
    @Bean(name = "restTemplateForShipmentApi")
    public RestTemplate restTemplateForShipmentApi(RestTemplateBuilder builder,
                                                   FlipkartProxyProperties flipkartProxyProperties) {
        return generateRestTemplate(Long.parseLong(flipkartProxyProperties.getReadTimeout()),
                Long.parseLong(flipkartProxyProperties.getConnectTimeout()), builder).build();
    }

    private RestTemplateBuilder generateRestTemplate(long readTimeout,
                                                     long connectTimeout,
                                                     RestTemplateBuilder builder) {
        return builder.requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout));
    }
}
