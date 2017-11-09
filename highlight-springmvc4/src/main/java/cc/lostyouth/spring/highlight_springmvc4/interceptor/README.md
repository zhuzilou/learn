# 拦截器配置
## 定义拦截器
1. 继承HandlerInterceptorAdapter类来实现自定义配置Bean；
2. 重写preHandle方法，在请求发生前执行；
3. 重写postHandle方法，在请求完成后执行。
参见[DemoInterceptor](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/interceptor/DemoInterceptor.java)

## 注册拦截器
1. 在继承WebMvcConfigurerAdapter的配置类中配置拦截器的Bean；[MyMvcConfig#demoInterceptor](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/MyMvcConfig.java)
2. 重写addInterceptors方法，注册拦截器。[MyMvcConfig#addInterceptors](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/MyMvcConfig.java)