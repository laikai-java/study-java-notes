> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [blog.csdn.net](https://blog.csdn.net/java_wxid/article/details/106901318)

**Spring 注解：**

<table border="1" cellpadding="1" cellspacing="1"><tbody><tr><td colspan="2">声明 bean 的注解</td></tr><tr><td>@Component</td><td>组件<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>没有明确的角色</td></tr><tr><td>@Service</td><td>在业务逻辑层使用（service 层）</td></tr><tr><td>@Repository</td><td>在数据访问层使用（dao 层）</td></tr><tr><td>@Controller</td><td>在展现层使用<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>控制器的声明（C）</td></tr><tr><td colspan="2">注入 bean 的注解</td></tr><tr><td>@Autowired</td><td>由 Spring 提供</td></tr><tr><td>@Resource</td><td>由 JSR-250 提供</td></tr><tr><td colspan="2">java 配置类相关注解</td></tr><tr><td>@Bean</td><td>注解在方法上<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>声明当前方法的返回值为一个 bean<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>替代 xml 中的方式（方法上）</td></tr><tr><td>@Configuration</td><td>声明当前类为配置类<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>其中内部组合了 @Component 注解<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>表明这个类是一个 bean（类上）</td></tr><tr><td>@ComponentScan</td><td>用于对 Component 进行扫描<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>相当于 xml 中的（类上）</td></tr><tr><td colspan="2">切面（AOP）相关注解</td></tr><tr><td>@Aspect</td><td>声明一个切面（类上） 使用 @After<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>@Before<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>@Around 定义建言（advice）<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>可直接将拦截规则（切点）作为参数<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td></tr><tr><td>@After</td><td>在方法执行之后执行（方法上） @Before 在方法执行之前执行（方法上） @Around 在方法执行之前与之后执行（方法上）</td></tr><tr><td>@PointCut</td><td>声明切点 在 java 配置类中使用 @EnableAspectJAutoProxy 注解开启 Spring 对 AspectJ 代理的支持（类上）</td></tr><tr><td colspan="2">@Value 注解</td></tr><tr><td>@Value 为属性注入值</td><td>注入操作系统属性 @Value("#{systemProperties['os.name']}")String osName;<br>注入表达式结果 @Value("#{ T(java.lang.Math).random() * 100 }") String randomNumber;<br>注入其它 bean 属性 @Value("#{domeClass.name}")String name;<br>注入文件资源 @Value("classpath:com/hgs/hello/test.txt")String Resource file;<br>注入网站资源 @Value("http://www.cznovel.com")Resource url;<br>注入配置文件 Value("${book.name}")String bookName;</td></tr><tr><td colspan="2">异步相关</td></tr><tr><td>@EnableAsync</td><td>配置类中<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>通过此注解开启对异步任务的支持<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>叙事性 AsyncConfigurer 接口（类上）</td></tr><tr><td>@Async</td><td>在实际执行的 bean 方法使用该注解来申明其是一个异步任务（方法上或类上所有的方法都将异步<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>需要 @EnableAsync 开启异步任务）</td></tr><tr><td colspan="2">定时任务相关</td></tr><tr><td>@EnableScheduling</td><td>在配置类上使用<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>开启计划任务的支持（类上）</td></tr><tr><td>@Scheduled</td><td>来申明这是一个任务<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>包括 cron,fixDelay,fixRate 等类型（方法上<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>需先开启计划任务的支持）</td></tr></tbody></table>

**SpringMVC 注解**

<table border="1" cellpadding="1" cellspacing="1"><tbody><tr><td>@EnableWebMvc</td><td>在配置类中开启 Web MVC 的配置支持<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>如一些 ViewResolver 或者 MessageConverter 等<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>若无此句<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>重写 WebMvcConfigurerAdapter 方法（用于对 SpringMVC 的配置）<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td></tr><tr><td>@Controller</td><td>声明该类为 SpringMVC 中的 Controller</td></tr><tr><td>@RequestMapping</td><td>用于映射 Web 请求<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>包括访问路径和参数（类或方法上）</td></tr><tr><td>@ResponseBody</td><td>支持将返回值放在 response 内<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>而不是一个页面<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>通常用户返回 json 数据（返回值旁或方法上）</td></tr><tr><td>@RequestBody</td><td>允许 request 的参数在 request 体中<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>而不是在直接连接在地址后面<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char>（放在参数前）</td></tr><tr><td>@PathVariable</td><td>用于接收路径参数<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>比如 @RequestMapping(<h-char unicode="201c" class="biaodian cjk bd-open punct">“</h-char>/hello/{name}<h-char unicode="201d" class="biaodian cjk bd-close bd-end punct">”</h-char>) 申明的路径<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>将注解放在参数中前<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>即可获取该值<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>通常作为 Restful 的接口实现方法<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td></tr><tr><td>@RestController</td><td>该注解为一个组合注解<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>相当于 @Controller 和 @ResponseBody 的组合<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>注解在类上<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>意味着<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>该 Controller 的所有方法都默认加上了 @ResponseBody<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td></tr><tr><td>@ControllerAdvice</td><td>通过该注解<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>我们可以将对于控制器的全局配置放置在同一个位置<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>注解了 @Controller 的类的方法可使用 @ExceptionHandler<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>@InitBinder<h-char unicode="3001" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>、</h-inner></h-char>@ModelAttribute 注解到方法上<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char> 这对所有注解了 @RequestMapping 的控制器内的方法有效<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td></tr><tr><td>@ExceptionHandler</td><td>用于全局处理控制器里的异常</td></tr><tr><td>@InitBinder</td><td>用来设置 WebDataBinder<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>WebDataBinder 用来自动绑定前台请求参数到 Model 中<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td></tr><tr><td>@ModelAttribute</td><td>本来的作用是绑定键值对到 Model 里<h-char unicode="ff0c" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>，</h-inner></h-char>在 @ControllerAdvice 中是让全局的 @RequestMapping 都能获得在此处设置的键值对<h-char unicode="3002" class="biaodian cjk bd-end bd-cop bd-hangable"><h-inner>。</h-inner></h-char></td></tr></tbody></table>

**Mybatis 注解：（偷个懒，不使用表格了，嘻嘻）**

*   **增删改查：**@Insert、@Update、@Delete、@Select、@MapKey、@Options、@SelelctKey、@Param、@InsertProvider、@UpdateProvider、@DeleteProvider、@SelectProvider
    
*   **结果集映射：**@Results、@Result、@ResultMap、@ResultType、@ConstructorArgs、@Arg、@One、@Many、@TypeDiscriminator、@Case
    
*   **缓存：**@CacheNamespace、@Property、@CacheNamespaceRef、@Flush
    

**SpringBoot 注解：**

*   @**SpringBootApplication**：申明让 spring boot 自动给程序进行必要的配置，这个配置等同于：
*   @**Configuration**，@EnableAutoConfiguration 和 @ComponentScan 三个配置。
*   @**ResponseBody**：表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用，用于构建 RESTful 的 api。在使用 @RequestMapping 后，返回值通常解析为跳转路径，加上 @esponsebody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。比如异步获取 json 数据，加上 @Responsebody 后，会直接返回 json 数据。该注解一般会配合 @RequestMapping 一起使用。
*   @**Controller**：用于定义控制器类，在 spring 项目中由控制器负责将用户发来的 URL 请求转发到对应的服务接口（service 层），一般这个注解在类中，通常方法需要配合注解 @RequestMapping。
*   @**RestController**：用于标注控制层组件 (如 struts 中的 action)，@ResponseBody 和 @Controller 的合集。
*   @**RequestMapping**：提供路由信息，负责 URL 到 Controller 中的具体函数的映射。
*   @**EnableAutoConfiguration**：SpringBoot 自动配置（auto-configuration）：尝试根据你添加的 jar 依赖自动配置你的 Spring 应用。例如，如果你的 classpath 下存在 HSQLDB，并且你没有手动配置任何数据库连接 beans，那么我们将自动配置一个内存型（in-memory）数据库”。你可以将 @EnableAutoConfiguration 或者 @SpringBootApplication 注解添加到一个 @Configuration 类上来选择自动配置。如果发现应用了你不想要的特定自动配置类，你可以使用 @EnableAutoConfiguration 注解的排除属性来禁用它们。
*   @**ComponentScan**：表示将该类自动发现扫描组件。个人理解相当于，如果扫描到有 @Component、@Controller、@Service 等这些注解的类，并注册为 Bean，可以自动收集所有的 Spring 组件，包括 @Configuration 类。我们经常使用 @ComponentScan 注解搜索 beans，并结合 @Autowired 注解导入。可以自动收集所有的 Spring 组件，包括 @Configuration 类。我们经常使用 @ComponentScan 注解搜索 beans，并结合 @Autowired 注解导入。如果没有配置的话，Spring Boot 会扫描启动类所在包下以及子包下的使用了 @Service,@Repository 等注解的类。
*   @**Configuration**：相当于传统的 xml 配置文件，如果有些第三方库需要用到 xml 文件，建议仍然通过 @Configuration 类作为项目的配置主类——可以使用 @ImportResource 注解加载 xml 配置文件。
*   @**Import**：用来导入其他配置类。
*   @**ImportResource**：用来加载 xml 配置文件。
*   @**Repository**：使用 @Repository 注解可以确保 DAO 或者 repositories 提供异常转译，这个注解修饰的 DAO 或者 repositories 类会被 ComponetScan 发现并配置，同时也不需要为它们提供 XML 配置项。
*   @**Bean**：用 @Bean 标注方法等价于 XML 中配置的 bean
*   @**AutoWired**：自动导入依赖的 bean。byType 方式。把配置好的 Bean 拿来用，完成属性、方法的组装，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。当加上（required=false）时，就算找不到 bean 也不报错。
*   @**Qualifier**：当有多个同一类型的 Bean 时，可以用 @Qualifier(“name”) 来指定。与 @Autowired 配合使用。@Qualifier 限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者，具体使用方式如下：
*   @**Resource**(name=”name”,type=”type”)：没有括号内内容的话，默认 byName。与 @Autowired 干类似的事。

**SpringMVC 的工作原理：**

![](https://img-blog.csdnimg.cn/20200411183049843.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

**SpringBoot 框架的优点:**

![](https://img-blog.csdnimg.cn/20200411183206954.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2phdmFfd3hpZA==,size_16,color_FFFFFF,t_70)

*   -- 创建独立的 Spring 应用程序 ；
*   -- 嵌入的 Tomcat、 Jetty 或者 Undertow，无须部署 WAR 文件：
*   -- 允许通过 Maven 来根据需要获取 starter;
*   -- 尽可能地自动配置 Spring;
*   -- 提供生产就绪型功能，如指标、健康检查和外部配置；
*   -- 绝对没有代码生成，对 XML 没有要求配置。

**MyBatis 框架的优点：**

*   JDBC 相比，减少了 50% 以上的代码量，消除了 JDBC 大量冗余的代码，不需要手动开关连接
*   很好的与各种数据库兼容（因为 MyBatis 使用 JDBC 来连接数据库，所以只要 JDBC 支持的数据库 MyBatis 都支持，而 JDBC 提供了可扩展性，所以只要这个数据库有针对 Java 的 jar 包就可以就可以与 MyBatis 兼容），开发人员不需要考虑数据库的差异性。
*   提供了很多第三方插件（分页插件 / 逆向工程）
*   SQL 写在 XML 里，从程序代码中彻底分离，解除 sql 与程序代码的耦合，便于统一管理和优化，并可重用。
*   提供映射标签，支持对象与数据库的 ORM 字段关系映射。