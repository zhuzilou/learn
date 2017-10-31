package cc.lostyouth.spring.highlight_spring4.ch02.prepost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <p>注解方式：利用JSR-250的@PostConstruct和@PreDestroy</p>
 * Created by endless on 10/31/2017.
 */
public class JSR250WayService {
    private static final Logger LOG = LoggerFactory.getLogger(JSR250WayService.class);

    //在构造函数执行完之后执行
    @PostConstruct
    public void init() {
        LOG.info("jsr250-init-method");
    }

    public JSR250WayService() {
        super();
        LOG.info("初始化构造函数-JSR250WayService");
    }

    //在Bean销毁之前执行
    @PreDestroy
    public void destroy() {
        LOG.info("jsr250-destroy-method");
    }
}
