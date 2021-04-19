> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/u011863024/article/details/114298288)

[Spring Cloud 学习笔记（1 / 3）](https://blog.csdn.net/u011863024/article/details/114298270)

[Spring Cloud 学习笔记（2 / 3）](https://blog.csdn.net/u011863024/article/details/114298282)

<table><thead><tr><th>-</th><th>-</th><th>-</th></tr></thead><tbody><tr><td><a href="#108_NacosLinux_22" target="_self">108_Nacos 之 Linux 版本安装</a></td><td><a href="#109_Nacos_47" target="_self">109_Nacos 集群配置 (上)</a></td><td><a href="#110_Nacos_143" target="_self">110_Nacos 集群配置 (下)</a></td></tr><tr><td><a href="#111_Sentinel_240" target="_self">111_Sentinel 是什么</a></td><td><a href="#112_Sentinel_294" target="_self">112_Sentinel 下载安装运行</a></td><td><a href="#113_Sentinel_334" target="_self">113_Sentinel 初始化监控</a></td></tr><tr><td><a href="#114_Sentinel_530" target="_self">114_Sentinel 流控规则简介</a></td><td><a href="#115_SentinelQPS_557" target="_self">115_Sentinel 流控 - QPS 直接失败</a></td><td><a href="#116_Sentinel_595" target="_self">116_Sentinel 流控 - 线程数直接失败</a></td></tr><tr><td><a href="#117_Sentinel_603" target="_self">117_Sentinel 流控 - 关联</a></td><td><a href="#118_Sentinel_661" target="_self">118_Sentinel 流控 - 预热</a></td><td><a href="#119_Sentinel_705" target="_self">119_Sentinel 流控 - 排队等待</a></td></tr><tr><td><a href="#120_Sentinel_764" target="_self">120_Sentinel 降级简介</a></td><td><a href="#121_SentinelRT_807" target="_self">121_Sentinel 降级 - RT</a></td><td><a href="#122_Sentinel_867" target="_self">122_Sentinel 降级 - 异常比例</a></td></tr><tr><td><a href="#123_Sentinel_925" target="_self">123_Sentinel 降级 - 异常数</a></td><td><a href="#124_Sentinelkey_975" target="_self">124_Sentinel 热点 key(上)</a></td><td><a href="#125_Sentinelkey_1071" target="_self">125_Sentinel 热点 key(下)</a></td></tr><tr><td><a href="#126_Sentinel_1140" target="_self">126_Sentinel 系统规则</a></td><td><a href="#127_SentinelResource_1164" target="_self">127_SentinelResource 配置 (上)</a></td><td><a href="#128_SentinelResource_1289" target="_self">128_SentinelResource 配置 (中)</a></td></tr><tr><td><a href="#129_SentinelResource_1345" target="_self">129_SentinelResource 配置 (下)</a></td><td><a href="#130_SentinelRibbon_1378" target="_self">130_Sentinel 服务熔断 Ribbon 环境预说</a></td><td><a href="#131_Sentinel_1794" target="_self">131_Sentinel 服务熔断无配置</a></td></tr><tr><td><a href="#132_Sentinelfallback_1827" target="_self">132_Sentinel 服务熔断只配置 fallback</a></td><td><a href="#133_SentinelblockHandler_1879" target="_self">133_Sentinel 服务熔断只配置 blockHandler</a></td><td><a href="#134_SentinelfallbackblockHandler_1929" target="_self">134_Sentinel 服务熔断 fallback 和 blockHandler 都配置</a></td></tr><tr><td><a href="#135_SentinelexceptionsToIgnore_1975" target="_self">135_Sentinel 服务熔断 exceptionsToIgnore</a></td><td><a href="#136_SentinelOpenFeign_2010" target="_self">136_Sentinel 服务熔断 OpenFeign</a></td><td><a href="#137_Sentinel_2147" target="_self">137_Sentinel 持久化规则</a></td></tr><tr><td><a href="#138__2270" target="_self">138_分布式事务问题由来</a></td><td><a href="#139_Seata_2287" target="_self">139_Seata 术语</a></td><td><a href="#140_SeataServer_2325" target="_self">140_Seata-Server 安装</a></td></tr><tr><td><a href="#141_Seata_2524" target="_self">141_Seata 业务数据库准备</a></td><td><a href="#142_SeataOrderModule_2648" target="_self">142_Seata 之 Order-Module 配置搭建</a></td><td><a href="#143_SeataOrderModule_3063" target="_self">143_Seata 之 Order-Module 撸码 (上)</a></td></tr><tr><td><a href="#144_SeataOrderModule_3225" target="_self">144_Seata 之 Order-Module 撸码 (下)</a></td><td><a href="#145_SeataStorageModule_3343" target="_self">145_Seata 之 Storage-Module 说明</a></td><td><a href="#146_SeataAccountModule_3548" target="_self">146_Seata 之 Account-Module 说明</a></td></tr><tr><td><a href="#147_SeataGlobalTransactional_3785" target="_self">147_Seata 之 @GlobalTransactional 验证</a></td><td><a href="#148_Seata_3885" target="_self">148_Seata 之原理简介</a></td><td><a href="#149__3962" target="_self">149_大厂面试第三季预告片之雪花算法 (上)</a></td></tr><tr><td><a href="#150__4080" target="_self">150_大厂面试第三季预告片之雪花算法 (下)</a></td><td><a href="#Spring_Cloud_4391" target="_self">Spring Cloud 组件总结</a></td><td>-</td></tr></tbody></table>

108_Nacos 之 Linux 版本安装
----------------------

预计需要，1 个 Nginx+3 个 nacos 注册中心 + 1 个 mysql

> 请确保是在环境中安装使用:
> 
> 1.  64 bit OS Linux/Unix/Mac，推荐使用 Linux 系统。
> 2.  64 bit JDK 1.8+；[下载](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). [配置](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)。
> 3.  Maven 3.2.x+；[下载](https://maven.apache.org/download.cgi). [配置](https://maven.apache.org/settings.html)。
> 4.  3 个或 3 个以上 Nacos 节点才能构成集群。
> 
> [link](https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html)

Nacos 下载 Linux 版

*   https://github.com/alibaba/nacos/releases/tag/1.1.4
    
*   nacos-server-1.1.4.tar.gz 解压后安装
    

109_Nacos 集群配置 (上)
------------------

集群配置步骤 (重点)

**1.Linux 服务器上 mysql 数据库配置**

SQL 脚本在哪里 - 目录 nacos/conf/nacos-mysql.sql

![](https://img-blog.csdnimg.cn/img_convert/e845f90f1003384a9db91bc34dfdd248.png)

自己 Linux 机器上的 Mysql 数据库上运行

**2.application.properties 配置**

位置

![](https://img-blog.csdnimg.cn/img_convert/1f5549ab8a788ff450f4cfb2bed03f58.png)

添加以下内容，设置数据源

```
spring.datasource.platform=mysql

db.num=1
db.url.0=jdbc:mysql://localhost:3306/nacos_devtest?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=1234
```

**3.Linux 服务器上 nacos 的集群配置 cluster.conf**

梳理出 3 台 nacos 集器的不同服务端口号，设置 3 个端口：

*   3333
*   4444
*   5555

复制出 cluster.conf

![](https://img-blog.csdnimg.cn/img_convert/d742baa2bf4354db8dd9d588724e1f5c.png)

内容

```
192.168.111.144:3333
192.168.111.144:4444
192.168.111.144:5555
```

**注意**，这个 IP 不能写 127.0.0.1，必须是 Linux 命令 `hostname -i` 能够识别的 IP

![](https://img-blog.csdnimg.cn/img_convert/431d5c0a090b88dffce35768e89e5a90.png)

**4. 编辑 Nacos 的启动脚本 startup.sh，使它能够接受不同的启动端口**

/mynacos/nacos/bin 目录下有 startup.sh

![](https://img-blog.csdnimg.cn/img_convert/2cd7289348079d580cefed591a7568b9.png)

平时单机版的启动，都是./startup.sh 即可

但是，集群启动，我们希望可以类似其它软件的 shell 命令，传递不同的端口号启动不同的 nacos 实例。  
命令: ./startup.sh -p 3333 表示启动端口号为 3333 的 nacos 服务器实例，和上一步的 cluster.conf 配置的一致。

修改内容

![](https://img-blog.csdnimg.cn/img_convert/5b1fc1f634176ad17a19e4021d2b3b5e.png)

![](https://img-blog.csdnimg.cn/img_convert/9a3b1d043e5d55236216a46f296e8606.png)

执行方式 - `startup.sh - p 端口号`

![](https://img-blog.csdnimg.cn/img_convert/c68aec0dbcc1ed3d61b7e482718f9270.png)

110_Nacos 集群配置 (下)
------------------

**5.Nginx 的配置，由它作为负载均衡器**

修改 nginx 的配置文件 - nginx.conf

![](https://img-blog.csdnimg.cn/img_convert/700b800ca2e5a3dc01d0312cbeacda38.png)

修改内容

![](https://img-blog.csdnimg.cn/img_convert/769472eda4b6a5e1b284db80c705d17f.png)

按照指定启动

![](https://img-blog.csdnimg.cn/img_convert/f97a514ee914fb6050fd7428beb20639.png)

**6. 截止到此处，1 个 Nginx+3 个 nacos 注册中心 + 1 个 mysql**

**测试**

*   启动 3 个 nacos 注册中心
    
    *   `startup.sh - p 3333`
        
    *   `startup.sh - p 4444`
        
    *   `startup.sh - p 5555`
        
    *   查看 nacos 进程启动数 `ps -ef | grep nacos | grep -v grep | wc -l`
        
*   启动 nginx
    
    *   `./nginx -c /usr/local/nginx/conf/nginx.conf`
    *   查看 nginx 进程 `ps - ef| grep nginx`
*   测试通过 nginx，访问 nacos - http://192.168.111.144:1111/nacos/#/login
    
*   新建一个配置测试
    

![](https://img-blog.csdnimg.cn/img_convert/a550718db79bd46ee21031e36cb3be00.png)

*   新建后，可在 linux 服务器的 mysql 新插入一条记录

```
select * from config;
```

![](https://img-blog.csdnimg.cn/img_convert/acc1d20f83d539d0e7943a11859328f5.png)

*   让微服务 cloudalibaba-provider-payment9002 启动注册进 nacos 集群 - 修改配置文件

```
server:
  port: 9002

spring:
  application:
    name: nacos-payment-provider
  c1oud:
    nacos:
      discovery:
        #配置Nacos地址
        #server-addr: Localhost:8848
        #换成nginx的1111端口，做集群
        server-addr: 192.168.111.144:1111

management:
  endpoints:
    web:
      exposure:
        inc1ude: '*'
```

*   启动微服务 cloudalibaba-provider-payment9002
    
*   访问 nacos，查看注册结果
    

![](https://img-blog.csdnimg.cn/img_convert/b463fc3b4e9796fa7d98fb72a3c421b6.png)

**高可用小总结**

![](https://img-blog.csdnimg.cn/img_convert/42ff7ef670012437b046f099192d7484.png)

111_Sentinel 是什么
----------------

[官方 Github](https://github.com/alibaba/Sentinel)

[官方文档](https://sentinelguard.io/zh-cn/docs/introduction.html)

> **Sentinel 是什么？**
> 
> 随着微服务的流行，服务和服务之间的稳定性变得越来越重要。Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。
> 
> Sentinel 具有以下特征:
> 
> *   **丰富的应用场景**：Sentinel 承接了阿里巴巴近 10 年的双十一大促流量的核心场景，例如秒杀（即突发流量控制在系统容量可以承受的范围）、消息削峰填谷、集群流量控制、实时熔断下游不可用应用等。
> *   **完备的实时监控**：Sentinel 同时提供实时的监控功能。您可以在控制台中看到接入应用的单台机器秒级数据，甚至 500 台以下规模的集群的汇总运行情况。
> *   **广泛的开源生态**：Sentinel 提供开箱即用的与其它开源框架 / 库的整合模块，例如与 Spring Cloud、Dubbo、gRPC 的整合。您只需要引入相应的依赖并进行简单的配置即可快速地接入 Sentinel。
> *   **完善的 SPI 扩展点**：Sentinel 提供简单易用、完善的 SPI 扩展接口。您可以通过实现扩展接口来快速地定制逻辑。例如定制规则管理、适配动态数据源等。
> 
> Sentinel 的主要特性：
> 
> ![](https://img-blog.csdnimg.cn/img_convert/e4efa9c3547366ae4f747ad4007f6447.png)
> 
> [link](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D#sentinel-%E6%98%AF%E4%BB%80%E4%B9%88)

—句话解释，之前我们讲解过的 Hystrix。

Hystrix 与 Sentinel 比较：

*   Hystrix
    1.  需要我们程序员自己手工搭建监控平台
    2.  没有一套 web 界面可以给我们进行更加细粒度化得配置流控、速率控制、服务熔断、服务降级
*   Sentinel
    1.  单独一个组件，可以独立出来。
    2.  直接界面化的细粒度统一配置。

约定 > 配置 > 编码

都可以写在代码里面，但是我们本次还是大规模的学习使用配置和注解的方式，尽量少写代码

> sentinel  
> 英 [ˈsentɪnl] 美 [ˈsentɪnl]  
> n. 哨兵

112_Sentinel 下载安装运行
-------------------

[官方文档](https://spring-cloud-alibaba-group.github.io/github-pages/greenwich/spring-cloud-alibaba.html#_spring_cloud_alibaba_sentinel)

服务使用中的各种问题：

*   服务雪崩
*   服务降级
*   服务熔断
*   服务限流

Sentinel 分为两个部分：

*   核心库（Java 客户端）不依赖任何框架 / 库，能够运行于所有 Java 运行时环境，同时对 Dubbo / Spring Cloud 等框架也有较好的支持。
*   控制台（Dashboard）基于 Spring Boot 开发，打包后可以直接运行，不需要额外的 Tomcat 等应用容器。

安装步骤：

*   下载
    
    *   https://github.com/alibaba/Sentinel/releases
    *   下载到本地 sentinel-dashboard-1.7.0.jar
*   运行命令
    
    *   前提
        *   Java 8 环境
        *   8080 端口不能被占用
    *   命令
        *   `java -jar sentinel-dashboard-1.7.0.jar`
*   访问 Sentinel 管理界面
    
    *   localhost:8080
    *   登录账号密码均为 sentinel

113_Sentinel 初始化监控
------------------

**启动 Nacos8848 成功**

**新建工程 - cloudalibaba-sentinel-service8401**

POM

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

    <artifactId>cloudalibaba-sentinel-service8401</artifactId>

    <dependencies>
        <dependency><!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
            <groupId>com.atguigu.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!--SpringCloud ailibaba nacos -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--SpringCloud ailibaba sentinel-datasource-nacos 后续做持久化用到-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>
        <!--SpringCloud ailibaba sentinel -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!-- SpringBoot整合Web组件+actuator -->
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
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>4.6.3</version>
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

YML

```
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #Nacos服务注册中心地址
    sentinel:
      transport:
        dashboard: localhost:8080 #配置Sentinel dashboard地址
        port: 8719

management:
  endpoints:
    web:
      exposure:
        include: '*'

feign:
  sentinel:
    enabled: true # 激活Sentinel对Feign的支持
```

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainApp8401 {
    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class, args);
    }
}
```

业务类 FlowLimitController

```
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA()
    {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "------testB";
    }
}
```

**启动 Sentinel8080 - `java -jar sentinel-dashboard-1.7.0.jar`**

**启动微服务 8401**

**启动 8401 微服务后查看 sentienl 控制台**

*   刚启动，空空如也，啥都没有

![](https://img-blog.csdnimg.cn/img_convert/bab574546fe65f719c095cf7d9e1db64.png)

*   Sentinel 采用的懒加载说明
    *   执行一次访问即可
        *   http://localhost:8401/testA
        *   http://localhost:8401/testB
    *   效果 - sentinel8080 正在监控微服务 8401

![](https://img-blog.csdnimg.cn/img_convert/cf6561c14a2214b90c9002f2161b296f.png)

114_Sentinel 流控规则简介
-------------------

基本介绍

![](https://img-blog.csdnimg.cn/img_convert/d8ae2bea252af0bb278332b3aeb8fb77.png)

进一步解释说明：

*   资源名：唯一名称，默认请求路径。
*   针对来源：Sentinel 可以针对调用者进行限流，填写微服务名，默认 default（不区分来源）。
*   阈值类型 / 单机阈值：
    *   QPS(每秒钟的请求数量)︰当调用该 API 的 QPS 达到阈值的时候，进行限流。
    *   线程数：当调用该 API 的线程数达到阈值的时候，进行限流。
*   是否集群：不需要集群。
*   流控模式：
    *   直接：API 达到限流条件时，直接限流。
    *   关联：当关联的资源达到阈值时，就限流自己。
    *   链路：只记录指定链路上的流量（指定资源从入口资源进来的流量，如果达到阈值，就进行限流)【API 级别的针对来源】。
*   流控效果：
    *   快速失败：直接失败，抛异常。
    *   Warm up：根据 Code Factor（冷加载因子，默认 3）的值，从阈值 / codeFactor，经过预热时长，才达到设置的 QPS 阈值。
    *   排队等待：匀速排队，让请求以匀速的速度通过，阈值类型必须设置为 QPS，否则无效。

115_Sentinel 流控 - QPS 直接失败
--------------------------

**直接 -> 快速失败（系统默认）**

**配置及说明**

表示 1 秒钟内查询 1 次就是 OK，若超过次数 1，就直接 -> 快速失败，报默认错误

![](https://img-blog.csdnimg.cn/img_convert/56642cc2b7dd5b0d1252235c84f69173.png)

**测试**

快速多次点击访问 http://localhost:8401/testA

**结果**

返回页面 Blocked by Sentinel (flow limiting)

**源码**

com.alibaba.csp.sentinel.slots.block.flow.controller.DefaultController

**思考**

直接调用默认报错信息，技术方面 OK，但是，是否应该有我们自己的后续处理？类似有个 fallback 的兜底方法?

116_Sentinel 流控 - 线程数直接失败
-------------------------

线程数：当调用该 API 的线程数达到阈值的时候，进行限流。

![](https://img-blog.csdnimg.cn/img_convert/65af4de19564cceebe7cd67589babd69.png)

117_Sentinel 流控 - 关联
--------------------

**是什么？**

*   当自己关联的资源达到阈值时，就限流自己
    
*   当与 A 关联的资源 B 达到阀值后，就限流 A 自己（B 惹事，A 挂了）
    

**设置 testA**

当关联资源 / testB 的 QPS 阀值超过 1 时，就限流 / testA 的 Rest 访问地址，**当关联资源到阈值后限制配置好的资源名**。

![](https://img-blog.csdnimg.cn/img_convert/12cd41ae91ba50fe3b5525bab7bc3805.png)

**Postman 模拟并发密集访问 testB**

![](https://img-blog.csdnimg.cn/img_convert/531e3c582fd2be3aa543ecca5b88c26e.png)

访问 testB 成功

![](https://img-blog.csdnimg.cn/img_convert/f0bdbe602b9c7185b10a2255772b3304.png)

postman 里新建多线程集合组

![](https://img-blog.csdnimg.cn/img_convert/e66c6aef5cb47beecd7c232f6eac6686.png)

将访问地址添加进新新线程组

![](https://img-blog.csdnimg.cn/img_convert/d476cfa823eee6589955e4762a11dfcf.png)

Run - 大批量线程高并发访问 B

Postman 运行后，点击访问 http://localhost:8401/testA，发现 testA 挂了

*   结果 Blocked by Sentinel(flow limiting)

HOMEWORK：

自己上机测试

链路：只记录指定链路上的流量（指定资源从入口资源进来的流量，如果达到阈值，就进行限流)【API 级别的针对来源】

118_Sentinel 流控 - 预热
--------------------

> **Warm Up**
> 
> Warm Up（`RuleConstant.CONTROL_BEHAVIOR_WARM_UP`）方式，即预热 / 冷启动方式。当系统长期处于低水位的情况下，当流量突然增加时，直接把系统拉升到高水位可能瞬间把系统压垮。通过 " 冷启动 "，让通过的流量缓慢增加，在一定时间内逐渐增加到阈值上限，给冷系统一个预热的时间，避免冷系统被压垮。详细文档可以参考 [流量控制 - Warm Up 文档](https://github.com/alibaba/Sentinel/wiki/%E9%99%90%E6%B5%81---%E5%86%B7%E5%90%AF%E5%8A%A8)，具体的例子可以参见 [WarmUpFlowDemo](https://github.com/alibaba/Sentinel/blob/master/sentinel-demo/sentinel-demo-basic/src/main/java/com/alibaba/csp/sentinel/demo/flow/WarmUpFlowDemo.java)。
> 
> 通常冷启动的过程系统允许通过的 QPS 曲线如下图所示：
> 
> ![](https://img-blog.csdnimg.cn/img_convert/ede9b7e029c54840e3b40b69c4f371b5.png)
> 
> [link](https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6#warm-up)

> 默认 coldFactor 为 3，即请求 QPS 从 threshold / 3 开始，经预热时长逐渐升至设定的 QPS 阈值。[link](https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6#warm-up)

**源码** - com.alibaba.csp.sentinel.slots.block.flow.controller.WarmUpController

**WarmUp 配置**

案例，阀值为 10 + 预热时长设置 5 秒。

系统初始化的阀值为 10/ 3 约等于 3, 即阀值刚开始为 3; 然后过了 5 秒后阀值才慢慢升高恢复到 10

![](https://img-blog.csdnimg.cn/img_convert/c26846d68d79eae1e962f37942a2c99f.png)

**测试**

多次快速点击 http://localhost:8401/testB - 刚开始不行，后续慢慢 OK

**应用场景**

如：秒杀系统在开启的瞬间，会有很多流量上来，很有可能把系统打死，预热方式就是把为了保护系统，可慢慢的把流量放进来, 慢慢的把阀值增长到设置的阀值。

119_Sentinel 流控 - 排队等待
----------------------

匀速排队，让请求以均匀的速度通过，阀值类型必须设成 QPS，否则无效。

设置：/testA 每秒 1 次请求，超过的话就排队等待，等待的超时时间为 20000 毫秒。

![](https://img-blog.csdnimg.cn/img_convert/0ddd217545dd0fe2b1f251dbea814ac2.png)

> **匀速排队**
> 
> 匀速排队（`RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER`）方式会严格控制请求通过的间隔时间，也即是让请求以均匀的速度通过，对应的是漏桶算法。详细文档可以参考 [流量控制 - 匀速器模式](https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6-%E5%8C%80%E9%80%9F%E6%8E%92%E9%98%9F%E6%A8%A1%E5%BC%8F)，具体的例子可以参见 [PaceFlowDemo](https://github.com/alibaba/Sentinel/blob/master/sentinel-demo/sentinel-demo-basic/src/main/java/com/alibaba/csp/sentinel/demo/flow/PaceFlowDemo.java)。
> 
> 该方式的作用如下图所示：
> 
> ![](https://img-blog.csdnimg.cn/img_convert/79f93ab9f5dc11b05bbed9b793ef7c20.png)
> 
> 这种方式主要用于处理间隔性突发的流量，例如消息队列。想象一下这样的场景，在某一秒有大量的请求到来，而接下来的几秒则处于空闲状态，我们希望系统能够在接下来的空闲期间逐渐处理这些请求，而不是在第一秒直接拒绝多余的请求。
> 
> > 注意：匀速排队模式暂时不支持 QPS > 1000 的场景。
> 
> [link](https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6#%E5%8C%80%E9%80%9F%E6%8E%92%E9%98%9F)

源码 - com.alibaba.csp.sentinel.slots.block.flow.controller.RateLimiterController

**测试**

*   添加日志记录代码到 FlowLimitController 的 testA 方法

```
@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA()
    {
        log.info(Thread.currentThread().getName()+"\t"+"...testA");//<----
        return "------testA";
    }

    ...
}
```

*   Postman 模拟并发密集访问 testA。具体操作参考 [117_Sentinel 流控 - 关联](#)
    
*   后台结果
    

![](https://img-blog.csdnimg.cn/img_convert/c89a2124391676992c8fabffdaf1a07c.png)

120_Sentinel 降级简介
-----------------

[官方文档](https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7)

> **熔断降级概述**
> 
> 除了流量控制以外，对调用链路中不稳定的资源进行熔断降级也是保障高可用的重要措施之一。一个服务常常会调用别的模块，可能是另外的一个远程服务、数据库，或者第三方 API 等。例如，支付的时候，可能需要远程调用银联提供的 API；查询某个商品的价格，可能需要进行数据库查询。然而，这个被依赖服务的稳定性是不能保证的。如果依赖的服务出现了不稳定的情况，请求的响应时间变长，那么调用服务的方法的响应时间也会变长，线程会产生堆积，最终可能耗尽业务自身的线程池，服务本身也变得不可用。
> 
> 现代微服务架构都是分布式的，由非常多的服务组成。不同服务之间相互调用，组成复杂的调用链路。以上的问题在链路调用中会产生放大的效果。复杂链路上的某一环不稳定，就可能会层层级联，最终导致整个链路都不可用。因此我们需要对不稳定的**弱依赖服务调用**进行熔断降级，暂时切断不稳定调用，避免局部不稳定因素导致整体的雪崩。熔断降级作为保护自身的手段，通常在客户端（调用端）进行配置。
> 
> [link](https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7#%E6%A6%82%E8%BF%B0)

![](https://img-blog.csdnimg.cn/img_convert/6a002ef360a4e5f20ee2748a092f0211.png)

*   RT（平均响应时间，秒级）
    
    *   平均响应时间 超出阈值 且 在时间窗口内通过的请求 >=5，两个条件同时满足后触发降级。
    *   窗口期过后关闭断路器。
    *   RT 最大 4900（更大的需要通过 - Dcsp.sentinel.statistic.max.rt=XXXX 才能生效）。
*   异常比列（秒级）
    
    *   QPS >= 5 且异常比例（秒级统计）超过阈值时，触发降级; 时间窗口结束后，关闭降级。
*   异常数 (分钟级)
    
    *   异常数 (分钟统计）超过阈值时，触发降级; 时间窗口结束后，关闭降级

Sentinel 熔断降级会在调用链路中某个资源出现不稳定状态时（例如调用超时或异常比例升高)，对这个资源的调用进行限制，让请求快速失败，避免影响到其它的资源而导致级联错误。

当资源被降级后，在接下来的降级时间窗口之内，对该资源的调用都自动熔断（默认行为是抛出 DegradeException）。

Sentinei 的断路器是没有类似 Hystrix 半开状态的。(Sentinei 1.8.0 已有半开状态)

半开的状态系统自动去检测是否请求有异常，没有异常就关闭断路器恢复使用，有异常则继续打开断路器不可用。

具体可以参考 [49_Hystrix 的服务降级熔断限流概念初讲](#)。

121_Sentinel 降级 - RT
--------------------

是什么？

> 平均响应时间 (`DEGRADE_GRADE_RT`)：当 1s 内持续进入 5 个请求，对应时刻的平均响应时间（**秒级**）均超过阈值（`count`，以 ms 为单位），那么在接下的时间窗口（`DegradeRule` 中的`timeWindow`，以 s 为单位）之内，对这个方法的调用都会自动地熔断 (抛出 `DegradeException` )。注意 Sentinel 默认统计的 RT 上限是 4900 ms，超出此阈值的都会算作 4900ms，若需要变更此上限可以通过启动配置项`-Dcsp.sentinel.statistic.max.rt=xxx` 来配置。

**注意**：Sentinel 1.7.0 才有**平均响应时间**（`DEGRADE_GRADE_RT`），Sentinel 1.8.0 的没有这项，取而代之的是**慢调用比例** (`SLOW_REQUEST_RATIO`)。

> 慢调用比例 (`SLOW_REQUEST_RATIO`)：选择以慢调用比例作为阈值，需要设置允许的慢调用 RT（即最大的响应时间），请求的响应时间大于该值则统计为慢调用。当单位统计时长（`statIntervalMs`）内请求数目大于设置的最小请求数目，并且慢调用的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求响应时间小于设置的慢调用 RT 则结束熔断，若大于设置的慢调用 RT 则会再次被熔断。[link](https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7#%E7%86%94%E6%96%AD%E7%AD%96%E7%95%A5)

接下来讲解 Sentinel 1.7.0 的。

![](https://img-blog.csdnimg.cn/img_convert/dcf85d4362c017e543173c76b7dcc2a8.png)

**测试**

代码

```
@RestController
@Slf4j
public class FlowLimitController {
	...

    @GetMapping("/testD")
    public String testD() {
        try { 
            TimeUnit.SECONDS.sleep(1); 
        } catch (InterruptedException e) { 
            e.printStackTrace(); 
        }
        log.info("testD 测试RT");
    }
}
```

配置

![](https://img-blog.csdnimg.cn/img_convert/3a608908cef3d557322967e6bc0e5696.png)

jmeter 压测

![](https://img-blog.csdnimg.cn/img_convert/6dcaee9f62bfd3c8334560df34f6aaa6.png)

结论

按照上述配置，永远一秒钟打进来 10 个线程（大于 5 个了）调用 testD，我们希望 200 毫秒处理完本次任务，如果超过 200 毫秒还没处理完，在未来 1 秒钟的时间窗口内，断路器打开（保险丝跳闸）微服务不可用，保险丝跳闸断电了后续我停止 jmeter，没有这么大的访问量了，断路器关闭（保险丝恢复），微服务恢复 OK。

122_Sentinel 降级 - 异常比例
----------------------

**是什么？**

> 异常比例 (`DEGRADE_GRADE_EXCEPTION_RATIO`)：当资源的每秒请求量 >= 5，并且每秒异常总数占通过量的比值超过阈值（ `DegradeRule` 中的 `count`）之后，资源进入降级状态，即在接下的时间窗口 ( `DegradeRule` 中的`timeWindow`，以 s 为单位）之内，对这个方法的调用都会自动地返回。异常比率的阈值范围是`[0.0, 1.0]`，代表 0% -100%。

**注意**，与 Sentinel 1.8.0 相比，有些不同（Sentinel 1.8.0 才有的半开状态），Sentinel 1.8.0 的如下：

> 异常比例 (`ERROR_RATIO`)：当单位统计时长（`statIntervalMs`）内请求数目大于设置的最小请求数目，并且异常的比例大于阈值，则接下来的熔断时长内请求会自动被熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。异常比率的阈值范围是`[0.0, 1.0]`，代表 0% - 100%。[link](https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7#%E7%86%94%E6%96%AD%E7%AD%96%E7%95%A5)

接下来讲解 Sentinel 1.7.0 的。

![](https://img-blog.csdnimg.cn/img_convert/b8f35b00fffd79ef68e8f744403b92f3.png)

**测试**

代码

```
@RestController
@Slf4j
public class FlowLimitController {

    ...

    @GetMapping("/testD")
    public String testD() {
        log.info("testD 异常比例");
        int age = 10/0;
        return "------testD";
    }
}
```

配置

![](https://img-blog.csdnimg.cn/img_convert/ab66591ba085c32e9303d96be7b44f0d.png)

jmeter

![](https://img-blog.csdnimg.cn/img_convert/6b4fd3cb04118ae77181fe8bf2019176.png)

_结论_

按照上述配置，单独访问一次，必然来一次报错一次 (int age = 10/0)，调一次错一次。

开启 jmeter 后，直接高并发发送请求，多次调用达到我们的配置条件了。断路器开启 (保险丝跳闸)，微服务不可用了，不再报错 error 而是服务降级了。

123_Sentinel 降级 - 异常数
---------------------

**是什么？**

> 异常数 ( `DEGRADE_GRADF_EXCEPTION_COUNT` )：当资源近 1 分钟的异常数目超过阈值之后会进行熔断。注意由于统计时间窗口是分钟级别的，若 `timeWindow` 小于 60s，则结束熔断状态后码可能再进入熔断状态。

**注意**，与 Sentinel 1.8.0 相比，有些不同（Sentinel 1.8.0 才有的半开状态），Sentinel 1.8.0 的如下：

> 异常数 (`ERROR_COUNT`)：当单位统计时长内的异常数目超过阈值之后会自动进行熔断。经过熔断时长后熔断器会进入探测恢复状态（HALF-OPEN 状态），若接下来的一个请求成功完成（没有错误）则结束熔断，否则会再次被熔断。

接下来讲解 Sentinel 1.7.0 的。

**异常数是按照分钟统计的，时间窗口一定要大于等于 60 秒**。

![](https://img-blog.csdnimg.cn/img_convert/d92c6a9ae5ed514b52ddf43fdf0d5f0e.png)

**测试**

代码

```
@RestController
@Slf4j
public class FlowLimitController{
	...

    @GetMapping("/testE")
    public String testE()
    {
        log.info("testE 测试异常数");
        int age = 10/0;
        return "------testE 测试异常数";
    }
}
```

配置

![](https://img-blog.csdnimg.cn/img_convert/218fe52e19c07b30bbf4d994d05e6a8e.png)

访问 http://localhost:8401/testE，第一次访问绝对报错，因为除数不能为零，我们看到 error 窗口，但是达到 5 次报错后，进入熔断后降级。

124_Sentinel 热点 key(上)
----------------------

**基本介绍**

![](https://img-blog.csdnimg.cn/img_convert/9d2aa6d777767b3233aa643330eb9cf4.png)

**官网**

[官方文档](https://github.com/alibaba/Sentinel/wiki/%E7%83%AD%E7%82%B9%E5%8F%82%E6%95%B0%E9%99%90%E6%B5%81)

> 何为热点？热点即经常访问的数据。很多时候我们希望统计某个热点数据中访问频次最高的 Top K 数据，并对其访问进行限制。比如：
> 
> *   商品 ID 为参数，统计一段时间内最常购买的商品 ID 并进行限制
> *   用户 ID 为参数，针对一段时间内频繁访问的用户 ID 进行限制
> 
> 热点参数限流会统计传入参数中的热点参数，并根据配置的限流阈值与模式，对包含热点参数的资源调用进行限流。热点参数限流可以看做是一种特殊的流量控制，仅对包含热点参数的资源调用生效。
> 
> ![](https://img-blog.csdnimg.cn/img_convert/16d2ddeff96b7cb68a064b6ec05bde25.png)
> 
> Sentinel 利用 LRU 策略统计最近最常访问的热点参数，结合令牌桶算法来进行参数级别的流控。热点参数限流支持集群模式。
> 
> [link](https://github.com/alibaba/Sentinel/wiki/%E7%83%AD%E7%82%B9%E5%8F%82%E6%95%B0%E9%99%90%E6%B5%81#overview)

**承上启下复习 start**

兜底方法，分为系统默认和客户自定义，两种

之前的 case，限流出问题后，都是用 sentinel 系统默认的提示: Blocked by Sentinel (flow limiting)

我们能不能自定？类似 hystrix，某个方法出问题了，就找对应的兜底降级方法?

结论 - **从 HystrixCommand 到 @SentinelResource**

**代码**

com.alibaba.csp.sentinel.slots.block.BlockException

```
@RestController
@Slf4j
public class FlowLimitController
{

    ...

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler/*兜底方法*/ = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        //int age = 10/0;
        return "------testHotKey";
    }
    
    /*兜底方法*/
    public String deal_testHotKey (String p1, String p2, BlockException exception) {
        return "------deal_testHotKey,o(╥﹏╥)o";  //sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
    }

}
```

**配置**

![](https://img-blog.csdnimg.cn/img_convert/9620ee4e7e54d48ba7dda394fa1c8cd0.png)

一

*   `@SentinelResource(value = "testHotKey")`
*   异常打到了前台用户界面看到，不友好

二

*   `@SentinelResource(value = "testHotKey", blockHandler = "dealHandler_testHotKey")`
*   方法 testHotKey 里面第一个参数只要 QPS 超过每秒 1 次，马上降级处理
*   异常用了我们自己定义的兜底方法

**测试**

*   error
    *   http://localhost:8401/testHotKey?p1=abc
    *   http://localhost:8401/testHotKey?p1=abc&p2=33
*   right
    *   http://localhost:8401/testHotKey?p2=abc

125_Sentinel 热点 key(下)
----------------------

上述案例演示了第一个参数 p1，当 QPS 超过 1 秒 1 次点击后马上被限流。

**参数例外项**

*   普通 - 超过 1 秒钟一个后，达到阈值 1 后马上被限流
*   **我们期望 p1 参数当它是某个特殊值时，它的限流值和平时不一样**
*   特例 - 假如当 p1 的值等于 5 时，它的阈值可以达到 200

**配置**

![](https://img-blog.csdnimg.cn/img_convert/3aa08b15109cd346a6083f080a0468fa.png)

**测试**

*   right - http://localhost:8401/testHotKey?p1=5
*   error - http://localhost:8401/testHotKey?p1=3
*   当 p1 等于 5 的时候，阈值变为 200
*   当 p1 不等于 5 的时候，阈值就是平常的 1

**前提条件** - 热点参数的注意点，参数必须是基本类型或者 String

**其它**

在方法体抛异常

```
@RestController
@Slf4j
public class FlowLimitController
{

    ...

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler/*兜底方法*/ = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        int age = 10/0;//<----------------------------会抛异常的地方
        return "------testHotKey";
    }
    
    /*兜底方法*/
    public String deal_testHotKey (String p1, String p2, BlockException exception) {
        return "------deal_testHotKey,o(╥﹏╥)o";  //sentinel系统默认的提示：Blocked by Sentinel (flow limiting)
    }

}
```

将会抛出 Spring Boot 2 的默认异常页面，而不是兜底方法。

*   @SentinelResource - 处理的是 sentinel 控制台配置的违规情况，有 blockHandler 方法配置的兜底处理;
    
*   RuntimeException`int age = 10/0`，这个是 java 运行时报出的运行时异常 RunTimeException，@SentinelResource 不管
    

总结 - @SentinelResource 主管配置出错，运行出错该走异常走异常

126_Sentinel 系统规则
-----------------

[官方文档](https://github.com/alibaba/Sentinel/wiki/%E7%B3%BB%E7%BB%9F%E8%87%AA%E9%80%82%E5%BA%94%E9%99%90%E6%B5%81)

> Sentinel 系统自适应限流**从整体维度**对应用入口流量进行控制，结合应用的 Load、CPU 使用率、总体平均 RT、入口 QPS 和并发线程数等几个维度的监控指标，通过自适应的流控策略，让系统的入口流量和系统的负载达到一个平衡，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。[link](https://github.com/alibaba/Sentinel/wiki/%E7%B3%BB%E7%BB%9F%E8%87%AA%E9%80%82%E5%BA%94%E9%99%90%E6%B5%81)

> **系统规则**
> 
> 系统保护规则是从应用级别的入口流量进行控制，从单台机器的 load、CPU 使用率、平均 RT、入口 QPS 和并发线程数等几个维度监控应用指标，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。
> 
> 系统保护规则是应用整体维度的，而不是资源维度的，并且**仅对入口流量生效**。入口流量指的是进入应用的流量（`EntryType.IN`），比如 Web 服务或 Dubbo 服务端接收的请求，都属于入口流量。
> 
> 系统规则支持以下的模式：
> 
> *   **Load 自适应**（仅对 Linux/Unix-like 机器生效）：系统的 load1 作为启发指标，进行自适应系统保护。当系统 load1 超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护（BBR 阶段）。系统容量由系统的 `maxQps * minRt` 估算得出。设定参考值一般是`CPU cores * 2.5`。
> *   **CPU usage**（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏。
> *   **平均 RT**：当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。
> *   **并发线程数**：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。
> *   **入口 QPS**：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护。
> 
> [link](https://github.com/alibaba/Sentinel/wiki/%E7%B3%BB%E7%BB%9F%E8%87%AA%E9%80%82%E5%BA%94%E9%99%90%E6%B5%81#%E7%B3%BB%E7%BB%9F%E8%A7%84%E5%88%99)

127_SentinelResource 配置 (上)
---------------------------

_按资源名称限流 + 后续处理_

**启动 Nacos 成功**

**启动 Sentinel 成功**

**Module - cloudalibaba-sentinel-service8401**

```
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.myhandler.CustomerBlockHandler;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {
    
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }
    
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444,exception.getClass().getCanonicalName()+"\t 服务不可用");
    }
}
```

**配置流控规则**

配置步骤

![](https://img-blog.csdnimg.cn/img_convert/91aa0ac210011218db9557a2bfcfebd1.png)

图形配置和代码关系

表示 1 秒钟内查询次数大于 1，就跑到我们自定义的处流，限流

**测试**

1 秒钟点击 1 下，OK

超过上述，疯狂点击，返回了自己定义的限流处理信息，限流发生

```
{"code":444, "message":"com.alibaba.csp.sentinel.slots.block.flow.FlowException\t 服务不可用", "data":null}
```

**额外问题**

此时关闭问服务 8401 -> Sentinel 控制台，流控规则消失了

_按照 Url 地址限流 + 后续处理_

**通过访问的 URL 来限流，会返回 Sentinel 自带默认的限流处理信息**

**业务类 RateLimitController**

```
@RestController
public class RateLimitController
{
	...

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }
}
```

**Sentinel 控制台配置**

![](https://img-blog.csdnimg.cn/img_convert/d6a79b7cc3f2f9c8b6dcbe3f77f78c6b.png)

**测试**

*   快速点击 http://localhost:8401/rateLimit/byUrl
*   结果 - 会返回 Sentinel 自带的限流处理结果 Blocked by Sentinel (flow limiting)

**上面兜底方案面临的问题**

1.  系统默认的，没有体现我们自己的业务要求。
2.  依照现有条件，我们自定义的处理方法又和业务代码耦合在一块，不直观。
3.  每个业务方法都添加—个兜底的，那代码膨胀加剧。
4.  全局统—的处理方法没有体现。

128_SentinelResource 配置 (中)
---------------------------

客户自定义限流处理逻辑

自定义限流处理类 - 创建 CustomerBlockHandler 类用于自定义限流处理逻辑

```
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }
    
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}
```

RateLimitController

```
@RestController
public class RateLimitController {
	...

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class,//<-------- 自定义限流处理类
            blockHandler = "handlerException2")//<-----------
    public CommonResult customerBlockHandler()
    {
        return new CommonResult(200,"按客戶自定义",new Payment(2020L,"serial003"));
    }
}
```

Sentinel 控制台配置

![](https://img-blog.csdnimg.cn/img_convert/44dccf4107a74fda56f0807d39fa53f1.png)

启动微服务后先调用一次 - http://localhost:8401/rateLimit/customerBlockHandler。然后，多次快速刷新 http://localhost:8401/rateLimit/customerBlockHandler。刷新后，我们自定义兜底方法的字符串信息就返回到前端。

129_SentinelResource 配置 (下)
---------------------------

> **@SentinelResource 注解**
> 
> > 注意：注解方式埋点不支持 private 方法。
> 
> `@SentinelResource` 用于定义资源，并提供可选的异常处理和 fallback 配置项。 `@SentinelResource` 注解包含以下属性：
> 
> *   `value`：资源名称，必需项（不能为空）
> *   `entryType`：entry 类型，可选项（默认为 `EntryType.OUT`）
> *   `blockHandler` / `blockHandlerClass`: `blockHandler` 对应处理 `BlockException` 的函数名称，可选项。blockHandler 函数访问范围需要是`public`，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为`BlockException`。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `blockHandlerClass` 为对应的类的 `Class` 对象，注意对应的函数必需为 static 函数，否则无法解析。
> *   `fallback` /`fallbackClass`：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了 `exceptionsToIgnore` 里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
>     *   返回值类型必须与原函数返回值类型一致；
>     *   方法参数列表需要和原函数一致，或者可以额外多一个 `Throwable` 类型的参数用于接收对应的异常。
>     *   fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `fallbackClass` 为对应的类的 `Class` 对象，注意对应的函数必需为 static 函数，否则无法解析。
> *   `defaultFallback`（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了 `exceptionsToIgnore` 里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
>     *   返回值类型必须与原函数返回值类型一致；
>     *   方法参数列表需要为空，或者可以额外多一个 `Throwable` 类型的参数用于接收对应的异常。
>     *   defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `fallbackClass` 为对应的类的 `Class` 对象，注意对应的函数必需为 static 函数，否则无法解析。
> *   `exceptionsToIgnore`（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。
> 
> [link](https://github.com/alibaba/Sentinel/wiki/%E6%B3%A8%E8%A7%A3%E6%94%AF%E6%8C%81#sentinelresource-%E6%B3%A8%E8%A7%A3)

Sentinel 主要有三个核心 Api：

1.  SphU 定义资源
2.  Tracer 定义统计
3.  ContextUtil 定义了上下文

130_Sentinel 服务熔断 Ribbon 环境预说
-----------------------------

sentinel 整合 ribbon+openFeign+fallback

Ribbon 系列

*   启动 nacos 和 sentinel
*   提供者 9003/9004
*   消费者 84

**提供者 9003/9004**

新建 cloudalibaba-provider-payment9003/9004，两个一样的做法

POM

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

    <artifactId>cloudalibaba-provider-payment9003</artifactId>

    <dependencies>
        <!--SpringCloud ailibaba nacos -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <dependency><!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
            <groupId>com.atguigu.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
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

YML

```
server:
  port: 9003

spring:
  application:
    name: nacos-payment-provider
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #配置Nacos地址

management:
  endpoints:
    web:
      exposure:
        include: '*'
```

**记得修改不同的端口号**

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9003.class, args);
    }
}
```

业务类

```
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    //模拟数据库
    public static HashMap<Long,Payment> hashMap = new HashMap<>();
    static
    {
        hashMap.put(1L,new Payment(1L,"28a8c1e3bc2742d8848569891fb42181"));
        hashMap.put(2L,new Payment(2L,"bba8c1e3bc2742d8848569891ac32182"));
        hashMap.put(3L,new Payment(3L,"6ua8c1e3bc2742d8848569891xt92183"));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult(200,"from mysql,serverPort:  "+serverPort,payment);
        return result;
    }

}
```

测试地址 - http://localhost:9003/paymentSQL/1

**消费者 84**

新建 cloudalibaba-consumer-nacos-order84

POM

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

    <artifactId>cloudalibaba-consumer-nacos-order84</artifactId>

    <dependencies>
        <!--SpringCloud openfeign -->
        <!--
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
		-->
        <!--SpringCloud ailibaba nacos -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--SpringCloud ailibaba sentinel -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
        <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.atguigu.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
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

YML

```
server:
  port: 84

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848
    sentinel:
      transport:
        #配置Sentinel dashboard地址
        dashboard: localhost:8080
        #默认8719端口，假如被占用会自动从8719开始依次+1扫描,直至找到未被占用的端口
        port: 8719

#消费者将要去访问的微服务名称(注册成功进nacos的微服务提供者)
service-url:
  nacos-user-service: http://nacos-payment-provider

# 激活Sentinel对Feign的支持
feign:
  sentinel:
    enabled: false
```

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class OrderNacosMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class, args);
    }
}
```

业务类

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
//@EnableFeignClients
public class OrderNacosMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class, args);
    }
}
```

ApplicationContextConfig

```
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
```

CircleBreakerController

```
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.service.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;
 
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback")//没有配置
    public CommonResult<Payment> fallback(@PathVariable Long id)
    {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }
    
}
```

修改后请重启微服务

*   热部署对 java 代码级生效及时
*   对 @SentinelResource 注解内属性，有时效果不好

目的

*   fallback 管运行异常
*   blockHandler 管配置违规

测试地址 - http://localhost:84/consumer/fallback/1

没有任何配置

只配置 fallback

只配置 blockHandler

fallback 和 blockHandler 都配置

忽略属性

131_Sentinel 服务熔断无配置
--------------------

没有任何配置 - **给用户 error 页面，不友好**

```
@RestController
@Slf4j
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;
 
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback")//没有配置
    public CommonResult<Payment> fallback(@PathVariable Long id)
    {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }
    
}
```

132_Sentinel 服务熔断只配置 fallback
-----------------------------

fallback 只负责业务异常

```
@RestController
@Slf4j
public class CircleBreakerController {
    
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;
 
    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//没有配置
    @SentinelResource(value = "fallback", fallback = "handlerFallback") //fallback只负责业务异常
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }
    
    //本例是fallback
    public CommonResult handlerFallback(@PathVariable  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }
    
}
```

测试地址 - http://localhost:84/consumer/fallback/4

页面返回结果：

```
{"code":444,"message":"兜底异常nandlerFal1back, exception内容illegalkrgumentEBxceptiorn,非法参数异常……","data":{"id":4,"seria:"null"}}
```

133_Sentinel 服务熔断只配置 blockHandler
---------------------------------

blockHandler 只负责 **sentinel 控制台配置违规**

```
@RestController
@Slf4j
public class CircleBreakerController
{
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") //没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
    @SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    public CommonResult<Payment> fallback(@PathVariable Long id)
    {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }
    //本例是fallback
/*    public CommonResult handlerFallback(@PathVariable  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }*/
    
    //本例是blockHandler
    public CommonResult blockHandler(@PathVariable  Long id,BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }
}
```

测试地址 - http://localhost:84/consumer/fallback/4

134_Sentinel 服务熔断 fallback 和 blockHandler 都配置
---------------------------------------------

若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。

```
@RestController
@Slf4j
public class CircleBreakerController
{
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") //没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable Long id)
    {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }
    //本例是fallback
    public CommonResult handlerFallback(@PathVariable  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }
    //本例是blockHandler
    public CommonResult blockHandler(@PathVariable  Long id,BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }
}
```

135_Sentinel 服务熔断 exceptionsToIgnore
------------------------------------

exceptionsToIgnore，忽略指定异常，即这些异常不用兜底方法处理。

```
@RestController
@Slf4j
public class CircleBreakerController    

    ...
    
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})//<-------------
    public CommonResult<Payment> fallback(@PathVariable Long id)
    {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            //exceptionsToIgnore属性有IllegalArgumentException.class，
            //所以IllegalArgumentException不会跳入指定的兜底程序。
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

	...
}
```

136_Sentinel 服务熔断 OpenFeign
---------------------------

**修改 84 模块**

*   84 消费者调用提供者 9003
    
*   Feign 组件一般是消费侧
    

POM

```
<!--SpringCloud openfeign -->

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

YML

```
# 激活Sentinel对Feign的支持
feign:
  sentinel:
    enabled: true
```

业务类

带 @Feignclient 注解的业务接口，fallback = PaymentFallbackService.class

```
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = PaymentFallbackService.class)
public interface PaymentService
{
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
```

```
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id)
    {
        return new CommonResult<>(44444,"服务降级返回,---PaymentFallbackService",new Payment(id,"errorSerial"));
    }
}
```

Controller

```
@RestController
@Slf4j
public class CircleBreakerController {

    ...
    
	//==================OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
        return paymentService.paymentSQL(id);
    }
}
```

主启动

```
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients//<------------------------
public class OrderNacosMain84 {
    public static void main(String[] args) {
        SpringApplication.run(OrderNacosMain84.class, args);
    }
}
```

测试 - http://localhost:84/consumer/paymentSQL/1

测试 84 调用 9003，此时故意关闭 9003 微服务提供者，**84 消费侧自动降级**，不会被耗死。

**熔断框架比较**

<table><thead><tr><th>-</th><th>Sentinel</th><th>Hystrix</th><th>resilience4j</th></tr></thead><tbody><tr><td>隔离策略</td><td>信号量隔离（并发线程数限流）</td><td>线程池隔商 / 信号量隔离</td><td>信号量隔离</td></tr><tr><td>熔断降级策略</td><td>基于响应时间<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>异常比率<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>异常数</td><td>基于异常比率</td><td>基于异常比率<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>响应时间</td></tr><tr><td>实时统计实现</td><td>滑动窗口（LeapArray）</td><td>滑动窗口（基于 RxJava）</td><td>Ring Bit Buffer</td></tr><tr><td>动态规则配置</td><td>支持多种数据源</td><td>支持多种数据源</td><td>有限支持</td></tr><tr><td>扩展性</td><td>多个扩展点</td><td>插件的形式</td><td>接口的形式</td></tr><tr><td>基于注解的支持</td><td>支持</td><td>支持</td><td>支持</td></tr><tr><td>限流</td><td>基于 QPS<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>支持基于调用关系的限流</td><td>有限的支持</td><td>Rate Limiter</td></tr><tr><td>流量整形</td><td>支持预热模式匀速器模式<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>预热排队模式</td><td>不支持</td><td>简单的 Rate Limiter 模式</td></tr><tr><td>系统自适应保护</td><td>支持</td><td>不支持</td><td>不支持</td></tr><tr><td>控制台</td><td>提供开箱即用的控制台<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>可配置规则<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>查看秒级监控<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>机器发观等</td><td>简单的监控查看</td><td>不提供控制台<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>可对接其它监控系统</td></tr></tbody></table>

137_Sentinel 持久化规则
------------------

**是什么**

一旦我们重启应用，sentinel 规则将消失，生产环境需要将配置规则进行持久化。

**怎么玩**

将限流配置规则持久化进 Nacos 保存，只要刷新 8401 某个 rest 地址，sentinel 控制台的流控规则就能看到，只要 Nacos 里面的配置不删除，针对 8401 上 sentinel 上的流控规则持续有效。

**步骤**

修改 cloudalibaba-sentinel-service8401

POM

```
<!--SpringCloud ailibaba sentinel-datasource-nacos 后续做持久化用到-->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```

YML

```
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #Nacos服务注册中心地址
    sentinel:
      transport:
        dashboard: localhost:8080 #配置Sentinel dashboard地址
        port: 8719
      datasource: #<---------------------------关注点，添加Nacos数据源配置
        ds1:
          nacos:
            server-addr: localhost:8848
            dataId: cloudalibaba-sentinel-service
            groupId: DEFAULT_GROUP
            data-type: json
            rule-type: flow

management:
  endpoints:
    web:
      exposure:
        include: '*'

feign:
  sentinel:
    enabled: true # 激活Sentinel对Feign的支持
```

添加 Nacos 业务规则配置

![](https://img-blog.csdnimg.cn/img_convert/2401a6b2df715ee64f647da2f31e1eeb.png)

配置内容解析

```
[{
    "resource": "/rateLimit/byUrl",
    "IimitApp": "default",
    "grade": 1,
    "count": 1, 
    "strategy": 0,
    "controlBehavior": 0,
    "clusterMode": false
}]
```

*   resource：资源名称；
*   limitApp：来源应用；
*   grade：阈值类型，0 表示线程数, 1 表示 QPS；
*   count：单机阈值；
*   strategy：流控模式，0 表示直接，1 表示关联，2 表示链路；
*   controlBehavior：流控效果，0 表示快速失败，1 表示 Warm Up，2 表示排队等待；
*   clusterMode：是否集群。

启动 8401 后刷新 sentinel 发现业务规则有了

![](https://img-blog.csdnimg.cn/img_convert/c854e986254c09d0a7866811ec1e0cb4.png)

快速访问测试接口 - http://localhost:8401/rateLimit/byUrl - 页面返回 `Blocked by Sentinel (flow limiting)`

停止 8401 再看 sentinel - 停机后发现流控规则没有了

![](https://img-blog.csdnimg.cn/img_convert/09ea175d22d31718e15c3b569d98d381.png)

重新启动 8401 再看 sentinel

*   乍一看还是没有，稍等一会儿
*   多次调用 - http://localhost:8401/rateLimit/byUrl
*   重新配置出现了，持久化验证通过

138_分布式事务问题由来
-------------

分布式前

*   单机单库没这个问题
*   从 1:1 -> 1:N -> N:N

单体应用被拆分成微服务应用，原来的三个模块被拆分成三个独立的应用, 分别使用三个独立的数据源，业务操作需要调用三三 个服务来完成。此时**每个服务内部的数据一致性由本地事务来保证， 但是全局的数据一致性问题没法保证**。

![](https://img-blog.csdnimg.cn/img_convert/9a619fb6a635ac96f2f17734bcda7967.png)

一句话：**一次业务操作需要跨多个数据源或需要跨多个系统进行远程调用，就会产生分布式事务问题**。

139_Seata 术语
------------

**是什么**

Seata 是一款开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务。

[官方网址](http://seata.io/zh-cn/)

**能干嘛**

一个典型的分布式事务过程

分布式事务处理过程的一 ID + 三组件模型：

*   Transaction ID XID 全局唯一的事务 ID
*   三组件概念
    *   TC (Transaction Coordinator) - 事务协调者：维护全局和分支事务的状态，驱动全局事务提交或回滚。
    *   TM (Transaction Manager) - 事务管理器：定义全局事务的范围：开始全局事务、提交或回滚全局事务。
    *   RM (Resource Manager) - 资源管理器：管理分支事务处理的资源，与 TC 交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。

处理过程：

1.  TM 向 TC 申请开启一个全局事务，全局事务创建成功并生成一个全局唯一的 XID；
2.  XID 在微服务调用链路的上下文中传播；
3.  RM 向 TC 注册分支事务，将其纳入 XID 对应全局事务的管辖；
4.  TM 向 TC 发起针对 XID 的全局提交或回滚决议；
5.  TC 调度 XID 下管辖的全部分支事务完成提交或回滚请求。

![](https://img-blog.csdnimg.cn/img_convert/2d2c6aa29c3158413f66d4ef8c1000dc.png)

140_Seata-Server 安装
-------------------

**去哪下**

发布说明: https://github.com/seata/seata/releases

**怎么玩**

本地 @Transactional

全局 @GlobalTransactional

**SEATA 的分布式交易解决方案**

![](https://img-blog.csdnimg.cn/img_convert/302377d33ddcd708e20b996bd9f2c7b8.png)

我们只需要使用一个 `@GlobalTransactional` 注解在业务方法上:

**Seata-Server 安装**

官网地址 - http://seata.io/zh-cn/

下载版本 - 0.9.0

seata-server-0.9.0.zip 解压到指定目录并修改 conf 目录下的 file.conf 配置文件

先备份原始 file.conf 文件

主要修改: 自定义事务组名称 + 事务日志存储模式为 db + 数据库连接信息

file.conf

service 模块

```
service {
    ##fsp_tx_group是自定义的
    vgroup_mapping.my.test.tx_group="fsp_tx_group" 
    default.grouplist = "127.0.0.1:8091"
    enableDegrade = false
    disable = false
    max.commitretry.timeout= "-1"
    max.ollbackretry.timeout= "-1"
}
```

store 模块

```
## transaction log store
store {
	## store mode: file, db
	## 改成db
	mode = "db"
	
	## file store
	file {
		dir = "sessionStore"
		
		# branch session size, if exceeded first try compress lockkey, still exceeded throws exceptions
		max-branch-session-size = 16384
		# globe session size, if exceeded throws exceptions
		max-global-session-size = 512
		# file buffer size, if exceeded allocate new buffer
		file-write-buffer-cache-size = 16384
		# when recover batch read size
		session.reload.read_size= 100
		# async, sync
		flush-disk-mode = async
	}

	# database store
	db {
		## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
		datasource = "dbcp"
		## mysql/oracle/h2/oceanbase etc.
		## 配置数据源
		db-type = "mysql"
		driver-class-name = "com.mysql.jdbc.Driver"
		url = "jdbc:mysql://127.0.0.1:3306/seata"
		user = "root"
		password = "你自己密码"
		min-conn= 1
		max-conn = 3
		global.table = "global_table"
		branch.table = "branch_table"
		lock-table = "lock_table"
		query-limit = 100
	}
}
```

mysql5.7 数据库新建库 seata，在 seata 库里建表

建表 db_store.sql 在 \ seata-server-0.9.0\seata\conf 目录里面

```
-- the table to store GlobalSession data
drop table if exists `global_table`;
create table `global_table` (
  `xid` varchar(128)  not null,
  `transaction_id` bigint,
  `status` tinyint not null,
  `application_id` varchar(32),
  `transaction_service_group` varchar(32),
  `transaction_name` varchar(128),
  `timeout` int,
  `begin_time` bigint,
  `application_data` varchar(2000),
  `gmt_create` datetime,
  `gmt_modified` datetime,
  primary key (`xid`),
  key `idx_gmt_modified_status` (`gmt_modified`, `status`),
  key `idx_transaction_id` (`transaction_id`)
);

-- the table to store BranchSession data
drop table if exists `branch_table`;
create table `branch_table` (
  `branch_id` bigint not null,
  `xid` varchar(128) not null,
  `transaction_id` bigint ,
  `resource_group_id` varchar(32),
  `resource_id` varchar(256) ,
  `lock_key` varchar(128) ,
  `branch_type` varchar(8) ,
  `status` tinyint,
  `client_id` varchar(64),
  `application_data` varchar(2000),
  `gmt_create` datetime,
  `gmt_modified` datetime,
  primary key (`branch_id`),
  key `idx_xid` (`xid`)
);

-- the table to store lock data
drop table if exists `lock_table`;
create table `lock_table` (
  `row_key` varchar(128) not null,
  `xid` varchar(96),
  `transaction_id` long ,
  `branch_id` long,
  `resource_id` varchar(256) ,
  `table_name` varchar(32) ,
  `pk` varchar(36) ,
  `gmt_create` datetime ,
  `gmt_modified` datetime,
  primary key(`row_key`)
);
```

修改 seata-server-0.9.0\seata\conf 目录下的 registry.conf 配置文件

```
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  # 改用为nacos
  type = "nacos"

  nacos {
  	## 加端口号
    serverAddr = "localhost:8848"
    namespace = ""
    cluster = "default"
  }
  ...
}
```

目的是：指明注册中心为 nacos，及修改 nacos 连接信息

先启动 Nacos 端口号 8848 nacos\bin\startup.cmd

再启动 seata-server - seata-server-0.9.0\seata\bin\seata-server.bat

141_Seata 业务数据库准备
-----------------

以下演示都需要先启动 Nacos 后启动 Seata, 保证两个都 OK。

分布式事务业务说明

这里我们会创建三个服务，一个订单服务，一个库存服务，一个账户服务。

当用户下单时, 会在订单服务中创建一个订单, 然后通过远程调用库存服务来扣减下单商品的库存，再通过远程调用账户服务来扣减用户账户里面的余额，最后在订单服务中修改订单状态为已完成。

该操作跨越三个数据库，有两次远程调用，很明显会有分布式事务问题。

**一言蔽之**，下订单—> 扣库存—> 减账户 (余额)。

创建业务数据库

*   seata_ order：存储订单的数据库;
*   seata_ storage：存储库存的数据库;
*   seata_ account：存储账户信息的数据库。

建库 SQL

```
CREATE DATABASE seata_order;
CREATE DATABASE seata_storage;
CREATE DATABASE seata_account;
```

按照上述 3 库分别建对应业务表

*   seata_order 库下建 t_order 表

```
CREATE TABLE t_order (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
    `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
    `count` INT(11) DEFAULT NULL COMMENT '数量',
    `money` DECIMAL(11,0) DEFAULT NULL COMMENT'金额',
    `status` INT(1) DEFAULT NULL COMMENT '订单状态: 0:创建中; 1:已完结',
) ENGINE=INNODB AUTO_INCREMENT=` DEFAULT CHARSET=utf8;

SELECT * FROM t_order;
```

*   seata_storage 库下建 t_storage 表

```
CREATE TABLE t_storage (
`id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
`total` INT(11) DEFAULT NULL COMMENT '总库存'，
`used` INT(11) DEFAULT NULL COMMENT '已用库存'，
`residue` INT(11) DEFAULT NULL COMMENT '剩余库存'
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO seata_storage.t_storage(`id`, `product_id`, `total`, `used`, `residue`)
VALUES ('1', '1', '100', '0','100');

SELECT * FROM t_storage;
```

*   seata_account 库下建 t_account 表

```
CREATE TABLE t_account(
	`id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
	`user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
	`total` DECIMAL(10,0) DEFAULT NULL COMMENT '总额度',
	`used` DECIMAL(10,0) DEFAULT NULL COMMENT '已用余额', I
	`residue` DECIMAL(10,0) DEFAULT '0' COMMENT '剩余可用额度'
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO seata_account.t_account(`id`, `user_id`, `total`, `used`, `residue`)
VALUES ('1', '1', '1000', '0', '1000');

SELECT * FROM t_account;
```

按照上述 3 库分别建对应的回滚日志表

*   订单 - 库存 - 账户 3 个库下**都需要建各自的回滚日志表**
*   \seata-server-0.9.0\seata\conf 目录下的 db_ undo_ log.sql
*   建表 SQL

```
-- the table to store seata xid data
-- 0.7.0+ add context
-- you must to init this sql for you business databese. the seata server not need it.
-- 此脚本必须初始化在你当前的业务数据库中，用于AT 模式XID记录。与server端无关（注：业务数据库）
-- 注意此处0.3.0+ 增加唯一索引 ux_undo_log
drop table `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

142_Seata 之 Order-Module 配置搭建
-----------------------------

下订单 -> 减库存 -> 扣余额 -> 改（订单）状态

seata-order-service2001

POM

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

    <artifactId>seata-order-service2001</artifactId>

    <dependencies>
        <!--nacos-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--seata-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>seata-all</artifactId>
                    <groupId>io.seata</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>io.seata</groupId>
            <artifactId>seata-all</artifactId>
            <version>0.9.0</version>
        </dependency>
        <!--feign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <!--web-actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--mysql-druid-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.37</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

</project>
```

配置文件

YML

```
server:
  port: 2001

spring:
  application:
    name: seata-order-service
  cloud:
    alibaba:
      seata:
        #自定义事务组名称需要与seata-server中的对应
        tx-service-group: fsp_tx_group
    nacos:
      discovery:
        server-addr: localhost:8848
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/seata_order
    username: root
    password: 123456

feign:
  hystrix:
    enabled: false

logging:
  level:
    io:
      seata: info

mybatis:
  mapperLocations: classpath:mapper/*.xml
```

file.conf

```
transport {
  # tcp udt unix-domain-socket
  type = "TCP"
  #NIO NATIVE
  server = "NIO"
  #enable heartbeat
  heartbeat = true
  #thread factory for netty
  thread-factory {
    boss-thread-prefix = "NettyBoss"
    worker-thread-prefix = "NettyServerNIOWorker"
    server-executor-thread-prefix = "NettyServerBizHandler"
    share-boss-worker = false
    client-selector-thread-prefix = "NettyClientSelector"
    client-selector-thread-size = 1
    client-worker-thread-prefix = "NettyClientWorkerThread"
    # netty boss thread size,will not be used for UDT
    boss-thread-size = 1
    #auto default pin or 8
    worker-thread-size = 8
  }
  shutdown {
    # when destroy server, wait seconds
    wait = 3
  }
  serialization = "seata"
  compressor = "none"
}

service {

  vgroup_mapping.fsp_tx_group = "default" #修改自定义事务组名称

  default.grouplist = "127.0.0.1:8091"
  enableDegrade = false
  disable = false
  max.commit.retry.timeout = "-1"
  max.rollback.retry.timeout = "-1"
  disableGlobalTransaction = false
}


client {
  async.commit.buffer.limit = 10000
  lock {
    retry.internal = 10
    retry.times = 30
  }
  report.retry.count = 5
  tm.commit.retry.count = 1
  tm.rollback.retry.count = 1
}

## transaction log store
store {
  ## store mode: file、db
  mode = "db"

  ## file store
  file {
    dir = "sessionStore"

    # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
    max-branch-session-size = 16384
    # globe session size , if exceeded throws exceptions
    max-global-session-size = 512
    # file buffer size , if exceeded allocate new buffer
    file-write-buffer-cache-size = 16384
    # when recover batch read size
    session.reload.read_size = 100
    # async, sync
    flush-disk-mode = async
  }

  ## database store
  db {
    ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
    datasource = "dbcp"
    ## mysql/oracle/h2/oceanbase etc.
    db-type = "mysql"
    driver-class-name = "com.mysql.jdbc.Driver"
    url = "jdbc:mysql://127.0.0.1:3306/seata"
    user = "root"
    password = "123456"
    min-conn = 1
    max-conn = 3
    global.table = "global_table"
    branch.table = "branch_table"
    lock-table = "lock_table"
    query-limit = 100
  }
}
lock {
  ## the lock store mode: local、remote
  mode = "remote"

  local {
    ## store locks in user's database
  }

  remote {
    ## store locks in the seata's server
  }
}
recovery {
  #schedule committing retry period in milliseconds
  committing-retry-period = 1000
  #schedule asyn committing retry period in milliseconds
  asyn-committing-retry-period = 1000
  #schedule rollbacking retry period in milliseconds
  rollbacking-retry-period = 1000
  #schedule timeout retry period in milliseconds
  timeout-retry-period = 1000
}

transaction {
  undo.data.validation = true
  undo.log.serialization = "jackson"
  undo.log.save.days = 7
  #schedule delete expired undo_log in milliseconds
  undo.log.delete.period = 86400000
  undo.log.table = "undo_log"
}

## metrics settings
metrics {
  enabled = false
  registry-type = "compact"
  # multi exporters use comma divided
  exporter-list = "prometheus"
  exporter-prometheus-port = 9898
}

support {
  ## spring
  spring {
    # auto proxy the DataSource bean
    datasource.autoproxy = false
  }
}
```

registry.conf

```
registry {
  # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  type = "nacos"

  nacos {
    serverAddr = "localhost:8848"
    namespace = ""
    cluster = "default"
  }
  eureka {
    serviceUrl = "http://localhost:8761/eureka"
    application = "default"
    weight = "1"
  }
  redis {
    serverAddr = "localhost:6379"
    db = "0"
  }
  zk {
    cluster = "default"
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  consul {
    cluster = "default"
    serverAddr = "127.0.0.1:8500"
  }
  etcd3 {
    cluster = "default"
    serverAddr = "http://localhost:2379"
  }
  sofa {
    serverAddr = "127.0.0.1:9603"
    application = "default"
    region = "DEFAULT_ZONE"
    datacenter = "DefaultDataCenter"
    cluster = "default"
    group = "SEATA_GROUP"
    addressWaitTime = "3000"
  }
  file {
    name = "file.conf"
  }
}

config {
  # file、nacos 、apollo、zk、consul、etcd3
  type = "file"

  nacos {
    serverAddr = "localhost"
    namespace = ""
  }
  consul {
    serverAddr = "127.0.0.1:8500"
  }
  apollo {
    app.id = "seata-server"
    apollo.meta = "http://192.168.1.204:8801"
  }
  zk {
    serverAddr = "127.0.0.1:2181"
    session.timeout = 6000
    connect.timeout = 2000
  }
  etcd3 {
    serverAddr = "http://localhost:2379"
  }
  file {
    name = "file.conf"
  }
}
```

domain

```
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>
{
    private Integer code;
    private String  message;
    private T       data;

    public CommonResult(Integer code, String message)
    {
        this(code,message,null);
    }
}
```

```
package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order
{
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private BigDecimal money;

    private Integer status; //订单状态：0：创建中；1：已完结
}
```

143_Seata 之 Order-Module 撸码 (上)
-------------------------------

Dao 接口及实现

```
import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao
{
    //1 新建订单
    void create(Order order);

    //2 修改订单状态，从零改为1
    void update(@Param("userId") Long userId,@Param("status") Integer status);
}
```

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.atguigu.springcloud.alibaba.dao.OrderDao">

    <resultMap id="BaseResultMap" type="com.atguigu.springcloud.alibaba.domain.Order">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <result column="user_id" property="userId" jdbcType="BIGINT"/>
        <result column="product_id" property="productId" jdbcType="BIGINT"/>
        <result column="count" property="count" jdbcType="INTEGER"/>
        <result column="money" property="money" jdbcType="DECIMAL"/>
        <result column="status" property="status" jdbcType="INTEGER"/>
    </resultMap>

    <insert id="create">
        insert into t_order (id,user_id,product_id,count,money,status)
        values (null,#{userId},#{productId},#{count},#{money},0);
    </insert>


    <update id="update">
        update t_order set status = 1
        where user_id=#{userId} and status = #{status};
    </update>

</mapper>
```

Service 接口及实现

*   OrderService
    *   OrderServiceImpl
*   StorageService
*   AccountService

```
import com.atguigu.springcloud.alibaba.domain.Order;

public interface OrderService
{
    void create(Order order);
}
```

```
import com.atguigu.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-storage-service")
public interface StorageService
{
    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
```

```
import com.atguigu.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService
{
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
```

```
import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService
{
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说：下订单->扣库存->减余额->改状态
     */
    @Override
    //@GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order)
    {
        log.info("----->开始新建订单");
        //1 新建订单
        orderDao.create(order);

        //2 扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //3 扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        //4 修改订单状态，从零到1,1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了，O(∩_∩)O哈哈~");

    }
}
```

144_Seata 之 Order-Module 撸码 (下)
-------------------------------

Controller

```
import com.atguigu.springcloud.alibaba.domain.CommonResult;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController
{
    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order)
    {
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }
}
```

Config 配置

*   MyBatisConfig
*   DataSourceProxyConfig

```
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.atguigu.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
```

```
import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;

/**
 * 使用Seata对数据源进行代理
 */
@Configuration
public class DataSourceProxyConfig {

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
        return sqlSessionFactoryBean.getObject();
    }

}
```

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
//取消数据源的自动创建，而是使用自己定义的
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SeataOrderMainApp2001
{

    public static void main(String[] args)
    {
        SpringApplication.run(SeataOrderMainApp2001.class, args);
    }
}
```

145_Seata 之 Storage-Module 说明
-----------------------------

与 seata-order-service2001 模块大致相同

seata- storage - service2002

POM（与 seata-order-service2001 模块大致相同）

YML

```
server:
  port: 2002

spring:
  application:
    name: seata-storage-service
  cloud:
    alibaba:
      seata:
        tx-service-group: fsp_tx_group
    nacos:
      discovery:
        server-addr: localhost:8848
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/seata_storage
    username: root
    password: 123456

logging:
  level:
    io:
      seata: info

mybatis:
  mapperLocations: classpath:mapper/*.xml
```

file.conf（与 seata-order-service2001 模块大致相同）

registry.conf（与 seata-order-service2001 模块大致相同）

domain

```
import lombok.Data;

@Data
public class Storage {

    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}
```

CommonResult（与 seata-order-service2001 模块大致相同）

Dao 接口及实现

```
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    //扣减库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
```

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.atguigu.springcloud.alibaba.dao.StorageDao">

    <resultMap id="BaseResultMap" type="com.atguigu.springcloud.alibaba.domain.Storage">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <result column="product_id" property="productId" jdbcType="BIGINT"/>
        <result column="total" property="total" jdbcType="INTEGER"/>
        <result column="used" property="used" jdbcType="INTEGER"/>
        <result column="residue" property="residue" jdbcType="INTEGER"/>
    </resultMap>

    <update id="decrease">
        UPDATE
            t_storage
        SET
            used = used + #{count},residue = residue - #{count}
        WHERE
            product_id = #{productId}
    </update>

</mapper>
```

Service 接口及实现

```
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
```

```
import com.atguigu.springcloud.alibaba.dao.StorageDao;
import com.atguigu.springcloud.alibaba.service.StorageService ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    /**
     * 扣减库存
     */
    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        LOGGER.info("------->storage-service中扣减库存结束");
    }
}
```

Controller

```
import com.atguigu.springcloud.alibaba.domain.CommonResult ;
import com.atguigu.springcloud.alibaba.service.StorageService ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
```

Config 配置（与 seata-order-service2001 模块大致相同）

主启动（与 seata-order-service2001 模块大致相同）

146_Seata 之 Account-Module 说明
-----------------------------

与 seata-order-service2001 模块大致相同

seata- account- service2003

POM（与 seata-order-service2001 模块大致相同）

YML

```
server:
  port: 2003

