package cc.lostyouth.spring.ch8_2.web;

import cc.lostyouth.spring.ch8_2.domain.Person;
import cc.lostyouth.spring.ch8_2.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by endless on 12/4/2017.
 */
@RestController
public class DataController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/save")
    public Person save(String name, String address, Integer age) {
        Person people = personRepository.save(new Person(null, name, address, age));
        return people;
    }

    @RequestMapping("/q1")
    public List<Person> q1(String address) {
        List<Person> peoples = personRepository.findByAddress(address);
        return peoples;
    }

    @RequestMapping("/q2")
    public Person q2(String name, String address) {
        Person people = personRepository.findByNameAndAddress(name, address);
        return people;
    }

    @RequestMapping("/q3")
    public Person q3(String name, String address) {
        Person people = personRepository.withNameAndAddressQuery(name, address);
        return people;
    }

    @RequestMapping("/q4")
    public List<Person> q4(String name, String address) {
        List<Person> peoples = personRepository.withNameAndAddressNamedQuery(name, address);
        return peoples;
    }

    /**
     * 根据参数排序
     *
     * @return
     */
    @RequestMapping("/sort")
    public List<Person> sort() {
        List<Person> peoples = personRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return peoples;
    }

    /**
     * 使用默认的分页查询，该方法不支持条件查询后分页，需要自定义实现。
     *
     * @return
     */
    @RequestMapping("/page")
    public Page<Person> page() {
        Page<Person> pagePeoples = personRepository.findAll(new PageRequest(1, 2));
        return pagePeoples;
    }
}
