package com.simmon.spring_phone_stroe.service;

import com.simmon.spring_phone_stroe.form.AddressForm;
import com.simmon.spring_phone_stroe.vo.AddressVO;

import java.util.List;

public interface AddressService {
    public List<AddressVO> findAll();
    public void saveOrUpdate(AddressForm addressForm);
}
