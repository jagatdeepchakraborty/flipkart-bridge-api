package com.flipkartbridge.flipkartbridgeapi.model;

import lombok.Data;

import java.util.List;

@Data
public class SubShipment {
    public String subShipmentId;
    public List<Package> packages;
}
