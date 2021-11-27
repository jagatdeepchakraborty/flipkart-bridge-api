package com.flipkartbridge.flipkartbridgeapi.controller;

import com.flipkartbridge.flipkartbridgeapi.model.Root;
import com.flipkartbridge.flipkartbridgeapi.service.FlipkartBridgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FlipkartBridgeController {
    private final FlipkartBridgeService flipkartBridgeService;

    @GetMapping(name = "/shipments")
    public ResponseEntity<Root> getShipments() {
        return  ResponseEntity.ok(new Root());
    }
}
