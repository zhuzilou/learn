package cc.lostyouth.spring.highlight_springmvc4.web.ch4_6;

import cc.lostyouth.spring.highlight_springmvc4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by endless on 11/15/2017.
 */
@Controller
public class NormalController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/normal")
    public String testPage(Model model) {
        model.addAttribute("msg", demoService.saySomething());
        return "page";
    }
}
