package com.simmon.spring_phone_stroe.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * @author 11496
 */
@Data
@AllArgsConstructor
public class PhoneInfoVO {
    @JsonProperty("id")
    private Integer phoneId;
    @JsonProperty("title")
    private String phoneName;
    @JsonProperty("price")
    private String phonePrice;
    @JsonProperty("desc")
    private String phoneDescription;
    private List<Map<String,String>> tag;
    @JsonProperty("thumb")
    private String phoneIcon;
}
