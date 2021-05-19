package com.lk.spring.generator.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * user_class
 * @author 
 */
@Data
public class UserClass implements Serializable {
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 课程名
     */
    private String className;

    private static final long serialVersionUID = 1L;
}