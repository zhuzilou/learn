package cc.lostyouth.spring.highlight_springmvc4.web.ch4_3;

import cc.lostyouth.spring.highlight_springmvc4.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by endless on 11/8/2017.
 */
@RestController
@RequestMapping("/rest")
public class DemoRestController {
    @RequestMapping(value = "/getjson", produces = {"application/json;charset=UTF-8"})
    public DemoObj getjson(DemoObj obj) {
        //直接返回对象，对象会自动转换成json
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");
    }

    @RequestMapping(value = "/getxml", produces = {"application/xml;charset=UTF-8"})
    public DemoObj getxml(DemoObj obj) {
        //直接返回对象，对象会自动转换为xml
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");
    }
}
