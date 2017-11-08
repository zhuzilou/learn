package cc.lostyouth.spring.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by endless on 11/7/2017.
 */
//利用@Controller注解声明是一个控制器。
@Controller
public class HelloController {
    //利用@RequestMapping配置URL和方法之间的映射。
    @RequestMapping("/index")
    public String hello() {
        //通过ViewResolver的Bean配置，返回值为index，说明我们的页面放置的路径为/WEB-INF/classes/views/index.jsp
        return "index";
    }
}
