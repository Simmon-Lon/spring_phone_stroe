package com.simmon.spring_phone_stroe.service;

import com.simmon.spring_phone_stroe.vo.DateVO;
import com.simmon.spring_phone_stroe.vo.PhoneInfoVO;
import com.simmon.spring_phone_stroe.vo.SpecsPackageVO;

import java.util.List;

public interface PhoneService {
    public DateVO findDateVO();
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType);
    public SpecsPackageVO findSpecsByPhoneId(Integer integer);
    public void subStock(Integer specsId,Integer quantity);
}
