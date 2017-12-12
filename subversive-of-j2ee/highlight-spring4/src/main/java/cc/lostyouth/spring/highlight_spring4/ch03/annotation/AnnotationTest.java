package cc.lostyouth.spring.highlight_spring4.ch03.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 11/6/2017.
 */
public class AnnotationTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoAnnotationConfig.class);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        demoAnnotationService.outputResult();
        context.close();
    }
}
