package com.simmon.spring_phone_stroe.vo;

import lombok.Data;

import java.util.List;

@Data
public class DateVO {
    private List<PhoneCategoriesVO> categories;
    private List<PhoneInfoVO> phones;
}
