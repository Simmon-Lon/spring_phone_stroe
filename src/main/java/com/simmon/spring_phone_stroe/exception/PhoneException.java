package com.simmon.spring_phone_stroe.exception;

import com.simmon.spring_phone_stroe.enums.ResultEnum;

public class PhoneException extends RuntimeException{
    public PhoneException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }

    public PhoneException(String error) {
        super(error);
    }
}
