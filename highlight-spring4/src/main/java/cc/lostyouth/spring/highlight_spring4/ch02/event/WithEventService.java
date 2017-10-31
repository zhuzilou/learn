package cc.lostyouth.spring.highlight_spring4.ch02.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 * <p>
 * <ol>
 * <li>通过注入{@link DemoPublisher}或者直接使用容器发布事件{@link DemoEvent}，在需要通知其他Bean时调用。</li>
 * <li>在{@link DemoEventListener}监听中获得发布事件{@link DemoEvent}。</li>
 * <li>根据消息或者source做相应处理。</li>
 * </ol>
 * </p>
 * Created by endless on 10/31/2017.
 */
@Service
public class WithEventService {
    private static final Logger LOG = LoggerFactory.getLogger(WithEventService.class);
    @Autowired
    DemoPublisher demoPublisher;

    public void handle() {
        LOG.info("\nHandle business in service. \n...\n...\nHandle over.");
        demoPublisher.publish("Business XXX handle over.");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        WithEventService withEventService = context.getBean(WithEventService.class);
        withEventService.handle();
        context.close();
    }
}
