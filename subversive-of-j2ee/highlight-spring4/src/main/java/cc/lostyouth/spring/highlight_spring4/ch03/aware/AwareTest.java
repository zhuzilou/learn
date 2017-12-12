package cc.lostyouth.spring.highlight_spring4.ch03.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 11/1/2017.
 */
public class AwareTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);
        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();
        context.close();
    }
}
