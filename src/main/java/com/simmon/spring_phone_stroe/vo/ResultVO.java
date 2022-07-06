package com.simmon.spring_phone_stroe.vo;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;/*泛型不用转型所以不使用object*/
}
