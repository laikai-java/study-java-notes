package com.lk.spring.transaction.propagation.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lk.spring.generator.entity.User2;
import com.lk.spring.generator.mapper.User2Mapper;
import com.lk.spring.transaction.propagation.service.User2Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User2ServiceImpl implements User2Service{

    @Resource
    private User2Mapper user2Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User2 record) {
        user2Mapper.insert(record);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User2 user){
        user2Mapper.insert(user);
        throw new RuntimeException();
    }
}
