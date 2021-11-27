package com.flipkartbridge.flipkartbridgeapi.model;

import lombok.Data;

import java.util.Date;

@Data
public class Shipment {
    public String shipmentId;
    public Date dispatchByDate;
    public Date dispatchAfterDate;
    public Date updatedAt;
    public String locationId;
    public boolean hold;
    public boolean mps;
    public String packagingPolicy;
    public List<SubShipment> subShipments;
    public List<OrderItem> orderItems;
    public List<Form> forms;
    public String shipmentType;
}
