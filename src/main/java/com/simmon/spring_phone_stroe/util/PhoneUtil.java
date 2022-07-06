package com.simmon.spring_phone_stroe.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneUtil {
    public static List<Map<String,String>> createTag(String tag){
        String[] tags=tag.split("&");
        List<Map<String,String>> list=new ArrayList<>();
        for (String s : tags) {
            Map<String,String> map=new HashMap<>();
            map.put("name",s);
            list.add(map);
        }
        return list;
    }
}
