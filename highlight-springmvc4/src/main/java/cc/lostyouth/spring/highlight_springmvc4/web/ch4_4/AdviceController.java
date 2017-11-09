package cc.lostyouth.spring.highlight_springmvc4.web.ch4_4;

import cc.lostyouth.spring.highlight_springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by endless on 11/8/2017.
 */
@Controller
@RequestMapping("/advice")
public class AdviceController {
    @RequestMapping
    public String getSomething(@ModelAttribute("msg") String msg) {
        throw new IllegalArgumentException("非常抱歉，参数有误" + "来自@ModelAttribute：" + msg);
    }

    /**
     * <p>当请求路径为http://localhost:18080/hightlight-springmvc4/advice/modelattribute?id=1&name=xx，使用路径中的参数值。</p>
     * <p>当请求路径为http://localhost:18080/hightlight-springmvc4/advice/modelattribute，使用建言中定义的默认值。</p>
     *
     * @param demoObj
     * @return
     */
    @RequestMapping("/modelattribute")
    public String modeattribute(@ModelAttribute("demoObj") DemoObj demoObj) {
        return "modelattribute";
    }
}
