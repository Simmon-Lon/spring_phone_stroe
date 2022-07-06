package com.simmon.spring_phone_stroe.controller;

import com.simmon.spring_phone_stroe.service.PhoneService;
import com.simmon.spring_phone_stroe.util.ResultVOUtil;
import com.simmon.spring_phone_stroe.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phone")
public class PhoneHandler {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/index")
    public ResultVO index(){
        return ResultVOUtil.success(phoneService.findDateVO());
    }

    @GetMapping("/findByCategoryType/{categoryType}")
    public ResultVO findByCategoryType(@PathVariable("categoryType") Integer categoryType){
        return ResultVOUtil.success(phoneService.findPhoneInfoVOByCategoryType(categoryType));
    }

    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public ResultVO findSpecsByPhoneId(@PathVariable("phoneId") Integer phoneId){
        return ResultVOUtil.success(phoneService.findSpecsByPhoneId(phoneId));
    }
}
