package cc.lostyouth.spring.highlight_spring4.ch02.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by endless on 10/31/2017.
 */
@Component
public class DemoPublisher {
    /**
     * 注入ApplicationContext用来发布事件
     */
    @Autowired
    ApplicationContext applicationContext;

    public void publish(String msg) {
        //使用ApplicationContext的publishEvent方法来发布
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }
}
