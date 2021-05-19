package com.lk.spring.business.mapper;

import com.lk.spring.generator.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();

    List<User> selectByName(String name);

    List<User> selectOneWithDollars(String name);

    List<User> selectByNameAndAge(@Param("name") String name,@Param("age") Integer age);

    int insertBatch(List<User> users);
}