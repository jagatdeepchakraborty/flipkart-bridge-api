package com.flipkartbridge.flipkartbridgeapi.model;

import lombok.Data;

import java.util.List;

@Data
public class ShipmentResponse {
    public List<Shipment> shipments;
}
