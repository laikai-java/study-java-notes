package com.lk.spring.business.mapper;

import com.lk.spring.generator.entity.UserClass;

public interface UserClassDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserClass record);

    int insertSelective(UserClass record);

    UserClass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserClass record);

    int updateByPrimaryKey(UserClass record);
}