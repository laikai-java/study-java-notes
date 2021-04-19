> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/java_wxid/article/details/106630556)

前三个已经烂大街了，我这里就不写了，点击蓝色字体查看相关的博文

[JVM 内存模型](https://www.jianshu.com/p/76959115d486)

[JVM 算法](https://blog.csdn.net/gaoying_blogs/article/details/77748211?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522159291028919724845004669%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=159291028919724845004669&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_v2~rank_blog_default-3-77748211.pc_v2_rank_blog_default&utm_term=JVM+%E7%AE%97%E6%B3%95)

[JVM 垃圾回收器](https://blog.csdn.net/lijian12388806/article/details/80546442?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522159291067319724811835723%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=159291067319724811835723&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~top_click~default-1-80546442.pc_v2_rank_blog_default&utm_term=JVM+%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6)

JVM 调优
======

### 查看参数

**第一种：**

查看进程编号：jps -l

jinfo -flag 具体参数 java 进程编号

jinfo -flags 具体参数

![](https://img-blog.csdnimg.cn/20200623194334548.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**第二种：**

**查看 JVM 出厂默认设置：java -XX:+PrintFlagsInitial**

**查看 JVM 修改更新的内容：java -XX:+PrintFlagsFinal -version**

![](https://img-blog.csdnimg.cn/20200623195655863.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200623195921438.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**查看默认垃圾回收器：java -XX:+PrintCommandLineFlags -version**

![](https://img-blog.csdnimg.cn/20200623195955281.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

### 常用参数：

**-Xmx(-XX:MaxHeapSize)：初始大小内存：初始化的值是物理内存的四分之一**

**-Xms(-XX:InitialHeapSize)：最大分配内存：初始化的值是物理内存的六十四分之一**

**-Xss(-XX:ThreadStackSize)：设置单个线程栈的大小，一般默认为 512k~1024k**

![](https://img-blog.csdnimg.cn/2020062320153410.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**-Xmn：设置年轻代的大小**

**-XX:MetaspaceSize：设置元空间大小 元空间的本质和永久代类似，都是对 JVM 规范中方法区的实现。不过元空间与永久代之间的区别在于：元空间不在虚拟机中，而是在本地内存中，默认情况下，元空间的大小仅受本地内存限制。**

-Xms 128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC

-Xms 128m：初始内存 128M

-Xmx4096m ：最大堆内存 4G

-Xss1024k：初始栈大小 1024K

-XX:MetaspaceSize=512m：元空间 512M

-XX:+PrintCommandLineFlags：打印默认参数

-XX:+PrintGCDetails ：打印 GC 回收的细节

-XX:+UseSerialGC：串行垃圾回收器

![](https://img-blog.csdnimg.cn/20200623203800926.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**-XX:+PrintGCDetails 命令打印的：**

![](https://img-blog.csdnimg.cn/202006232041050.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

案例：[https://blog.csdn.net/java_wxid/article/details/103021907](https://blog.csdn.net/java_wxid/article/details/103021907)

**-XX:SurvivorRatio：设置新生代中 eden 和 S0/S1 空间的比例。**

默认：-XX:SurvivorRatio=8,Eden:S0:S1=8:1:1；

假如 - XX:SurvivorRatio=4,Eden:S0:S1=4:1:1。SurvivorRatio 的值就是设置 eden 去的比例占多少, S0 和 S1 相同

**-XX:NewRatio：配置年轻带与老年带在堆结构的占比。**

默认：-XX:NewRatio=2 新生代占 1，老年代 2，年轻带占整个堆的 1/3。假如：-XX:NewRatio=4 新生代占 1，老年代占 4，年轻带占整个堆的 1/5。NewRatio 的值就是设置老年代的占比，剩下的 1 给新生代。

**-XX:MaxTenuringThreshold：设置垃圾的最大年龄**

java8 之后这个值最大只能设置为 15，最低是 0

![](https://img-blog.csdnimg.cn/20200623205542283.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**落地实现：**

![](https://img-blog.csdnimg.cn/20200628175151572.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

拖地实现的案例：

![](https://img-blog.csdnimg.cn/20200628175252901.png)

### 四大引用

![](https://img-blog.csdnimg.cn/20200628120518447.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**强引用 Reference(默认支持模式)**

例如：Book book = new Book();

理论：

![](https://img-blog.csdnimg.cn/20200628120653517.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

实战：

![](https://img-blog.csdnimg.cn/20200628121011124.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**软引用 SoftReference**

理论：

![](https://img-blog.csdnimg.cn/20200628121139616.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

实战：

内存足够时：

![](https://img-blog.csdnimg.cn/20200628121317252.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

 内存不够时：

![](https://img-blog.csdnimg.cn/20200628121507220.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200628121604576.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**弱引用 WeakReference**

**理论：**

![](https://img-blog.csdnimg.cn/20200628121725314.png)

实战：

![](https://img-blog.csdnimg.cn/20200628121904561.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

软引用和弱引用的应用场景：

![](https://img-blog.csdnimg.cn/2020062812212586.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

 实战：WeakHashMap 的使用

![](https://img-blog.csdnimg.cn/20200628122609848.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**虚引用 PhantomReference**

**理论：**

![](https://img-blog.csdnimg.cn/20200628122905828.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

实战：

弱引用与引用队列

![](https://img-blog.csdnimg.cn/2020062812341223.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

虚引用与引用队列:

![](https://img-blog.csdnimg.cn/20200628123449446.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200628123547209.png)

### 常见的 JVM 异常

**StackOverflowError：线程栈空间被耗尽，没有足够资源分配给新创建的栈帧**

![](https://img-blog.csdnimg.cn/20200628123847691.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

 **OutofMemoryError：Java heap space 堆内存中的空间不足以存放新创建的对象**

![](https://img-blog.csdnimg.cn/20200628124213678.png)

**OutOfMemoryError: GC overhead limit exceeded 超过 98% 的时间用来做 GC 并且回收了不到 2% 的堆内存**

![](https://img-blog.csdnimg.cn/20200628124530719.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200628125549639.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**OutOfMemoryError: Direct buffer memory 堆外内存**

![](https://img-blog.csdnimg.cn/20200628124746568.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200628125508740.png)

**OutofMemoryError：unable to create new native thread**

![](https://img-blog.csdnimg.cn/20200628125237715.png)

![](https://img-blog.csdnimg.cn/20200628125351442.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200628125647847.png)

解决方案：

![](https://img-blog.csdnimg.cn/20200628125659837.png)

**OutOfMemoryError: Metaspace 元数据区 (Metaspace) 已被用满**

![](https://img-blog.csdnimg.cn/20200628125815440.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

![](https://img-blog.csdnimg.cn/20200628125943176.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

解决方案：-XX:MaxMetaspaceSize=512m

### **[类加载机制（双亲委派）](https://blog.csdn.net/zhangjingao/article/details/86680206)**

启动类加载器（Bootstrap）C++  
扩展类加载器（Extension）Java  
应用程序类加载器（AppClassLoader）Java

双亲委派模型工作原理：如果一个类加载器收到类加载的请求，它首先不会自己去尝试加载这个类，而是把这个请  
求委派给父类加载器完成。每个类加载器都是如此，只有当父加载器在自己的搜索范围内找不到指定的类时（即  
ClassNotFoundException），子加载器才会尝试自己去加载。

### **创建一个对象，这个对象在内存中是怎么分配的？**

[https://www.zhihu.com/question/55237879/answer/1275043584](https://www.zhihu.com/question/55237879/answer/1275043584)