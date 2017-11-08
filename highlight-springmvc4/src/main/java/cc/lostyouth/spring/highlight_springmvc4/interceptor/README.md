# 拦截器配置
## 定义拦截器
1. 继承HandlerInterceptorAdapter类来实现自定义配置Bean；
2. 重写preHandle方法，在请求发生前执行；
3. 重写postHandle方法，在请求完成后执行。
cc.lostyouth.spring.highlight_springmvc4.interceptor.DemoInterceptor

## 注册拦截器
1. 在继承WebMvcConfigurerAdapter的配置类中配置拦截器的Bean；
2. 重写addInterceptors方法，注册拦截器。
cc.lostyouth.spring.highlight_springmvc4.MyMvcConfig#demoInterceptor
cc.lostyouth.spring.highlight_springmvc4.MyMvcConfig#addInterceptors