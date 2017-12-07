package cc.lostyouth.spring.ch8_2.repository;

import cc.lostyouth.spring.ch8_2.domain.Person;
import cc.lostyouth.spring.ch8_2.support.CustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by endless on 12/6/2017.
 */
public interface CustomPersonRepository extends CustomRepository<Person, Long> {
    List<Person> findByAddress(String address);

    Person findByNameAndAddress(String name, String address);

    @Query("select p from Person p where p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    Person withNameAndAddressNamedQuery(String name, String address);

}
