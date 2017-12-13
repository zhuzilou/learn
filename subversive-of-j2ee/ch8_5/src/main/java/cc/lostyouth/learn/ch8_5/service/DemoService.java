package cc.lostyouth.learn.ch8_5.service;

import cc.lostyouth.learn.ch8_5.domain.Person;

/**
 * Created by endless on 12/13/2017.
 */
public interface DemoService {
    Person save(Person person);

    void remove(Long id);

    Person findOne(Person person);
}
