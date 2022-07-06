package com.simmon.spring_phone_stroe.service.impl;

import com.simmon.spring_phone_stroe.form.AddressForm;
import com.simmon.spring_phone_stroe.service.AddressService;
import com.simmon.spring_phone_stroe.vo.AddressVO;
import com.simmon.spring_phone_stroe.vo.OrderDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Test
    void findAll(){
        List<AddressVO> list=addressService.findAll();
        int id=0;
    }

    @Test
    void saveOrUpdate(){
        AddressForm addressForm=new AddressForm();
        addressForm.setId(38);
        addressForm.setName("李四");
        addressForm.setTel("15182656322");
        addressForm.setProvince("石家庄");
        addressForm.setCity("北京市");
        addressForm.setCounty("东城区");
        addressForm.setAreaCode("110101");
        addressForm.setAddressDetail("168号306室");
        addressService.saveOrUpdate(addressForm);
    }
}