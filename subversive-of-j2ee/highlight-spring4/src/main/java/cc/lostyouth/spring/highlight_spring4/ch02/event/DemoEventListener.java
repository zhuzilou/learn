package cc.lostyouth.spring.highlight_spring4.ch02.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p>定义事件监听器，实现ApplicationListener</p>
 * Created by endless on 10/31/2017.
 */
@Component
public class DemoEventListener implements ApplicationListener<DemoEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(DemoEventListener.class);

    /**
     * 使用onApplicationEvent方法对消息进行接受处理
     *
     * @param demoEvent
     */
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {
        String msg = demoEvent.getMsg();
        LOG.info("Bean-demoEventListener接收到了Bean-demoPublisher发布的消息：" + msg);
    }
}
