package cc.lostyouth.learn.ch8_6_1.web;

import cc.lostyouth.learn.ch8_6_1.domain.Location;
import cc.lostyouth.learn.ch8_6_1.domain.Person;
import cc.lostyouth.learn.ch8_6_1.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by endless on 12/18/2017.
 */
@RestController
public class DataController {
    @Autowired
    PersonRepo personRepo;

    @RequestMapping("/save")
    public Person save() {
        Person p = new Person("wyf", 32);
        Collection<Location> locations = new LinkedHashSet<>();
        Location loc1 = new Location("上海", "2009");
        Location loc2 = new Location(" 合 肥", " 2010");
        Location loc3 = new Location(" 广 州", " 2011");
        Location loc4 = new Location(" 马 鞍 山", " 2012");
        locations.add(loc1);
        locations.add(loc2);
        locations.add(loc3);
        locations.add(loc4);
        p.setLocations(locations);
        return personRepo.save(p);
    }

    @RequestMapping("/q1")
    public Person q1(String name) {
        return personRepo.findByName(name);
    }

    @RequestMapping("/q2")
    public List<Person> q2(Integer age) {
        return personRepo.withQueryFindByAge(age);
    }
}
