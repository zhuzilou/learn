package cc.lostyouth.spring.highlight_spring4.ch02.profile;

/**
 * Created by endless on 10/31/2017.
 */
public class DemoProfileBean {
    private String content;

    public DemoProfileBean(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
