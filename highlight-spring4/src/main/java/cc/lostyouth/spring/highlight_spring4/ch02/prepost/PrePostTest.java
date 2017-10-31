package cc.lostyouth.spring.highlight_spring4.ch02.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 10/31/2017.
 */
public class PrePostTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);
        BeanWayService beanWayService = context.getBean(BeanWayService.class);
        JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);
        context.close();
    }
}
