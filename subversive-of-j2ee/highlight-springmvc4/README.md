# JavaEE开发的颠覆者Spring Boot学习代码--Spring MVC篇
## [Spring MVC 常用注解](https://github.com/zhuzilou/spring-learn/tree/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/web/ch4_3)

## Spring MVC 基本配置
Spring MVC的定制配置需要配置类继承一个WebMvcConfigurerAdapter类，并在此类使用@EnableWebMvc注解， 来开启对
SpringMVC的配置支持。[MyMvcConfig](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/MyMvcConfig.java)

### 静态资源映射
程序的静态文件（js、css、图片等）需要直接访问，这时可以在配置里重写addResourceHandlers方法来实现。
参见[MyMvcConfig#addResourceHandlers](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/MyMvcConfig.java)

## [Spring MVC 拦截器](https://github.com/zhuzilou/spring-learn/tree/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/interceptor)

## [Spring MVC 控制器全局配置](https://github.com/zhuzilou/spring-learn/tree/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/advice)

## [快捷的ViewController](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/MyMvcConfig.java)，针对无任何业务处理，只是简单的页面转向。

## Spring MVC 高级配置

### [文件上传配置](https://github.com/zhuzilou/spring-learn/tree/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/web/ch4_5_1)

### [自定义HttpMessageConverter处理request和response里的数据](https://github.com/zhuzilou/spring-learn/tree/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/messageconverter)

### [服务器端推送技术-SSE](https://github.com/zhuzilou/spring-learn/tree/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/web/ch4_5_3)

## [Spring MVC 测试](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/test/java/cc/lostyouth/spring/highlight_springmvc4/web/ch4_6/TestControllerIntegrationTests.java)
