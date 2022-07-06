package com.simmon.spring_phone_stroe.service.impl;

import com.simmon.spring_phone_stroe.dto.OrderDto;
import com.simmon.spring_phone_stroe.entity.OrderMaster;
import com.simmon.spring_phone_stroe.entity.PhoneInfo;
import com.simmon.spring_phone_stroe.entity.PhoneSpecs;
import com.simmon.spring_phone_stroe.enums.PayStatusEnum;
import com.simmon.spring_phone_stroe.enums.ResultEnum;
import com.simmon.spring_phone_stroe.exception.PhoneException;
import com.simmon.spring_phone_stroe.repository.OrderMasterRepository;
import com.simmon.spring_phone_stroe.repository.PhoneInfoRepository;
import com.simmon.spring_phone_stroe.repository.PhoneSpecsRepository;
import com.simmon.spring_phone_stroe.service.OrderService;
import com.simmon.spring_phone_stroe.service.PhoneService;
import com.simmon.spring_phone_stroe.util.KeyUtil;
import com.simmon.spring_phone_stroe.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PhoneService phoneService;

    @Override
    public OrderDto create(OrderDto orderDto) {
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);

        PhoneSpecs phoneSpecs=phoneSpecsRepository.findById(orderDto.getSpecsId()).get();
        if(phoneSpecs == null){
            log.error("[创建订单]规格为空,phoneSpecs={}",phoneSpecs);
            throw new PhoneException(ResultEnum.SPECS_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneSpecs,orderMaster);

        PhoneInfo phoneInfo=phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        if(phoneInfo == null){
            log.error("[查询手机]手机为空,phoneInfo={}",phoneInfo);
            throw new PhoneException(ResultEnum.PHONE_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneInfo,orderMaster);

        /*总价*/
        BigDecimal orderAmount=new BigDecimal(0);
        orderAmount=phoneSpecs.getSpecsPrice().divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDto.getPhoneQuantity()))
                .add(orderAmount)
                .add(new BigDecimal(10));
        orderMaster.setOrderAmount(orderAmount);

        /*orderId*/
        orderMaster.setOrderId(KeyUtil.createUniquekey());
        orderDto.setOrderId(orderMaster.getOrderId());

        /*payStatus*/
        orderMaster.setPayStatus(PayStatusEnum.UNPIAD.getCode());

        orderMasterRepository.save(orderMaster);
        phoneService.subStock(orderDto.getSpecsId(),orderDto.getPhoneQuantity());
        return orderDto;
    }

    @Override
    public OrderDetailVO findOrderDetailByOrderId(String orderId) {
        OrderDetailVO orderDetailVO=new OrderDetailVO();
        OrderMaster orderMaster=orderMasterRepository.findById(orderId).get();
        if(orderMaster == null){
            log.error("[查询订单]订单为空,orderMaster={}",orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster,orderDetailVO);
        orderDetailVO.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100))+".00");

        return orderDetailVO;
    }

    @Override
    public String pay(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findById(orderId).get();
        if(orderMaster == null){
            log.error("[支付订单]订单为空,orderMaster={}",orderMaster);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (orderMaster.getPayStatus().equals(PayStatusEnum.UNPIAD.getCode())){
            orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
            orderMasterRepository.save(orderMaster);
        }else {
            log.error("[支付订单]订单已支付,orderMaster={}",orderMaster);
            throw new PhoneException(ResultEnum.PHONE_PAY_EXIST);
        }
        return orderId;
    }
}
