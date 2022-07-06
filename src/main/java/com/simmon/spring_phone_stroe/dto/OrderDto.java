package com.simmon.spring_phone_stroe.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private Integer specsId;
    private Integer phoneQuantity;
}
