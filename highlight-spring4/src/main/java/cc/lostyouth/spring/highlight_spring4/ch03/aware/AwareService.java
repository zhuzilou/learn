package cc.lostyouth.spring.highlight_spring4.ch03.aware;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

/**
 * <p>实现BeanNamAware、ResourceLoaderAware接口，获得Bean名称和资源加载的服务。</p>
 * <p>
 * <ol>
 * <li>BeanNameAware: 获得容器中的Bean的名称。</li>
 * <li>BeanFactoryAware: 获得当前bean factory，这样可以调用容器的服务。</li>
 * <li>ApplicationContextAware: 当前的application context，这样可以调用容器的服务。</li>
 * <li>MessageSourceAware: 获得message source，这样可以获得文本信息。</li>
 * <li>ApplicationEventPublisherAware: 应用事件发布器，可以发布事件。2.5的{@link cc.lostyouth.spring.highlight_spring4.ch02.event.DemoPublisher}也可实现这个接口来发布事件。</li>
 * <li>ResourceLoaderAware: 获得资源加载器，可以获得外部资源文件。</li>
 * </ol>
 * </p>
 * Created by endless on 11/1/2017.
 */
@Service
public class AwareService implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, MessageSourceAware, ApplicationEventPublisherAware, ResourceLoaderAware {
    private static final Logger LOG = LoggerFactory.getLogger(AwareService.class);
    private String beanName;
    private ResourceLoader loader;
    private BeanFactory beanFactory;
    private ApplicationContext context;
    private MessageSource msgSource;

    public void outputResult() {
        //BeanNameAware
        LOG.info("Bean的名称为：" + beanName);
        //BeanFactoryAware
        AwareService getFromFactory = beanFactory.getBean(AwareService.class);
        LOG.info("校验本类与BeanFactory中的Bean是否一致: " + this.equals(getFromFactory));
        //ApplicationContextAware
        AwareService getFromContext = context.getBean(AwareService.class);
        LOG.info("校验本类与ApplicationContext中的Bean是否一致: " + this.equals(getFromContext));
        //MessageSourceAware
        LOG.info(msgSource.getMessage("aware.message", null, Locale.ENGLISH));
        LOG.info(msgSource.getMessage("aware.message", null, Locale.CHINA));
        //ResourceLoaderAware
        Resource resource = loader.getResource("classpath:aware.txt");
        try {
            LOG.info("ResourceLoader加载的文件内容为：" + IOUtils.toString(resource.getInputStream(), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        //未实现，参考cc.lostyouth.spring.highlight_spring4.ch02.event.DemoPublisher
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.msgSource = messageSource;
    }
}
