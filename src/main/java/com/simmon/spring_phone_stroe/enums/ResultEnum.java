package com.simmon.spring_phone_stroe.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PHONE_STOCK_ERROR(0,"手机库存不足"),
    ORDER_NOT_EXIST(1,"订单不存在"),
    SPECS_NOT_EXIST(2,"规格不存在"),
    PHONE_NOT_EXIST(3,"手机不存在"),
    PHONE_PAY_EXIST(1,"手机以支付请勿重复支付");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
