package com.simmon.spring_phone_stroe.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    UNPIAD(0,"未支付"),
    PAID(1,"已支付");
    public Integer code;
    public String msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
