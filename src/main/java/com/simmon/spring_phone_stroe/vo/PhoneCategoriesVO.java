package com.simmon.spring_phone_stroe.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhoneCategoriesVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")/*与给前端响应传输数据名为type*/
    private Integer categoryType;
}
