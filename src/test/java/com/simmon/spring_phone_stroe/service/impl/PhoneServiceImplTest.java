package com.simmon.spring_phone_stroe.service.impl;

import com.simmon.spring_phone_stroe.service.PhoneService;
import com.simmon.spring_phone_stroe.vo.DateVO;
import com.simmon.spring_phone_stroe.vo.PhoneInfoVO;
import com.simmon.spring_phone_stroe.vo.SpecsPackageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneServiceImplTest {

    /**
     * 打断点debug运行
     * */

    @Autowired
    private PhoneService phoneService;

    @Test
    void findDateVO(){
        DateVO dateVO=phoneService.findDateVO();
        int id=0;
    }

    @Test
    void findPhoneInfoVOByCategoryType(){
        List<PhoneInfoVO> list=phoneService.findPhoneInfoVOByCategoryType(2);
        int id=0;
    }

    @Test
    void findSku(){
        SpecsPackageVO specsPackageVO=phoneService.findSpecsByPhoneId(1);
        int id=0;
    }

    @Test
    void subStock(){
        phoneService.subStock(1,1);
    }
}