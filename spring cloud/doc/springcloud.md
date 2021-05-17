

# 微服务构成

## 注册中心

【服务 A 去调用服务 B，你不知道服务 B 在哪个服务器上面，不可能在配置文件上写死吧？让每一个服务都进行一个注册，把自己所在的服务器地址注册上去，其他的服务可以进行**服务发现**，知道别的服务器地址在哪。】

## RPC 框架

【不同的服务器怎么进行远程调用呢？RPC 框架可以进行远程的调用】

## 多环境隔离

【服务 A 在测试环境调用测试环境的服务 B，不会调用生产环境的服务 B。集成测试环境、功能测试环境、预发布测试环境等。】

## 自动化部署

【线上部署要纯粹的自动化部署】

## 分布式事务

【单块系统连接单个数据库，他只要用单个数据库的事务就行了，一个请求需要调用不同的服务器上面的不同的数据库。如果服务 A 更新了自己的库，服务 C 失败了，就需要分布式事务】

## 限流 / 熔断 / 降级

【一个服务挂了，其他服务也会连锁式宕机。**服务雪崩**现象】

## 配置中心

【不用每个服务更新都去更新配置文件。配置中心修改后，会把配置推送给服务，服务可以立马使用最新的配置】

## 监控中心

【服务很多，进行监控，业务指标，服务器的硬件情况】

## 链路监控

【每一个请求都是很多服务串成的一个链路，需要对每一条链路的执行进行一个监控。这条链路耗时多少时间，是否成功等】

## 日志中心

【每一个微服务都把服务上报到日志中心。在日志中心里对日志进行检索】

## 服务治理

【会包含之前的很多东西，对这些服务怎么来管控，怎么管理怎么治理。】

## API 网关

【用户都是发送请求给前端或者客户端，再前后端交互。API 网关是屏蔽掉后端所有的服务。减少前端调用的复杂度。API 网关会把不同的请求**路由**给不同的服务。进行微服务和后端的解耦】

## 安全认证



【安全认证、统一限流等。符合认证才能发现请求】





