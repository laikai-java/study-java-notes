package com.lk.spring.transaction.propagation.service.impl;

import com.lk.spring.generator.entity.User1;
import com.lk.spring.generator.entity.User2;
import com.lk.spring.transaction.propagation.service.TransactionPropagationService;
import com.lk.spring.transaction.propagation.service.User1Service;
import com.lk.spring.transaction.propagation.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TransactionPropagationServiceImpl implements TransactionPropagationService {

    @Autowired
    User1Service user1Service;
    @Autowired
    User2Service user2Service;

    /**
     * 两个required
     * 此场景外围方法没有开启事务。
     */
    @Override
    public void notransaction_exception_required_required() {
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);
        throw new RuntimeException();
    }

    @Override
    public void notransaction_required_required_exception() {
        User1 user1=new User1();
        user1.setName("张三");
        user1Service.addRequired(user1);

        User2 user2=new User2();
        user2.setName("李四");
        user2Service.addRequired(user2);
    }
}
