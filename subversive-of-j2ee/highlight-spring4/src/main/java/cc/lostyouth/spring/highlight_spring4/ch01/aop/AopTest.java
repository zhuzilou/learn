package cc.lostyouth.spring.highlight_spring4.ch01.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 10/25/2017.
 */
public class AopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
        DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
        demoAnnotationService.add();
        demoMethodService.add();
        context.close();
    }
}
