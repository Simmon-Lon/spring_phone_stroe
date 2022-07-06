package com.simmon.spring_phone_stroe.service.impl;

import com.simmon.spring_phone_stroe.entity.BuyerAddress;
import com.simmon.spring_phone_stroe.form.AddressForm;
import com.simmon.spring_phone_stroe.repository.BuyerAddressRepository;
import com.simmon.spring_phone_stroe.service.AddressService;
import com.simmon.spring_phone_stroe.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private BuyerAddressRepository buyerAddressRepository;

    @Override
    public List<AddressVO> findAll() {
        List<AddressVO> list=buyerAddressRepository.findAll().stream()
                .map(e -> new AddressVO(
                        e.getAddressId(),
                        e.getAreaCode(),
                        e.getBuyerName(),
                        e.getBuyerPhone(),
                        e.getBuyerPhone()
                )).collect(Collectors.toList());
        return list;
    }

    @Override
    public void saveOrUpdate(AddressForm addressForm) {
        BuyerAddress buyerAddress;
        if (addressForm.getId() == null){
            buyerAddress=new BuyerAddress();
        }else {
            buyerAddress = buyerAddressRepository.findById(addressForm.getId()).get();
        }
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(addressForm.getProvince())
                .append(addressForm.getCity())
                .append(addressForm.getCounty())
                .append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(stringBuffer.toString());
        buyerAddress.setAreaCode(addressForm.getAreaCode());

        buyerAddressRepository.save(buyerAddress);
    }

}
