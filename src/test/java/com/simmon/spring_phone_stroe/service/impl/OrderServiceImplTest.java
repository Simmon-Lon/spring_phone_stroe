package com.simmon.spring_phone_stroe.service.impl;

import com.simmon.spring_phone_stroe.dto.OrderDto;
import com.simmon.spring_phone_stroe.service.OrderService;
import com.simmon.spring_phone_stroe.vo.OrderDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void create(){
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName("张三");
        orderDto.setBuyerPhone("18182605431");
        orderDto.setBuyerAddress("广东省深圳市罗湖区科技路321号123室");
        orderDto.setSpecsId(1);
        orderDto.setPhoneQuantity(1);

        OrderDto result=orderService.create(orderDto);
        System.out.println(result);
    }

    @Test
    void findDetail(){
        OrderDetailVO orderDetailVO=orderService.findOrderDetailByOrderId("1588061173489688721");
        int id=0;
    }

    @Test
    void pay(){
        System.out.println(orderService.pay("1588061173489688721"));
    }
}