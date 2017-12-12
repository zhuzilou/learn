package cc.lostyouth.spring.ch8_2.web;

import cc.lostyouth.spring.ch8_2.domain.Person;
import cc.lostyouth.spring.ch8_2.repository.CustomPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>控制器中接受一个Person对象，当Person的name有值时，会自动对name进行like查询（因为name为String类型）；当age有值时，会进行等于查询。</p>
 * <p>当Person中有多个值不为空时，会自动构造多个查询条件；当Person所有值为空的时候，默认查询出所有记录。</p>
 * Created by endless on 12/6/2017.
 */
@RestController
public class CustomDataController {
    @Autowired
    CustomPersonRepository personRepository;

    @RequestMapping("/auto")
    public Page<Person> auto(Person person) {
        Page<Person> pagePeople = personRepository.findByAuto(person, new PageRequest(0, 10));
        return pagePeople;
    }

    @RequestMapping("/em")
    public List<Person> byEm() {
        return personRepository.findByCustomEm();
    }
}
