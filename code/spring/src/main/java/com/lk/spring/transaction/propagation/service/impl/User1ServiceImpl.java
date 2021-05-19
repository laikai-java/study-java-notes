package com.lk.spring.transaction.propagation.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lk.spring.generator.entity.User1;
import com.lk.spring.generator.mapper.User1Mapper;
import com.lk.spring.transaction.propagation.service.User1Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User1ServiceImpl implements User1Service{

    @Resource
    private User1Mapper user1Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 record) {
         user1Mapper.insert(record);
    }

}
