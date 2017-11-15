package cc.lostyouth.spring.highlight_springmvc4.web.ch4_5_2;

import cc.lostyouth.spring.highlight_springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by endless on 11/9/2017.
 */
@Controller
public class ConverterController {
    @RequestMapping(value = "/convert", produces = {"application/x-wisely"})
    public @ResponseBody
    DemoObj convert(@RequestBody DemoObj demoObj) {
        return demoObj;
    }
}
