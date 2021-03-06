package cc.lostyouth.spring.highlight_spring4.ch02.el;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.nio.charset.Charset;

/**
 * Created by endless on 10/17/2017.
 */
@Configuration
@ComponentScan("cc.lostyouth.spring.highlight_spring4.ch02.el")
//注入配置文件需要使用@PropertySource指定文件地址
@PropertySource("classpath:el.properties")
public class ElConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ElConfig.class);
    //注入普通字符串
    @Value("I Love You!")
    private String normal;

    //注入操作系统属性
    @Value("#{systemProperties['os.name']}")
    private String osName;

    //注入表达式结果
    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double randomNumber;

    //注入其他Bean属性
    @Value("#{demoService.another}")
    private String fromAnother;

    //注入文件资源
    @Value("classpath:el.txt")
    private Resource testFile;

    //注入网址资源
    @Value("http://www.baidu.com")
    private Resource testUrl;

    /*
     * 注入配置文件
     * 使用@Value注入，则需要配置一个PropertySourcesPlaceholderConfigurer的Bean。
     * 注意@Value("${book.name}")Value使用的是"$"，而不是"#"
     */
    @Value("${book.name}")
    private String bookName;

    //注入Properties还可从Environment中获得
    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigure() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    /* 注入配置文件 End */

    public void outputResource() {
        try {
            LOG.info(normal);
            LOG.info(osName);
            LOG.info(String.valueOf(randomNumber));
            LOG.info(fromAnother);
            LOG.info(IOUtils.toString(testFile.getInputStream(), Charset.forName("UTF-8")));
            LOG.info(bookName);
            LOG.info(environment.getProperty("book.author"));
            //LOG.info(testUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
