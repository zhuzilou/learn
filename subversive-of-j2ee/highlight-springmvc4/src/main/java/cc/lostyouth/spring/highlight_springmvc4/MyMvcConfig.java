package cc.lostyouth.spring.highlight_springmvc4;

import cc.lostyouth.spring.highlight_springmvc4.interceptor.DemoInterceptor;
import cc.lostyouth.spring.highlight_springmvc4.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created by endless on 11/7/2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan("cc.lostyouth.spring.highlight_springmvc4")
//继承WebMvcConfigurerAdapter类，重写其方法可对Spring MVC进行配置。
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //映射路径和实际页面的位置
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        //配置JSP的ViewResolver
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * 配置静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceLocations指的是文件放置的目录，addResourceHandler指的是对外暴露的访问路径。
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    /**
     * 配置拦截器的Bean
     *
     * @return
     */
    @Bean
    public DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }

    /**
     * 重写addInterceptors方法，注册拦截器。
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }

    /**
     * 快捷的ViewController：对于只是页面跳转不需要业务处理的控制器，用此配置减少冗余代码。
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //此配置可替换HelloController#hello
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        //添加viewController映射页面访问演示页面
        registry.addViewController("/converter").setViewName("/converter");
        registry.addViewController("/sse").setViewName("/sse");
    }

    /**
     * 文件上传配置
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**
     * 注册MyMessageConverter
     *
     * @return
     */
    @Bean
    public MyMessageConverter converter() {
        return new MyMessageConverter();
    }

    /**
     * 仅添加一个自定义的HttpMessageConverter，不覆盖掉Spring MVC默认注册的HttpMessageConverter。
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    /**
     * 重载会覆盖掉Spring MVC默认注册的多个HttpMessageConverter。
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }
}
