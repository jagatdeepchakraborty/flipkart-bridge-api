package com.flipkartbridge.flipkartbridgeapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RestTemplateConfig {

    @Bean(name = "restTemplateForShipmentApi")
    //TODO: Just because we can't remove the config yet
    public RestTemplate restTemplateForShipmentApi(@Value("${shipment.read.timeout}") long readTimeout,
                                                       @Value("${shipment.connect.timeout}") long connectTimeout,
                                                       RestTemplateBuilder builder) {
        return generateRestTemplate(readTimeout, connectTimeout, builder).build();
    }

    private RestTemplateBuilder generateRestTemplate(long readTimeout,
                                                     long connectTimeout,
                                                     RestTemplateBuilder builder) {
        return builder.requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout));
    }
}
