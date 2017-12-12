package cc.lostyouth.spring.spring_boot_starter_hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by endless on 11/16/2017.
 */
@Configuration
//开启属性注入
@EnableConfigurationProperties(HelloServiceProperties.class)
//判断HelloService这个类在类路径中是否存在
@ConditionalOnClass(HelloService.class)
//指定的属性是否有指定的值
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true)
public class HelloServiceAutoConfiguration {
    @Autowired
    private HelloServiceProperties helloServiceProperties;

    @Bean
    //当容器中没有这个Bean的情况下自动配置这个Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        //设置默认值
        helloService.setMsg(helloServiceProperties.getMsg());
        return helloService;
    }
}
