package com.flipkartbridge.flipkartbridgeapi.model;

import lombok.Data;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */
@Data
public class Dimensions{
    public int length;
    public int breadth;
    public int height;
}

