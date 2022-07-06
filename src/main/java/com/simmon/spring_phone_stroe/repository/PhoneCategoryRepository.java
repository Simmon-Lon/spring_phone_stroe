package com.simmon.spring_phone_stroe.repository;

import com.simmon.spring_phone_stroe.entity.PhoneCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneCategoryRepository extends JpaRepository<PhoneCategory,Integer> {
    public PhoneCategory findByCategoryType(Integer categoryType);
}
