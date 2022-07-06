package com.simmon.spring_phone_stroe.service;

import com.simmon.spring_phone_stroe.dto.OrderDto;
import com.simmon.spring_phone_stroe.vo.OrderDetailVO;


public interface OrderService {
    public OrderDto create(OrderDto orderDto);
    public OrderDetailVO findOrderDetailByOrderId(String orderId);
    public String pay(String orderId);
}
