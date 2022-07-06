package com.simmon.spring_phone_stroe.repository;

import com.simmon.spring_phone_stroe.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressRepositoryTest {

    @Autowired
    private BuyerAddressRepository buyerAddressRepository;

    @Test
    void findAll(){
        List<BuyerAddress> list=buyerAddressRepository.findAll();
        for (BuyerAddress buyerAddress : list) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    void save(){
        BuyerAddress buyerAddress=new BuyerAddress();
        buyerAddress.setAreaCode("330104");
        buyerAddress.setBuyerAddress("四川省自贡市自流井区汇源路");
        buyerAddress.setBuyerName("小憨");
        buyerAddress.setBuyerPhone("13605285251");
        buyerAddressRepository.save(buyerAddress);
    }
    @Test
    void update(){
        BuyerAddress buyerAddress=buyerAddressRepository.findById(37).get();
        buyerAddress.setBuyerName("小超");
        buyerAddressRepository.save(buyerAddress);
    }
}