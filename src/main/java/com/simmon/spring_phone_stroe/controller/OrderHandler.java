package com.simmon.spring_phone_stroe.controller;

import com.simmon.spring_phone_stroe.dto.OrderDto;
import com.simmon.spring_phone_stroe.exception.PhoneException;
import com.simmon.spring_phone_stroe.form.OrderForm;
import com.simmon.spring_phone_stroe.service.OrderService;
import com.simmon.spring_phone_stroe.service.PhoneService;
import com.simmon.spring_phone_stroe.util.ResultVOUtil;
import com.simmon.spring_phone_stroe.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 11496
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderHandler {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("[创建订单失败]参数错误,orderForm={}",orderForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getTel());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setSpecsId(orderForm.getSpecsId());
        orderDto.setPhoneQuantity(orderForm.getQuantity());

        OrderDto result=orderService.create(orderDto);

        Map<String,String> map=new HashMap<>();
        map.put("orderId",result.getOrderId());

        return ResultVOUtil.success(map);
    }

    @GetMapping("/detail/{orderId}")
    public ResultVO findOrderDetail(@PathVariable("orderId") String orderId){
        return ResultVOUtil.success(orderService.findOrderDetailByOrderId(orderId));
    }

    @PutMapping("/pay/{orderId}")
    public ResultVO pay(@PathVariable("orderId") String orderId){
        Map<String,String> map=new HashMap<>();
        map.put("orderId",orderService.pay(orderId));
        return ResultVOUtil.success(map);
    }
}
