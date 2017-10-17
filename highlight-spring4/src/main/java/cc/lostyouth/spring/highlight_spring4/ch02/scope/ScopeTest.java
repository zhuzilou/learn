package cc.lostyouth.spring.highlight_spring4.ch02.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <ol>
 *     <li>Singleton: 一个Spring容器中只有一个Bean的实例，此为Spring的默认配置，全容器共享一个实例。</li>
 *     <li>Prototype: 每次调用新建一个Bean的实例。</li>
 *     <li>Request: Web项目中，给每一个http request新建一个Bean实例。</li>
 *     <li>Session: Web项目中，给第一个http session新建一个Bean实例。</li>
 *     <li>GlobalSession: 这个只在portal应用中有用，给每一个global http session新建一个Bean实例。</li>
 * </ol>
 * Created by endless on 10/17/2017.
 */
public class ScopeTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
        DemoSingletonService s1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService s2 = context.getBean(DemoSingletonService.class);
        DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);
        System.out.println("Singleton: s1.equals(s2) = " + s1.equals(s2));
        System.out.println("Prototype: p1.equals(p2) = " + p1.equals(p2));
        context.close();
    }
}
