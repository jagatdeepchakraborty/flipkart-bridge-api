package com.flipkartbridge.flipkartbridgeapi.model;

import lombok.Data;

@Data
public class PriceComponents {
    public int sellingPrice;
    public int totalPrice;
    public int shippingCharge;
    public int customerPrice;
    public int flipkartDiscount;
}
