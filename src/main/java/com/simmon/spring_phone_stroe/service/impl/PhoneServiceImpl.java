package com.simmon.spring_phone_stroe.service.impl;

import com.simmon.spring_phone_stroe.entity.PhoneCategory;
import com.simmon.spring_phone_stroe.entity.PhoneInfo;
import com.simmon.spring_phone_stroe.entity.PhoneSpecs;
import com.simmon.spring_phone_stroe.enums.ResultEnum;
import com.simmon.spring_phone_stroe.exception.PhoneException;
import com.simmon.spring_phone_stroe.repository.PhoneCategoryRepository;
import com.simmon.spring_phone_stroe.repository.PhoneInfoRepository;
import com.simmon.spring_phone_stroe.repository.PhoneSpecsRepository;
import com.simmon.spring_phone_stroe.service.PhoneService;
import com.simmon.spring_phone_stroe.util.PhoneUtil;
import com.simmon.spring_phone_stroe.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Override
    public DateVO findDateVO() {
        DateVO dateVO=new DateVO();
        /**
         *类型
         * 手机
         */
        List<PhoneCategory> phoneCategoryList=phoneCategoryRepository.findAll();
        /*常规写法*/
       /* List<PhoneCategoriesVO> phoneCategoriesVOList=new ArrayList<>();
        for (PhoneCategory phoneCategory : phoneCategoryList) {
            PhoneCategoriesVO phoneCategoriesVO=new PhoneCategoriesVO();
            phoneCategoriesVO.setCategoryName(phoneCategory.getCategoryName());
            phoneCategoriesVO.setCategoryType(phoneCategory.getCategoryType());
            phoneCategoriesVOList.add(phoneCategoriesVO);
        }*/
        //jdk8写法lamda表达式
        List<PhoneCategoriesVO> phoneCategoriesVOList=phoneCategoryList.stream()
                .map(e -> new PhoneCategoriesVO(e.getCategoryName(),
                        e.getCategoryType())).collect(Collectors.toList());
        dateVO.setCategories(phoneCategoriesVOList);

        List<PhoneInfo> phoneInfoList=phoneInfoRepository.findAllByCategoryType(phoneCategoryList.get(0).getCategoryType());

        //第二种
        /*常规写法*/
        /*List<PhoneInfoVO> phoneInfoVOS =new ArrayList<>();
        for (PhoneInfo phoneInfo : phoneInfoList) {
            PhoneInfoVO phoneInfoVO =new PhoneInfoVO();
            *//*意思是找到entity.phoneInfo将里面的类型赋值给entity.phoneInfoVO,只复制数据源一样的*//*
            BeanUtils.copyProperties(phoneInfo,phoneInfoVO);*//*这里是spring框架自带的一种形式*//*
            phoneInfoVO.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
            phoneInfoVOS.add(phoneInfoVO);
        }*/
        //stream
        List<PhoneInfoVO> phoneInfoVOS =phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        dateVO.setPhones(phoneInfoVOS);
        return dateVO;
    }

    @Override
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType) {
        List<PhoneInfo> phoneInfoList=phoneInfoRepository.findAllByCategoryType(categoryType);
        List<PhoneInfoVO> phoneInfoVOList=phoneInfoList.stream()
                .map(e -> new PhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.createTag(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        return phoneInfoVOList;
    }

    @Override
    public SpecsPackageVO findSpecsByPhoneId(Integer phoneId) {
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecsList =phoneSpecsRepository.findAllByPhoneId(phoneId);

        //tree
        List<PhoneSpecsVO> phoneSpecsVOList =new ArrayList<>();

        List<PhoneSpecsCasVO> phoneSpecsCasVOList =new ArrayList<>();
        PhoneSpecsVO phoneSpecsVO;
        PhoneSpecsCasVO phoneSpecsCasVO;
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {
            phoneSpecsVO =new PhoneSpecsVO();
            phoneSpecsCasVO=new PhoneSpecsCasVO();
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsVO);
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsCasVO);
            phoneSpecsVOList.add(phoneSpecsVO);
            phoneSpecsCasVOList.add(phoneSpecsCasVO);
        }
        TreeVO treeVO=new TreeVO();
        treeVO.setV(phoneSpecsVOList);
        List<TreeVO> treeVOList=new ArrayList<>();
        treeVOList.add(treeVO);

        SkuVO skuVO=new SkuVO();
        Integer price= phoneInfo.getPhonePrice().intValue();
        skuVO.setPrice(price+".00");
        skuVO.setStock_num(phoneInfo.getPhoneStock());
        skuVO.setTree(treeVOList);
        skuVO.setList(phoneSpecsCasVOList);

        SpecsPackageVO specsPackageVO=new SpecsPackageVO();
        specsPackageVO.setSku(skuVO);
        Map<String,String> goods=new HashMap<>();
        goods.put("picture",phoneInfo.getPhoneIcon());
        specsPackageVO.setGoods(goods);
        return specsPackageVO;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        PhoneSpecs phoneSpecs =phoneSpecsRepository.findById(specsId).get();
        PhoneInfo phoneInfo=phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        Integer result=phoneSpecs.getSpecsStock()-quantity;
        if (result<0){
            log.error("库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        phoneSpecs.setSpecsStock(result);
        phoneSpecsRepository.save(phoneSpecs);

        result=phoneInfo.getPhoneStock()-quantity;
        if (result<0){
            log.error("库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }

        phoneInfo.setPhoneStock(result);
        phoneInfoRepository.save(phoneInfo);
    }
}
