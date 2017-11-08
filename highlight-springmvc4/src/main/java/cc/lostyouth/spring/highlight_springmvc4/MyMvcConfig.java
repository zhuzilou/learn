package cc.lostyouth.spring.highlight_springmvc4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by endless on 11/7/2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan("cc.lostyouth.spring.highlight_springmvc4")
public class MyMvcConfig {
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
}
