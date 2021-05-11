package com.lk.spring.transaction.service;

import com.lk.generator.mapper.UserDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lk.generator.entity.User;
@Service
public class UserService{

    @Resource
    private UserDao userDao;

    
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    
    public int insert(User record) {
        return userDao.insert(record);
    }

    
    public int insertSelective(User record) {
        return userDao.insertSelective(record);
    }

    
    public User selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

}
