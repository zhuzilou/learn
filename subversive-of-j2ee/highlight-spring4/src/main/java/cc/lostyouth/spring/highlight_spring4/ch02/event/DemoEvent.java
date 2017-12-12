package cc.lostyouth.spring.highlight_spring4.ch02.event;

import org.springframework.context.ApplicationEvent;

/**
 * <p>自定义事件，继承ApplicationEvent</p>
 * Created by endless on 10/31/2017.
 */
public class DemoEvent extends ApplicationEvent {
    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
