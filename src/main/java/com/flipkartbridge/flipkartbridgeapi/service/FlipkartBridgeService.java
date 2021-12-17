package com.flipkartbridge.flipkartbridgeapi.service;

import com.flipkartbridge.flipkartbridgeapi.model.ShipmentResponse;
import com.flipkartbridge.flipkartbridgeapi.proxy.AccessTokenGenerator;
import com.flipkartbridge.flipkartbridgeapi.proxy.FlipkartApiProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FlipkartBridgeService {
    private final FlipkartApiProxy flipkartApiProxy;

    public ShipmentResponse getShipments(){
        return flipkartApiProxy.getShipmentsByOrderId("1");
    }
}
