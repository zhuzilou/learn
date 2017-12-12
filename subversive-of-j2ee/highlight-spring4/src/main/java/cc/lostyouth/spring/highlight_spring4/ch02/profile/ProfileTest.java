package cc.lostyouth.spring.highlight_spring4.ch02.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 10/31/2017.
 */
public class ProfileTest {
    private static final Logger LOG = LoggerFactory.getLogger(ProfileTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //先将活动的Profile设置为prod
        context.getEnvironment().setActiveProfiles("prod");
        //后置注册Bean配置类，不然会报Bean未定义的错误
        context.register(ProfileConfig.class);
        //刷新容器
        context.refresh();

        DemoProfileBean demoProfileBean = context.getBean(DemoProfileBean.class);
        LOG.info(demoProfileBean.getContent());
        context.close();

        //测试活动的Profile为dev
        context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.register(ProfileConfig.class);
        context.refresh();
        demoProfileBean = context.getBean(DemoProfileBean.class);
        LOG.info(demoProfileBean.getContent());
        context.close();
    }
}
