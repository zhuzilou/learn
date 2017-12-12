package cc.lostyouth.spring.highlight_spring4.ch03.aware;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * <p>
 * 使用MessageSource时，需要手动配置MessageSource
 * </p>
 * Created by endless on 11/1/2017.
 */
@Configuration
@ComponentScan("cc.lostyouth.spring.highlight_spring4.ch03.aware")
public class AwareConfig {
    @Bean
    public MessageSource messageSource() {
        //使用ResourceBundleMessageSource
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        //使用ResourceBundleMessageSource实现时，会试图在根路径的属性文件中解析信息。
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    public MessageSource messageSourceExternal() {
        //使用ReloadableResourceBundleMessageSource，工作方式与ResourceBundleMessageSource，但是它能够重新加载信息属性，而不必重新编译或重启应用。
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //basename属性可以设置为在类路径下（以classpath:作为前缀）、文件系统中（以file:作为前缀）或Web应用的根路径下（没有前缀）查找属性。
        messageSource.setBasename("classpath://messages");
        //设置缓存时间
        messageSource.setCacheSeconds(1000);
        return messageSource;
    }
}
