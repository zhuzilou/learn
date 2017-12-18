package cc.lostyouth.learn.ch8_6_2.web;

import cc.lostyouth.learn.ch8_6_2.domain.Person;
import cc.lostyouth.learn.ch8_6_2.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by endless on 12/18/2017.
 */
@RestController
public class DataController {
    @Autowired
    PersonRepo personRepo;

    @RequestMapping("/set")
    public void set() {
        Person person = new Person("1", "wyf", 32);
        personRepo.save(person);
        personRepo.stringRedisTemplateDemo();
    }

    @RequestMapping("/getStr")
    public String getStr() {
        return personRepo.getString();
    }

    @RequestMapping("/getPerson")
    public Person getPerson() {
        return personRepo.getPerson();
    }
}
