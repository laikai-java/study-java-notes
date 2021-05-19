package com.lk.spring.transaction.propagation.service;

import com.lk.spring.generator.entity.User2;
public interface User2Service{

    void addRequired(User2 record);
    void addRequiredException(User2 user);
}
