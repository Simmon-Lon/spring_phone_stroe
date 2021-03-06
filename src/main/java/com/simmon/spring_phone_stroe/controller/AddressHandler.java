package com.simmon.spring_phone_stroe.controller;

import com.simmon.spring_phone_stroe.exception.PhoneException;
import com.simmon.spring_phone_stroe.form.AddressForm;
import com.simmon.spring_phone_stroe.service.AddressService;
import com.simmon.spring_phone_stroe.util.ResultVOUtil;
import com.simmon.spring_phone_stroe.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressHandler {

    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(addressService.findAll());
    }

    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){/*启用验证@Valid*/
        if(bindingResult.hasErrors()){
            log.error("[添加地址] 参数错误,addressForm={}",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);
    }
    @PutMapping("/update")
    public ResultVO update(@Valid @RequestBody AddressForm addressForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("[修改地址] 参数错误,addressForm={}",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);
    }
}
