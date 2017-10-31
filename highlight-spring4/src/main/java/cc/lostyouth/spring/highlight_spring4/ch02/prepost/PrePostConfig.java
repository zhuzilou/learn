package cc.lostyouth.spring.highlight_spring4.ch02.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by endless on 10/31/2017.
 */
@Configuration
@ComponentScan("cc.lostyouth.spring.highlight_spring4.ch02.prepost")
public class PrePostConfig {
    //initMethod和destroyMethod指定BeanWayService类的init和destroy方法在构造之后、Bean销毁之前执行。
    @Bean(initMethod = "init", destroyMethod = "destroy")
    BeanWayService beanWayService() {
        return new BeanWayService();
    }

    @Bean
    JSR250WayService jsr250WayService() {
        return new JSR250WayService();
    }
}