[![img](https://i.loli.net/2021/02/24/pK4PGEqTOSHasb9.png)](https://i.loli.net/2021/02/24/pK4PGEqTOSHasb9.png)

01_前言闲聊和课程说明
------------

[教学视频](https://www.bilibili.com/video/BV18E411x7eT)

[源码文件 1](https://github.com/zzyybs/atguigu_spirngcloud2020)、[源码文件 2](https://github.com/OT-mt/cloud2020)

02_零基础微服务架构理论入门
---------------

**什么是微服务**

> In short, the **microservice architectural style** is an approach to developing a single application as a **suite of small services**, each **running in its own process** and communicating with **lightweight** mechanisms, often an **HTTP** resource API. These services are built around **business capabilities** and **independently deployable** by fully **automated deployment** machinery. There is a bare minimum of centralized management of these services, which may be **written in different programming languages** and use different data storage technologies.——[James Lewis and Martin Fowler (2014)](https://martinfowler.com/articles/microservices.html)

*   微服务是一种架构风格
*   一个应用拆分为一组小型服务
*   每个服务运行在自己的进程内，也就是可独立部署和升级
*   服务之间使用轻量级 HTTP 交互
*   服务围绕业务功能拆分
*   可以由全自动部署机制独立部署
*   去中心化，服务自治。服务可以使用不同的语言、不同的存储技术

**主题词 01：现代数字化生活 - 落地维度**

*   手机
*   PC
*   智能家居
*   …

**主题词 02：分布式微服务架构 - 落地维度**

满足哪些维度？支撑起这些维度的具体技术？

![](https://img-blog.csdnimg.cn/img_convert/fa69e6841ce850672a3ec9cf8f4acad8.png)

*   服务调用
*   服务降级
*   服务注册与发先
*   服务熔断
*   负载均衡
*   服务消息队列
*   服务网关
*   配置中心管理
*   自动化构建部署
*   服务监控
*   全链路追踪
*   服务定时任务
*   调度操作

**Spring Cloud 简介**

是什么？符合微服务技术维度

**SpringCloud = 分布式微服务架构的站式解决方案，是多种微服务架构落地技术的集合体，俗称微服务全家桶**

猜猜 SpringCloud 这个大集合里有多少种技术?

![](https://img-blog.csdnimg.cn/img_convert/eeb48f15799b978e45ed980172c9f06e.png)

SpringCloud 俨然已成为微服务开发的主流技术栈，在国内开发者社区非常火爆。

**“微” 力十足，互联网大厂微服务架构案例**

京东的：

![](https://img-blog.csdnimg.cn/img_convert/2b9f44abea91af3c4b77c1c77eae6eb3.png)

阿里的：

![](https://img-blog.csdnimg.cn/img_convert/ef6092b03932cb7f6f4b7e20ff370261.png)

京东物流的：

![](https://img-blog.csdnimg.cn/img_convert/b7f15e802845e6ecdc4c13e2685789c1.png)

![](https://img-blog.csdnimg.cn/img_convert/60b96a66ac3b4dceda8f7ac2f8d8d79e.png)

**Spring Cloud 技术栈**

![](https://img-blog.csdnimg.cn/img_convert/fa347f3da197c3df86bf5d36961c8cde.png)

![](https://img-blog.csdnimg.cn/img_convert/b39a21012bed11a837c1edff840e5024.png)

![](https://img-blog.csdnimg.cn/img_convert/fc8ed10fca5f7cc56e4d4623a39245eb.png)

**总结**

![](https://img-blog.csdnimg.cn/img_convert/735076e24e1096e38b0ee8ef50f08a17.png)

03_第二季 Boot 和 Cloud 版本选型
------------------------

*   Spring Boot 2.X 版
    
    *   [源码地址](https://github.com/spring-projects/spring-boot/releases/)
    *   [Spring Boot 2 的新特性](https://github.com/spring-projects/spring-boot/wiki/spring-Boot-2.0-Release-Notes)
    *   通过上面官网发现，Boot 官方**强烈建议**你升级到 2.X 以上版本
*   Spring Cloud H 版
    
    *   [源码地址](https://github.com/spring-projects/spring-cloud)
    *   [官网](https://spring.io/projects/spring-cloud)
*   Spring Boot 与 Spring Cloud 兼容性查看
    
    *   [文档](https://spring.io/projects/spring-cloud#adding-spring-cloud-to-an-existing-spring-boot-application)
    *   [JSON 接口](https://start.spring.io/actuator/info)
*   接下来开发用到的组件版本
    
    *   Cloud - Hoxton.SR1
    *   Boot - 2.2.2.RELEASE
    *   Cloud Alibaba - 2.1.0.RELEASE
    *   Java - Java 8
    *   Maven - 3.5 及以上
    *   MySQL - 5.7 及以上

04_Cloud 组件停更说明
---------------

*   停更引发的 “升级惨案”
    
    *   停更不停用
    *   被动修复 bugs
    *   不再接受合并请求
    *   不再发布新版本
*   Cloud 升级
    
    *   服务注册中心
        
        *   × Eureka
        *   √ Zookeeper
        *   √ Consul
        *   √ Nacos
    *   服务调用
        
        *   √ Ribbon
        *   √ LoadBalancer
    *   服务调用 2
        
        *   × Feign
        *   √ OpenFeign
    *   服务降级
        
        *   × Hystrix
        *   √ resilience4j
        *   √ sentienl
    *   服务网关
        
        *   × Zuul
        *   ! Zuul2
        *   √ gateway
    *   服务配置
        
        *   × Config
        *   √ Nacos
    *   服务总线
        
        *   × Bus
        *   √ Nacos

[Spring Cloud 官方文档](https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/)

[Spring Cloud 中文文档](https://www.bookstack.cn/read/spring-cloud-docs/docs-index.md)

[Spring Boot 官方文档](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/)

05_父工程 Project 空间新建
-------------------

约定 > 配置 > 编码

创建微服务 cloud 整体聚合父工程 Project，有 8 个关键步骤：

1.  New Project - maven 工程 - create from archetype: maven-archetype-site
2.  聚合总父工程名字
3.  Maven 选版本
4.  工程名字
5.  字符编码 - Settings - File encoding
6.  注解生效激活 - Settings - Annotation Processors
7.  Java 编译版本选 8
8.  File Type 过滤 - Settings - File Type

> archetype 英 [ˈɑːkitaɪp] 美 [ˈɑːrkitaɪp]  
> n. 典型

> site 英 [saɪt] 美 [saɪt]  
> n. (建筑物、城镇等的) 地点，位置，建筑工地; 现场; 发生地; 场所; 网站; 站点  
> v. 使坐落在; 为… 选址

06_父工程 pom 文件
-------------

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.lk</groupId>
    <artifactId>spring-cloud</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <!-- 统一管理jar包版本 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <junit.version>4.12</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.16.18</lombok.version>
        <mysql.version>5.1.47</mysql.version>
        <druid.version>1.1.16</druid.version>
        <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
    </properties>


    <!-- 子模块继承之后，提供作用：
		锁定版本+子modlue不用写groupId和version -->
    <dependencyManagement>
        <dependencies>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR1-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud alibaba 2.1.0.RELEASE-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>${mysql.version}</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>${druid.version}</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>${mybatis.spring.boot.version}</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
                <optional>true</optional>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

07_复习 DependencyManagement 和 Dependencies
-----------------------------------------

Maven 使用 `dependencyManagement` 元素来提供了一种管理依赖版本号的方式。

通常会在一个组织或者项目的最顶层的父 POM 中看到 `dependencyManagement` 元素。

使用 pom.xml 中的 `dependencyManagement` 元素能让所有在子项目中引用个依赖而不用显式的列出版本量。

Maven 会沿着父子层次向上走，直到找到一个拥有 `dependencyManagement` 元素的项目，然后它就会使用这个  
`dependencyManagement` 元素中指定的版本号。

```
<dependencyManagement>
    <dependencies>
        <dependency>
        <groupId>mysq1</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.2</version>
        </dependency>
    <dependencies>
</dependencyManagement>
```

然后在子项目里就可以添加 `mysql-connector` 时可以不指定版本号，例如：

```
<dependencies>
    <dependency>
    <groupId>mysq1</groupId>
    <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencies>
```

这样做的**好处**就是：如果有多个子项目都引用同一样依赖，则可以避免在每个使用的子项目里都声明一个版本号，这样当想升级或切换到另一个版本时，只需要在顶层父容器里更新，而不需要一个一个子项目的修改；另外如果某个子项目需要另外的一个版本，只需要声明 version 就可。

*   `dependencyManagement` 里只是声明依赖，**并不实现引入**，因此**子项目需要显示的声明需要用的依赖**。
*   如果不在子项目中声明依赖，是不会从父项目中继承下来的；只有在子项目中写了该依赖项, 并且没有指定具体版本，才会从父项目中继承该项，并且 version 和 scope 都读取自父 pom。
*   如果子项目中指定了版本号，那么会使用子项目中指定的 jar 版本。

IDEA 右侧旁的 Maven 插件有 `Toggle ' Skip Tests' Mode` 按钮，这样 maven 可以跳过单元测试

父工程创建完成执行 `mvn : install` 将父工程发布到仓库方便子工程继承。

08_支付模块构建 (上)
-------------

创建微服务模块套路：

1.  建 Module
2.  改 POM
3.  写 YML
4.  主启动
5.  业务类

创建 cloud-provider-payment8001 微服务提供者支付 Module 模块：

**1. 建名为 cloud-provider-payment8001 的 Maven 工程**

**2. 改 POM**

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud</artifactId>
        <groupId>com.lk</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>


    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <!--mysql-connector-java-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!--jdbc-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

**3. 写 YML**

```
server:
  port: 8081
spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: root
mybatis:
  mapper-locations: classpath:mapper/*.xml


```

**4. 主启动**

```
package com.lk.cloud.provider.payment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class,args);
        System.out.println("支付服务启动成功");
    }
}

```

09_支付模块构建 (中)
-------------

**5. 业务类**

**SQL**：

```
CREATE TABLE `payment`(
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `serial` varchar(200) DEFAULT '',
	PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4
```

**Entities**：

实体类 Payment：

```
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
```

JSON 封装体 CommonResult：

```
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
```

**DAO**：

接口 PaymentDao：

```
package com.lk.cloud.provider.payment.dao;

import com.lk.cloud.provider.payment.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}

```

MyBatis 映射文件 PaymentMapper.xml，路径：resources/mapper/PaymentMapper.xml

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.lk.cloud.provider.payment.dao.PaymentDao">

    <resultMap id="BaseResultMap" type="com.lk.cloud.provider.payment.entity.Payment">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <result column="serial" property="serial" jdbcType="VARCHAR"/>
    </resultMap>

    <insert id="create" parameterType="com.lk.cloud.provider.payment.entity.Payment" useGeneratedKeys="true" keyProperty="id">
        insert into payment(serial)  values(#{serial});
    </insert>


    <select id="getPaymentById" parameterType="Long" resultMap="BaseResultMap">
        select * from payment where id=#{id};
    </select>

</mapper>
```

**Service**：

接口 PaymentService

```
package com.lk.cloud.provider.payment.service;

import com.lk.cloud.provider.payment.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}

```

实现类

```
package com.lk.cloud.provider.payment.service.impl;

import com.lk.cloud.provider.payment.dao.PaymentDao;
import com.lk.cloud.provider.payment.entity.Payment;
import com.lk.cloud.provider.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}

```

**Controller**：

```
package com.lk.cloud.provider.payment.controller;


import com.lk.cloud.provider.payment.common.CommonResult;
import com.lk.cloud.provider.payment.entity.Payment;
import com.lk.cloud.provider.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/create")
    public CommonResult create(Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);

        if(result > 0)
        {
            return new CommonResult(200,"插入数据库成功,serverPort: "+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功,serverPort:  "+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id,null);
        }
    }


}

```

10_支付模块构建 (下)
-------------

**6. 测试**

1.  浏览器 - http://localhost:8001/payment/get/1
2.  Postman - http://localhost:8001/payment/create?serial=lun2

**7. 小总结**

创建微服务模块套路：

1.  建 Module
2.  改 POM
3.  写 YML
4.  主启动
5.  业务类

11_热部署 Devtools
---------------

**开发时使用，生产环境关闭**

**1.Adding devtools to your project**

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

**2.Adding plugin to your pom.xml**

下段配置复制到聚合父类总工程的 pom.xml

```
<build>
    <!--
	<finalName>你的工程名</finalName>（单一工程时添加）
    -->
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
                <addResources>true</addResources>
            </configuration>
        </plugin>
    </plugins>
</build>
```

**3.Enabling automatic build**

File -> Settings(New Project Settings->Settings for New Projects) ->Complier

下面项勾选

*   Automatically show first error in editor
*   Display notification on build completion
*   Build project automatically
*   Compile independent modules in parallel

**4.Update the value of**

键入 Ctrl + Shift + Alt + /，打开 Registry，勾选：

*   compiler.automake.allow.when.app.running
    
*   actionSystem.assertFocusAccessFromEdt
    

**5. 重启 IDEA**

12_消费者订单模块 (上)
--------------

**1. 建 Module**

创建名为 cloud-consumer-order80 的 maven 工程。

**2. 改 POM**

```
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <parent>
    <artifactId>LearnCloud</artifactId>
    <groupId>com.lun</groupId>
    <version>1.0.0-SNAPSHOT</version>
  </parent>
  <modelVersion>4.0.0</modelVersion>

  <artifactId>cloud-consumer-order80</artifactId>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <scope>runtime</scope>
      <optional>true</optional>
    </dependency>
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

**3. 写 YML**

```
server:
  port: 80
```

**4. 主启动**

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class OrderMain80
{
    public static void main( String[] args ){
        SpringApplication.run(OrderMain80.class, args);
    }
}
```

**5. 业务类**

实体类：

```
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
```

```
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
```

控制层：

```
import com.lun.springcloud.entities.CommonResult;
import com.lun.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){

        return restTemplate.postForObject(PAYMENT_URL+"/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }
}
```

配置类：

```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
```

**6. 测试**

运行 cloud-consumer-order80 与 cloud-provider-payment8001 两工程

*   浏览器 - http://localhost/consumer/payment/get/1

**RestTemplate**

RestTemplate 提供了多种便捷访问远程 Http 服务的方法，是一种简单便捷的访问 restful 服务模板类，是 Spring 提供的用于访问 Rest 服务的客户端模板工具集

[官网地址](https://docs.spring.io/spring-framework/docs/5.2.2.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html)

使用：

*   使用 restTemplate 访问 restful 接口非常的简单粗暴无脑。
*   `(url, requestMap, ResponseBean.class)` 这三个参数分别代表。
*   REST 请求地址、请求参数、HTTP 响应转换被转换成的对象类型。

13_消费者订单模块 (下)
--------------

浏览器 - http://localhost/consumer/payment/create?serial=lun3

虽然，返回成功，但是观测数据库中，并没有创建 `serial` 为 `lun3` 的行。

解决之道：在 loud-provider-payment8001 工程的 `PaymentController` 中添加`@RequestBody` 注解。

```
public class PaymentController
{

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody/*添加到这里*/ Payment payment){
		...
    }
}
```

通过修改 idea 的 workspace.xml 的方式来**快速打开 Run Dashboard 窗口**（这个用来显示哪些 Spring Boot 工程运行，停止等信息。我 idea 2020.1 版本在名为 Services 窗口就可以显示哪些 Spring Boot 工程运行，停止等信息出来，所以这仅作记录参考）。

*   开启 Run DashBoard
    
    1.  打开工程路径下的. idea 文件夹的 workspace.xml
        
    2.  在`<component >`中修改或添加以下代码：
        

```
<option >
	<set>
		<option value="SpringBootApplicationConfigurationType"/>
    </set>
</option>
```

由于 idea 版本差异，可能需要关闭重启。

14_工程重构
-------

观察 cloud-consumer-order80 与 cloud-provider-payment8001 两工程有重复代码（entities 包下的实体）（坏味道），重构。

1. 新建 - cloud-api-commons

2.POM

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>LearnCloud</artifactId>
        <groupId>com.lun.springcloud</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-api-commons</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.1.0</version>
        </dependency>
    </dependencies>

</project>
```

3.entities

将 cloud-consumer-order80 与 cloud-provider-payment8001 两工程的公有 entities 包移至 cloud-api-commons 工程下。

4.maven clean、install cloud-api-commons 工程，以供给 cloud-consumer-order80 与 cloud-provider-payment8001 两工程调用。

5. 订单 80 和支付 8001 分别改造

*   将 cloud-consumer-order80 与 cloud-provider-payment8001 两工程的公有 entities 包移除
*   引入 cloud-api-commons 依赖

```
<dependency>
    <groupId>com.lun.springcloud</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```

6. 测试

15_Eureka 基础知识
--------------

**什么是服务治理**

Spring Cloud 封装了 Netflix 公司开发的 Eureka 模块来实现服务治理

在传统的 RPC 远程调用框架中，管理每个服务与服务之间依赖关系比较复杂，管理比较复杂，所以需要使用服务治理，管理服务于服务之间依赖关系，可以实现服务调用、负载均衡、容错等，实现服务发现与注册。

**什么是服务注册与发现**

Eureka 采用了 CS 的设计架构，Eureka Sever 作为服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用 Eureka 的客户端连接到 Eureka Server 并维持心跳连接。这样系统的维护人员就可以通过 Eureka Server 来监控系统中各个微服务是否正常运行。

在服务注册与发现中，有一个注册中心。当服务器启动的时候，会把当前自己服务器的信息比如服务地址通讯地址等以别名方式注册到注册中心上。另一方 (消费者服务提供者)，以该别名的方式去注册中心上获取到实际的服务通讯地址，然后再实现本地 RPC 调用 RPC 远程调用框架核心设计思想: 在于注册中心，因为使用注册中心管理每个服务与服务之间的一个依赖关系 (服务治理概念)。在任何 RPC 远程框架中，都会有一个注册中心存放服务地址相关信息 (接口地址)

![](https://img-blog.csdnimg.cn/img_convert/3956561052b9dc3909f16f1ff26d01bb.png)

**Eureka 包含两个组件: Eureka Server 和 Eureka Client**

**Eureka Server** 提供服务注册服务

各个微服务节点通过配置启动后，会在 EurekaServer 中进行注册，这样 EurekaServer 中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观看到。

**EurekaClient** 通过注册中心进行访问

它是一个 Java 客户端，用于简化 Eureka Server 的交互，客户端同时也具备一个内置的、使用轮询 (round-robin) 负载算法的负载均衡器。在应用启动后，将会向 Eureka Server 发送心跳 (默认周期为 30 秒)。如果 Eureka Server 在多个心跳周期内没有接收到某个节点的心跳，EurekaServer 将会从服务注册表中把这个服务节点移除（默认 90 秒)

16_EurekaServer 服务端安装
---------------------

IDEA 生成 eurekaServer 端服务注册中心，类似物业公司

1. 创建名为 cloud-eureka-server7001 的 Maven 工程

2. 修改 pom.xml

```
<!-- eureka新旧版本 -->
<!-- 以前的老版本（2018）-->
<dependency>
    <groupid>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>

<!-- 现在新版本（2020.2）--><!-- 我们使用最新的 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>LearnCloud</artifactId>
        <groupId>com.lun.springcloud</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-eureka-server7001</artifactId>

    <dependencies>
        <!--eureka-server-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
        <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.lun.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--boot web actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般通用配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
    </dependencies>

</project>
```

3. 添加 application.yml

```
server:
  port: 7001

eureka:
  instance:
    hostname: locathost #eureka服务端的实例名称
  client:
    #false表示不向注册中心注册自己。
    register-with-eureka: false
    #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    fetch-registry: false
    service-url:
      #设置与Eureka server交互的地址查询服务和注册服务都需要依赖这个地址。
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

4. 主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
    }
}
```

5. 测试运行`EurekaMain7001`，浏览器输入 `http://localhost:7001/`回车，会查看到 Spring Eureka 服务主页。

17_支付微服务 8001 入驻进 EurekaServer
------------------------------

EurekaClient 端 cloud-provider-payment8001 将注册进 EurekaServer 成为服务提供者 provider，类似学校对外提供授课服务。

1. 修改 cloud-provider-payment8001

2. 改 POM

添加 spring-cloud-starter-netflix-eureka-client 依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

3. 写 YML

```
eureka:
  client:
    #表示是否将自己注册进Eurekaserver默认为true。
    register-with-eureka: true
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetchRegistry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
```

4. 主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//<-----添加该注解
public class PaymentMain001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain001.class, args);
    }
}
```

5. 测试

*   启动 cloud-provider-payment8001 和 cloud-eureka-server7001 工程。
    
*   浏览器输入 - http://localhost:7001/ 主页内的 **Instances currently registered with Eureka** 会显示 cloud-provider-payment8001 的配置文件 application.yml 设置的应用名 `cloud-payment-service`
    

```
spring:
  application:
    name: cloud-payment-service
```

6. 自我保护机制

EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY’RE NOT. RENEWALS ARELESSER THAN THRESHOLD AND HENCFT ARE NOT BEING EXPIRED JUST TO BE SAFE.

紧急情况！EUREKA 可能错误地声称实例在没有启动的情况下启动了。续订小于阈值，因此实例不会为了安全而过期。

18_订单微服务 80 入驻进 EurekaServer
----------------------------

EurekaClient 端 cloud-consumer-order80 将注册进 EurekaServer 成为服务消费者 consumer，类似来上课消费的同学

1.cloud-consumer-order80

2.POM

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

3.YML

```
server:
  port: 80

spring:
  application:
    name: cloud-order-service

eureka:
  client:
    #表示是否将自己注册进Eurekaserver默认为true。
    register-with-eureka: true
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetchRegistry: true
    service-url:
      defaultZone: http://localhost:7001/eureka
```

4. 主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//<--- 添加该标签
public class OrderMain80
{
    public static void main( String[] args ){
        SpringApplication.run(OrderMain80.class, args);
    }
}
```

5. 测试

*   启动 cloud-provider-payment8001、cloud-eureka-server7001 和 cloud-consumer-order80 这三工程。
*   浏览器输入 http://localhost:7001 , 在主页的 Instances currently registered with Eureka 将会看到 cloud-provider-payment8001、cloud-consumer-order80 两个工程名。

**注意**，application.yml 配置中层次缩进和空格，两者不能少，否则，会抛出异常`Failed to bind properties under 'eureka.client.service-url' to java.util.Map <java.lang.String, java.lang.String>`。

19_Eureka 集群原理说明
----------------

1.Eureka 集群原理说明

![](https://img-blog.csdnimg.cn/img_convert/14570c4b7c4dd8653be6211da2675e45.png)

服务注册：将服务信息注册进注册中心

服务发现：从注册中心上获取服务信息

实质：存 key 服务命取 value 闭用地址

1 先启动 eureka 注册中心

2 启动服务提供者 payment 支付服务

3 支付服务启动后会把自身信息 (比服务地址 L 以别名方式注册进 eureka

4 消费者 order 服务在需要调用接口时，使用服务别名去注册中心获取实际的 RPC 远程调用地址

5 消费者获取调用地址后，底层实际是利用 HttpClient 技术实现远程调用

6 消费者获取服务地址后会缓存在本地 jvm 内存中，默认每间隔 30 秒更新—次服务调用地址

问题: 微服务 RPC 远程服务调用最核心的是什么  
高可用，试想你的注册中心只有一个 only one，万一它出故障了，会导致整个为服务环境不可用。

解决办法：搭建 Eureka 注册中心集群，实现负载均衡 + 故障容错。

**互相注册，相互守望**。

20_Eureka 集群环境构建
----------------

创建 cloud-eureka-server7002 工程，过程参考 [16_EurekaServer 服务端安装](#)



*   找到 C:\Windows\System32\drivers\etc 路径下的 hosts 文件，修改映射配置添加进 hosts 文件

```
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
```

*   修改 cloud-eureka-server7001 配置文件

```
server:
  port: 7001

eureka:
  instance:
    hostname: eureka7001.com #eureka服务端的实例名称
  client:
    register-with-eureka: false     #false表示不向注册中心注册自己。
    fetch-registry: false     #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    service-url:
    #集群指向其它eureka
      defaultZone: http://eureka7002.com:7002/eureka/
    #单机就是7001自己
      #defaultZone: http://eureka7001.com:7001/eureka/
```

*   修改 cloud-eureka-server7002 配置文件

```
server:
  port: 7002

eureka:
  instance:
    hostname: eureka7002.com #eureka服务端的实例名称
  client:
    register-with-eureka: false     #false表示不向注册中心注册自己。
    fetch-registry: false     #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    service-url:
    #集群指向其它eureka
      defaultZone: http://eureka7001.com:7001/eureka/
    #单机就是7002自己
      #defaultZone: http://eureka7002.com:7002/eureka/
```

实践的时候，遇到异常情况

在开启 cloud-eureka-server7002 时，开启失败，说 7002 端口被占用，然后在 cmd 中输入`netstat -ano | find "7002"`，查不到任何东西。

纳闷一阵，重启电脑，问题解决。

21_订单支付两微服务注册进 Eureka 集群
------------------------

*   将支付服务 8001 微服务，订单服务 80 微服务发布到上面 2 台 Eureka 集群配置中

将它们的配置文件的 eureka.client.service-url.defaultZone 进行修改

```
eureka:
  client:
    #表示是否将自己注册进Eurekaserver默认为true。
    register-with-eureka: true
    #是否从EurekaServer抓取已有的注册信息，默认为true。单节点无所谓，集群必须设置为true才能配合ribbon使用负载均衡
    fetchRegistry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka, http://eureka7002.com:7002/eureka
```

*   测试 01
    1.  先要启动 EurekaServer，7001/7002 服务
    2.  再要启动服务提供者 provider，8001
    3.  再要启动消费者，80
    4.  浏览器输入 - http://localhost/order/consumer/payment/get/1

22_支付微服务集群配置
------------

**支付服务提供者 8001 集群环境构建**

参考 cloud-provicer-payment8001

1. 新建 cloud-provider-payment8002

2. 改 POM

3. 写 YML - 端口 8002

4. 主启动

5. 业务类

6. 修改 8001/8002 的 Controller，添加 serverPort

```
@RestController
@Slf4j
public class PaymentController{

    @Value("${server.port}")
    private String serverPort;//添加serverPort

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入结果：" + result);

        if(result > 0) {
            return new CommonResult(200,"插入数据库成功,serverPort: "+serverPort/*添加到此处*/, result);
        }else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }
}
```

**负载均衡**

cloud-consumer-order80 订单服务访问地址不能写死

```
@Slf4j
@RestController
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    
    ...
}
```

使用 @LoadBalanced 注解赋予 RestTemplate 负载均衡的能力

```
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//使用@LoadBalanced注解赋予RestTemplate负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
```

ApplicationContextBean - 提前说一下 Ribbon 的负载均衡功能

**测试**

先要启动 EurekaServer，7001/7002 服务

再要启动服务提供者 provider，8001/8002 服务

浏览器输入 - http://localhost/consumer/payment/get/31

结果：负载均衡效果达到，8001/8002 端口交替出现

Ribbon 和 Eureka 整合后 Consumer 可以直接调用服务而不用再关心地址和端口号，且该服务还有负载功能。

**相互注册，相互守望**

![](https://img-blog.csdnimg.cn/img_convert/94c4c3eca8c2f9eb7497fe643b9b0622.png)

23_actuator 微服务信息完善
-------------------

主机名称：服务名称修改（也就是将 IP 地址，换成可读性高的名字）

修改 cloud-provider-payment8001，cloud-provider-payment8002

修改部分 - YML - eureka.instance.instance-id

```
eureka:
  ...
  instance:
    instance-id: payment8001 #添加此处
```

```
eureka:
  ...
  instance:
    instance-id: payment8002 #添加此处
```

修改之后

eureka 主页将显示 payment8001，payment8002 代替原来显示的 IP 地址。

访问信息有 IP 信息提示，（就是将鼠标指针移至 payment8001，payment8002 名下，会有 IP 地址提示）

修改部分 - YML - eureka.instance.prefer-ip-address

```
eureka:
  ...
  instance:
    instance-id: payment8001 
    prefer-ip-address: true #添加此处
```

```
eureka:
  ...
  instance:
    instance-id: payment8002
    prefer-ip-address: true #添加此处
```

24_服务发现 Discovery
-----------------

对于注册进 eureka 里面的微服务，可以通过服务发现来获得该服务的信息

*   修改 cloud-provider-payment8001 的 Controller

```
@RestController
@Slf4j
public class PaymentController{
	...
    
    @Resource
    private DiscoveryClient discoveryClient;

    ...

    @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: "+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return this.discoveryClient;
    }
}
```

*   8001 主启动类

```
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient//添加该注解
public class PaymentMain001 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMain001.class, args);
    }
}
```

*   自测

先要启动 EurekaSeryer

再启动 8001 主启动类，需要稍等一会儿

浏览器输入 http://localhost:8001/payment/discovery

浏览器输出：

```
{"services":["cloud-payment-service"],"order":0}
```

后台输出：

```
*****element: cloud-payment-service
CLOUD-PAYMENT-SERVICE	192.168.199.218	8001	http://192.168.199.218:8001
```

25_Eureka 自我保护理论知识
------------------

**概述**

保护模式主要用于一组客户端和 Eureka Server 之间存在网络分区场景下的保护。一旦进入保护模式，Eureka Server 将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据，也就是不会注销任何微服务。

如果在 Eureka Server 的首页看到以下这段提示，则说明 Eureka 进入了保护模式:

EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY’RE NOT. RENEWALS ARE LESSER THANTHRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUSTTO BE SAFE

**导致原因**

一句话：某时刻某一个微服务不可用了，Eureka 不会立刻清理，依旧会对该微服务的信息进行保存。

属于 CAP 里面的 AP 分支。

**为什么会产生 Eureka 自我保护机制?**

为了 EurekaClient 可以正常运行，防止与 EurekaServer 网络不通情况下，EurekaServer 不会立刻将 EurekaClient 服务剔除

**什么是自我保护模式?**

默认情况下，如果 EurekaServer 在一定时间内没有接收到某个微服务实例的心跳，EurekaServer 将会注销该实例 (默认 90 秒)。但是当网络分区故障发生 (延时、卡顿、拥挤) 时，微服务与 EurekaServer 之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的，此时本不应该注销这个微服务。Eureka 通过 “自我保护模式” 来解决这个问题——当 EurekaServer 节点在短时间内丢失过多客户端时 (可能发生了网络分区故障)，那么这个节点就会进入自我保护模式。

![](https://img-blog.csdnimg.cn/img_convert/264b66e8099a3761beaea2ba44b8fc5e.png)

自我保护机制∶默认情况下 EurekaClient 定时向 EurekaServer 端发送心跳包

如果 Eureka 在 server 端在一定时间内 (默认 90 秒) 没有收到 EurekaClient 发送心跳包，便会直接从服务注册列表中剔除该服务，但是在短时间 ( 90 秒中) 内丢失了大量的服务实例心跳，这时候 Eurekaserver 会开启自我保护机制，不会剔除该服务（该现象可能出现在如果网络不通但是 EurekaClient 为出现宕机，此时如果换做别的注册中心如果一定时间内没有收到心跳会将剔除该服务，这样就出现了严重失误，因为客户端还能正常发送心跳，只是网络延迟问题，而保护机制是为了解决此问题而产生的)。

**在自我保护模式中，Eureka Server 会保护服务注册表中的信息，不再注销任何服务实例**。

它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。一句话讲解：**好死不如赖活着**。

综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留）也不盲目注销任何健康的微服务。使用自我保护模式，可以让 Eureka 集群更加的健壮、稳定。

26_怎么禁止自我保护
-----------

*   在 eurekaServer 端 7001 处设置关闭自我保护机制

出厂默认，自我保护机制是开启的

使用 `eureka.server.enable-self-preservation = false` 可以禁用自我保护模式

```
eureka:
  ...
  server:
    #关闭自我保护机制，保证不可用服务被及时踢除
    enable-self-preservation: false
    eviction-interval-timer-in-ms: 2000
```

关闭效果：

spring-eureka 主页会显示出一句：

**THE SELF PRESERVATION MODE IS TURNED OFF. THIS MAY NOT PROTECT INSTANCE EXPIRY IN CASE OF NETWORK/OTHER PROBLEMS.**

*   生产者客户端 eureakeClient 端 8001

默认：

`eureka.instance.lease-renewal-interval-in-seconds=30`

`eureka.instance.lease-expiration-duration-in-seconds=90`

```
eureka:
  ...
  instance:
    instance-id: payment8001
    prefer-ip-address: true
    #心跳检测与续约时间
    #开发时没置小些，保证服务关闭后注册中心能即使剔除服务
    #Eureka客户端向服务端发送心跳的时间间隔，单位为秒(默认是30秒)
    lease-renewal-interval-in-seconds: 1
    #Eureka服务端在收到最后一次心跳后等待时间上限，单位为秒(默认是90秒)，超时将剔除服务
    lease-expiration-duration-in-seconds: 2
```

*   测试
    *   7001 和 8001 都配置完成
    *   先启动 7001 再启动 8001

结果：先关闭 8001，马上被删除了

27_Eureka 停更说明
--------------

https://github.com/Netflix/eureka/wiki

> **Eureka 2.0 (Discontinued)**
> 
> The existing open source work on eureka 2.0 is discontinued. The code base and artifacts that were released as part of the existing repository of work on the 2.x branch is considered use at your own risk.
> 
> Eureka 1.x is a core part of Netflix’s service discovery system and is still an active project.

我们用 ZooKeeper 代替 Eureka 功能。

28_支付服务注册进 zookeeper
--------------------

*   注册中心 Zookeeper

zookeeper 是一个分布式协调工具，可以实现注册中心功能

关闭 Linux 服务器防火墙后，启动 zookeeper 服务器

用到的 Linux 命令行：

*   `systemctl stop firewalld` 关闭防火墙
*   `systemctl status firewalld` 查看防火墙状态
*   `ipconfig` 查看 IP 地址
*   `ping` 查验结果

zookeeper 服务器取代 Eureka 服务器，zk 作为服务注册中心

视频里是用虚拟机 CentOS 开启 ZooKeeper，我打算在本机启动 ZooKeeper，具体操作参考 [ZooKeeper 学习笔记](https://blog.csdn.net/u011863024/article/details/107434932)。

*   服务提供者

1. 新建名为 cloud-provider-payment8004 的 Maven 工程。

2.POM

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>LearnCloud</artifactId>
        <groupId>com.lun.springcloud</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8004</artifactId>
    <dependencies>
        <!-- SpringBoot整合Web组件 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency><!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
            <groupId>com.lun.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!-- SpringBoot整合zookeeper客户端 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <!--先排除自带的zookeeper3.5.3 防止与3.4.9起冲突-->
            <exclusions>
                <exclusion>
                    <groupId>org.apache.zookeeper</groupId>
                    <artifactId>zookeeper</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--添加zookeeper3.4.9版本-->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.9</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

3.YML

```
#8004表示注册到zookeeper服务器的支付服务提供者端口号
server:
  port: 8004

#服务别名----注册zookeeper到注册中心名称
spring:
  application:
    name: cloud-provider-payment
  cloud:
    zookeeper:
      connect-string: 127.0.0.1:2181 # 192.168.111.144:2181 #
```

4. 主启动类

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
```

5.Controller

```
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk()
    {
        return "springcloud with zookeeper: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
```

6. 启动 8004 注册进 zookeeper（要先启动 zookeeper 的 server）

*   验证测试：浏览器 - http://localhost:8004/payment/zk
    
*   验证测试 2 ：接着用 zookeeper 客户端操作
    

```
[zk: localhost:2181(CONNECTED) 3] ls /
[dubbo, services, zookeeper]
[zk: localhost:2181(CONNECTED) 4] ls /services
[zookeeper-cloud-provider-payment]
[zk: localhost:2181(CONNECTED) 5] ls /services/zookeeper-cloud-provider-payment /
dubbo       services    zookeeper
[zk: localhost:2181(CONNECTED) 5] ls /services/zookeeper-cloud-provider-payment /
'ls path [watch]' has been deprecated. Please use 'ls [-w] path' instead.
[57d3b227-2733-4f4d-8701-8d26e258bfed]
[zk: localhost:2181(CONNECTED) 6] ls /services/zookeeper-cloud-provider-payment/57d3b227-2733-4f4d-8701-8d26e258bfed
[]
[zk: localhost:2181(CONNECTED) 7] get /services/zookeeper-cloud-provider-payment/57d3b227-2733-4f4d-8701-8d26e258bfed
{"name":"zookeeper-cloud-provider-payment","id":"57d3b227-2733-4f4d-8701-8d26e258bfed","address":"DESKTOP-9GB45MB","port":8084,"sslPort":null,"payload":{"@class":"org.springframework.cloud.zookeeper.discovery.ZookeeperInstance","id":"application-1","name":"zookeeper-cloud-provider-payment","metadata":{}},"registrationTimeUTC":1618713229034,"serviceType":"DYNAMIC","uriSpec":{"parts":[{"value":"scheme","variable":true},{"value":"://","variable":false},{"value":"address","variable":true},{"value":":","variable":false},{"value":"port","variable":true}]}}
```

json 格式化 `get /services/cloud-provider-payment/a4567f50-6ad9-47a3-9fbb-7391f41a9f3d` 的结果：

```
{
	"name": "zookeeper-cloud-provider-payment",
	"id": "57d3b227-2733-4f4d-8701-8d26e258bfed",
	"address": "DESKTOP-9GB45MB",
	"port": 8084,
	"sslPort": null,
	"payload": {
		"@class": "org.springframework.cloud.zookeeper.discovery.ZookeeperInstance",
		"id": "application-1",
		"name": "zookeeper-cloud-provider-payment",
		"metadata": {}
	},
	"registrationTimeUTC": 1618713229034,
	"serviceType": "DYNAMIC",
	"uriSpec": {
		"parts": [{
			"value": "scheme",
			"variable": true
		}, {
			"value": "://",
			"variable": false
		}, {
			"value": "address",
			"variable": true
		}, {
			"value": ":",
			"variable": false
		}, {
			"value": "port",
			"variable": true
		}]
	}
}
```

29_临时还是持久节点
-----------

ZooKeeper 的服务节点是**临时节点**，没有 Eureka 那含情脉脉。

30_订单服务注册进 zookeeper
--------------------

1. 新建 cloud-consumerzk-order80

2.POM

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>LearnCloud</artifactId>
        <groupId>com.lun.springcloud</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumerzk-order80</artifactId>

    <dependencies>
        <!-- SpringBoot整合Web组件 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- SpringBoot整合zookeeper客户端 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
            <!--先排除自带的zookeeper-->
            <exclusions>
                <exclusion>
                    <groupId>org.apache.zookeeper</groupId>
                    <artifactId>zookeeper</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--添加zookeeper3.4.9版本-->
        <dependency>
            <groupId>org.apache.zookeeper</groupId>
            <artifactId>zookeeper</artifactId>
            <version>3.4.9</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

3.YML

```
server:
  port: 80

#服务别名----注册zookeeper到注册中心名称
spring:
  application:
    name: cloud-consumer-order
  cloud:
    zookeeper:
      connect-string: 127.0.0.1:2181 # 192.168.111.144:2181 #
```

4. 主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderZKMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZKMain80.class, args);
    }
}
```

5. 业务类

```
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig
{
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
```

```
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZKController
{
    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo()
    {
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return result;
    }
}
```

6. 验证测试

运行 ZooKeeper 服务端，cloud-consumerzk-order80，cloud-provider-payment8004。

打开 ZooKeeper 客户端：

```
[zk: localhost:2181(CONNECTED) 0] ls /
[services, zookeeper]
[zk: localhost:2181(CONNECTED) 1] ls /services
[cloud-consumer-order, cloud-provider-payment]
[zk: localhost:2181(CONNECTED) 2]
```

7. 访问测试地址 - http://localhost/consumer/payment/zk

31_Consul 简介
------------

[Consul 官网](https://www.consul.io/)

[Consul 下载地址](https://www.consul.io/downloads)

> **What is Consul?**
> 
> Consul is a service mesh solution providing a full featured control plane with service discovery, configuration, and segmentation functionality. Each of these features can be used individually as needed, or they can be used together to build a full service mesh. Consul requires a data plane and supports both a proxy and native integration model. Consul ships with a simple built-in proxy so that everything works out of the box, but also supports 3rd party proxy integrations such as Envoy. [link](https://www.consul.io/docs/intro#what-is-consul)
> 
> Consul 是一个服务网格解决方案，它提供了一个功能齐全的控制平面，具有服务发现、配置和分段功能。这些特性中的每一个都可以根据需要单独使用，也可以一起用于构建全服务网格。Consul 需要一个数据平面，并支持代理和本机集成模型。Consul 船与一个简单的内置代理，使一切工作的开箱即用，但也支持第三方代理集成，如 Envoy。

> consul  
> 英 [ˈkɒnsl] 美 [ˈkɑːnsl]  
> n. 领事

Consul 是一套开源的分布式服务发现和配置管理系统，由 HashiCorp 公司用 Go 语言开发。

提供了微服务系统中的服务治理、配置中心、控制总线等功能。这些功能中的每一个都可以根据需要单独使用，也可以一起使用以构建全方位的服务网格，总之 Consul 提供了一种完整的服务网格解决方案。

它具有很多优点。包括：基于 raft 协议，比较简洁；支持健康检查，同时支持 HTTP 和 DNS 协议支持跨数据中心的 WAN 集群提供图形界面跨平台，支持 Linux、Mac、Windows。

> The key features of Consul are:
> 
> *   **Service Discovery**: Clients of Consul can register a service, such as `api` or `mysql`, and other clients can use Consul to discover providers of a given service. Using either DNS or HTTP, applications can easily find the services they depend upon.
> *   **Health Checking**: Consul clients can provide any number of health checks, either associated with a given service (“is the webserver returning 200 OK”), or with the local node (“is memory utilization below 90%”). This information can be used by an operator to monitor cluster health, and it is used by the service discovery components to route traffic away from unhealthy hosts.
> *   **KV Store**: Applications can make use of Consul’s hierarchical key/value store for any number of purposes, including dynamic configuration, feature flagging, coordination, leader election, and more. The simple HTTP API makes it easy to use.
> *   **Secure Service Communication**: Consul can generate and distribute TLS certificates for services to establish mutual TLS connections. [Intentions](https://www.consul.io/docs/connect/intentions) can be used to define which services are allowed to communicate. Service segmentation can be easily managed with intentions that can be changed in real time instead of using complex network topologies and static firewall rules.
> *   **Multi Datacenter**: Consul supports multiple datacenters out of the box. This means users of Consul do not have to worry about building additional layers of abstraction to grow to multiple regions.
> 
> [link](https://www.consul.io/docs/intro#what-is-consul)

能干嘛？

*   服务发现 - 提供 HTTP 和 DNS 两种发现方式。
*   健康监测 - 支持多种方式，HTTP、TCP、Docker、Shell 脚本定制化
*   KV 存储 - Key、Value 的存储方式
*   多数据中心 - Consul 支持多数据中心
*   可视化 Web 界面

[怎么玩](https://www.springcloud.cc/spring-cloud-consul.html)

32_安装并运行 Consul
---------------

[官网安装说明](https://learn.hashicorp.com/consul/getting-started/install.html)

windows 版解压缩后，得 consul.exe，打开 cmd

*   查看版本 `consul -v`：

```
D:\Consul>consul -v
Consul v1.9.3
Revision f55da9306
Protocol 2 spoken by default, understands 2 to 3 (agent will automatically use protocol >2 when speaking to compatible agents)
```

*   开发模式启动 `consul agent -dev`：

浏览器输入 - http://localhost:8500/ - 打开 Consul 控制页。

33_服务提供者注册进 Consul
------------------

1. 新建 Module 支付服务 provider8006

2.POM

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>LearnCloud</artifactId>
        <groupId>com.lun.springcloud</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-providerconsul-payment8006</artifactId>
    <dependencies>
        <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.lun.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--SpringCloud consul-server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
        <!-- SpringBoot整合Web组件 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--日常通用jar包配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

3.YML

```
###consul服务端口号
server:
  port: 8006

spring:
  application:
    name: consul-provider-payment
####consul注册中心地址
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
```

4. 主启动类

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8006
{
    public static void main(String[] args) {
            SpringApplication.run(PaymentMain8006.class, args);
    }
}
```

5. 业务类 Controller

```
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul()
    {
        return "springcloud with consul: "+serverPort+"\t   "+ UUID.randomUUID().toString();
    }
}
```

6. 验证测试

*   http://localhost:8006/payment/consul
*   http://localhost:8500 - 会显示 provider8006

34_服务消费者注册进 Consul
------------------

1. 新建 Module 消费服务 order80 - cloud-consumerconsul-order80

2.POM

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>LearnCloud</artifactId>
        <groupId>com.lun.springcloud</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumerconsul-order80</artifactId>
    <dependencies>
        <!--SpringCloud consul-server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-consul-discovery</artifactId>
        </dependency>
        <!-- SpringBoot整合Web组件 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--日常通用jar包配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

3.YML

```
###consul服务端口号
server:
  port: 80

spring:
  application:
    name: cloud-consumer-order
####consul注册中心地址
  cloud:
    consul:
      host: localhost
      port: 8500
      discovery:
        #hostname: 127.0.0.1
        service-name: ${spring.application.name}
```

4. 主启动类

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或者zookeeper作为注册中心时注册服务
public class OrderConsulMain80
{
    public static void main(String[] args) {
            SpringApplication.run(OrderConsulMain80.class, args);
    }
}
```

5. 配置 Bean

```
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 */
@Configuration
public class ApplicationContextConfig
{
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
```

6.Controller

```
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderConsulController
{
    public static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo()
    {
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return result;
    }
}
```

7. 验证测试

运行 consul，cloud-providerconsul-payment8006，cloud-consumerconsul-order80

http://localhost:8500/ 主页会显示出 consul，cloud-providerconsul-payment8006，cloud-consumerconsul-order80 三服务。

8. 访问测试地址 - http://localhost/consumer/payment/consul

35_三个注册中心异同点
------------

<table><thead><tr><th>组件名</th><th>语言 CAP</th><th>服务健康检查</th><th>对外暴露接口</th><th>Spring Cloud 集成</th></tr></thead><tbody><tr><td>Eureka</td><td>Java</td><td>AP</td><td>可配支持</td><td>HTTP</td></tr><tr><td>Consul</td><td>Go</td><td>CP</td><td>支持</td><td>HTTP/DNS</td></tr><tr><td>Zookeeper</td><td>Java</td><td>CP</td><td>支持客户端</td><td>已集成</td></tr></tbody></table>

CAP：

*   C：Consistency (强一致性)
    
*   A：Availability (可用性)
    
*   P：Partition tolerance （分区容错性)
    

![](https://img-blog.csdnimg.cn/img_convert/b41e0791c9652955dd3a2bc9d2d60983.png)

**最多只能同时较好的满足两个**。

CAP 理论的核心是：**一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求**。

因此，根据 CAP 原理将 NoSQL 数据库分成了满足 CA 原则、满足 CP 原则和满足 AP 原则三大类:

*   CA - 单点集群，满足—致性，可用性的系统，通常在可扩展性上不太强大。
*   CP - 满足一致性，分区容忍必的系统，通常性能不是特别高。
*   AP - 满足可用性，分区容忍性的系统，通常可能对一致性要求低一些。

AP 架构（Eureka）

当网络分区出现后，为了保证可用性，系统 B 可以返回旧值，保证系统的可用性。

结论：违背了一致性 C 的要求，只满足可用性和分区容错，即 AP

![](https://img-blog.csdnimg.cn/img_convert/2d07748539300b9c466eb1d9bac5cd1b.png)

CP 架构（ZooKeeper/Consul）

当网络分区出现后，为了保证一致性，就必须拒接请求，否则无法保证一致性。

结论：违背了可用性 A 的要求，只满足一致性和分区容错，即 CP。

![](https://img-blog.csdnimg.cn/img_convert/c6f2926a97420015fcebc89b094c5598.png)

CP 与 AP 对立同一的矛盾关系。

36_Ribbon 入门介绍
--------------

Spring Cloud Ribbon 是基于 Netflix Ribbon 实现的一套**客户端负载均衡的工具**。

简单的说，Ribbon 是 Netflix 发布的开源项目，主要功能是提供**客户端的软件负载均衡算法和服务调用**。Ribbon 客户端组件提供一系列完善的配置项如连接超时，重试等。

简单的说，就是在配置文件中列出 Load Balancer(简称 LB) 后面所有的机器，Ribbon 会自动的帮助你基于某种规则 (如简单轮询，随机连接等）去连接这些机器。我们很容易使用 Ribbon 实现自定义的负载均衡算法。

> ribbon
> 
> 英 [ˈrɪbən] 美 [ˈrɪbən]
> 
> n. (用于捆绑或装饰的) 带子; 丝带; 带状物; 狭长的东西; 绶带; 勋带

[Github - Ribbon](https://github.com/Netflix/ribbon/wiki/Getting-Started)

Ribbon 目前也进入维护模式。

Ribbon 未来可能被 Spring Cloud LoadBalacer 替代。

**LB 负载均衡 (Load Balance) 是什么**

简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的 HA (高可用)。

常见的负载均衡有软件 Nginx，LVS，硬件 F5 等。

**Ribbon 本地负载均衡客户端 VS Nginx 服务端负载均衡区别**

Nginx 是服务器负载均衡，客户端所有请求都会交给 nginx，然后由 nginx 实现转发请求。即负载均衡是由服务端实现的。  
Ribbon 本地负载均衡，在调用微服务接口时候，会在注册中心上获取注册信息服务列表之后缓存到 JVM 本地，从而在本地实现 RPC 远程服务调用技术。

**集中式 LB**

即在服务的消费方和提供方之间使用独立的 LB 设施 (可以是硬件，如 F5, 也可以是软件，如 nginx)，由该设施负责把访问请求通过某种策略转发至服务的提供方;

**进程内 LB**

将 LB 逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。

**Ribbon 就属于进程内 LB**，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址。

**一句话**

负载均衡 + RestTemplate 调用

37_Ribbon 的负载均衡和 Rest 调用
------------------------

**架构说明**

总结：Ribbon 其实就是一个软负载均衡的客户端组件，它可以和其他所需请求的客户端结合使用，和 Eureka 结合只是其中的一个实例。

![](https://img-blog.csdnimg.cn/img_convert/145b915e56a85383b3ad40f0bb2256e0.png)

Ribbon 在工作时分成两步：

*   第一步先选择 EurekaServer , 它优先选择在同一个区域内负载较少的 server。
    
*   第二步再根据用户指定的策略，在从 server 取到的服务注册列表中选择一个地址。
    

其中 Ribbon 提供了多种策略：比如轮询、随机和根据响应时间加权。

**POM**

先前工程项目没有引入 spring-cloud-starter-ribbon 也可以使用 ribbon。

```
<dependency>
    <groupld>org.springframework.cloud</groupld>
    <artifactld>spring-cloud-starter-netflix-ribbon</artifactid>
</dependency>
```

这是因为 spring-cloud-starter-netflix-eureka-client 自带了 spring-cloud-starter-ribbon 引用。

**二说 RestTemplate 的使用**

[RestTemplate Java Doc](https://docs.spring.io/spring-framework/docs/5.2.2.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html)

**getForObject() / getForEntity()** - GET 请求方法

getForObject()：返回对象为响应体中数据转化成的对象，基本上可以理解为 Json。

getForEntity()：返回对象为 ResponseEntity 对象，包含了响应中的一些重要信息，比如响应头、响应状态码、响应体等。

```
@GetMapping("/consumer/payment/getForEntity/{id}")
public CommonResult<Payment> getPayment2(@PathVariable("id") Long id)
{
    ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

    if(entity.getStatusCode().is2xxSuccessful()){
        return entity.getBody();//getForObject()
    }else{
        return new CommonResult<>(444,"操作失败");
    }
}
```

**postForObject() / postForEntity()** - POST 请求方法

38_Ribbon 默认自带的负载规则
-------------------

lRule：根据特定算法中从服务列表中选取一个要访问的服务

![](https://img-blog.csdnimg.cn/img_convert/87243c00c0aaea211819c0d8fc97e445.png)

*   RoundRobinRule 轮询
*   RandomRule 随机
*   RetryRule 先按照 RoundRobinRule 的策略获取服务，如果获取服务失败则在指定时间内会进行重
*   WeightedResponseTimeRule 对 RoundRobinRule 的扩展，响应速度越快的实例选择权重越大，越容易被选择
*   BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
*   AvailabilityFilteringRule 先过滤掉故障实例，再选择并发较小的实例
*   ZoneAvoidanceRule 默认规则, 复合判断 server 所在区域的性能和 server 的可用性选择服务器

39_Ribbon 负载规则替换
----------------

1. 修改 cloud-consumer-order80

2. 注意配置细节

官方文档明确给出了警告:

这个自定义配置类不能放在 @ComponentScan 所扫描的当前包下以及子包下，

否则我们自定义的这个配置类就会被所有的 Ribbon 客户端所共享，达不到特殊化定制的目的了。

（**也就是说不要将 Ribbon 配置类与主启动类同包**）

3. 新建 package - com.lun.myrule

4. 在 com.lun.myrule 下新建 MySelfRule 规则类

```
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
```

5. 主启动类添加 @RibbonClient

```
import com.lun.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//添加到此处
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderMain80
{
    public static void main( String[] args ){
        SpringApplication.run(OrderMain80.class, args);
    }
}
```

6. 测试

开启 cloud-eureka-server7001，cloud-consumer-order80，cloud-provider-payment8001，cloud-provider-payment8002

浏览器 - 输入 http://localhost/consumer/payment/get/1

返回结果中的 serverPort 在 8001 与 8002 两种间反复横跳。

40_Ribbon 默认负载轮询算法原理
--------------------

**默认负载轮训算法: rest 接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标，每次服务重启动后 rest 接口计数从 1 开始**。

`List<Servicelnstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");`

如:

*   List [0] instances = 127.0.0.1:8002
*   List [1] instances = 127.0.0.1:8001

8001+ 8002 组合成为集群，它们共计 2 台机器，集群总数为 2，按照轮询算法原理：

*   当总请求数为 1 时: 1%2=1 对应下标位置为 1，则获得服务地址为 127.0.0.1:8001
*   当总请求数位 2 时: 2%2=О 对应下标位置为 0，则获得服务地址为 127.0.0.1:8002
*   当总请求数位 3 时: 3%2=1 对应下标位置为 1，则获得服务地址为 127.0.0.1:8001
*   当总请求数位 4 时: 4%2=О 对应下标位置为 0，则获得服务地址为 127.0.0.1:8002
*   如此类推…

41_RoundRobinRule 源码分析
----------------------

```
public interface IRule{
    /*
     * choose one alive server from lb.allServers or
     * lb.upServers according to key
     * 
     * @return choosen Server object. NULL is returned if none
     *  server is available 
     */

    //重点关注这方法
    public Server choose(Object key);
    
    public void setLoadBalancer(ILoadBalancer lb);
    
    public ILoadBalancer getLoadBalancer();    
}
```

```
package com.netflix.loadbalancer;

import com.netflix.client.config.IClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The most well known and basic load balancing strategy, i.e. Round Robin Rule.
 *
 * @author stonse
 * @author Nikos Michalakis <nikos@netflix.com>
 *
 */
public class RoundRobinRule extends AbstractLoadBalancerRule {

    private AtomicInteger nextServerCyclicCounter;
    private static final boolean AVAILABLE_ONLY_SERVERS = true;
    private static final boolean ALL_SERVERS = false;

    private static Logger log = LoggerFactory.getLogger(RoundRobinRule.class);

    public RoundRobinRule() {
        nextServerCyclicCounter = new AtomicInteger(0);
    }

    public RoundRobinRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    //重点关注这方法。
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    /**
     * Inspired by the implementation of {@link AtomicInteger#incrementAndGet()}.
     *
     * @param modulo The modulo to bound the value of the counter.
     * @return The next value.
     */
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
 
        }
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
```

42_Ribbon 之手写轮询算法
-----------------

自己试着写一个类似 RoundRobinRule 的本地负载均衡器。

*   7001/7002 集群启动
    
*   8001/8002 微服务改造 - controller
    

```
@RestController
@Slf4j
public class PaymentController{

    ...
    
	@GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;//返回服务接口
    }
    
    ...
}
```

*   80 订单微服务改造

1.ApplicationContextConfig 去掉注解 @LoadBalanced，OrderMain80 去掉注解 @RibbonClient

```
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
```

2. 创建 LoadBalancer 接口

```
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 */
public interface LoadBalancer
{
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
```

3.MyLB

实现 LoadBalancer 接口

```
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 */
@Component//需要跟主启动类同包，或者在其子孙包下。
public class MyLB implements LoadBalancer
{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement()
    {
        int current;
        int next;

        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****第几次访问，次数next: "+next);
        return next;
    }

    //负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标  ，每次服务重启动后rest接口计数从1开始。
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances)
    {
        int index = getAndIncrement() % serviceInstances.size();

        return serviceInstances.get(index);
    }
}
```

4.OrderController

```
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.lun.springcloud.lb.LoadBalancer;

@Slf4j
@RestController
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

	...

    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

	...

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB()
    {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size() <= 0){
            return null;
        }

        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();

        return restTemplate.getForObject(uri+"/payment/lb",String.class);

    }
}
```

5. 测试 不停地刷新 http://localhost/consumer/payment/lb，可以看到 8001/8002 交替出现。

43_OpenFeign 是什么
----------------

[官方文档](https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/#spring-cloud-openfeign)

[Github 地址](https://github.com/spring-cloud/spring-cloud-openfeign)

> [Feign](https://github.com/OpenFeign/feign) is a declarative web service client. It makes writing web service clients easier. To use Feign create an interface and annotate it. It has pluggable annotation support including Feign annotations and JAX-RS annotations. Feign also supports pluggable encoders and decoders. Spring Cloud adds support for Spring MVC annotations and for using the same `HttpMessageConverters` used by default in Spring Web. Spring Cloud integrates Ribbon and Eureka, as well as Spring Cloud LoadBalancer to provide a load-balanced http client when using Feign. [link](https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/#spring-cloud-feign)
> 
> Feign 是一个声明式 WebService 客户端。使用 Feign 能让编写 Web Service 客户端更加简单。它的使用方法是**定义一个服务接口然后在上面添加注解**。Feign 也支持可拔插式的编码器和解码器。Spring Cloud 对 Feign 进行了封装，使其支持了 Spring MVC 标准注解和 HttpMessageConverters。Feign 可以与 Eureka 和 Ribbon 组合使用以支持负载均衡。

**Feign 能干什么**

Feign 旨在使编写 Java Http 客户端变得更容易。

前面在使用 Ribbon+RestTemplate 时，利用 RestTemplate 对 http 请求的封装处理，形成了一套模版化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务的调用。所以，Feign 在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。在 Feign 的实现下，我们只需创建一个接口并使用注解的方式来配置它 (以前是 Dao 接口上面标注 Mapper 注解, 现在是一个微服务接口上面标注一个 Feign 注解即可)，即可完成对服务提供方的接口绑定，简化了使用 Spring cloud Ribbon 时，自动封装服务调用客户端的开发量。

**Feign 集成了 Ribbon**

利用 Ribbon 维护了 Payment 的服务列表信息，并且通过轮询实现了客户端的负载均衡。而与 Ribbon 不同的是，**通过 feign 只需要定义服务绑定接口且以声明式的方法**，优雅而简单的实现了服务调用。

**Feign 和 OpenFeign 两者区别**

**Feign** 是 Spring Cloud 组件中的一个轻量级 RESTful 的 HTTP 服务客户端 Feign 内置了 Ribbon，用来做客户端负载均衡，去调用服务注册中心的服务。Feign 的使用方式是: 使用 Feign 的注解定义接口，调用这个接口，就可以调用服务注册中心的服务。

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
</dependency>
```

**OpenFeign** 是 Spring Cloud 在 Feign 的基础上支持了 SpringMVC 的注解，如 @RequesMapping 等等。OpenFeign 的 @Feignclient 可以解析 SpringMVc 的 @RequestMapping 注解下的接口，并通过动态代理的方式产生实现类，实现类中做负载均衡并调用其他服务。

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

> feign  
> 英 [feɪn] 美 [feɪn]  
> v. 假装，装作，佯装 (有某种感觉或生病、疲倦等)

44_OpenFeign 服务调用
-----------------

接口 + 注解：微服务调用接口 + @FeignClient

1. 新建 cloud-consumer-feign-order80

2.POM

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>LearnCloud</artifactId>
        <groupId>com.lun.springcloud</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-feign-order80</artifactId>

    <dependencies>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.lun.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般基础通用配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

3.YML

```
server:
  port: 80

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/
```

4. 主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
```

5. 业务类

业务逻辑接口 +@FeignClient 配置调用 provider 服务

新建 PaymentFeignService 接口并新增注解 @FeignClient

```
import com.lun.springcloud.entities.CommonResult;
import com.lun.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService
{
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

}
```

控制层 Controller

```
import com.lun.springcloud.entities.CommonResult;
import com.lun.springcloud.entities.Payment;
import com.lun.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController
{
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        return paymentFeignService.getPaymentById(id);
    }

}
```

6. 测试

先启动 2 个 eureka 集群 7001/7002

再启动 2 个微服务 8001/8002

启动 OpenFeign 启动

http://localhost/consumer/payment/get/1

Feign 自带负载均衡配置项

45_OpenFeign 超时控制
-----------------

**超时设置，故意设置超时演示出错情况**

1. 服务提供方 8001/8002 故意写暂停程序

```
@RestController
@Slf4j
public class PaymentController {
    
    ...
    
    @Value("${server.port}")
    private String serverPort;

    ...
    
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout()
    {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
    
    ...
}
```

2. 服务消费方 80 添加超时方法 PaymentFeignService

```
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService{

    ...

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
```

3. 服务消费方 80 添加超时方法 OrderFeignController

```
@RestController
@Slf4j
public class OrderFeignController
{
    @Resource
    private PaymentFeignService paymentFeignService;

    ...

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout()
    {
        // OpenFeign客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
```

4. 测试：

多次刷新 http://localhost/consumer/payment/feign/timeout

将会跳出错误 Spring Boot 默认错误页面，主要异常：`feign.RetryableException:Read timed out executing GET http://CLOUD-PAYMENT-SERVCE/payment/feign/timeout`。

**OpenFeign 默认等待 1 秒钟，超过后报错**

**YML 文件里需要开启 OpenFeign 客户端超时控制**

```
#设置feign客户端超时时间(OpenFeign默认支持ribbon)(单位：毫秒)
ribbon:
  #指的是建立连接所用的时间，适用于网络状况正常的情况下,两端连接所用的时间
  ReadTimeout: 5000
  #指的是建立连接后从服务器读取到可用资源所用的时间
  ConnectTimeout: 5000
```

46_OpenFeign 日志增强
-----------------

**日志打印功能**

Feign 提供了日志打印功能，我们可以通过配置来调整日恙级别，从而了解 Feign 中 Http 请求的细节。

说白了就是对 Feign 接口的调用情况进行监控和输出

**日志级别**

*   NONE：默认的，不显示任何日志;
*   BASIC：仅记录请求方法、URL、响应状态码及执行时间;
*   HEADERS：除了 BASIC 中定义的信息之外，还有请求和响应的头信息;
*   FULL：除了 HEADERS 中定义的信息之外，还有请求和响应的正文及元数据。

**配置日志 bean**

```
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig
{
    @Bean
    Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL;
    }
}
```

**YML 文件里需要开启日志的 Feign 客户端**

```
logging:
  level:
    # feign日志以什么级别监控哪个接口
    com.lun.springcloud.service.PaymentFeignService: debug
```

**后台日志查看**

得到更多日志信息。

47_Hystrix 是什么
--------------

**概述**

**分布式系统面临的问题**

复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时候将不可避免地失败。

**服务雪崩**

多个微服务之间调用的时候，假设微服务 A 调用微服务 B 和微服务 C，微服务 B 和微服务 C 又调用其它的微服务，这就是所谓的 “扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务 A 的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的 “雪崩效应”.  
对于高流量的应用来说，单一的后避依赖可能会导致所有服务器上的所有资源都在几秒钟内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

所以，通常当你发现一个模块下的某个实例失败后，这时候这个模块依然还会接收流量，然后这个有问题的模块还调用了其他的模块，这样就会发生级联故障，或者叫雪崩。

**Hystrix 是什么**

Hystrix 是一个用于处理分布式系统的**延迟**和**容错**的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix 能够保证在一个依赖出问题的情况下，**不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性**。

" 断路器” 本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控（**类似熔断保险丝**)，向调用方返回一个符合预期的、可处理的备选响应（FallBack)，而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

> hystrix  
> n. 豪猪属; 猬草属; 豪猪; 豪猪亚属

48_Hystrix 停更进维
---------------

**能干嘛**

*   服务降级
*   服务熔断
*   接近实时的监控
*   …

**官网资料**

[link](https://github.com/Netflix/Hystrix/wiki/How-To-Use)

**Hystrix 官宣，停更进维**

[link](https://github.com/Netflix/Hystrix)

*   被动修 bugs
*   不再接受合并请求
*   不再发布新版本

49_Hystrix 的服务降级熔断限流概念初讲
------------------------

**服务降级** fallback

服务器忙，请稍后再试，不让客户端等待并立刻返回一个友好提示，fallback

**哪些情况会触发降级**

*   程序运行导常
*   超时
*   服务熔断触发服务降级
*   线程池 / 信号量打满也会导致服务降级

**服务熔断** break

**类比保险丝**达到最大服务访问后，直接拒绝访问，拉闸限电，然后调用服务降级的方法并返回友好提示。

服务的降级 -> 进而熔断 -> 恢复调用链路

**服务限流** flowlimit

秒杀高并发等操作，严禁一窝蜂的过来拥挤，大家排队，一秒钟 N 个，有序进行。

50_Hystrix 支付微服务构建
------------------

将 cloud-eureka-server7001 改配置成单机版

1. 新建 cloud-provider-hygtrix-payment8001

2.POM

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2020</artifactId>
        <groupId>com.atguigu.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-hystrix-payment8001</artifactId>

    <dependencies>
        <!--hystrix-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency><!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
            <groupId>com.atguigu.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

3.YML

```
server:
  port: 8001

spring:
  application:
    name: cloud-provider-hystrix-payment

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      #defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka
      defaultZone: http://eureka7001.com:7001/eureka
```

4. 主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentHystrixMain8001
{
    public static void main(String[] args) {
            SpringApplication.run(PaymentHystrixMain8001.class, args);
    }
}
```

5. 业务类

service

```
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 */
@Service
public class PaymentService {
    /**
     */
    public String paymentInfo_OK(Integer id)
    {
        return "线程池:  "+Thread.currentThread().getName()+"  paymentInfo_OK,id:  "+id+"\t"+"O(∩_∩)O哈哈~";
    }

    public String paymentInfo_TimeOut(Integer id)
    {
        try { TimeUnit.MILLISECONDS.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:  "+Thread.currentThread().getName()+" id:  "+id+"\t"+"O(∩_∩)O哈哈~"+"  耗时(秒): 3";
    }
}
```

controller

```
import com.lun.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 */
@RestController
@Slf4j
public class PaymentController
{
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentInfo_OK(id);
        log.info("*****result: "+result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("*****result: "+result);
        return result;
    }
}
```

6. 正常测试

启动 eureka7001

启动 cloud-provider-hystrix-payment8001

访问

success 的方法 - http://localhost:8001/payment/hystrix/ok/1  
每次调用耗费 5 秒钟 - http://localhost:8001/payment/hystrix/timeout/1

上述 module 均 OK

以上述为根基平台，从正确 -> 错误 -> 降级熔断 -> 恢复。

51_JMeter 高并发压测后卡顿
------------------

**上述在非高并发情形下，还能勉强满足**

**Jmeter 压测测试**

[JMeter 官网](https://jmeter.apache.org/index.html)

> The **Apache JMeter™** application is open source software, a 100% pure Java application designed to load test functional behavior and measure performance. It was originally designed for testing Web Applications but has since expanded to other test functions.

开启 Jmeter，来 20000 个并发压死 8001，20000 个请求都去访问 paymentInfo_TimeOut 服务

1. 测试计划中右键添加 -》线程 -》线程组（线程组 202102，线程数：200，线程数：100，其他参数默认）

2. 刚刚新建线程组 202102，右键它 -》添加 -》取样器 -》Http 请求 -》基本 输入 http://localhost:8001/payment/hystrix/ok/1

3. 点击绿色三角形图标启动。

看演示结果：拖慢，原因：tomcat 的默认的工作线程数被打满了，没有多余的线程来分解压力和处理。

**Jmeter 压测结论**

上面还是服务提供者 8001 自己测试，假如此时外部的消费者 80 也来访问，那消费者只能干等，最终导致消费端 80 不满意，服务端 8001 直接被拖慢。

52_订单微服务调用支付服务出现卡顿
------------------

**看热闹不嫌弃事大，80 新建加入**

1. 新建 - cloud-consumer-feign-hystrix-order80

2.POM

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>LearnCloud</artifactId>
        <groupId>com.lun.springcloud</groupId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-feign-hystrix-order80</artifactId>

    <dependencies>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--hystrix-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
        </dependency>
        <!--eureka client-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.lun.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般基础通用配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

3.YML

```
server:
  port: 80

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/
```

4. 主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 */
@SpringBootApplication
@EnableFeignClients
//@EnableHystrix
public class OrderHystrixMain80
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
```

5. 业务类

```
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 */
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT" /*,fallback = PaymentFallbackService.class*/)
public interface PaymentHystrixService
{
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
```

```
import com.lun.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystirxController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }
}
```

6. 正常测试

http://localhost/consumer/payment/hystrix/ok/1

7. 高并发测试

2W 个线程压 8001

消费端 80 微服务再去访问正常的 Ok 微服务 8001 地址

http://localhost/consumer/payment/hystrix/ok/32

消费者 80 被拖慢

原因：8001 同一层次的其它接口服务被困死，因为 tomcat 线程池里面的工作线程已经被挤占完毕。

正因为有上述故障或不佳表现才有我们的降级 / 容错 / 限流等技术诞生。

53_降级容错解决的维度要求
--------------

超时导致服务器变慢 (转圈) - 超时不再等待

出错 (宕机或程序运行出错) - 出错要有兜底

解决：

*   对方服务 (8001) 超时了，调用者 (80) 不能一直卡死等待，必须有服务降级。
*   对方服务 (8001)down 机了，调用者 (80) 不能一直卡死等待，必须有服务降级。
*   对方服务 (8001)OK，调用者 (80) 自己出故障或有自我要求 (自己的等待时间小于服务提供者)，自己处理降级。

54_Hystrix 之服务降级支付侧 fallback
----------------------------

降级配置 - @HystrixCommand

8001 先从自身找问题

**设置自身调用超时时间的峰值，峰值内可以正常运行，超过了需要有兜底的方法处埋，作服务降级 fallback**。

**8001fallback**

业务类启用 - @HystrixCommand 报异常后如何处理

—旦调用服务方法失败并抛出了错误信息后，会自动调用 @HystrixCommand 标注好的 fallbackMethod 调用类中的指定方法

```
@Service
public class PaymentService{

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler"/*指定善后方法名*/,commandProperties = {
            @HystrixProperty()
    })
    public String paymentInfo_TimeOut(Integer id)
    {
        //int age = 10/0;
        try { TimeUnit.MILLISECONDS.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:  "+Thread.currentThread().getName()+" id:  "+id+"\t"+"O(∩_∩)O哈哈~"+"  耗时(秒): ";
    }

    //用来善后的方法
    public String paymentInfo_TimeOutHandler(Integer id)
    {
        return "线程池:  "+Thread.currentThread().getName()+"  8001系统繁忙或者运行报错，请稍后再试,id:  "+id+"\t"+"o(╥﹏╥)o";
    }
    
}
```

上面故意制造两种异常:

1.  int age = 10/0，计算异常
2.  我们能接受 3 秒钟，它运行 5 秒钟，超时异常。

当前服务不可用了，做服务降级，兜底的方案都是 paymentInfo_TimeOutHandler

**主启动类激活**

添加新注解 @EnableCircuitBreaker

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker//添加到此处
public class PaymentHystrixMain8001{
    public static void main(String[] args) {
            SpringApplication.run(PaymentHystrixMain8001.class, args);
    }
}
```

55_Hystrix 之服务降级订单侧 fallback
----------------------------

80 订单微服务，也可以更好的保护自己，自己也依样画葫芦进行客户端降级保护

题外话，切记 - 我们自己配置过的热部署方式对 java 代码的改动明显

但对 @HystrixCommand 内属性的修改建议重启微服务

YML

```
server:
  port: 80

eureka:
  client:
    register-with-eureka: false
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/

#开启
feign:
  hystrix:
    enabled: true
```

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix//添加到此处
public class OrderHystrixMain80{
    
    public static void main(String[] args){
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
```

业务类

```
import com.lun.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystirxController {
    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty()
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        //int age = 10/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }
    
    //善后方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

}
```