spring:
  application:
    name: seata-account-service
  cloud:
    alibaba:
      seata:
        tx-service-group: fsp_tx_group
    nacos:
      discovery:
        server-addr: localhost:8848
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/seata_account
    username: root
    password: 123456

feign:
  hystrix:
    enabled: false

logging:
  level:
    io:
      seata: info

mybatis:
  mapperLocations: classpath:mapper/*.xml
```

file.conf（与 seata-order-service2001 模块大致相同）

registry.conf（与 seata-order-service2001 模块大致相同）

domain

```
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总额度
     */
    private BigDecimal total;

    /**
     * 已用额度
     */
    private BigDecimal used;

    /**
     * 剩余额度
     */
    private BigDecimal residue;
}
```

CommonResult（与 seata-order-service2001 模块大致相同）

Dao 接口及实现

```
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {

    /**
     * 扣减账户余额
     */
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
```

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="com.atguigu.springcloud.alibaba.dao.AccountDao">

    <resultMap id="BaseResultMap" type="com.atguigu.springcloud.alibaba.domain.Account">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <result column="user_id" property="userId" jdbcType="BIGINT"/>
        <result column="total" property="total" jdbcType="DECIMAL"/>
        <result column="used" property="used" jdbcType="DECIMAL"/>
        <result column="residue" property="residue" jdbcType="DECIMAL"/>
    </resultMap>

    <update id="decrease">
        UPDATE t_account
        SET
          residue = residue - #{money},used = used + #{money}
        WHERE
          user_id = #{userId};
    </update>

</mapper>
```

Service 接口及实现

```
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
```

```
import com.atguigu.springcloud.alibaba.dao.AccountDao;
import com.atguigu.springcloud.alibaba.service.AccountService ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Resource
    AccountDao accountDao;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->account-service中扣减账户余额开始");
        accountDao.decrease(userId,money);
        LOGGER.info("------->account-service中扣减账户余额结束");
    }
}
```

Controller

```
import com.atguigu.springcloud.alibaba.domain.CommonResult ;
import com.atguigu.springcloud.alibaba.service.AccountService ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult(200,"扣减账户余额成功！");
    }
}
```

Config 配置（与 seata-order-service2001 模块大致相同）

主启动（与 seata-order-service2001 模块大致相同）

147_Seata 之 @GlobalTransactional 验证
-----------------------------------

下订单 -> 减库存 -> 扣余额 -> 改（订单）状态

数据库初始情况：

![](https://img-blog.csdnimg.cn/img_convert/e639c859e870eebd847d579347ed8755.png)

正常下单 - http://localhost:2001/order/create?userld=1&productld=1&count=10&money=100

数据库正常下单后状况：

![](https://img-blog.csdnimg.cn/img_convert/32401b689cf9a7d624fd0f2aea7ce414.png)

**超时异常，没加 @GlobalTransactional**

模拟 AccountServiceImpl 添加超时

```
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);


    @Resource
    AccountDao accountDao;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        LOGGER.info("------->account-service中扣减账户余额开始");
        //模拟超时异常，全局事务回滚
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(20); } catch (InterruptedException e) { e.printStackTrace(); }
        accountDao.decrease(userId,money);
        LOGGER.info("------->account-service中扣减账户余额结束");
    }
}
```

另外，OpenFeign 的调用默认时间是 1s 以内，所以最后会抛异常。

数据库情况

![](https://img-blog.csdnimg.cn/img_convert/af40cc3756cef7179e58c813ed404db3.png)

**故障情况**

*   当库存和账户金额扣减后，订单状态并没有设置为已经完成，没有从零改为 1
    
*   而且由于 feign 的重试机制，账户余额还有可能被多次扣减
    

**超时异常，加了 @GlobalTransactional**

用 @GlobalTransactional 标注 OrderServiceImpl 的 create() 方法。

```
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    
    ...

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * 简单说：下订单->扣库存->减余额->改状态
     */
    @Override
    //rollbackFor = Exception.class表示对任意异常都进行回滚
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order)
    {
		...
    }
}
```

还是模拟 AccountServiceImpl 添加超时，下单后数据库数据并没有任何改变，记录都添加不进来，**达到出异常，数据库回滚的效果**。

148_Seata 之原理简介
---------------

2019 年 1 月份蚂蚁金服和阿里巴巴共同开源的分布式事务解决方案。

Simple Extensible Autonomous Transaction Architecture，简单可扩展自治事务框架。

2020 起始，用 1.0 以后的版本。Alina Gingertail

![](https://img-blog.csdnimg.cn/img_convert/2d2c6aa29c3158413f66d4ef8c1000dc.png)

**分布式事务的执行流程**

*   TM 开启分布式事务 (TM 向 TC 注册全局事务记录) ;
*   按业务场景，编排数据库、服务等事务内资源 (RM 向 TC 汇报资源准备状态) ;
*   TM 结束分布式事务，事务一阶段结束 (TM 通知 TC 提交 / 回滚分布式事务) ;
*   TC 汇总事务信息，决定分布式事务是提交还是回滚；
*   TC 通知所有 RM 提交 / 回滚资源，事务二阶段结束。

**AT 模式如何做到对业务的无侵入**

*   是什么

> **前提**
> 
> *   基于支持本地 ACID 事务的关系型数据库。
> *   Java 应用，通过 JDBC 访问数据库。
> 
> **整体机制**
> 
> 两阶段提交协议的演变：
> 
> *   一阶段：业务数据和回滚日志记录在同一个本地事务中提交，释放本地锁和连接资源。
> *   二阶段：
>     *   提交异步化，非常快速地完成。
>     *   回滚通过一阶段的回滚日志进行反向补偿。
> 
> [link](http://seata.io/zh-cn/docs/overview/what-is-seata.html)

*   一阶段加载

在一阶段，Seata 会拦截 “业务 SQL”

1.  解析 SQL 语义，找到 “业务 SQL" 要更新的业务数据，在业务数据被更新前，将其保存成 "before image”
    
2.  执行 “业务 SQL" 更新业务数据，在业务数据更新之后,
    
3.  其保存成 "after image”，最后生成行锁。
    

以上操作全部在一个数据库事务内完成, 这样保证了一阶段操作的原子性。

![](https://img-blog.csdnimg.cn/img_convert/80a7bd6cacef78392b278af04d446562.png)

*   二阶段提交

二阶段如果顺利提交的话，因为 " 业务 SQL" 在一阶段已经提交至数据库，所以 Seata 框架只需将一阶段保存的快照数据和行锁删掉，完成数据清理即可。

![](https://img-blog.csdnimg.cn/img_convert/a16483118166481bd7f9d06f91a28146.png)

*   二阶段回滚

二阶段如果是回滚的话，Seata 就需要回滚一阶段已经执行的 “业务 SQL"，还原业务数据。

回滚方式便是用 "before image" 还原业务数据；但在还原前要首先要校验脏写，对比 “数据库当前业务数据” 和 "after image"。

如果两份数据完全一致就说明没有脏写， 可以还原业务数据，如果不一致就说明有脏写, 出现脏写就需要转人工处理。

![](https://img-blog.csdnimg.cn/img_convert/828b79e4c7679ce5f09069e551c2a717.png)

补充

![](https://img-blog.csdnimg.cn/img_convert/21da4fdc4260008c3324574abc33f0ae.png)

149_大厂面试第三季预告片之雪花算法 (上)
-----------------------

**为什么需要分布式全局唯一 ID 以及分布式 ID 的业务需求？集群高并发情况下如何保证分布式唯一全局 Id 生成？**

在复杂分布式系统中，往往需婴对大量的数据和消息进行唯一标识，如在美团点评的金融、支付、餐饮、酒店，猫眼电影等产品的系统中数据日渐增长，对数据分库分表后需要有一个唯一 ID 来标识一条数据或消息。特别一点的如订单、骑手、优惠券也都雷要有唯一 ID 做标识。此时一个能够生成全局唯一 ID 的系统是非常必要的。

**ID 生成规则部分硬性要求**

*   _全局唯一_：不能出现重复的 ID 号，既然是唯一 - 标识，这是最基本的要求
    
*   _趋势递增_：在 MySQL 的 InnoDB 引擎中使用的是聚集索引，由于多数 RDBMS 使用 Btree 的数据结构来存储索引数据，在主键的选择上面我们应该尽量使用有序的主键保证写入性能。
    
*   _单调递增_：保证下一个 ID 一定大于上一个 ID，例如事务版本号、IM 增量消息、排序等特殊需求
    
*   _信息安全_：如果 ID 是连续的，恶意用户的扒取工作就非常容易做了，直接按照顺序下载指定 URL 即可。如果是订单号就更危险了，竞对可以直接知道我们一天的单量。所以在一些应用场景下，需要 ID 无规则不规则，让竞争对手否好猜。
    
*   _含时间戳_：这样就能够在开发中快速了解这个分布式 id 的生成时间。
    

**ID 号生成系统的可用性要求**

*   _高可用_：发一个获取分布式 ID 的请求，服务器就要保证 99.999% 的情况下给我创建一个唯一分布式 ID。
    
*   _低延迟_：发一个获取分布式 ID 的请求，服务器就要快，极速。
    
*   _高 QPS_：假如并发一口气 10 万个创建分布式 ID 请求同时杀过来，服务器要顶的住且一下子成功创建 10 万个分布式 ID。
    

**一般通用方案**

_UUID_

UUID(Universally Unique ldentifer) 的标准型式包含 32 个 16 进制数字，以连了号分为五段，形式为 8-4-4-4-12 的 36 个字符， 示例：550e8400-e29b-41d4-a716-446655440000

性能非常高：本地生成，没有网络消耗

如果只是考虑唯一性，那就选用它吧

但是，入数据库性能差

**为什么无序的 UUID 会导致入库性能变差呢？**

1.  无序，无法预测他的生成顺序，不能生成递增有序的数字。首先分布式 ID 一般都会作为主键， 但是安装 MySQL 官方推荐主键要尽量越短越好，UUID 每一个都很长，所以不是很推荐。
    
2.  主键，ID 作为主键时在特定的环境会存在一些问题。比如做 DB 主键的场景下，UUID 就非常不适用 MySQL 官方有明确的建议主键要尽量越短越好 36 个字符长度的 UUID 不符合要求。
    
3.  索引，既然分布式 ID 是主键，然后主键是包含索引的，然后 MySQL 的索引是通过 B + 树来实现的，每一次新的 UUID 数据的插入，为了查询的优化，都会对索引底层的 B + 树进行修改，因为 UUID 数据是无序的，所以每一次 UUID 数据的插入都会对主键地械的 B + 树进行很大的修改，这一点很不好。 插入完全无序，不但会导致一 - 些中间节点产生分裂，也会白白创造出很多不饱和的节点，这样大大降低了数据库插入的性能。
    

> All indexes other than the clustered index are known as [secondary indexes](https://dev.mysql.com/doc/refman/8.0/en/glossary.html#glos_secondary_index). In `InnoDB`, each record in a secondary index contains the primary key columns for the row, as well as the columns specified for the secondary index. `InnoDB` uses this primary key value to search for the row in the clustered index.
> 
> If the primary key is long, the secondary indexes use more space, so it is advantageous to have a short primary key.
> 
> [link](https://dev.mysql.com/doc/refman/8.0/en/innodb-index-types.html)

**数据库自增主键**

_单机_

在单机里面，数据库的自增 ID 机制的主要原理是：数据库自增 ID 和 MySQL 数据库的 replace into 实现的。

REPLACE INTO 的含义是插入一条记录，如果表中唯一索引的值遇到冲突，则替换老数据。

这里的 replace into 跟 inset 功能类似，不同点在于：replace into 首先尝试插入数据列表中，如果发现表中已经有此行数据（根据主键或唯一索引判断）则先删除，再插入。否则直接插入新数据。

```
CREATE TABLE t_test(
	id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	stub CHAR(1) NOT NULL DEFAULT '',
	UNIQUE KEY stub(stub)
)

SELECT * FROMt_ test;

REPLACE INTO t_test (stub) VALUES('b');

SELECT LAST_INSERT_ID();
```

_集群分布式_

那数据库自增 ID 机制适合作分布式 ID 吗？答案是不太适合

1：系统水平扩展比较困难，比如定义好了步长和机器台数之后，如果要添加机器该怎么做？假设现在只有一台机器发号是 1，2，3，4，5（步长是 1），这  
个时候需要扩容机器一台。可以这样做：把第二台机器的初始值设置得比第一台超过很多，貌似还好，现在想象一下如果我们线上有 100 台机器，这  
个时候要扩容该怎么做？简直是噩梦，所以系统水平扩展方案复杂难以实现。

2：数据库压力还是很大，每次获取 ID 都得读写一次数据库， 非常影响性能，不符合分布式 ID 里面的延迟低和要高 QPS 的规则（在高并发下，如果都去数据库里面获取 id，那是非常影响性能的）

_基于 Redis 生成全局 ID 策略_

因为 Redis 是单线的天生保证原子性，可以使用原子操作 INCR 和 INCRBY 来实现

注意：在 Redis 集群情况下，同样和 MySQL 一样需要设置不同的增长步长，同时 key 一定要设置有效期可以使用 Redis 集群来获取更高的吞吐量。

假如一个集群中有 5 台 Redis。可以初始化每台 Redis 的值分别是 1,2,3,4,5，然后步长都是 5。

各个 Redis 生成的 ID 为:

A：1, 6, 11, 16, 21  
B：2, 7 , 12, 17, 22  
C：3, 8, 13, 18, 23  
D：4, 9, 14, 19, 24  
E：5, 10, 15, 20, 25

150_大厂面试第三季预告片之雪花算法 (下)
-----------------------

Twitter 的分布式自增 ID 算法 snowflake

**概述**

Twitter 的 snowflake 解决了这种需求，最初 Twitter 把存储系统从 MySQL 迁移到 Cassandra（由 Facebook 开发一套开源分布式 NoSQL 数据库系统）。因为 Cassandra 没有顺序 ID 生成机制，所以开发了这样一套全局唯一生成服务。

Twitter 的分布式雪花算法 SnowFlake，经测试 snowflake 每秒能够产生 26 万个自增可排序的 ID

1.  Twitter 的 SnowFlake 生成 ID 能够按照时间有序生成。
2.  SnowFlake 算法生成 ID 的结果是一个 64bit 大小的整数， 为一个 Long 型（转换成字符串后长度最多 19）。
3.  分布式系统内不会产生 ID 碰撞（由 datacenter 和 workerld 作区分）并且效率较高。

分布式系统中，有一些需要使用全局唯一 ID 的场景， 生成 ID 的基本要求：

1.  在分布式的环境下必须全局且唯一。
2.  一般都需要单调递增，因为一般唯一 ID 都会存到数据库，而 Innodb 的特性就是将内容存储在主键索引树上的叶子节点而且是从左往右，递增的，所以考  
    虑到数据库性能，一般生成的 ID 也最好是单调递增。 为了防止 ID 冲突可以使用 36 位的 UUID，但是 UUID 有一些缺点， 首先他相对比较长， 另外 UUID 一般是无序的。
3.  可能还会需要无规则，因为如果使用唯一 ID 作为订单号这种，为了不然别人知道一天的订单量是多少，就需要这个规则。

**结构**

雪花算法的几个核心组成部分：

![](https://img-blog.csdnimg.cn/img_convert/795b3d1fa01bbd15d8b7b85c2724bf42.png)

号段解析：

1bit：

不用，因为二进制中最高位是符号位，1 表示负数，0 表示正数。生成的 id 一般都是用整数，所以最高位固定为 0。

41bit - 时间戳，用来记录时间戳，毫秒级：

*   41 位可以表示 2 41 − 1 2^{41}-1 241−1 个数字。
*   如果只用来表示正整数（计算机中正数包含 0），可以表示的数值范围是：0 至 2 41 − 1 2^{41}-1 241−1， **减 1 是因为可表示的数值范围是从 0 开始算的，而不是 1**。
*   也就是说 41 位可以表示 2 41 − 1 2^{41}-1 241−1 个毫秒的值，转化成单位年则是 ( 2 41 − 1 ) / ( 1000 ∗ 60 ∗ 60 ∗ 24 ∗ 365 ) = 69 (2^{41}-1)/ (1000 * 60 * 60 * 24 *365) = 69 (241−1)/(1000∗60∗60∗24∗365)=69 年。

10bit - 工作机器 ID，用来记录工作机器 ID：

*   可以部署在 2 10 = 1024 2^{10}= 1024 210=1024 个节点，包括 5 位 DataCenterId 和 5 位 Workerld。
    
*   5 位 (bit) 可以表示的最大正整数是 2 5 − 1 = 31 2^{5}-1=31 25−1=31, 即可以用 0、1、2、3、…31 这 32 个数字，来表示不同的 DataCenterld 或 Workerld。
    

12bit - 序列号，用来记录同毫秒内产生的不同 id。

*   12 位 (bit) 可以表示的最大正整数是 2 12 − 1 = 4095 2^{12} - 1 = 4095 212−1=4095， 即可以用 0、1、2、 3、…4094 这 4095 个数字，来表示同一机器同一时间截 (毫秒) 内产生的 4095 个 ID 序号。

SnowFlake 可以保证：

*   所有生成的 ID 按时间趋势递增。
*   整个分布式系统内不会产生重复 id（因为有 DataCenterId 和 Workerld 来做区分)

**源码**

以下代码仅供学习：

```
/**
 * Twitter_Snowflake
 * SnowFlake的结构如下(每部分用-分开):
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 * 41位时间戳(毫秒级)，注意，41位时间戳不是存储当前时间的时间戳，而是存储时间戳的差值（当前时间戳 - 开始时间戳)
 * 得到的值），这里的的开始时间戳，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下面程序SnowflakeIdWorker类的startTime属性）。41位的时间戳，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
 * 10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间戳)产生4096个ID序号
 * 加起来刚好64位，为一个Long型。
 */
public class SnowflakeIdWorker {
    /** 开始时间戳 (2015-01-01) */
    private final long twepoch = 1420041600000L;

    /** 机器id所占的位数 */
    private final long workerIdBits = 5L;

    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 5L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /** 支持的最大数据标识id，结果是31 */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在id中占的位数 */
    private final long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间戳向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~31) */
    private long workerId;

    /** 数据中心ID(0~31) */
    private long datacenterId;

    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;

    /** 上次生成ID的时间戳 */
    private long lastTimestamp = -1L;

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public SnowflakeIdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间戳
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    /** 测试 */
    public static void main(String[] args) {
        System.out.println("开始："+System.currentTimeMillis());
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        for (int i = 0; i < 50; i++) {
            long id = idWorker.nextId();
            System.out.println(id);
//            System.out.println(Long.toBinaryString(id));
        }
        System.out.println("结束："+System.currentTimeMillis());
    }
}
```

**工程落地经验**

[Hutool 的 Snowflake 文档](https://www.hutool.cn/docs/#/core/%E5%B7%A5%E5%85%B7%E7%B1%BB/%E5%94%AF%E4%B8%80ID%E5%B7%A5%E5%85%B7-IdUtil?id=snowflake)

添加依赖

```
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-captcha</artifactId>
    <version>4.6.8</version>
</dependency>
```

示例程序：

```
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil; 
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class IdGeneratorSnowflake{
	private long workerId = 0;
	private long datacenterId = 1;
	private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

	public synchronized long snowflakeId(){
		return snowflake.nextId();
	}

	public synchronized long snowflakeId(long workerId, long datacenterId){
		Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
		return snowflake.nextId();
	}

	public static void main(String[] args){
	    IdGeneratorSnowflake idGenerator = new IdGeneratorSnowflake();
		System.out.println(idGenerator.snowflakeId());
        
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
		for (int i = 1; i <= 20; i++){
			threadPool.submit(() -> {
				System.out.print1n(idGenerator.snowflakeId());
			});
		}
        
		threadPool.shutdown();

	}
}
```

**优缺点**

优点：

毫秒数在高位，自增序列在低位，整个 ID 都是趋势递增的。

不依赖数据库等第三方系统，以服务的方式部署，稳定性更高，生成 ID 的性能也是非常高的。

可以根据自身业务特性分配 bit 位，非常灵活。

缺点：

依赖机器时钟，如果机器时钟回拨，会导致重复 ID 生成。

在单机上是递增的，但是由于设计到分布式环境，每台机器上的时钟不可能完全同步，有时候会出现不是全局递增的情况。

（此缺点可以认为无所谓，一般分布式 ID 只要求趋势递增，并不会严格要求递增，90% 的需求都只要求趋势递增）

**其他补充**

百度开源的分布式唯一 ID 生成器 UidGenerator

美团点评分布式 ID 生成系统 Leaf

Spring Cloud 组件总结
-----------------

<table><thead><tr><th>组件</th><th>简介</th><th>分类</th><th>官网</th><th>笔记</th><th>备注</th></tr></thead><tbody><tr><td>Eureka</td><td>Eureka is the Netflix Service Discovery Server and Client.</td><td>服务注册中心</td><td><a href="https://docs.spring.io/spring-cloud-netflix/docs/2.2.7.RELEASE/reference/html/#service-discovery-eureka-clients">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298270#15_Eureka_1148">link</a></td><td>eureka 中文解释：int.(因找到某物<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>尤指问题的答案而高兴) 我发现了<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>我找到了</td></tr><tr><td>Zookeeper</td><td>ZooKeeper is a centralized service for maintaining configuration information, naming, providing distributed synchronization, and providing group services.</td><td>服务注册中心</td><td><a href="https://zookeeper.apache.org/">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/107434932">link</a></td><td>zookeeper 中文解释：n. 动物园管理员</td></tr><tr><td>Consul</td><td>Consul is a service mesh solution providing a full featured control plane with service discovery, configuration, and segmentation functionality.</td><td>服务注册中心</td><td><a href="https://www.consul.io/">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298270#31_Consul_2333">link</a></td><td>consul 中文解释：n. 领事</td></tr><tr><td>Ribbon</td><td>Ribbon is a client-side load balancer that gives you a lot of control over the behavior of HTTP and TCP clients.</td><td>服务调用</td><td><a href="https://docs.spring.io/spring-cloud-netflix/docs/2.2.7.RELEASE/reference/html/#spring-cloud-ribbon">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298270#36_Ribbon_2780">link</a></td><td>ribbon 中文解释：n.(用于捆绑或装饰的) 带子; 丝带; 带状物;</td></tr><tr><td>OpenFeign</td><td>Feign is a declarative web service client. It makes writing web service clients easier.</td><td>服务调用</td><td><a href="https://docs.spring.io/spring-cloud-openfeign/docs/2.2.7.RELEASE/reference/html/">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298270#43_OpenFeign_3304">link</a></td><td>feign 中文意思：v. 假装<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>装作<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>佯装 (有某种感觉或生病<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>疲倦等)</td></tr><tr><td>Hystrix</td><td>Netflix has created a library called Hystrix that implements the circuit breaker pattern.</td><td>服务降级</td><td><a href="https://docs.spring.io/spring-cloud-netflix/docs/2.2.7.RELEASE/reference/html/#circuit-breaker-spring-cloud-circuit-breaker-with-hystrix">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298270#47_Hystrix_3696">link</a></td><td>hystrix 中文意思：n. 豪猪属; 猬草属; 豪猪; 豪猪亚属</td></tr><tr><td>GateWay</td><td>Spring Cloud Gateway aims to provide a simple, yet effective way to route to APIs and provide cross cutting concerns to them such as: security, monitoring/metrics, and resiliency.</td><td>服务网关</td><td><a href="https://docs.spring.io/spring-cloud-gateway/docs/2.2.7.RELEASE/reference/html/">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298282#66_GateWay_771">link</a></td><td>gateway 中文意思：n. 网关; 途径; 门道; 手段</td></tr><tr><td>Config</td><td>Spring Cloud Config provides server-side and client-side support for externalized configuration in a distributed system.</td><td>服务配置</td><td><a href="https://docs.spring.io/spring-cloud-config/docs/2.2.7.RELEASE/reference/html/">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298282#74_Config_1524">link</a></td><td>-</td></tr><tr><td>Bus</td><td>Spring Cloud Bus links nodes of a distributed system with a lightweight message broker.</td><td>服务总线</td><td><a href="https://docs.spring.io/spring-cloud-bus/docs/2.2.3.RELEASE/reference/html/">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298282#78_Bus_2078">link</a></td><td>-</td></tr><tr><td>Stream</td><td>Spring Cloud Stream is a framework for building message-driven microservice applications.</td><td>消息队列</td><td><a href="https://docs.spring.io/spring-cloud-stream/docs/3.0.10.RELEASE/reference/html/spring-cloud-stream.html#spring-cloud-stream-overview-introducing">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298282#83_Stream_2576">link</a></td><td>-</td></tr><tr><td>Sleuth</td><td>Spring Cloud Sleuth implements a distributed tracing solution for Spring Cloud.</td><td>服务跟踪</td><td><a href="https://docs.spring.io/spring-cloud-sleuth/docs/2.2.7.RELEASE/reference/html/">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298282#92_Sleuth_3191">link</a></td><td>sleuth 中文意思：n. 侦探</td></tr><tr><td>Nacos</td><td>Nacos 致力于帮助您发现<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>配置和管理微服务<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td><td>服务注册中心<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>服务配置<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>服务总线</td><td><a href="https://nacos.io/zh-cn/docs/what-is-nacos.html">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298282#96_Nacos_3488">link</a></td><td>NAme + COnfiguration + Service</td></tr><tr><td>Sentinel</td><td>Sentinel 是面向分布式服务架构的流量控制组件<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>主要以流量为切入点<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>从流量控制<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>熔断降级<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>系统自适应保护等多个维度来帮助您保障微服务的稳定性<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td><td>服务降级</td><td><a href="https://sentinelguard.io/zh-cn/docs/introduction.html">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298288#111_Sentinel_240">link</a></td><td>sentinel 中文意思：n. 哨兵</td></tr><tr><td>Seata</td><td>Seata 是一款开源的分布式事务解决方案<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>致力于在微服务架构下提供高性能和简单易用的分布式事务服务<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td><td>分布式事务</td><td><a href="https://seata.io/zh-cn/">link</a></td><td><a href="https://blog.csdn.net/u011863024/article/details/114298288#139_Seata_2287">link</a></td><td>-</td></tr></tbody></table>