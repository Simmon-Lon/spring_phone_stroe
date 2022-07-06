package com.simmon.spring_phone_stroe.util;

import java.util.Random;

public class KeyUtil {
    public static synchronized String createUniquekey(){
        Random random=new Random();
        Integer key=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(key);
    }
}
