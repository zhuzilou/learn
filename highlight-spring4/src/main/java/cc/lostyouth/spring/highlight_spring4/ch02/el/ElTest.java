package cc.lostyouth.spring.highlight_spring4.ch02.el;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 10/17/2017.
 */
public class ElTest {
    private static final Logger LOG = LoggerFactory.getLogger(ElTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);
        ElConfig resourceService = context.getBean(ElConfig.class);
        resourceService.outputResource();
        DemoService demoService = context.getBean(DemoService.class);
        LOG.info("DemoService use init value: " + demoService.getAnother());
        demoService.setAnother("Change attribute 'another' value.");
        LOG.info(demoService.getAnother());
        context.close();
    }
}
