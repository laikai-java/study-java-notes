package com.lk.spring.generator.mapper;

import com.lk.spring.generator.entity.User2;

public interface User2Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User2 record);

    int insertSelective(User2 record);

    User2 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User2 record);

    int updateByPrimaryKey(User2 record);
}