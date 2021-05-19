package com.lk.spring.business.service;

import com.lk.spring.business.mapper.UserDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.lk.spring.generator.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService{

    @Resource
    private UserDao userDao;

    
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }


    @Transactional(rollbackFor = Exception.class)
    public void insert(User record) {
        int insert = userDao.insert(record);
        throw new RuntimeException("sss");
    }

    
    public int insertSelective(User record) {
        return userDao.insertSelective(record);
    }

    
    public User selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    public List<User> selectAll() {
        return userDao.selectAll();
    }

    
    public int updateByPrimaryKeySelective(User record) {
        return userDao.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

}
