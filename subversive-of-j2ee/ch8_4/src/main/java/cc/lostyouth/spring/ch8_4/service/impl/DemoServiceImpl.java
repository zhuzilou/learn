package cc.lostyouth.spring.ch8_4.service.impl;

import cc.lostyouth.spring.ch8_4.domain.Person;
import cc.lostyouth.spring.ch8_4.repository.PersonRepo;
import cc.lostyouth.spring.ch8_4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by endless on 12/12/2017.
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    PersonRepo personRepo;

    @Transactional(rollbackFor = {IllegalArgumentException.class})
    @Override
    public Person savePersonWithRollBack(Person person) {
        Person p = personRepo.save(person);
        if ("汪云飞".equals(person.getName())) {
            throw new IllegalArgumentException("汪云飞已存在，数据将回滚。");
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class})
    @Override
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepo.save(person);
        if ("汪云飞".equals(person.getName())) {
            throw new IllegalArgumentException("汪云飞虽已存在，数据将不会回滚。");
        }
        return p;
    }
}
