package cc.lostyouth.spring.highlight_spring4.ch02.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 10/17/2017.
 */
public class ElTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);
        ElConfig resourceService = context.getBean(ElConfig.class);
        resourceService.outputResource();
        context.close();
    }
}
