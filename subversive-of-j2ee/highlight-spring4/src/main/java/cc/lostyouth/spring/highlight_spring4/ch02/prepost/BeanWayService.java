package cc.lostyouth.spring.highlight_spring4.ch02.prepost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Java配置方式：使用@Bean的initMethod和destroyMethod（相当于xml配置的init-method和destroy-method）</p>
 * Created by endless on 10/31/2017.
 */
public class BeanWayService {
    private static final Logger LOG = LoggerFactory.getLogger(BeanWayService.class);

    public void init() {
        LOG.info("@Bean-init-method");
    }

    public BeanWayService() {
        super();
        LOG.info("初始化构造函数-BeanWayService");
    }

    public void destroy() {
        LOG.info("@Bean-destroy-method");
    }
}
