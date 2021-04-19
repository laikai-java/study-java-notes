> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/java_wxid/article/details/107029848)

### Dubbo

**简单的介绍一下 Dubbo？(Dubbo 是什么)**

dubbo 就是个服务调用的东东。

为什么怎么说呢？

因为 Dubbo 是由阿里开源的一个 RPC 分布式框架

那么 RPC 是什么呢？

就是不同的应用部署到不同的服务器上，应用之间想要调用没有办法直接调用，因为不在一个内存空间，需要通过网络通讯来调用，或者传达调用的数据。而且 RPC 会将远程调用的细节隐藏起来，让调用远程服务像调用本地服务一样简单。

**dubbo 有哪些组件？**

![](https://img-blog.csdnimg.cn/20200629222218661.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**紫色虚线**: 在 Dubbo 启动时完成的功能　　**蓝青色的线**：都是程序运行过程中执行的功能，**虚线**是异步操作，**实线**是同步操作

　　**Provider：**提供者，服务发布方。如果是采用 SOA 开发的模式，这个就是和数据库交互的接口，也就是 service 主要放在生产者这边

　　**Consumer：**消费者，调用服务方。面向前端的 Controller 主要是在这边，可以远程调用生产者中的方法，生产者发生变化时也会实时更新消费者的调用列表。具体的看下面介绍

　　**Container：**主要负责启动、加载、运行服务提供者。Dubbo 容器，依赖于 Spring 容器。这里比较注意的就是 Dubbo 是依赖与 Spring 容器的。所以必须要和 Spring 配合着使用

　　**Registry：**注册中心. 当 Container 启动时把所有可以提供的服务列表上 Registry 中进行注册。作用：告诉 Consumer 提供了什么服务和服务方在哪里.

　　**Monitor:** 监控中心：监控中心负责统计各服务调用次数、调用时间

**运行原理？**

**0.Start：** 启动容器, 相当于在启动 Dubbo 的 Provider，并且会创建对应的目录结构，例如代码中的共用接口名为 com.learnDubbo.demo.DemoService，就会创建 /dubbo/com.learnDubbo.demo.DemoService 目录，然后在创建 providers 目录，再在 providers 目录下写入自己的 URL 地址。

**1.Register：**启动后会去注册中心进行注册，注册所有可以提供的服务列表。即订阅 / dubbo/com.learnDubbo.demo.DemoService 目录下的所有提供者和消费者 URL 地址。

**2.Subscribe：**Consumer 在启动时，不仅仅会注册自身到 …/consumers / 目录下，同时还会订阅…/providers 目录，实时获取其上 Provider 的 URL 字符串信息。当服务消费者启动时：会在 / dubbo/com.learnDubbo.demo.DemoService 目录创建 / consumers 目录，并在 / consumers 目录写入自己的 URL 地址。

**3.notify：**当 Provider 有修改后，注册中心会把消息推送给 Consummer。也就是注册中心会对 Provider 进行观察，这里就是使用设计模式中的观察者模式。以 Zookeeper 注册中心为例，Dubbo 中有 ZookeeperRegistry 中的 doSubscribe 方法也就是进行生产者订阅和监听。

**4、invoke：**根据获取到的 Provider 地址，真实调用 Provider 中功能。这里就是唯一一个同步的方法，因为消费者要得到生产者传来的数据才能进行下一步操作，但是 Dubbo 是一个 RPC 框架，RPC 的核心就在于只能知道接口不能知道内部具体实现。所以在 Consumer 方使用了代理设计模式，创建一个 Provider 方类的一个代理对象，通过代理对象获取 Provider 中真实功能，起到保护 Provider 真实功能的作用。

**5、Monitor：**Consumer 和 Provider 每隔 1 分钟向 Monitor 发送统计信息, 统计信息包含, 访问次数, 频率等

**Dubbo 与 SpringCould 相比它为什么效率要高一些**

首先看一下 Dubbo 支持什么协议？dubbo 各种协议的性能对比：

![](https://img-blog.csdnimg.cn/20200629220131231.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**thrift 协议**：

thrift 原生协议性能表现卓越，是 dubbo 原生性能的 6 倍

**dubbo 协议：**

定义：缺省协议、采用了单一长连接和 NIO 异步通讯、使用线程池并发处理请求，能减少握手和加大并发效率

适用范围：传入传出参数数据包较小（建议小于 100K），消费者比提供者个数多，单一消费者无法压满提供者，尽量不要用 dubbo 协议传输大文件或超大字符串。  
适用场景：常规远程服务方法调用

**hession 协议：**  
定义：用于集成 Hessian 的服务，Hessian 底层采用 Http 通讯，采用 Servlet 暴露服务，Dubbo 缺省内嵌 Jetty 作为服务器实现  
适用范围：传入传出参数数据包较大，提供者比消费者个数多，提供者压力较大，可传文件。  
适用场景：页面传输，文件传输，或与原生 hessian 服务互操作。

各种协议的取舍：[https://www.cnblogs.com/barrywxx/p/8589374.html](https://www.cnblogs.com/barrywxx/p/8589374.html)

**案例测试**：

![](https://img-blog.csdnimg.cn/20200629220706853.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

可以看出 dubbo 通信的效率上高与 SpringCould，那为什么会高于呢？

**SpringCloud 服务间的通信方式有两种**

*   RestTemplate 方式
    
*   Feign 的方式
    

不管是什么方式，它都是通过 REST 接口调用服务的 http 接口，参数和结果默认都是通过 jackson 序列化和反序列化。

也就是说 SpringCould 是 Http 请求。

dubbo 我们都知道是 RPC 分布式框架，默认是基于 dubbo 自定义的二进制协议进行传输，消息体比较简单，传输数据要小很多。

案例测试：

![](https://img-blog.csdnimg.cn/20200629221633133.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**结论：RPC 请求的效率是 HTTP 请求的 1.6 倍左右，性能明显比 HTTP 请求要高很多，因为 HTTP 协议包含大量的请求头、响应头信息。**

### Zookeeper:

Zookeeper 的实现原理？（工作原理）

Zookeeper 会维护一个类似于标准的文件系统的具有层次关系的数据结构。这个文件系统中每个子目录项都被称为 znode 节点，这个 znode 节点也可以有子节点，每个节点都可以存储数据，客户端也可以对这些 node 节点进行 getChildren，getData,exists 方法，同时也可以在 znode tree 路径上设置 watch（类似于监听），当 watch 路径上发生节点 create、delete、update 的时候，会通知到 client。client 可以得到通知后，再获取数据，执行业务逻辑操作。Zookeeper 的作用主要是用来维护和监控存储的 node 节点上这些数据的状态变化，通过监控这些数据状态的变化，从而达到基于数据的集群管理。

为什么要用 zookeeper 作为 dubbo 的注册中心？能选择其他的吗？

Zookeeper 的数据模型是由一系列的 Znode 数据节点组成，和文件系统类似。zookeeper 的数据全部存储在内存中，性能高；zookeeper 也支持集群，实现了高可用；同时基于 zookeeper 的特性，也支持事件监听（服务的暴露方发生变化，可以进行推送），所以 zookeeper 适合作为 dubbo 的注册中心区使用。redis、Simple 也可以作为 dubbo 的注册中心来使用。

项目中主要用 zookeeper 做了什么？（作用）

作为注册中心用；主要是在服务器上搭建 zookeeper，其次在 spring 管理的 dubbo 的配置文件中配置（暴露方和消费方都需要配置）