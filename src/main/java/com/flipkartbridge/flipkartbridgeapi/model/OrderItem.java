package com.flipkartbridge.flipkartbridgeapi.model;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class OrderItem {
    public String orderItemId;
    public String orderId;
    public String cancellationGroupId;
    public Date orderDate;
    public Date cancellationDate;
    public String paymentType;
    public String status;
    public String cancellationReason;
    public String cancellationSubReason;
    public boolean courierReturn;
    public int quantity;
    public String fsn;
    public String sku;
    public String listingId;
    public String hsn;
    public String title;
    public List<String> packageIds;
    public PriceComponents priceComponents;
    public String serviceProfile;
    public boolean is_replacement;
}
