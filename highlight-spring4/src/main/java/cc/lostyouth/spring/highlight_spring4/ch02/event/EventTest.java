package cc.lostyouth.spring.highlight_spring4.ch02.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>Spring的事件（Application Event）为Bean与Bean之间的消息通信提供了支持。当一个Bean处理完一个任务之后，希望另外一Bean
 * 知道并能做相应的处理，这时我们就需要让另外一个Bean监听当前Bean所发送的事件。</p>
 * <p>
 * Spring的事件需要遵循如下流程：
 * <ol>
 * <li>自定义事件，继承ApplicationEvent。{@link DemoEvent}</li>
 * <li>定义事件监听器，实现ApplicationListener。{@link DemoEventListener}</li>
 * <li>使用容器发布事件。{@link DemoPublisher}</li>
 * </ol>
 * </p>
 * Created by endless on 10/31/2017.
 */
public class EventTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher demoPublisher = context.getBean(DemoPublisher.class);
        demoPublisher.publish("Hello application event.");
        context.close();
    }
}
