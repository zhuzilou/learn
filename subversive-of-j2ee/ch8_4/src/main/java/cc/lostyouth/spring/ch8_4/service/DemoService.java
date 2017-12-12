package cc.lostyouth.spring.ch8_4.service;

import cc.lostyouth.spring.ch8_4.domain.Person;

/**
 * Created by endless on 12/12/2017.
 */
public interface DemoService {
    Person savePersonWithRollBack(Person person);

    Person savePersonWithoutRollBack(Person person);
}
