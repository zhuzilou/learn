# JavaEE开发的颠覆者Spring Boot学习代码--Spring MVC篇
## Spring MVC 常用注解
参见cc.lostyouth.spring.highlight_springmvc4.web.ch4_3

## Spring MVC 基本配置
Spring MVC的定制配置需要配置类继承一个WebMvcConfigurerAdapter类，并在此类使用@EnableWebMvc注解， 来开启对
SpringMVC的配置支持。

### 静态资源映射
程序的静态文件（js、css、图片等）需要直接访问，这时可以在配置里重写addResourceHandlers方法来实现。
参见cc.lostyouth.spring.highlight_springmvc4.MyMvcConfig#addResourceHandlers

## Spring MVC 拦截器
参见cc.lostyouth.spring.highlight_springmvc4.interceptor