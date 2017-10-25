package cc.lostyouth.spring.highlight_spring4.ch01.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by endless on 10/25/2017.
 */
@Configuration
@ComponentScan("cc.lostyouth.spring.highlight_spring4.ch01.aop")
//使用@EnableAspectJAutoProxy注解开启Spring对AspectJ代理的支持
@EnableAspectJAutoProxy
public class AopConfig {
}
