> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/u011863024/article/details/114298282)

[Spring Cloud 学习笔记（1 / 3）](https://blog.csdn.net/u011863024/article/details/114298270)

[Spring Cloud 学习笔记（3 / 3）](https://blog.csdn.net/u011863024/article/details/114298288)

<table><thead><tr><th>-</th><th>-</th><th>-</th></tr></thead><tbody><tr><td><a href="#56_HystrixDefaultProperties_26" target="_self">56_Hystrix 之全局服务降级 DefaultProperties</a></td><td><a href="#57_HystrixFeignFallback_94" target="_self">57_Hystrix 之通配服务降级 FeignFallback</a></td><td><a href="#58_Hystrix_204" target="_self">58_Hystrix 之服务熔断理论</a></td></tr><tr><td><a href="#59_Hystrix_222" target="_self">59_Hystrix 之服务熔断案例 (上)</a></td><td><a href="#60_Hystrix_309" target="_self">60_Hystrix 之服务熔断案例 (下)</a></td><td><a href="#61_Hystrix_350" target="_self">61_Hystrix 之服务熔断总结</a></td></tr><tr><td><a href="#62_Hystrix_514" target="_self">62_Hystrix 工作流程最后总结</a></td><td><a href="#63_HystrixDashboard_556" target="_self">63_Hystrix 图形化 Dashboard 搭建</a></td><td><a href="#64_HystrixDashboard_664" target="_self">64_Hystrix 图形化 Dashboard 监控实战</a></td></tr><tr><td><a href="#65_GateWayZuul_763" target="_self">65_GateWay 和 Zuul 课程说明</a></td><td><a href="#66_GateWay_771" target="_self">66_GateWay 是什么</a></td><td><a href="#67_GateWay_814" target="_self">67_GateWay 非阻塞异步模型</a></td></tr><tr><td><a href="#68_Gateway_880" target="_self">68_Gateway 工作流程</a></td><td><a href="#69_Gateway9527_918" target="_self">69_Gateway9527 搭建</a></td><td><a href="#70_Gateway_1093" target="_self">70_Gateway 配置路由的两种方式</a></td></tr><tr><td><a href="#71_GateWay_1166" target="_self">71_GateWay 配置动态路由</a></td><td><a href="#72_GateWayPredicate_1244" target="_self">72_GateWay 常用的 Predicate</a></td><td><a href="#73_GateWayFilter_1413" target="_self">73_GateWay 的 Filter</a></td></tr><tr><td><a href="#74_Config_1524" target="_self">74_Config 分布式配置中心介绍</a></td><td><a href="#75_Config_1574" target="_self">75_Config 配置总控中心搭建</a></td><td><a href="#76_Config_1795" target="_self">76_Config 客户端配置与测试</a></td></tr><tr><td><a href="#77_Config_1985" target="_self">77_Config 动态刷新之手动版</a></td><td><a href="#78_Bus_2078" target="_self">78_Bus 消息总线是什么</a></td><td><a href="#79_BusRabbitMQ_2118" target="_self">79_Bus 之 RabbitMQ 环境配置</a></td></tr><tr><td><a href="#80_Bus_2150" target="_self">80_Bus 动态刷新全局广播的设计思想和选型</a></td><td><a href="#81_Bus_2339" target="_self">81_Bus 动态刷新全局广播配置实现</a></td><td><a href="#82_Bus_2544" target="_self">82_Bus 动态刷新定点通知</a></td></tr><tr><td><a href="#83_Stream_2576" target="_self">83_Stream 为什么被引入</a></td><td><a href="#84_StreamBinder_2595" target="_self">84_Stream 是什么及 Binder 介绍</a></td><td><a href="#85_Stream_2620" target="_self">85_Stream 的设计思想</a></td></tr><tr><td><a href="#86_Stream_2671" target="_self">86_Stream 编码常用注解简介</a></td><td><a href="#87_Stream_2714" target="_self">87_Stream 消息驱动之生产者</a></td><td><a href="#88_Stream_2915" target="_self">88_Stream 消息驱动之消费者</a></td></tr><tr><td><a href="#89_Stream_3072" target="_self">89_Stream 之消息重复消费</a></td><td><a href="#90_Streamgroup_3113" target="_self">90_Stream 之 group 解决消息重复消费</a></td><td><a href="#91_Stream_3177" target="_self">91_Stream 之消息持久化</a></td></tr><tr><td><a href="#92_Sleuth_3191" target="_self">92_Sleuth 是什么</a></td><td><a href="#93_Sleuthzipkin_3225" target="_self">93_Sleuth 之 zipkin 搭建安装</a></td><td><a href="#94_Sleuth_3274" target="_self">94_Sleuth 链路监控展现</a></td></tr><tr><td><a href="#95_Cloud_Alibaba_3390" target="_self">95_Cloud Alibaba 简介</a></td><td><a href="#96_Nacos_3488" target="_self">96_Nacos 简介和下载</a></td><td><a href="#97_Nacos_3531" target="_self">97_Nacos 安装</a></td></tr><tr><td><a href="#98_Nacos_3543" target="_self">98_Nacos 之服务提供者注册</a></td><td><a href="#99_Nacos_3710" target="_self">99_Nacos 之服务消费者注册和负载</a></td><td><a href="#100_Nacos_3886" target="_self">100_Nacos 服务注册中心对比提升</a></td></tr><tr><td><a href="#101_Nacos_3928" target="_self">101_Nacos 之服务配置中心</a></td><td><a href="#102_NacosDataID_4147" target="_self">102_Nacos 之命名空间分组和 DataID 三者关系</a></td><td><a href="#103_NacosDataID_4204" target="_self">103_Nacos 之 DataID 配置</a></td></tr><tr><td><a href="#104_NacosGroup_4235" target="_self">104_Nacos 之 Group 分组方案</a></td><td><a href="#105_NacosNamespace_4259" target="_self">105_Nacos 之 Namespace 空间方案</a></td><td><a href="#106_Nacos__4308" target="_self">106_Nacos 集群_架构说明</a></td></tr><tr><td><a href="#107_Nacos_4383" target="_self">107_Nacos 持久化切换配置</a></td><td>-</td><td>-</td></tr></tbody></table>

56_Hystrix 之全局服务降级 DefaultProperties
------------------------------------

**目前问题 1** 每个业务方法对应一个兜底的方法，代码膨胀

**解决方法**

1:1 每个方法配置一个服务降级方法，技术上可以，但是不聪明

1:N 除了个别重要核心业务有专属，其它普通的可以通过 @DefaultProperties(defaultFallback = “”) 统一跳转到统一处理结果页面

通用的和独享的各自分开，避免了代码膨胀，合理减少了代码量

```
import com.lun.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
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
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty()
//    })
    @HystrixCommand//用全局的fallback方法
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        //int age = 10/0;
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "我是消费者80,对方支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }

    // 下面是全局fallback方法
    public String payment_Global_FallbackMethod()
    {
        return "Global异常处理信息，请稍后再试，/(ㄒoㄒ)/~~";
    }
}
```

57_Hystrix 之通配服务降级 FeignFallback
--------------------------------

**目前问题 2** 统一和自定义的分开，代码混乱

**服务降级，客户端去调用服务端，碰上服务端宕机或关闭**

本次案例服务降级处理是在客户端 80 实现完成的，与服务端 8001 没有关系，只需要为 Feign 客户端定义的接口添加一个服务降级处理的实现类即可实现解耦

**未来我们要面对的异常**

*   运行
*   超时
*   宕机

**修改 cloud-consumer-feign-hystrix-order80**

根据 cloud-consumer-feign-hystrix-order80 已经有的 PaymentHystrixService 接口，  
重新新建一个类 (AaymentFallbackService) 实现该接口，统一为接口里面的方法进行异常处理

PaymentFallbackService 类实现 PaymentHystrixService 接口

```
import org.springframework.stereotype.Component;


@Component
public class PaymentFallbackService implements PaymentHystrixService
{
    @Override
    public String paymentInfo_OK(Integer id)
    {
        return "-----PaymentFallbackService fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id)
    {
        return "-----PaymentFallbackService fall back-paymentInfo_TimeOut ,o(╥﹏╥)o";
    }
}
```

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

PaymentHystrixService 接口

```
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT" ,//
             fallback = PaymentFallbackService.class)//指定PaymentFallbackService类
public interface PaymentHystrixService
{
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
```

**测试**

单个 eureka 先启动 7001

PaymentHystrixMain8001 启动

正常访问测试 - http://localhost/consumer/payment/hystrix/ok/1

故意关闭微服务 8001

客户端自己调用提示 - 此时服务端 provider 已经 down 了，但是我们做了服务降级处理，让客户端在服务端不可用时也会获得提示信息而不会挂起耗死服务器。

58_Hystrix 之服务熔断理论
------------------

断路器，相当于保险丝。

**熔断机制概述**

熔断机制是应对雪崩效应的一种微服务链路保护机制。当扇出链路的某个微服务出错不可用或者响应时间太长时，会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的响应信息。**当检测到该节点微服务调用响应正常后，恢复调用链路**。

在 Spring Cloud 框架里，熔断机制通过 Hystrix 实现。Hystrix 会监控微服务间调用的状况，当失败的调用到一定阈值，缺省是 5 秒内 20 次调用失败，就会启动熔断机制。熔断机制的注解是 @HystrixCommand。

[Martin Fowler 的相关论文](https://martinfowler.com/bliki/CircuitBreaker.html)

![](https://img-blog.csdnimg.cn/img_convert/84d60234d01c4b7e9cae515066eb711b.png)

59_Hystrix 之服务熔断案例 (上)
----------------------

[Hutool 国产工具类](https://hutool.cn/)

修改 cloud-provider-hystrix-payment8001

```
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService{    

    ...
    
    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
```

> The precise way that the circuit opening and closing occurs is as follows:
> 
> 1.  Assuming the volume across a circuit meets a certain threshold : `HystrixCommandProperties.circuitBreakerRequestVolumeThreshold()`
> 2.  And assuming that the error percentage, as defined above exceeds the error percentage defined in : `HystrixCommandProperties.circuitBreakerErrorThresholdPercentage()`
> 3.  Then the circuit-breaker transitions from CLOSED to OPEN.
> 4.  While it is open, it short-circuits all requests made against that circuit-breaker.
> 5.  After some amount of time (`HystrixCommandProperties.circuitBreakerSleepWindowInMilliseconds()`), the next request is let through. If it fails, the command stays OPEN for the sleep window. If it succeeds, it transitions to CLOSED and the logic in 1) takes over again.
> 
> [link](https://github.com/Netflix/Hystrix/issues/674)

HystrixCommandProperties 配置类

```
package com.netflix.hystrix;

...

public abstract class HystrixCommandProperties {
    private static final Logger logger = LoggerFactory.getLogger(HystrixCommandProperties.class);

    /* defaults */
    /* package */ static final Integer default_metricsRollingStatisticalWindow = 10000;// default => statisticalWindow: 10000 = 10 seconds (and default of 10 buckets so each bucket is 1 second)
    private static final Integer default_metricsRollingStatisticalWindowBuckets = 10;// default => statisticalWindowBuckets: 10 = 10 buckets in a 10 second window so each bucket is 1 second
    private static final Integer default_circuitBreakerRequestVolumeThreshold = 20;// default => statisticalWindowVolumeThreshold: 20 requests in 10 seconds must occur before statistics matter
    private static final Integer default_circuitBreakerSleepWindowInMilliseconds = 5000;// default => sleepWindow: 5000 = 5 seconds that we will sleep before trying again after tripping the circuit
    private static final Integer default_circuitBreakerErrorThresholdPercentage = 50;// default => errorThresholdPercentage = 50 = if 50%+ of requests in 10 seconds are failures or latent then we will trip the circuit
    private static final Boolean default_circuitBreakerForceOpen = false;// default => forceCircuitOpen = false (we want to allow traffic)
    /* package */ static final Boolean default_circuitBreakerForceClosed = false;// default => ignoreErrors = false 
    private static final Integer default_executionTimeoutInMilliseconds = 1000; // default => executionTimeoutInMilliseconds: 1000 = 1 second
    private static final Boolean default_executionTimeoutEnabled = true;

    ...
}
```

60_Hystrix 之服务熔断案例 (下)
----------------------

```
@RestController
@Slf4j
public class PaymentController
{
    @Resource
    private PaymentService paymentService;

    ...
    
    //====服务熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }
}
```

**测试**

自测 cloud-provider-hystrix-payment8001

正确 - http://localhost:8001/payment/circuit/1

错误 - http://localhost:8001/payment/circuit/-1

多次错误，再来次正确，但错误得显示

重点测试 - 多次错误，然后慢慢正确，发现刚开始不满足条件，就算是正确的访问地址也不能进行

61_Hystrix 之服务熔断总结
------------------

**大神结论**

[Martin Fowler 的相关论文](https://martinfowler.com/bliki/CircuitBreaker.html)

![](https://img-blog.csdnimg.cn/img_convert/84d60234d01c4b7e9cae515066eb711b.png)

**熔断类型**

*   熔断打开：请求不再进行调用当前服务，内部设置时钟一般为 MTTR(平均故障处理时间)，当打开时长达到所设时钟则进入半熔断状态。
*   熔断关闭：熔断关闭不会对服务进行熔断。
*   熔断半开：部分请求根据规则调用当前服务，如果请求成功且符合规则则认为当前服务恢复正常，关闭熔断。

**官网断路器流程图**

![](https://img-blog.csdnimg.cn/img_convert/825d02fd7925521b1d76be0a21c15db0.png)

**官网步骤**

> The precise way that the circuit opening and closing occurs is as follows:
> 
> 1.  Assuming the volume across a circuit meets a certain threshold : `HystrixCommandProperties.circuitBreakerRequestVolumeThreshold()`
> 2.  And assuming that the error percentage, as defined above exceeds the error percentage defined in : `HystrixCommandProperties.circuitBreakerErrorThresholdPercentage()`
> 3.  Then the circuit-breaker transitions from CLOSED to OPEN.
> 4.  While it is open, it short-circuits all requests made against that circuit-breaker.
> 5.  After some amount of time (`HystrixCommandProperties.circuitBreakerSleepWindowInMilliseconds()`), the next request is let through. If it fails, the command stays OPEN for the sleep window. If it succeeds, it transitions to CLOSED and the logic in 1) takes over again.
> 
> [link](https://github.com/Netflix/Hystrix/issues/674)

**断路器在什么情况下开始起作用**

```
//=====服务熔断
@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
    @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
})
public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
    ...
}
```

涉及到断路器的三个重要参数：

1.  **快照时间窗**：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的 10 秒。
2.  **请求总数阀值**：在快照时间窗内，必须满足请求总数阀值才有资格熔断。默认为 20，意味着在 10 秒内，如果该 hystrix 命令的调用次数不足 20 次 7, 即使所有的请求都超时或其他原因失败，断路器都不会打开。
3.  **错误百分比阀值**：当请求总数在快照时间窗内超过了阀值，比如发生了 30 次调用，如果在这 30 次调用中，有 15 次发生了超时异常，也就是超过 50% 的错误百分比，在默认设定 50% 阀值情况下，这时候就会将断路器打开。

**断路器开启或者关闭的条件**

*   到达以下阀值，断路器将会开启：
    
    *   当满足一定的阀值的时候（默认 10 秒内超过 20 个请求次数)
    *   当失败率达到一定的时候（默认 10 秒内超过 50% 的请求失败)
*   当开启的时候，所有请求都不会进行转发
    
*   一段时间之后（默认是 5 秒)，这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。
    

**断路器打开之后**

1：再有请求调用的时候，将不会调用主逻辑，而是直接调用降级 fallback。通过断路器，实现了自动地发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。

2：原来的主逻辑要如何恢复呢？

对于这一问题，hystrix 也为我们实现了自动恢复功能。

当断路器打开，对主逻辑进行熔断之后，hystrix 会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时的成为主逻辑，当休眠时间窗到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请求正常返回，那么断路器将继续闭合，主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时。

**All 配置**

```
@HystrixCommand(fallbackMethod = "fallbackMethod", 
                groupKey = "strGroupCommand", 
                commandKey = "strCommand", 
                threadPoolKey = "strThreadPool",
                
                commandProperties = {
                    // 设置隔离策略，THREAD 表示线程池 SEMAPHORE：信号池隔离
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    // 当隔离策略选择信号池隔离的时候，用来设置信号池的大小（最大并发数）
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 配置命令执行的超时时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutinMilliseconds", value = "10"),
                    // 是否启用超时时间
                    @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
                    // 执行超时的时候是否中断
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
                    
                    // 执行被取消的时候是否中断
                    @HystrixProperty(name = "execution.isolation.thread.interruptOnCancel", value = "true"),
                    // 允许回调方法执行的最大并发数
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 服务降级是否启用，是否执行回调函数
                    @HystrixProperty(name = "fallback.enabled", value = "true"),
                    // 是否启用断路器
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    // 该属性用来设置在滚动时间窗中，断路器熔断的最小请求数。例如，默认该值为 20 的时候，如果滚动时间窗（默认10秒）内仅收到了19个请求， 即使这19个请求都失败了，断路器也不会打开。
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
                    
                    // 该属性用来设置在滚动时间窗中，表示在滚动时间窗中，在请求数量超过 circuitBreaker.requestVolumeThreshold 的情况下，如果错误请求数的百分比超过50, 就把断路器设置为 "打开" 状态，否则就设置为 "关闭" 状态。
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // 该属性用来设置当断路器打开之后的休眠时间窗。 休眠时间窗结束之后，会将断路器置为 "半开" 状态，尝试熔断的请求命令，如果依然失败就将断路器继续设置为 "打开" 状态，如果成功就设置为 "关闭" 状态。
                    @HystrixProperty(name = "circuitBreaker.sleepWindowinMilliseconds", value = "5000"),
                    // 断路器强制打开
                    @HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
                    // 断路器强制关闭
                    @HystrixProperty(name = "circuitBreaker.forceClosed", value = "false"),
                    // 滚动时间窗设置，该时间用于断路器判断健康度时需要收集信息的持续时间
                    @HystrixProperty(name = "metrics.rollingStats.timeinMilliseconds", value = "10000"),
                    
                    // 该属性用来设置滚动时间窗统计指标信息时划分"桶"的数量，断路器在收集指标信息的时候会根据设置的时间窗长度拆分成多个 "桶" 来累计各度量值，每个"桶"记录了一段时间内的采集指标。
                    // 比如 10 秒内拆分成 10 个"桶"收集这样，所以 timeinMilliseconds 必须能被 numBuckets 整除。否则会抛异常
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
                    // 该属性用来设置对命令执行的延迟是否使用百分位数来跟踪和计算。如果设置为 false, 那么所有的概要统计都将返回 -1。
                    @HystrixProperty(name = "metrics.rollingPercentile.enabled", value = "false"),
                    // 该属性用来设置百分位统计的滚动窗口的持续时间，单位为毫秒。
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "60000"),
                    // 该属性用来设置百分位统计滚动窗口中使用 “ 桶 ”的数量。
                    @HystrixProperty(name = "metrics.rollingPercentile.numBuckets", value = "60000"),
                    // 该属性用来设置在执行过程中每个 “桶” 中保留的最大执行次数。如果在滚动时间窗内发生超过该设定值的执行次数，
                    // 就从最初的位置开始重写。例如，将该值设置为100, 滚动窗口为10秒，若在10秒内一个 “桶 ”中发生了500次执行，
                    // 那么该 “桶” 中只保留 最后的100次执行的统计。另外，增加该值的大小将会增加内存量的消耗，并增加排序百分位数所需的计算时间。
                    @HystrixProperty(name = "metrics.rollingPercentile.bucketSize", value = "100"),
                    
                    // 该属性用来设置采集影响断路器状态的健康快照（请求的成功、 错误百分比）的间隔等待时间。
                    @HystrixProperty(name = "metrics.healthSnapshot.intervalinMilliseconds", value = "500"),
                    // 是否开启请求缓存
                    @HystrixProperty(name = "requestCache.enabled", value = "true"),
                    // HystrixCommand的执行和事件是否打印日志到 HystrixRequestLog 中
                    @HystrixProperty(name = "requestLog.enabled", value = "true"),

                },
                threadPoolProperties = {
                    // 该参数用来设置执行命令线程池的核心线程数，该值也就是命令执行的最大并发量
                    @HystrixProperty(name = "coreSize", value = "10"),
                    // 该参数用来设置线程池的最大队列大小。当设置为 -1 时，线程池将使用 SynchronousQueue 实现的队列，否则将使用 LinkedBlockingQueue 实现的队列。
                    @HystrixProperty(name = "maxQueueSize", value = "-1"),
                    // 该参数用来为队列设置拒绝阈值。 通过该参数， 即使队列没有达到最大值也能拒绝请求。
                    // 该参数主要是对 LinkedBlockingQueue 队列的补充,因为 LinkedBlockingQueue 队列不能动态修改它的对象大小，而通过该属性就可以调整拒绝请求的队列大小了。
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "5"),
                }
               )
public String doSomething() {
	...
}
```

62_Hystrix 工作流程最后总结
-------------------

**服务限流** - 后面高级篇讲解 alibaba 的 Sentinel 说明

[官方解释](https://github.com/Netflix/Hystrix/wiki/How-it-Works)

**官网图例**

[外链图片转存失败, 源站可能有防盗链机制, 建议将图片保存下来直接上传 (img-yX9XouZg-1614712032449)(https://raw.githubusercontent.com/wiki/Netflix/Hystrix/images/hystrix-command-flow-chart.png)]

**步骤说明**

1.  创建 HystrixCommand （用在依赖的服务返回单个操作结果的时候）或 HystrixObserableCommand（用在依赖的服务返回多个操作结果的时候）对象。
2.  命令执行。
3.  其中 HystrixCommand 实现了下面前两种执行方式
    1.  execute()：同步执行，从依赖的服务返回一个单一的结果对象或是在发生错误的时候抛出异常。  
        2. queue()：异步执行，直接返回一个 Future 对象，其中包含了服务执行结束时要返回的单一结果对象。
4.  而 HystrixObservableCommand 实现了后两种执行方式：
    1.  obseve()：返回 Observable 对象，它代表了操作的多个统  
        果，它是一个 Hot Observable （不论 “事件源” 是否有 “订阅者”，都会在创建后对事件进行发布，所以对于 Hot Observable 的每一个 “订阅者” 都有可能是从 “事件源” 的中途开始的，并可能只是看到了整个操作的局部过程）。  
        2. toObservable()：同样会返回 Observable 对象，也代表了操作的多个结果，但它返回的是一个 Cold Observable（没有 “订间者” 的时候并不会发布事件，而是进行等待，直到有 “订阅者 " 之后才发布事件，所以对于 Cold Observable 的订阅者，它可以保证从一开始看到整个操作的全部过程）。
5.  若当前命令的请求缓存功能是被启用的，并且该命令缓存命中，那么缓存的结果会立即以 Observable 对象的形式返回。
6.  检查断路器是否为打开状态。如果断路器是打开的，那么 Hystrix 不会执行命令，而是转接到 fallback 处理逻辑 (第 8 步)；如果断路器是关闭的，检查是否有可用资源来执行命令 (第 5 步)。
7.  线程池 / 请求队列信号量是否占满。如果命令依赖服务的专有线程地和请求队列，或者信号量（不使用线程的时候）已经被占满，那么 Hystrix 也不会执行命令，而是转接到 fallback 处理理辑 (第 8 步)。
8.  Hystrix 会根据我们编写的方法来决定采取什么样的方式去请求依赖服务。
    1.  HystrixCommand.run()：返回一个单一的结果，或者抛出异常。
    2.  HystrixObservableCommand.construct()：返回一个 Observable 对象来发射多个结果，或通过 onError 发送错误通知。
9.  Hystix 会将 “成功”、“失败”、“拒绝”、“超时” 等信息报告给断路器，而断路器会维护一组计数器来统计这些数据。断路器会使用这些统计数据来决定是否要将断路器打开，来对某个依赖服务的请求进行 " 熔断 / 短路 "。
10.  当命令执行失败的时候，Hystix 会进入 fallback 尝试回退处理，我们通常也称波操作为 “服务降级”。而能够引起服务降级处理的情况有下面几种：
    1.  第 4 步∶当前命令处于 “熔断 / 短路” 状态，断洛器是打开的时候。
    2.  第 5 步∶当前命令的钱程池、请求队列或者信号量被占满的时候。
    3.  第 6 步∶HystrixObsevableCommand.construct() 或 HytrixCommand.run() 抛出异常的时候。
11.  当 Hystrix 命令执行成功之后，它会将处理结果直接返回或是以 Observable 的形式返回。

**tips**：如果我们没有为命令实现降级逻辑或者在降级处理逻辑中抛出了异常，Hystrix 依然会运回一个 Obsevable 对象，但是它不会发射任结果数惯，而是通过 onError 方法通知命令立即中断请求，并通过 onError 方法将引起命令失败的异常发送给调用者。

63_Hystrix 图形化 Dashboard 搭建
---------------------------

**概述**

除了隔离依赖服务的调用以外，Hystrix 还提供了准实时的调用监控 (Hystrix Dashboard)，Hystrix 会持续地记录所有通过 Hystrix 发起的请求的执行信息，并以统计报表和图形的形式展示给用户，包括每秒执行多少请求多少成功，多少失败等。

Netflix 通过 hystrix-metrics-event-stream 项目实现了对以上指标的监控。Spring Cloud 也提供了 Hystrix Dashboard 的整合，对监控内容转化成可视化界面。

**仪表盘 9001**

1 新建 cloud-consumer-hystrix-dashboard9001

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

    <artifactId>cloud-consumer-hystrix-dashboard9001</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
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

3.YML

```
server:
  port: 9001
```

4.HystrixDashboardMain9001 + 新注解 @EnableHystrixDashboard

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardMain9001
{
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }
}
```

5. 所有 Provider 微服务提供类 (8001/8002/8003) 都需要监控依赖配置

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

6. 启动 cloud-consumer-hystrix-dashboard9001 该微服务后续将监控微服务 8001

浏览器输入 http://localhost:9001/hystrix

64_Hystrix 图形化 Dashboard 监控实战
-----------------------------

**修改 cloud-provider-hystrix-payment8001**

注意：新版本 Hystrix 需要在主启动类 PaymentHystrixMain8001 中指定监控路径

```
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PaymentHystrixMain8001
{
    public static void main(String[] args) {
            SpringApplication.run(PaymentHystrixMain8001.class, args);
    }


    /**
     *此配置是为了服务监控而配置，与服务容错本身无关，springcloud升级后的坑
     *ServletRegistrationBean因为springboot的默认路径不是"/hystrix.stream"，
     *只要在自己的项目里配置上下面的servlet就可以了
     *否则，Unable to connect to Command Metric Stream 404
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
```

**监控测试**

启动 1 个 eureka

启动 8001，9001

**观察监控窗口**

9001 监控 8001 - 填写监控地址 - http://localhost:8001/hystrix.stream 到 http://localhost:9001/hystrix 页面的输入框。

测试地址

*   http://localhost:8001/payment/circuit/1
    
*   http://localhost:8001/payment/circuit/-1
    
*   测试通过
    
*   先访问正确地址，再访问错误地址，再正确地址，会发现图示断路器都是慢慢放开的。
    

![](https://img-blog.csdnimg.cn/img_convert/34bd091b54f913b088bace6c3a89a79c.png)

**如何看?**

*   7 色

![](https://img-blog.csdnimg.cn/img_convert/6740b2a462751db0ce8f2813f740c5b5.png)

*   1 圈

实心圆：共有两种含义。它通过颜色的变化代表了实例的健康程度，它的健康度从绿色 < 黄色 < 橙色 < 红色递减。

该实心圆除了颜色的变化之外，它的大小也会根据实例的请求流量发生变化，**流量越大该实心圆就越大**。所以通过该实心圆的展示，就可以在大量的实例中快速的发现故障实例和高压力实例。

*   1 线

曲线：用来记录 2 分钟内流量的相对变化，可以通过它来观察到流量的上升和下降趋势。

*   整图说明

![](https://img-blog.csdnimg.cn/img_convert/8a8c682ab027e313e4d9af9e4bd96206.png)

*   整图说明 2

![](https://img-blog.csdnimg.cn/img_convert/7fe0003d738028e6e20a3bf8f802cd2d.png)

65_GateWay 和 Zuul 课程说明
----------------------

Zuul 开发人员窝里斗，实属明日黄花

重点关注 Gate Way

66_GateWay 是什么
--------------

[上一代 zuul 1.x 官网](https://github.com/Netflix/zuul/wiki)

[Gateway 官网](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/)

**概述**

Cloud 全家桶中有个很重要的组件就是网关，在 1.x 版本中都是采用的 Zuul 网关;

但在 2.x 版本中，zuul 的升级一直跳票，SpringCloud 最后自己研发了一个网关替代 Zuul，那就是 SpringCloud Gateway—句话：gateway 是原 zuul1.x 版的替代

![](https://img-blog.csdnimg.cn/img_convert/54b61d819aa1630bc61732de340b55b4.png)

Gateway 是在 Spring 生态系统之上构建的 API 网关服务，基于 Spring 5，Spring Boot 2 和 Project Reactor 等技术。

Gateway 旨在提供一种简单而有效的方式来对 API 进行路由，以及提供一些强大的过滤器功能，例如: 熔断、限流、重试等。

SpringCloud Gateway 是 Spring Cloud 的一个全新项目，基于 Spring 5.0+Spring Boot 2.0 和 Project Reactor 等技术开发的网关，它旨在为微服务架构提供—种简单有效的统一的 API 路由管理方式。

SpringCloud Gateway 作为 Spring Cloud 生态系统中的网关，目标是替代 Zuul，在 Spring Cloud 2.0 以上版本中，没有对新版本的 Zul 2.0 以上最新高性能版本进行集成，仍然还是使用的 Zuul 1.x 非 Reactor 模式的老版本。而为了提升网关的性能，**SpringCloud Gateway 是基于 WebFlux 框架实现的，而 WebFlux 框架底层则使用了高性能的 Reactor 模式通信框架 Netty**。

Spring Cloud Gateway 的目标提供统一的路由方式且基于 Filter 链的方式提供了网关基本的功能，例如: 安全，监控 / 指标，和限流。

**作用**

*   方向代理
*   鉴权
*   流量控制
*   熔断
*   日志监控
*   …

**微服务架构中网关的位置**

![](https://img-blog.csdnimg.cn/img_convert/5877d4b9035ead9cd2d037609dceb442.png)

67_GateWay 非阻塞异步模型
------------------

有 Zuull 了怎么又出来 Gateway？**我们为什么选择 Gateway?**

1.  netflix 不太靠谱，zuul2.0 一直跳票，迟迟不发布。
    
    1.  一方面因为 Zuul1.0 已经进入了维护阶段，而且 Gateway 是 SpringCloud 团队研发的，是亲儿子产品，值得信赖。而且很多功能 Zuul 都没有用起来也非常的简单便捷。
    2.  Gateway 是基于异步非阻塞模型上进行开发的，性能方面不需要担心。虽然 Netflix 早就发布了最新的 Zuul 2.x，但 Spring Cloud 貌似没有整合计划。而且 Netflix 相关组件都宣布进入维护期；不知前景如何?
    3.  多方面综合考虑 Gateway 是很理想的网关选择。
2.  SpringCloud Gateway 具有如下特性
    
    1.  基于 Spring Framework 5，Project Reactor 和 Spring Boot 2.0 进行构建；
    2.  动态路由：能够匹配任何请求属性；
    3.  可以对路由指定 Predicate (断言) 和 Filter(过滤器)；
    4.  集成 Hystrix 的断路器功能；
    5.  集成 Spring Cloud 服务发现功能；
    6.  易于编写的 Predicate (断言) 和 Filter (过滤器)；
    7.  请求限流功能；
    8.  支持路径重写。
3.  SpringCloud Gateway 与 Zuul 的区别
    
    1.  在 SpringCloud Finchley 正式版之前，Spring Cloud 推荐的网关是 Netflix 提供的 Zuul。
    2.  Zuul 1.x，是一个基于**阻塞** I/O 的 API Gateway。
    3.  Zuul 1.x 基于 **Servlet 2.5 使用阻塞架构**它不支持任何长连接 (如 WebSocket)Zuul 的设计模式和 Nginx 较像，每次 I/О 操作都是从工作线程中选择一个执行，请求线程被阻塞到工作线程完成，但是差别是 Nginx 用 C++ 实现，Zuul 用 Java 实现，而 JVM 本身会有第一 次加载较慢的情况，使得 Zuul 的性能相对较差。
    4.  Zuul 2.x 理念更先进，想基于 **Netty 非阻塞和支持长连接**，但 SpringCloud 目前还没有整合。Zuul .x 的性能较 Zuul 1.x 有较大提升。在性能方面，根据官方提供的基准测试, Spring Cloud Gateway 的 RPS(每秒请求数) 是 Zuul 的 1.6 倍。
    5.  Spring Cloud Gateway 建立在 Spring Framework 5、Project Reactor 和 Spring Boot2 之上，使用非阻塞 API。
    6.  Spring Cloud Gateway 还支持 **WebSocket**，并且与 Spring 紧密集成拥有更好的开发体验

**Zuul1.x 模型**

Springcloud 中所集成的 Zuul 版本，采用的是 Tomcat 容器，使用的是传统的 Serviet IO 处理模型。

Servlet 的生命周期？servlet 由 servlet container 进行生命周期管理。

*   container 启动时构造 servlet 对象并调用 servlet init() 进行初始化；
*   container 运行时接受请求，并为每个请求分配一个线程（一般从线程池中获取空闲线程）然后调用 service)；
*   container 关闭时调用 servlet destory() 销毁 servlet。

![](https://img-blog.csdnimg.cn/img_convert/b71ecbfb29c939615c988123a0704306.png)

上述模式的**缺点**：

Servlet 是一个简单的网络 IO 模型，当请求进入 Servlet container 时，Servlet container 就会为其绑定一个线程，在**并发不高的场景下**这种模型是适用的。但是一旦高并发 (如抽风用 Jmeter 压)，线程数量就会上涨，而线程资源代价是昂贵的（上线文切换，内存消耗大）严重影响请求的处理时间。在一些简单业务场景下，不希望为每个 request 分配一个线程，只需要 1 个或几个线程就能应对极大并发的请求，这种业务场景下 servlet 模型没有优势。

所以 **Zuul 1.X 是基于 servlet 之上的一个阻塞式处理模型**，即 Spring 实现了处理所有 request 请求的一个 servlet (DispatcherServlet) 并由该 servlet 阻塞式处理处理。所以 SpringCloud Zuul 无法摆脱 servlet 模型的弊端。

**Gateway 模型**

WebFlux 是什么？[官方文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#spring-webflux)

传统的 Web 框架，比如说: Struts2，SpringMVC 等都是基于 Servlet APl 与 Servlet 容器基础之上运行的。

但是在 Servlet3.1 之后有了**异步非阻塞**的支持。而 **WebFlux 是一个典型非阻塞异步的框架**，它的核心是基于 Reactor 的相关 API 实现的。相对于传统的 web 框架来说，它可以运行在诸如 Netty，Undertow 及支持 Servlet3.1 的容器上。**非阻塞式 + 函数式编程** (Spring 5 必须让你使用 Java 8)。

Spring WebFlux 是 Spring 5.0 引入的新的响应式框架，区别于 Spring MVC，它不需要依赖 Servlet APl，它是完全异步非阻塞的，并且基于 Reactor 来实现响应式流规范。

> **Spring Cloud Gateway** requires the Netty runtime provided by Spring Boot and **Spring Webflux**. It does not work in a traditional Servlet Container or when built as a WAR.[link](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gateway-starter)

68_Gateway 工作流程
---------------

**三大核心概念**

1.  Route(路由) - 路由是构建网关的基本模块, 它由 ID, 目标 URI, 一系列的断言和过滤器组成, 如断言为 true 则匹配该路由；
2.  Predicate(断言) - 参考的是 Java8 的 java.util.function.Predicate，开发人员可以匹配 HTTP 请求中的所有内容 (例如请求头或请求参数), 如果请求与断言相匹配则进行路由；
3.  Filter(过滤) - 指的是 Spring 框架中 GatewayFilter 的实例, 使用过滤器, 可以在请求被路由前或者之后对请求进行修改。

![](https://img-blog.csdnimg.cn/img_convert/70da1eecc951a338588356ee2db3fa1f.png)

web 请求，通过一些匹配条件，定位到真正的服务节点。并在这个转发过程的前后，进行一些精细化控制。

predicate 就是我们的匹配条件；而 fliter，就可以理解为一个无所不能的拦截器。有了这两个元素，再加上目标 uri，就可以实现一个具体的路由了

**Gateway 工作流程**

[官网总结](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gateway-how-it-works)

> ![](https://img-blog.csdnimg.cn/img_convert/62be54501c6e2b95620b79cc918a2e9a.png)
> 
> Clients make requests to Spring Cloud Gateway. If the Gateway Handler Mapping determines that a request matches a route, it is sent to the Gateway Web Handler. This handler runs the request through a filter chain that is specific to the request. The reason the filters are divided by the dotted line is that filters can run logic both before and after the proxy request is sent. All “pre” filter logic is executed. Then the proxy request is made. After the proxy request is made, the “post” filter logic is run. [link](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gateway-how-it-works)

客户端向 Spring Cloud Gateway 发出请求。然后在 Gateway Handler Mapping 中找到与请求相匹配的路由，将其发送到 GatewayWeb Handler。

Handler 再通过指定的过滤器链来将请求发送到我们实际的服务执行业务逻辑，然后返回。

过滤器之间用虚线分开是因为过滤器可能会在发送代理请求之前 (“pre”) 或之后 (“post"）执行业务逻辑。

Filter 在 “pre” 类型的过滤器可以做参数校验、权限校验、流量监控、日志输出、协议转换等

在 “post” 类型的过滤器中可以做响应内容、响应头的修改，日志的输出，流量监控等有着非常重要的作用。

**核心逻辑**：**路由转发 + 执行过滤器链。**

69_Gateway9527 搭建
-----------------

1. 新建 Module - cloud-gateway-gateway9527

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

    <artifactId>cloud-gateway-gateway9527</artifactId>

    <dependencies>
        <!--gateway-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>
        <!--eureka-client-->
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
        <!--一般基础配置类-->
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
  port: 9527

spring:
  application:
    name: cloud-gateway

eureka:
  instance:
    hostname: cloud-gateway-service
  client: #服务提供者provider注册进eureka服务列表内
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://eureka7001.com:7001/eureka
```

4. 业务类

无

5. 主启动类

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class GateWayMain9527
{
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain9527.class, args);
    }
}
```

6.9527 网关如何做路由映射?

cloud-provider-payment8001 看看 controller 的访问地址

*   get
*   lb

我们目前不想暴露 8001 端口，希望在 8001 外面套一层 9527

7.YML 新增网关配置

```
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
#############################新增网关配置###########################
  cloud:
    gateway:
      routes:
        - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: http://localhost:8001          #匹配后提供服务的路由地址
          #uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**         # 断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          uri: http://localhost:8001          #匹配后提供服务的路由地址
          #uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/lb/**         # 断言，路径相匹配的进行路由
####################################################################

eureka:
  instance:
    hostname: cloud-gateway-service
  client: #服务提供者provider注册进eureka服务列表内
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://eureka7001.com:7001/eureka
```

8. 测试

*   启动 7001
    
*   启动 8001-cloud-provider-payment8001
    
*   启动 9527 网关
    
*   访问说明
    
    *   添加网关前 - http://localhost:8001/payment/get/1
    *   添加网关后 - http://localhost:9527/payment/get/1
    *   两者访问成功，返回相同结果

70_Gateway 配置路由的两种方式
--------------------

**在配置文件 yml 中配置，见上一章节**

**代码中注入 RouteLocator 的 Bean**

官方案例 - [link](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#modifying-the-way-remote-addresses-are-resolved)

```
RemoteAddressResolver resolver = XForwardedRemoteAddressResolver
    .maxTrustedIndex(1);

...

.route("direct-route",
    r -> r.remoteAddr("10.1.1.1", "10.10.1.1/24")
        .uri("https://downstream1")
.route("proxied-route",
    r -> r.remoteAddr(resolver, "10.10.1.1", "10.10.1.1/24")
        .uri("https://downstream2")
)
```

百度国内新闻网址，需要外网 - http://news.baidu.com/guonei

**自己写一个**

业务需求 - 通过 9527 网关访问到外网的百度新闻网址

**编码**

cloud-gateway-gateway9527 业务实现

```
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GateWayConfig
{
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder)
    {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_atguigu",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();

        return routes.build();
    }
}
```

**测试**

浏览器输入 http://localhost:9527/guonei，返回 http://news.baidu.com/guonei 相同的页面。

71_GateWay 配置动态路由
-----------------

默认情况下 Gateway 会根据注册中心注册的服务列表，以注册中心上微服务名为路径创建**动态路由进行转发，从而实现动态路由的功能**（不写死一个地址）。

**启动**

*   eureka7001
*   payment8001/8002

**POM**

```
<!--eureka-client-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

**YML**

需要注意的是 uri 的协议为 lb，表示启用 Gateway 的负载均衡功能。

lb://serviceName 是 spring cloud gateway 在微服务中自动为我们创建的负载均衡 uri。

```
server:
  port: 9527

spring:
  application:
    name: cloud-gateway
#############################新增网关配置###########################
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由(可以不用使用routes 调用时使用服务名 + 路径进行转发)
      routes:
        - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001          #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**         # 断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001          #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/lb/**         # 断言，路径相匹配的进行路由
####################################################################

eureka:
  instance:
    hostname: cloud-gateway-service
  client: #服务提供者provider注册进eureka服务列表内
    service-url:
      register-with-eureka: true
      fetch-registry: true
      defaultZone: http://eureka7001.com:7001/eureka
```

**测试**

浏览器输入 - http://localhost:9527/payment/lb

结果

不停刷新页面，8001/8002 两个端口切换。

**注意**

如果不配置routes,可以使用服务名  加上方法路径进行服务调用

```http
如下:
http://localhost:9527/EUREKA-CLOUD-PAYMENT-SERVICE/payment/lb
```



72_GateWay 常用的 Predicate
------------------------

[官方文档](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gateway-request-predicates-factories)

**Route Predicate Factories 这个是什么**

> Spring Cloud Gateway matches routes as part of the Spring WebFlux `HandlerMapping` infrastructure. Spring Cloud Gateway includes many built-in **route predicate factories**. All of these predicates match on different attributes of the HTTP request. You can combine multiple route predicate factories with logical `and` statements. [link](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gateway-request-predicates-factories)

Spring Cloud Gateway 将路由匹配作为 Spring WebFlux HandlerMapping 基础架构的一部分。

Spring Cloud Gateway 包括许多内置的 Route Predicate 工厂。所有这些 Predicate 都与 HTTP 请求的不同属性匹配。多个 RoutePredicate 工厂可以进行组合。

Spring Cloud Gateway 创建 Route 对象时，使用 RoutePredicateFactory 创建 Predicate 对象，Predicate 对象可以赋值给 Route。Spring Cloud Gateway 包含许多内置的 Route Predicate Factories。  
所有这些谓词都匹配 HTTP 请求的不同属性。多种谓词工厂可以组合，并通过逻辑 and。

> **predicate**
> 
> 美: ['predɪkeɪt] 英: ['predɪkət]
> 
> **v.** 断言；使基于；使以… 为依据；表明
> 
> **adj.** 述语的；谓项的
> 
> **n.** 谓语（句子成分，对主语加以陈述，如 John went home 中的 went home）

**常用的 Route Predicate Factory**

1.  The After Route Predicate Factory
2.  The Before Route Predicate Factory
3.  The Between Route Predicate Factory
4.  The Cookie Route Predicate Factory
5.  The Header Route Predicate Factory
6.  The Host Route Predicate Factory
7.  The Method Route Predicate Factory
8.  The Path Route Predicate Factory
9.  The Query Route Predicate Factory
10.  The RemoteAddr Route Predicate Factory
11.  The weight Route Predicate Factory

**讨论几个 Route Predicate Factory**

**The After Route Predicate Factory**

```
spring:
  cloud:
    gateway:
      routes:
      - id: after_route
        uri: https://example.org
        predicates:
        # 这个时间后才能起效
        - After=2017-01-20T17:42:47.789-07:00[America/Denver]
```

可以通过下述方法获得上述格式的时间戳字符串

```
import java.time.ZonedDateTime;


public class T2
{
    public static void main(String[] args)
    {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);

       //2021-02-22T15:51:37.485+08:00[Asia/Shanghai]
    }
}
```

**The Between Route Predicate Factory**

```
spring:
  cloud:
    gateway:
      routes:
      - id: between_route
        uri: https://example.org
        # 两个时间点之间
        predicates:
        - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
```

**The Cookie Route Predicate Factory**

```
spring:
  cloud:
    gateway:
      routes:
      - id: cookie_route
        uri: https://example.org
        predicates:
        - Cookie=chocolate, ch.p
```

The cookie route predicate factory takes **two parameters**, the cookie name and a regular expression.

This predicate matches cookies that have the given name and whose values match the regular expression.

测试

```
# 该命令相当于发get请求，且没带cookie
curl http://localhost:9527/payment/lb

# 带cookie的
curl http://localhost:9527/payment/lb --cookie "chocolate=chip"
```

**The Header Route Predicate Factory**

```
spring:
  cloud:
    gateway:
      routes:
      - id: header_route
        uri: https://example.org
        predicates:
        - Header=X-Request-Id, \d+
```

The header route predicate factory takes **two parameters**, the header name and a regular expression.

This predicate matches with a header that has the given name whose value matches the regular expression.

测试

```
# 带指定请求头的参数的CURL命令
curl http://localhost:9527/payment/lb -H "X-Request-Id:123"
```

其它的，举一反三。

**小结**

说白了，Predicate 就是为了实现一组匹配规则，让请求过来找到对应的 Route 进行处理。

73_GateWay 的 Filter
-------------------

[官方文档](https://cloud.spring.io/spring-cloud-static/spring-cloud-gateway/2.2.1.RELEASE/reference/html/#gatewayfilter-factories)

> Route filters allow the modification of the incoming HTTP request or outgoing HTTP response in some manner. Route filters are scoped to a particular route. Spring Cloud Gateway includes many built-in GatewayFilter Factories.

路由过滤器可用于修改进入的 HTTP 请求和返回的 HTTP 响应，路由过滤器只能指定路由进行使用。Spring Cloud Gateway 内置了多种路由过滤器，他们都由 GatewayFilter 的工厂类来产生。

Spring Cloud Gateway 的 Filter:

*   生命周期：
    
    *   pre
    *   post
*   种类（具体看官方文档）：
    
    *   GatewayFilter - 有 31 种
    *   GlobalFilter - 有 10 种

常用的 GatewayFilter：AddRequestParameter GatewayFilter

自定义全局 GlobalFilter：

两个主要接口介绍：

1.  GlobalFilter
2.  Ordered

能干什么：

1.  全局日志记录
2.  统一网关鉴权
3.  …

代码案例：

GateWay9527 项目添加 MyLogGateWayFilter 类：

```
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered
{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        log.info("***********come in MyLogGateWayFilter:  "+new Date());

        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        if(uname == null)
        {
            log.info("*******用户名为null，非法用户，o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder()
    {
        return 0;
    }
}
```

测试：

启动：

*   EurekaMain7001
*   PaymentMain8001
*   GateWayMain9527
*   PaymentMain8002

浏览器输入：

*   http://localhost:9527/payment/lb - 访问异常
*   http://localhost:9527/payment/lb?uname=abc - 正常访问

74_Config 分布式配置中心介绍
-------------------

**分布式系统面临的配置问题**

微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务。由于每个服务都需要必要的配置信息才能运行，所以一套集中式的、动态的配置管理设施是必不可少的。

SpringCloud 提供了 ConfigServer 来解决这个问题，我们每一个微服务自己带着一个 application.yml，上百个配置文件的管理.……

**是什么**

![](https://img-blog.csdnimg.cn/img_convert/d5462e3b8c3a063561f5f8fc7fde327e.png)

SpringCloud Config 为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。

**怎么玩**

SpringCloud Config 分为**服务端**和**客户端**两部分。

*   服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密 / 解密信息等访问接口。
    
*   客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息配置服务器默认采用 git 来存储配置信息，这样就有助于对环境配置进行版本管理，并且可以通过 git 客户端工具来方便的管理和访问配置内容。
    

**能干嘛**

*   集中管理配置文件
*   不同环境不同配置，动态化的配置更新，分环境部署比如 dev/test/prod/beta/release
*   运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
*   当配置发生变动时，服务不需要重启即可感知到配置的变化并应用新的配置
*   将配置信息以 REST 接口的形式暴露 - post/crul 访问刷新即可…

**与 GitHub 整合配置**

由于 SpringCloud Config 默认使用 Git 来存储配置文件 (也有其它方式, 比如支持 SVN 和本地文件)，但最推荐的还是 Git，而且使用的是 http/https 访问的形式。

**官网**

[https://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.2.1.RELEASE/reference/html/](https://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.2.1.RELEASE/reference/html/)

75_Config 配置总控中心搭建
------------------

用你自己的账号在 GitHub 上新建一个名为 springcloud-config 的新 Repository。

由上一步获得刚新建的 git 地址 -`git@github.com:abc/springcloud-config.git`。

本地硬盘目录上新建 git 仓库并 clone。

*   工作目录为 D:\SpringCloud2021
    
*   `git clone git@github.com:abc/springcloud-config.git`
    

此时在工作目录会创建名为 springcloud-config 的文件夹。

在 springcloud-config 的文件夹种创建三个配置文件（为本次教学使用的）, 随后`git add .`，`git commit -m "sth"` 等一系列上传操作上传到 springcloud-config 的新 Repository。

*   config-dev.yml

```
config:
  info: "master branch,springcloud-config/config-dev.yml version=7"
```

*   config-prod.yml

```
config:
  info: "master branch,springcloud-config/config-prod.yml version=1"
```

*   config-test.yml

```
config:
  info: "master branch,springcloud-config/config-test.yml version=1"
```

新建 Module 模块 cloud-config-center-3344，它即为 Cloud 的配置中心模块 CloudConfig Center

POM

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

    <artifactId>cloud-config-center-3344</artifactId>

    <dependencies>
        <!--添加消息总线RabbitMQ支持-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
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

YML

```
server:
  port: 3344

spring:
  application:
    name:  cloud-config-center #注册进Eureka服务器的微服务名
  cloud:
    config:
      server:
        git:
          uri: git@github.com:zzyybs/springcloud-config.git #GitHub上面的git仓库名字
        ####搜索目录
          search-paths:
            - springcloud-config
      ####读取分支
      label: master

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
```

主启动类

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344
{
    public static void main(String[] args) {
            SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}
```

windows 下修改 hosts 文件，增加映射

```
127.0.0.1 config-3344.com
```

测试通过 Config 微服务是否可以从 GitHub 上获取配置内容

*   启动 ConfigCenterMain3344
    
*   浏览器防问 - http://config-3344.com:3344/master/config-dev.yml
    
*   页面返回结果：
    

```
config:
  info: "master branch,springcloud-config/config-dev.yml version=7"
```

配置读取规则

*   [官方文档](https://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.2.1.RELEASE/reference/html/#_quick_start)
    
*   /{label}/{application}-{profile}.yml（推荐）
    
    *   master 分支
        *   http://config-3344.com:3344/master/config-dev.yml
        *   http://config-3344.com:3344/master/config-test.yml
        *   http://config-3344.com:3344/master/config-prod.yml
    *   dev 分支
        *   http://config-3344.com:3344/dev/config-dev.yml
        *   http://config-3344.com:3344/dev/config-test.yml
        *   http://config-3344.com:3344/dev/config-prod.yml
*   /{application}-{profile}.yml
    
    *   http://config-3344.com:3344/config-dev.yml
    *   http://config-3344.com:3344/config-test.yml
    *   http://config-3344.com:3344/config-prod.yml
    *   http://config-3344.com:3344/config-xxxx.yml(不存在的配置)
*   /{application}/{profile}[/{label}]
    
    *   http://config-3344.com:3344/config/dev/master
    *   http://config-3344.com:3344/config/test/master
    *   http://config-3344.com:3344/config/test/dev
*   重要配置细节总结
    
    *   /{name}-{profiles}.yml
    *   /{label}-{name}-{profiles}.yml
    *   label：分支 (branch)
    *   name：服务名
    *   profiles：环境 (dev/test/prod)

成功实现了用 SpringCloud Config 通过 GitHub 获取配置信息

76_Config 客户端配置与测试
------------------

**新建 cloud-config-client-3355**

**POM**

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

    <artifactId>cloud-config-client-3355</artifactId>

    <dependencies>
        <!--添加消息总线RabbitMQ支持-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
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

**bootstrap.yml**

applicaiton.yml 是用户级的资源配置项

bootstrap.yml 是系统级的，优先级更加高

Spring Cloud 会创建一个 Bootstrap Context，作为 Spring 应用的 Application Context 的父上下文。

初始化的时候，BootstrapContext 负责从外部源加载配置属性并解析配置。这两个上下文共享一个从外部获取的 Environment。

Bootstrap 属性有高优先级，默认情况下，它们不会被本地配置覆盖。Bootstrap context 和 Application Context 有着不同的约定，所以新增了一个 bootstrap.yml 文件，保证 Bootstrap Context 和 Application Context 配置的分离。

要将 Client 模块下的 application.yml 文件改为 bootstrap.yml, 这是很关键的，因为 bootstrap.yml 是比 application.yml 先加载的。bootstrap.yml 优先级高于 application.yml。

```
server:
  port: 3355

spring:
  application:
    name: config-client
  cloud:
    #Config客户端配置
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称   上述3个综合：master分支上config-dev.yml的配置文件被读取http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心地址k


#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
```

**修改 config-dev.yml 配置并提交到 GitHub 中，比如加个变量 age 或者版本号 version**

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class ConfigClientMain3355
{
    public static void main(String[] args) {
            SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
```

业务类

```
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class ConfigClientController
{
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo;
    }
}
```

**测试**

*   启动 Config 配置中心 3344 微服务并自测
    
    *   http://config-3344.com:3344/master/config-prod.yml
    *   http://config-3344.com:3344/master/config-dev.yml
*   启动 3355 作为 Client 准备访问
    
    *   http://localhost:3355/configlnfo

**成功实现了客户端 3355 访问 SpringCloud Config3344 通过 GitHub 获取配置信息可题随时而来**

**分布式配置的动态刷新问题**

*   Linux 运维修改 GitHub 上的配置文件内容做调整
*   刷新 3344，发现 ConfigServer 配置中心立刻响应
*   刷新 3355，发现 ConfigClient 客户端没有任何响应
*   3355 没有变化除非自己重启或者重新加载
*   难到每次运维修改配置文件，客户端都需要重启?? 噩梦

77_Config 动态刷新之手动版
------------------

避免每次更新配置都要重启客户端微服务 3355

**动态刷新步骤**：

修改 3355 模块

POM 引入 actuator 监控

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

修改 YML，添加暴露监控端口配置：

```
# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

@RefreshScope 业务类 Controller 修改

```
import org.springframework.cloud.context.config.annotation.RefreshScope;
...

@RestController
@RefreshScope//<-----
public class ConfigClientController
{
...
}
```

测试

此时修改 github 配置文件内容 -> 访问 3344 -> 访问 3355

http://localhost:3355/configInfo

3355 改变没有??? **没有**，还需一步

How

需要运维人员发送 Post 请求刷新 3355

```
curl -X POST "http://localhost:3355/actuator/refresh"
```

再次测试

http://localhost:3355/configInfo

3355 改变没有??? **改了**。

成功实现了客户端 3355 刷新到最新配置内容，避免了服务重启

想想还有什么问题?

*   假如有多个微服务客户端 3355/3366/3377
*   每个微服务都要执行—次 post 请求，手动刷新?
*   可否广播，一次通知，处处生效?
*   我们想大范围的自动刷新，求方法

78_Bus 消息总线是什么
--------------

**上—讲解的加深和扩充**

一言以蔽之，分布式自动刷新配置功能。

Spring Cloud Bus 配合 Spring Cloud Config 使用可以实现配置的动态刷新。

**是什么**

Spring Cloud Bus 配合 Spring Cloud Config 使用可以实现配置的动态刷新。

![](https://img-blog.csdnimg.cn/img_convert/458fd679c01274ca84f785e1f75c1336.png)

Spring Cloud Bus 是用来将分布式系统的节点与轻量级消息系统链接起来的框架，它整合了 Java 的事件处理机制和消息中间件的功能。Spring Clud Bus 目前支持 RabbitMQ 和 Kafka。

刷新单个服务，全体广播

**能干嘛**

Spring Cloud Bus 能管理和传播分布式系统间的消息，就像一个分布式执行器，可用于广播状态更改、事件推送等，也可以当作微服务间的通信通道。

刷新config服务端  

![](https://img-blog.csdnimg.cn/img_convert/26c6ced30935219d4717814a446eb67a.png)

**为何被称为总线**

什么是总线

在微服务架构的系统中，通常会使用轻量级的消息代理来构建一个共用的消息主题，并让系统中所有微服务实例都连接上来。由于该主题中产生的消息会被所有实例监听和消费，所以称它为**消息总线**。在总线上的各个实例，都可以方便地广播一些需要让其他连接在该主题上的实例都知道的消息。

基本原理

ConfigClient 实例都监听 MQ 中同一个 topic(默认是 Spring Cloud Bus)。当一个服务刷新数据的时候，它会把这个信息放入到 Topic 中，这样其它监听同一 Topic 的服务就能得到通知，然后去更新自身的配置。

79_Bus 之 RabbitMQ 环境配置
----------------------

*   安装 Erlang，下载地址：http://erlang.org/download/otp_win64_21.3.exe
    
*   安装 RabbitMQ，下载地址：https://github.com/rabbitmq/rabbitmq-server/releases/download/v3.8.3/rabbitmq-server-3.8.3.exe
    
*   打开 cmd 进入 RabbitMQ 安装目录下的 sbin 目录，如：D:\devSoft\RabbitMQ Scrverk\rabbitmq_server-3.7.14\sbin
    
*   输入以下命令启动管理功能
    

```
rabbitmq-plugins enable rabbitmq _management
```

这样就可以添加可视化插件。

*   访问地址查看是否安装成功：http://localhost:15672/
    
*   输入账号密码并登录：guest guest
    

80_Bus 动态刷新全局广播的设计思想和选型
-----------------------

必须先具备良好的 RabbitMQ 环境先

演示广播效果，增加复杂度，再以 3355 为模板再制作一个 3366

1. 新建 cloud-config-client-3366

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

    <artifactId>cloud-config-client-3366</artifactId>

    <dependencies>
        <!--添加消息总线RabbitMQ支持-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
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

3.YML

```
server:
  port: 3366

spring:
  application:
    name: config-client
  cloud:
    #Config客户端配置
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称   上述3个综合：master分支上config-dev.yml的配置文件被读取http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心地址

#rabbitmq相关配置 15672是Web管理界面的端口；5672是MQ访问的端口
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka

# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

4. 主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfigClientMain3366
{
    public static void main(String[] args)
    {
        SpringApplication.run(ConfigClientMain3366.class,args);
    }
}
```

5.controller

```
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 */
@RestController
@RefreshScope
public class ConfigClientController
{
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String configInfo()
    {
        return "serverPort: "+serverPort+"\t\n\n configInfo: "+configInfo;
    }

}
```

**设计思想**

1. 利用消息总线触发一个客户端 / bus/refresh, 而刷新所有客户端的配置

![](https://img-blog.csdnimg.cn/img_convert/3a0975f4bac7393fe406821531e9daef.png)

2. 利用消息总线触发一个服务端 ConfigServer 的 / bus/refresh 端点，而刷新所有客户端的配置

![](https://img-blog.csdnimg.cn/img_convert/e2809f728b8eb3e776883e4f905b8712.png)

图二的架构显然更加适合，图—不适合的原因如下：

*   打破了微服务的职责单一性，因为微服务本身是业务模块，它本不应该承担配置刷新的职责。
    
*   破坏了微服务各节点的对等性。
    
*   有一定的局限性。例如，微服务在迁移时，它的网络地址常常会发生变化，此时如果想要做到自动刷新，那就会增加更多的修改。
    

81_Bus 动态刷新全局广播配置实现
-------------------

**给 cloud-config-center-3344 配置中心服务端添加消息总线支持**

POM

```
<!--添加消息总线RabbitNQ支持-->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bus-amap</artifactId>
</dependency>
<dependency>
	<groupId>org-springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

YML

```
server:
  port: 3344

spring:
  application:
    name:  cloud-config-center #注册进Eureka服务器的微服务名
  cloud:
    config:
      server:
        git:
          uri: git@github.com:zzyybs/springcloud-config.git #GitHub上面的git仓库名字
        ####搜索目录
          search-paths:
            - springcloud-config
      ####读取分支
      label: master
#rabbitmq相关配置<--------------------------
rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka

##rabbitmq相关配置,暴露bus刷新配置的端点<--------------------------
management:
  endpoints: #暴露bus刷新配置的端点
    web:
      exposure:
        include: 'bus-refresh'
```

**给 cloud-config-client-3355 客户端添加消息总线支持**

POM

```
<!--添加消息总线RabbitNQ支持-->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bus-amap</artifactId>
</dependency>
<dependency>
	<groupId>org-springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

YML

```
server:
  port: 3355

spring:
  application:
    name: config-client
  cloud:
    #Config客户端配置
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称   上述3个综合：master分支上config-dev.yml的配置文件被读取http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心地址k

#rabbitmq相关配置 15672是Web管理界面的端口；5672是MQ访问的端口<----------------------
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka

# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

**给 cloud-config-client-3366 客户端添加消息总线支持**

POM

```
<!--添加消息总线RabbitNQ支持-->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-bus-amap</artifactId>
</dependency>
<dependency>
	<groupId>org-springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

YML

```
server:
  port: 3366

spring:
  application:
    name: config-client
  cloud:
    #Config客户端配置
    config:
      label: master #分支名称
      name: config #配置文件名称
      profile: dev #读取后缀名称   上述3个综合：master分支上config-dev.yml的配置文件被读取http://config-3344.com:3344/master/config-dev.yml
      uri: http://localhost:3344 #配置中心地址

#rabbitmq相关配置 15672是Web管理界面的端口；5672是MQ访问的端口<-----------------------
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

#服务注册到eureka地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka

# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

**测试**

*   启动
    
    *   EurekaMain7001
    *   ConfigcenterMain3344
    *   ConfigclientMain3355
    *   ConfigclicntMain3366
*   运维工程师
    
    *   修改 Github 上配置文件内容，增加版本号
    *   发送 POST 请求
        *   `curl -X POST "http://localhost:3344/actuator/bus-refresh"`
        *   **—次发送，处处生效**
*   配置中心
    
    *   http://config-3344.com:3344/config-dev.yml
*   客户端
    
    *   http://localhost:3355/configlnfo
    *   http://localhost:3366/configInfo
    *   获取配置信息，发现都已经刷新了

**—次修改，广播通知，处处生效**

82_Bus 动态刷新定点通知
---------------

不想全部通知，只想定点通知

*   只通知 3355
*   不通知 3366

简单一句话 - **指定具体某一个实例生效而不是全部**

*   公式：http://localhost:3344/actuator/bus-refresh/{destination}
    
*   /bus/refresh 请求不再发送到具体的服务实例上，而是发给 config server 通过 destination 参数类指定需要更新配置的服务或实例
    

案例

*   我们这里以刷新运行在 3355 端口上的 config-client（配置文件中设定的应用名称）为例，只通知 3355，不通知 3366
*   `curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355`

通知总结

![](https://img-blog.csdnimg.cn/img_convert/ccd5fcc8293edec24d7e889e189d0bfe.png)

83_Stream 为什么被引入
----------------

常见 MQ(消息中间件)：

*   ActiveMQ
*   RabbitMQ
*   RocketMQ
*   Kafka

有没有一种新的技术诞生，让我们不再关注具体 MQ 的细节，我们只需要用一种适配绑定的方式，自动的给我们在各种 MQ 内切换。（类似于 Hibernate）

Cloud Stream 是什么？屏蔽底层消息中间件的差异，降低切换成本，统一消息的**编程模型**。

84_Stream 是什么及 Binder 介绍
------------------------

[官方文档 1](https://spring.io/projects/spring-cloud-stream#overview)

[官方文档 2](https://cloud.spring.io/spring-tloud-static/spring-cloud-stream/3.0.1.RELEASE/reference/html/Spring)

[Cloud Stream 中文指导手册](https://m.wang1314.com/doc/webapp/topic/20971999.html)

**什么是 Spring Cloud Stream？**

官方定义 Spring Cloud Stream 是一个构建消息驱动微服务的框架。

应用程序通过 inputs 或者 outputs 来与 Spring Cloud Stream 中 binder 对象交互。

通过我们配置来 binding(绑定)，而 Spring Cloud Stream 的 binder 对象负责与消息中间件交互。所以，我们只需要搞清楚如何与 Spring Cloud Stream 交互就可以方便使用消息驱动的方式。

通过使用 Spring Integration 来连接消息代理中间件以实现消息事件驱动。  
Spring Cloud Stream 为一些供应商的消息中间件产品提供了个性化的自动化配置实现，引用了发布 - 订阅、消费组、分区的三个核心概念。

目前仅支持 RabbitMQ、 Kafka。

85_Stream 的设计思想
---------------

**标准 MQ**

![](https://img-blog.csdnimg.cn/img_convert/dd57e502418ecdae99f29991abe8bb02.png)

*   生产者 / 消费者之间靠**消息**媒介传递信息内容
*   消息必须走特定的通道 - 消息通道 Message Channel
*   消息通道里的消息如何被消费呢，谁负责收发处理 - 消息通道 MessageChannel 的子接口 SubscribableChannel，由 MessageHandler 消息处理器所订阅。

**为什么用 Cloud Stream？**

比方说我们用到了 RabbitMQ 和 Kafka，由于这两个消息中间件的架构上的不同，像 RabbitMQ 有 exchange，kafka 有 Topic 和 Partitions 分区。

![](https://img-blog.csdnimg.cn/img_convert/5587b05def1c26b8c9d9874c78f80b28.png)

这些中间件的差异性导致我们实际项目开发给我们造成了一定的困扰，我们如果用了两个消息队列的其中一种，后面的业务需求，我想往另外一种消息队列进行迁移，这时候无疑就是一个灾难性的，一大堆东西都要重新推倒重新做，因为它跟我们的系统耦合了，这时候 Spring Cloud Stream 给我们提供了—种解耦合的方式。

**Stream 凭什么可以统一底层差异？**

在没有绑定器这个概念的情况下，我们的 SpringBoot 应用要直接与消息中间件进行信息交互的时候，由于各消息中间件构建的初衷不同，它们的实现细节上会有较大的差异性通过定义绑定器作为中间层，完美地实现了应用程序与消息中间件细节之间的隔离。通过向应用程序暴露统一的 Channel 通道，使得应用程序不需要再考虑各种不同的消息中间件实现。

**通过定义绑定器 Binder 作为中间层，实现了应用程序与消息中间件细节之间的隔离**。

**从 Stream 发布消息就是输出，接受消息就是输入。**

**Binder**：

*   INPUT 对应于消费者
    
*   OUTPUT 对应于生产者
    

![](https://img-blog.csdnimg.cn/img_convert/96256569e677453570b55209c26e0b8c.png)

**Stream 中的消息通信方式遵循了发布 - 订阅模式**

Topic 主题进行广播

*   在 RabbitMQ 就是 Exchange
*   在 Kakfa 中就是 Topic

86_Stream 编码常用注解简介
------------------

**Spring Cloud Stream 标准流程套路**

![](https://img-blog.csdnimg.cn/img_convert/077a3b34aec6eed91a7019a9d5ca4e3c.png)

![](https://img-blog.csdnimg.cn/img_convert/1ca02dd31581d92a7a610bcd137f6848.png)

*   Binder - 很方便的连接中间件，屏蔽差异。
    
*   Channel - 通道，是队列 Queue 的一种抽象，在消息通讯系统中就是实现存储和转发的媒介，通过 Channel 对队列进行配置。
    
*   Source 和 Sink - 简单的可理解为参照对象是 Spring Cloud Stream 自身，**从 Stream 发布消息就是输出，接受消息就是输入。**
    

**编码 API 和常用注解**

<table><thead><tr><th>组成</th><th>说明</th></tr></thead><tbody><tr><td>Middleware</td><td>中间件<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>目前只支持 RabbitMQ 和 Kafka</td></tr><tr><td>Binder</td><td>Binder 是应用与消息中间件之间的封装<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>目前实行了 Kafka 和 RabbitMQ 的 Binder<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>通过 Binder 可以很方便的连接中间件<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>可以动态的改变消息类型 (对应于 Kafka 的 topic,RabbitMQ 的 exchange)<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>这些都可以通过配置文件来实现</td></tr><tr><td>@Input</td><td>注解标识输入通道<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>通过该输入通道接收到的消息进入应用程序</td></tr><tr><td>@Output</td><td>注解标识输出通道<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>发布的消息将通过该通道离开应用程序</td></tr><tr><td>@StreamListener</td><td>监听队列<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>用于消费者的队列的消息接收</td></tr><tr><td>@EnableBinding</td><td>指信道 channel 和 exchange 绑定在一起</td></tr></tbody></table>

**案例说明**

准备 RabbitMQ 环境（[79_Bus 之 RabbitMQ 环境配置](#)有提及）

工程中新建三个子模块

*   cloud-stream-rabbitmq-provider8801，作为生产者进行发消息模块
*   cloud-stream-rabbitmq-consumer8802，作为消息接收模块
*   cloud-stream-rabbitmq-consumer8803，作为消息接收模块

87_Stream 消息驱动之生产者
------------------

新建 Module：cloud-stream-rabbitmq-provider8801

POM

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

    <artifactId>cloud-stream-rabbitmq-provider8801</artifactId>

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
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
        </dependency>
        <!--基础配置-->
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
  port: 8801

spring:
  application:
    name: cloud-stream-provider
  cloud:
      stream:
        binders: # 在此处配置要绑定的rabbitmq的服务信息；
          defaultRabbit: # 表示定义的名称，用于于binding整合
            type: rabbit # 消息组件类型
            environment: # 设置rabbitmq的相关的环境配置
              spring:
                rabbitmq:
                  host: localhost
                  port: 5672
                  username: guest
                  password: guest
        bindings: # 服务的整合处理
          output: # 这个名字是一个通道的名称
            destination: studyExchange # 表示要使用的Exchange名称定义
            content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
            binder: defaultRabbit # 设置要绑定的消息服务的具体设置

eureka:
  client: # 客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://localhost:7001/eureka
  instance:
    lease-renewal-interval-in-seconds: 2 # 设置心跳的时间间隔（默认是30秒）
    lease-expiration-duration-in-seconds: 5 # 如果现在超过了5秒的间隔（默认是90秒）
    instance-id: send-8801.com  # 在信息列表时显示主机名称
    prefer-ip-address: true     # 访问的路径变为IP地址
```

主启动类 StreamMQMain8801

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class,args);
    }
}
```

业务类

1. 发送消息接口

```
public interface IMessageProvider {
    public String send();
}
```

2. 发送消息接口实现类

```
import com.lun.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider
{
    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send()
    {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "+serial);
        return null;
    }
}
```

3.Controller

```
import com.lun.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController
{
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }

}
```

测试

*   启动 7001eureka
*   启动 RabpitMq（[79_Bus 之 RabbitMQ 环境配置](#)）
    *   rabbitmq-plugins enable rabbitmq_management
    *   http://localhost:15672/
*   启动 8801
*   访问 - http://localhost:8801/sendMessage
    *   后台将打印 `serial: UUID` 字符串

88_Stream 消息驱动之消费者
------------------

新建 Module：cloud-stream-rabbitmq-consumer8802

POM

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

    <artifactId>cloud-stream-rabbitmq-consumer8802</artifactId>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--基础配置-->
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
  port: 8802

spring:
  application:
    name: cloud-stream-consumer
  cloud:
      stream:
        binders: # 在此处配置要绑定的rabbitmq的服务信息；
          defaultRabbit: # 表示定义的名称，用于于binding整合
            type: rabbit # 消息组件类型
            environment: # 设置rabbitmq的相关的环境配置
              spring:
                rabbitmq:
                  host: localhost
                  port: 5672
                  username: guest
                  password: guest
        bindings: # 服务的整合处理
          input: # 这个名字是一个通道的名称
            destination: studyExchange # 表示要使用的Exchange名称定义
            content-type: application/json # 设置消息类型，本次为对象json，如果是文本则设置“text/plain”
            binder: defaultRabbit # 设置要绑定的消息服务的具体设置

eureka:
  client: # 客户端进行Eureka注册的配置
    service-url:
      defaultZone: http://localhost:7001/eureka
  instance:
    lease-renewal-interval-in-seconds: 2 # 设置心跳的时间间隔（默认是30秒）
    lease-expiration-duration-in-seconds: 5 # 如果现在超过了5秒的间隔（默认是90秒）
    instance-id: receive-8802.com  # 在信息列表时显示主机名称
    prefer-ip-address: true     # 访问的路径变为IP地址
```

主启动类 StreamMQMain8802

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamMQMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8802.class,args);
    }
}
```

业务类

```
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController
{
    @Value("${server.port}")
    private String serverPort;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> message)
    {
        System.out.println("消费者1号,----->接受到的消息: "+message.getPayload()+"\t  port: "+serverPort);
    }
}
```

测试

*   启动 EurekaMain7001
    
*   启动 StreamMQMain8801
    
*   启动 StreamMQMain8802
    
*   8801 发送 8802 接收消息
    

89_Stream 之消息重复消费
-----------------

依照 8802，克隆出来一份运行 8803 - cloud-stream-rabbitmq-consumer8803。

**启动**

*   RabbitMQ
*   服务注册 - 8801
*   消息生产 - 8801
*   消息消费 - 8802
*   消息消费 - 8802

**运行后有两个问题**

1.  有重复消费问题
2.  消息持久化问题

**消费**

*   http://localhost:8801/sendMessage
*   目前是 8802/8803 同时都收到了，存在重复消费问题
*   如何解决：分组和持久化属性 group（重要）

**生产实际案例**

比如在如下场景中，订单系统我们做集群部署，都会从 RabbitMQ 中获取订单信息，那如果一个订单同时被两个服务获取到，那么就会造成数据错误，我们得避免这种情况。这时我们就可以**使用 Stream 中的消息分组来解决**。

![](https://img-blog.csdnimg.cn/img_convert/f61e83441af907a42e8886368bde59ff.png)

注意在 Stream 中处于同一个 group 中的多个消费者是竞争关系，就能够保证消息只会被其中一个应用消费一次。不同组是可以全面消费的 (重复消费)。

90_Stream 之 group 解决消息重复消费
--------------------------

**原理**

微服务应用放置于同一个 group 中，就能够保证消息只会被其中一个应用消费一次。

**不同的组**是可以重复消费的，**同一个组**内会发生竞争关系，只有其中一个可以消费。

**8802/8803 都变成不同组，group 两个不同**

group: A_Group、B_Group

8802 修改 YML

```
spring:
  application:
    name: cloud-stream-provider
  cloud:
      stream:
        binders: # 在此处配置要绑定的rabbitmq的服务信息；
          defaultRabbit: # 表示定义的名称，用于于binding整合
            type: rabbit # 消息组件类型
            environment: # 设置rabbitmq的相关的环境配置
              spring:
                rabbitmq:
                  host: localhost
                  port: 5672
                  username: guest
                  password: guest
        bindings: # 服务的整合处理
          output: # 这个名字是一个通道的名称
            destination: studyExchange # 表示要使用的Exchange名称定义
            content-type: application/json # 设置消息类型，本次为json，文本则设置“text/plain”
            binder: defaultRabbit # 设置要绑定的消息服务的具体设置
            group: A_Group #<----------------------------------------关键
```

8803 修改 YML（与 8802 的类似位置 `group: B_Group`）

结论：**还是重复消费**

8802/8803 实现了轮询分组，每次只有一个消费者，8801 模块的发的消息只能被 8802 或 8803 其中一个接收到，这样避免了重复消费。

**8802/8803 都变成相同组，group 两个相同**

group: A_Group

8802 修改 YML`group: A_Group`

8803 修改 YML`group: A_Group`

结论：同一个组的多个微服务实例，每次只会有一个拿到

91_Stream 之消息持久化
----------------

通过上述，解决了重复消费问题，再看看持久化。

停止 8802/8803 并**去除掉** 8802 的分组`group: A_Group`，8803 的分组 `group: A_Group` 没有去掉。

8801 先发送 4 条消息到 RabbitMq。

先启动 8802，**无分组属性配置**，后台没有打出来消息。

再启动 8803，**有分组属性配置**，后台打出来了 MQ 上的消息。(消息持久化体现)

92_Sleuth 是什么
-------------

**为什么会出现这个技术？要解决哪些问题？**

在微服务框架中，一个由客户端发起的请求在后端系统中会经过多个不同的的服务节点调用来协同产生最后的请求结果，每一个前段请求都会形成一条复杂的分布式服务调用链路，链路中的任何一环出现高延时或错误都会引起整个请求最后的失败。

![](https://img-blog.csdnimg.cn/img_convert/b40478e2b2c83d7181b9c71cdcae05ea.png)

![](https://img-blog.csdnimg.cn/img_convert/f97d15b5686264d45b46f6f188e99873.png)

**是什么**

*   https://github.com/spring-cloud/spring-cloud-sleuth
*   Spring Cloud Sleuth 提供了一套完整的服务跟踪的解决方案
*   在分布式系统中提供追踪解决方案并且兼容支持了 zipkin

**解决**

![](https://img-blog.csdnimg.cn/img_convert/ca541262b26f809a0c25014feaa069d7.png)

> sleuth  
> 英 [sluːθ] 美 [sluːθ]  
> n. 侦探

93_Sleuth 之 zipkin 搭建安装
-----------------------

1.zipkin

**下载**

*   SpringCloud 从 F 版起已不需要自己构建 Zipkin Server 了，只需调用 jar 包即可
*   https://dl.bintray.com/openzipkin/maven/io/zipkin/java/zipkin-server/
*   zipkin-server-2.12.9-exec.jar

**运行 jar**

```
java -jar zipkin-server-2.12.9-exec.jar
```

**运行控制台**

http://localhost:9411/zipkin/

**术语**

完整的调用链路

表示一请求链路，一条链路通过 Trace ld 唯一标识，Span 标识发起的请求信息，各 span 通过 parent id 关联起来

![](https://img-blog.csdnimg.cn/img_convert/ec45d9d026fee8c83eaaf7bf8cb6893d.png)

—条链路通过 Trace ld 唯一标识，Span 标识发起的请求信息，各 span 通过 parent id 关联起来。

![](https://img-blog.csdnimg.cn/img_convert/f75fcfd2146df03428b9c8c53d13c1f1.png)

整个链路的依赖关系如下：

![](https://img-blog.csdnimg.cn/img_convert/c1d19c5e9724578ee9c8668903685fa4.png)

名词解释

*   Trace：类似于树结构的 Span 集合，表示一条调用链路，存在唯一标识
*   span：表示调用链路来源，通俗的理解 span 就是一次请求信息

94_Sleuth 链路监控展现
----------------

2. 服务提供者

cloud-provider-payment8001

POM

```
<!--包含了sleuth+zipkin-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

YML

```
spring:
  application:
    name: cloud-payment-service

  zipkin: #<-------------------------------------关键 
      base-url: http://localhost:9411
  sleuth: #<-------------------------------------关键
    sampler:
    #采样率值介于 0 到 1 之间，1 则表示全部采集
    probability: 1
    
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource            # 当前数据源操作类型
    driver-class-name: org.gjt.mm.mysql.Driver              # mysql驱动包
    url: jdbc:mysql://localhost:3306/db2019?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: 123456
```

业务类 PaymentController

```
@RestController
@Slf4j
public class PaymentController {
    
    ...
    
 	@GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to here, O(∩_∩)O哈哈~";
    }    
}
```

3. 服务消费者 (调用方)

cloue-consumer-order80

POM

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```

YML

```
spring:
    application:
        name: cloud-order-service
    zipkin:
      base-url: http://localhost:9411
    sleuth:
      sampler:
        probability: 1
```

业条类 OrderController

```
// ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }
}
```

4. 依次启动 eureka7001/8001/80 - 80 调用 8001 几次测试下

5. 打开浏览器访问: http://localhost:9411

![](https://img-blog.csdnimg.cn/img_convert/733ad2e18037059045ec80cb59d8d2a3.png)

95_Cloud Alibaba 简介
-------------------

**为什么会出现 SpringCloud alibaba**

Spring Cloud Netflix 项目进入维护模式

https://spring.io/blog/2018/12/12/spring-cloud-greenwich-rc1-available-now

什么是维护模式？

将模块置于维护模式，意味着 Spring Cloud 团队将不会再向模块添加新功能。

他们将修复 block 级别的 bug 以及安全问题，他们也会考虑并审查社区的小型 pull request。

**SpringCloud alibaba 带来了什么**

**是什么**

[官网](https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md)

Spring Cloud Alibaba 致力于提供微服务开发的一站式解决方案。此项目包含开发分布式应用微服务的必需组件，方便开发者通过 Spring Cloud 编程模型轻松使用这些组件来开发分布式应用服务。

依托 Spring Cloud Alibaba，您只需要添加一些注解和少量配置，就可以将 Spring Cloud 应用接入阿里微服务解决方案，通过阿里中间件来迅速搭建分布式应用系统。

诞生：2018.10.31，Spring Cloud Alibaba 正式入驻了 Spring Cloud 官方孵化器，并在 Maven 中央库发布了第一个版本。

**能干嘛**

*   **服务限流降级**：默认支持 WebServlet、WebFlux, OpenFeign、RestTemplate、Spring Cloud Gateway, Zuul, Dubbo 和 RocketMQ 限流降级功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。
*   **服务注册与发现**：适配 Spring Cloud 服务注册与发现标准，默认集成了 Ribbon 的支持。
*   **分布式配置管理**：支持分布式系统中的外部化配置，配置更改时自动刷新。
*   **消息驱动能力**：基于 Spring Cloud Stream 为微服务应用构建消息驱动能力。
*   **分布式事务**：使用 @GlobalTransactional 注解， 高效并且对业务零侵入地解决分布式事务问题。
*   **阿里云对象存储**：阿里云提供的海量、安全、低成本、高可靠的云存储服务。支持在任何应用、任何时间、任何地点存储和访问任意类型的数据。
*   **分布式任务调度**：提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。同时提供分布式的任务执行模型，如网格任务。网格任务支持海量子任务均匀分配到所有 Worker（schedulerx-client）上执行。
*   **阿里云短信服务**：覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

**去哪下**

如果需要使用已发布的版本，在 `dependencyManagement` 中添加如下配置。

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2.2.5.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

然后在 `dependencies` 中添加自己所需使用的依赖即可使用。

**怎么玩**

*   **[Sentinel](https://github.com/alibaba/Sentinel)**：把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。
*   **[Nacos](https://github.com/alibaba/Nacos)**：一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。
*   **[RocketMQ](https://rocketmq.apache.org/)**：一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。
*   **[Dubbo](https://github.com/apache/dubbo)**：Apache Dubbo™ 是一款高性能 Java RPC 框架。
*   **[Seata](https://github.com/seata/seata)**：阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。
*   **[Alibaba Cloud OSS](https://www.aliyun.com/product/oss)**: 阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。
*   **[Alibaba Cloud SchedulerX](https://help.aliyun.com/document_detail/43136.html)**: 阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。
*   **[Alibaba Cloud SMS](https://www.aliyun.com/product/sms)**: 覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

**Spring Cloud Alibaba 学习资料获取**

*   官网
    
    *   https://spring.io/projects/spring-cloud-alibaba#overview
*   英文
    
    *   https://github.com/alibaba/spring-cloud-alibaba
    *   https://spring-cloud-alibaba-group.github.io/github-pages/greenwich/spring-cloud-alibaba.html
*   中文
    
    *   https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md

96_Nacos 简介和下载
--------------

**为什么叫 Nacos**

*   前四个字母分别为 Naming 和 Configuration 的前两个字母，最后的 s 为 Service。

**是什么**

*   一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。
*   Nacos: Dynamic Naming and Configuration Service
*   Nacos 就是注册中心＋配置中心的组合 -> **Nacos = Eureka+Config+Bus**

**能干嘛**

*   替代 Eureka 做服务注册中心
*   替代 Config 做服务配置中心

去哪下

*   https://github.com/alibaba/nacos/releases
*   [官网文档](https://spring-cloud-alibaba-group.github.io/github-pages/greenwich/spring-cloud-alibaba.html#_spring%20cloud%20alibaba%20nacos_discovery)

**各中注册中心比较**

<table><thead><tr><th>服务注册与发现框架</th><th>CAP 模型</th><th>控制台管理</th><th>社区活跃度</th></tr></thead><tbody><tr><td>Eureka</td><td>AP</td><td>支持</td><td>低 (2.x 版本闭源)</td></tr><tr><td>Zookeeper</td><td>CP</td><td>不支持</td><td>中</td></tr><tr><td>consul</td><td>CP</td><td>支持</td><td>高</td></tr><tr><td>Nacos</td><td>AP</td><td>支持</td><td>高</td></tr></tbody></table>

据说 Nacos 在阿里巴巴内部有超过 10 万的实例运行，已经过了类似双十一等各种大型流量的考验。

97_Nacos 安装
-----------

*   本地 Java8+Maven 环境已经 OK 先
*   从[官网](https://github.com/alibaba/nacos/releases)下载 Nacos
*   解压安装包，直接运行 bin 目录下的 startup.cmd
*   命令运行成功后直接访问 http://localhost:8848/nacos，默认账号密码都是 nacos
*   结果页面

![](https://img-blog.csdnimg.cn/img_convert/a3ad68ab8165ff76356641c1f49a7683.png)

98_Nacos 之服务提供者注册
-----------------

[官方文档](https://spring-cloud-alibaba-group.github.io/github-pages/greenwich/spring-cloud-alibaba.html#_spring_cloud_alibaba_nacos_discovery)

新建 Module - cloudalibaba-provider-payment9001

POM

父 POM

```
<dependencyManagement>
    <dependencies>
        <!--spring cloud alibaba 2.1.0.RELEASE-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>2.1.0.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

本模块 POM

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

    <artifactId>cloudalibaba-provider-payment9001</artifactId>

    <dependencies>
        <!--SpringCloud ailibaba nacos -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
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
  port: 9001

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

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PaymentMain9001 {
    public static void main(String[] args) {
            SpringApplication.run(PaymentMain9001.class, args);
    }
}
```

业务类

```
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }
}
```

测试

*   http://localhost:9001/payment/nacos/1
*   nacos 控制台
*   nacos 服务注册中心 + 服务提供者 9001 都 OK 了

为了下一章节演示 nacos 的负载均衡，参照 9001 新建 9002

*   新建 cloudalibaba-provider-payment9002
*   9002 其它步骤你懂的
*   或者**取巧**不想新建重复体力劳动，可以利用 IDEA 功能，直接拷贝虚拟端口映射

![](https://img-blog.csdnimg.cn/img_convert/2bef79cd8f72b8f23b815b49f4ba07ce.png)

99_Nacos 之服务消费者注册和负载
--------------------

新建 Module - cloudalibaba-consumer-nacos-order83

POM

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

    <artifactId>cloudalibaba-consumer-nacos-order83</artifactId>

    <dependencies>
        <!--SpringCloud ailibaba nacos -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!-- 引入自己定义的api通用包，可以使用Payment支付Entity -->
        <dependency>
            <groupId>com.lun.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>1.0.0-SNAPSHOT</version>
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

为什么 nacos 支持负载均衡？因为 spring-cloud-starter-alibaba-nacos-discovery 内含 netflix-ribbon 包。

YML

```
server:
  port: 83

spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848

#消费者将要去访问的微服务名称(注册成功进nacos的微服务提供者)
service-url:
  nacos-user-service: http://nacos-payment-provider
```

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class OrderNacosMain83
{
    public static void main(String[] args)
    {
        SpringApplication.run(OrderNacosMain83.class,args);
    }
}
```

业务类

ApplicationContextConfig

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

OrderNacosController

```
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController {
    
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(serverURL+"/payment/nacos/"+id,String.class);
    }

}
```

测试

*   启动 nacos 控制台
*   http://localhost:83/Eonsumer/payment/nacos/13
    *   83 访问 9001/9002，轮询负载 OK

100_Nacos 服务注册中心对比提升
--------------------

**Nacos 全景图**

![](https://img-blog.csdnimg.cn/img_convert/a9c35ea022a95aa76bfec990d6b73d8a.png)

**Nacos 和 CAP**

Nacos 与其他注册中心特性对比

![](https://img-blog.csdnimg.cn/img_convert/62d5a8566a2dc588a5ed52346049a054.png)

**Nacos 服务发现实例模型**

![](https://img-blog.csdnimg.cn/img_convert/6578e36df056a995a39034045c36fc40.png)

**Nacos 支持 AP 和 CP 模式的切换**

C 是所有节点在同一时间看到的数据是一致的; 而 A 的定义是所有的请求都会收到响应。

_何时选择使用何种模式?_

—般来说，如果不需要存储服务级别的信息且服务实例是通过 nacos-client 注册，并能够保持心跳上报，那么就可以选择 AP 模式。当前主流的服务如 Spring cloud 和 Dubbo 服务，都适用于 AP 模式，AP 模式为了服务的可能性而减弱了一致性，因此 AP 模式下只支持注册临时实例。

如果需要在服务级别编辑或者存储配置信息，那么 CP 是必须，K8S 服务和 DNS 服务则适用于 CP 模式。CP 模式下则支持注册持久化实例，此时则是以 Raft 协议为集群运行模式，该模式下注册实例之前必须先注册服务，如果服务不存在，则会返回错误。

切换命令：

`curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP`

101_Nacos 之服务配置中心
-----------------

基础配置

cloudalibaba-config-nacos-client3377

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

    <artifactId>cloudalibaba-config-nacos-client3377</artifactId>

    <dependencies>
        <!--nacos-config-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <!--nacos-discovery-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>
        <!--web + actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--一般基础配置-->
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

Nacos 同 springcloud-config 一样，在项目初始化时，要保证先从配置中心进行配置拉取，拉取配置之后，才能保证项目的正常启动。

springboot 中配置文件的加载是存在优先级顺序的，bootstrap 优先级高于 application

bootstrap

```
# nacos配置
server:
  port: 3377

spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #Nacos服务注册中心地址
      config:
        server-addr: localhost:8848 #Nacos作为配置中心地址
        file-extension: yaml #指定yaml格式的配置
        group: DEV_GROUP
        namespace: 7d8f0f5a-6a53-4785-9686-dd460158e5d4


# ${spring.application.name}-${spring.profile.active}.${spring.cloud.nacos.config.file-extension}
# nacos-config-client-dev.yaml

# nacos-config-client-test.yaml   ----> config.info
```

application

```
spring:
  profiles:
    active: dev # 表示开发环境
    #active: test # 表示测试环境
    #active: info
```

主启动

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class NacosConfigClientMain3377
{
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigClientMain3377.class, args);
    }
}
```

业务类

```
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //支持Nacos的动态刷新功能。
public class ConfigClientController
{
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
```

**在 Nacos 中添加配置信息**

Nacos 中的 dataid 的组成格式及与 SpringBoot 配置文件中的匹配规则

[官方文档](https://nacos.io/zh-cn/docs/quick-start-spring-cloud.html)

说明：之所以需要配置 spring.application.name，是因为它是构成 Nacos 配置管理 dataId 字段的一部分。

在 Nacos Spring Cloud 中, dataId 的完整格式如下：

```
${prefix}-${spring-profile.active}.${file-extension}
```

*   prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix 来配置。
*   spring.profile.active 即为当前环境对应的 profile，详情可以参考 Spring Boot 文档。注意：当 spring.profile.active 为空时，对应的连接符 - 也将不存在，datald 的拼接格式变成`${prefix}.${file-extension}`
*   file-exetension 为配置内容的数据格式，可以通过配置项 spring .cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。
*   通过 Spring Cloud 原生注解 @RefreshScope 实现配置自动更新。

最后公式：

```
${spring.application.name)}-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
```

配置新增

![](https://img-blog.csdnimg.cn/img_convert/05d45948bf637614dbd70e2bc8ce992d.png)

Nacos 界面配置对应 - 设置 DataId

![](https://img-blog.csdnimg.cn/img_convert/c61619bbe5ea16f34efca8103b0f90ba.png)

配置小结

![](https://img-blog.csdnimg.cn/img_convert/b3bffc4a646b30f9bf64fc649bf26f7d.png)

**测试**

*   启动前需要在 nacos 客户端 - 配置管理 - 配置管理栏目下有对应的 yaml 配置文件
*   运行 cloud-config-nacos-client3377 的主启动类
*   调用接口查看配置信息 - http://localhost:3377/config/info

**自带动态刷新**

修改下 Nacos 中的 yaml 配置文件，再次调用查看配置的接口，就会发现配置已经刷新。

102_Nacos 之命名空间分组和 DataID 三者关系
------------------------------

**问题 - 多环境多项目管理**

问题 1:

实际开发中，通常一个系统会准备

1.  dev 开发环境
2.  test 测试环境
3.  prod 生产环境。

如何保证指定环境启动时服务能正确读取到 Nacos 上相应环境的配置文件呢?

问题 2:

一个大型分布式微服务系统会有很多微服务子项目，每个微服务项目又都会有相应的开发环境、测试环境、预发环境、正式环境… 那怎么对这些微服务配置进行管理呢?

Nacos 的图形化管理界面

![](https://img-blog.csdnimg.cn/img_convert/3a7d1ad9bea8356742997ed3ebbe9be3.png)

![](https://img-blog.csdnimg.cn/img_convert/fe336f99f44c4b0aefddf0ae38d1c470.png)

**Namespace+Group+Data lD 三者关系？为什么这么设计？**

1 是什么

类似 Java 里面的 package 名和类名最外层的 namespace 是可以用于区分部署环境的，Group 和 DatalD 逻辑上区分两个目标对象。

2 三者情况

![](https://img-blog.csdnimg.cn/img_convert/60712abd615dd86ac6c119bf132a28d6.png)

默认情况：Namespace=public，Group=DEFAULT_GROUP，默认 Cluster 是 DEFAULT

*   Nacos 默认的 Namespace 是 public，Namespace 主要用来实现隔离。
    *   比方说我们现在有三个环境：开发、测试、生产环境，我们就可以创建三个 Namespace，不同的 Namespace 之间是隔离的。
*   Group 默认是 DEFAULT_GROUP，Group 可以把不同的微服务划分到同一个分组里面去
*   Service 就是微服务: 一个 Service 可以包含多个 Cluster (集群)，Nacos 默认 Cluster 是 DEFAULT，Cluster 是对指定微服务的一个虚拟划分。
    *   比方说为了容灾，将 Service 微服务分别部署在了杭州机房和广州机房，这时就可以给杭州机房的 Service 微服务起一个集群名称 (HZ)，给广州机房的 Service 微服务起一个集群名称 (GZ)，还可以尽量让同一个机房的微服务互相调用，以提升性能。
*   最后是 Instance，就是微服务的实例。

103_Nacos 之 DataID 配置
---------------------

指定 spring.profile.active 和配置文件的 DatalD 来使不同环境下读取不同的配置

默认空间 + 默认分组 + 新建 dev 和 test 两个 DatalD

*   新建 dev 配置 DatalD

![](https://img-blog.csdnimg.cn/img_convert/5ea4b3fd5ca8cb6e7de6f0d9ac98f051.png)

*   新建 test 配置 DatalD

![](https://img-blog.csdnimg.cn/img_convert/b41fe36b41fa2d5abc6e5e492ee3625d.png)

通过 spring.profile.active 属性就能进行多环境下配置文件的读取

![](https://img-blog.csdnimg.cn/img_convert/281a70d387cb48ce82e94421adf17747.png)

**测试**

*   http://localhost:3377/config/info
*   配置是什么就加载什么 test/dev

104_Nacos 之 Group 分组方案
----------------------

通过 Group 实现环境区分 - 新建 Group

![](https://img-blog.csdnimg.cn/img_convert/bdf592aa566fe50f7f454118a70ca03c.png)

在 nacos 图形界面控制台上面新建配置文件 DatalD

![](https://img-blog.csdnimg.cn/img_convert/28aee2b45901bbb9a6776d5c4398a6bb.png)

bootstrap+application

在 config 下增加一条 group 的配置即可。可配置为 DEV_GROUP 或 TEST GROUP

![](https://img-blog.csdnimg.cn/img_convert/342a167a8bd948d8ba5cbfd760cf66a6.png)

105_Nacos 之 Namespace 空间方案
--------------------------

新建 dev/test 的 Namespace

![](https://img-blog.csdnimg.cn/img_convert/a10c71978c75c214aca5fa7057bb2834.png)

回到服务管理 - 服务列表查看

![](https://img-blog.csdnimg.cn/img_convert/2a9f3fa415f5cead0219d404a47131a0.png)

按照域名配置填写

![](https://img-blog.csdnimg.cn/img_convert/2177c126090c0db553a8ce77e838a7c9.png)

YML

```
# nacos配置
server:
  port: 3377

spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: localhost:8848 #Nacos服务注册中心地址
      config:
        server-addr: localhost:8848 #Nacos作为配置中心地址
        file-extension: yaml #指定yaml格式的配置
        group: DEV_GROUP
        namespace: 7d8f0f5a-6a53-4785-9686-dd460158e5d4 #<------------指定namespace


# ${spring.application.name}-${spring.profile.active}.${spring.cloud.nacos.config.file-extension}
# nacos-config-client-dev.yaml

# nacos-config-client-test.yaml   ----> config.info
```

106_Nacos 集群_架构说明
-----------------

> [官方文档](https://nacos.io/zh-cn/docs/cluster-mode-quick-start.html)
> 
> **官网架构图**
> 
> 集群部署架构图
> 
> 因此开源的时候推荐用户把所有服务列表放到一个 vip 下面，然后挂到一个域名下面
> 
> http://ip1:port/openAPI 直连 ip 模式，机器挂则需要修改 ip 才可以使用。
> 
> http://VIP:port/openAPI 挂载 VIP 模式，直连 vip 即可，下面挂 server 真实 ip，可读性不好。
> 
> http://nacos.com:port/openAPI 域名＋VIP 模式，可读性好，而且换 ip 方便，推荐模式
> 
> ![](https://img-blog.csdnimg.cn/img_convert/59bfb9114980c13f42d14e64dd2dafab.png)

上图官网翻译，真实情况

![](https://img-blog.csdnimg.cn/img_convert/681c3dc16a69f197896cbff482f2298e.png)

按照上述，**我们需要 mysql 数据库**。

> [官网说明](https://nacos.io/zh-cn/docs/deployment.html)
> 
> 默认 Nacos 使用嵌入式数据库实现数据的存储。所以，如果启动多个默认配置下的 Nacos 节点，数据存储是存在一致性问题的。为了解决这个问题，**Nacos 采用了集中式存储的方式来支持集群化部署，目前只支持 MySQL 的存储**。
> 
> Nacos 支持三种部署模式
> 
> *   单机模式 - 用于测试和单机试用。
> *   集群模式 - 用于生产环境，确保高可用。
> *   多集群模式 - 用于多数据中心场景。
> 
> **Windows**
> 
> cmd startup.cmd 或者双击 startup.cmd 文件
> 
> **单机模式支持 mysql**
> 
> 在 0.7 版本之前，在单机模式时 nacos 使用嵌入式数据库实现数据的存储，不方便观察数据存储的基本情况。0.7 版本增加了支持 mysql 数据源能力，具体的操作步骤:
> 
> 1.  安装数据库，版本要求: 5.6.5+
> 2.  初始化 mysq 数据库，数据库初始化文件: nacos-mysql.sql
> 3.  修改 conf/application.properties 文件，增加支持 mysql 数据源配置（目前只支持 mysql)，添加 mysql 数据源的 url、用户名和密码。
> 
> ```
> spring.datasource.platform=mysql
> 
> db.num=1
> db.url.0=jdbc:mysql://11.162.196.16:3306/nacos_devtest?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
> db.user=nacos_devtest
> db.password=youdontknow
> ```
> 
> 再以单机模式启动 nacos，nacos 所有写嵌入式数据库的数据都写到了 mysql。

107_Nacos 持久化切换配置
-----------------

Nacos 默认自带的是嵌入式数据库 derby，[nacos 的 pom.xml](github.com/alibaba/nacos/blob/develop/config/pom.xml) 中可以看出。

derby 到 mysql 切换配置步骤：

1.  nacos-server-1.1.4\nacos\conf 录下找到 nacos-mysql.sql 文件，执行脚本。
2.  nacos-server-1.1.4\nacos\conf 目录下找到 application.properties，添加以下配置（按需修改对应值）。

```
spring.datasource.platform=mysql

db.num=1
db.url.0=jdbc:mysql://localhost:3306/nacos_devtest?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=1234
```

启动 Nacos，可以看到是个全新的空记录界面，以前是记录进 derby。