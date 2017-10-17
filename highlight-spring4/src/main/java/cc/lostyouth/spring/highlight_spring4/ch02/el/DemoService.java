package cc.lostyouth.spring.highlight_spring4.ch02.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by endless on 10/17/2017.
 */
@Service
public class DemoService {
    @Value("其他类的属性")
    private String another;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
