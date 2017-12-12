package cc.lostyouth.spring.highlight_springmvc4.web.ch4_6;

import cc.lostyouth.spring.highlight_springmvc4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by endless on 11/15/2017.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/testRest", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String testRest() {
        return demoService.saySomething();
    }
}
