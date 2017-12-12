package cc.lostyouth.spring.ch8_4.web;

import cc.lostyouth.spring.ch8_4.domain.Person;
import cc.lostyouth.spring.ch8_4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by endless on 12/12/2017.
 */
@RestController
public class MyController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/rollback")
    public Person rollback(Person person) {
        return demoService.savePersonWithRollBack(person);
    }

    @RequestMapping("/norollback")
    public Person noRollback(Person person) {
        return demoService.savePersonWithoutRollBack(person);
    }
}
