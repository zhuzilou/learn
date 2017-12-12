package cc.lostyouth.spring.highlight_spring4.ch01.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <ol>
 *     <li>使用AnnotationConfigApplicationContext作为Spring容器，接受输入一个配置类作为参数。</li>
 *     <li>获得声明配置的UseFunctionService的Bean</li>
 * </ol>
 * Created by endless on 10/17/2017.
 */
public class DiTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.sayHello("di"));
        context.close();
    }
}
