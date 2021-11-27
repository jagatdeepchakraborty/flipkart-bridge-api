package com.flipkartbridge.flipkartbridgeapi.model;

import lombok.Data;

@Data
public class Package {
    public String id;
    public String name;
    public Dimensions dimensions;
    public int weight;
    public NotionalValue notional_value;
    public String description;
    public Handling handling;
}
