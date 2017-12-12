package cc.lostyouth.spring.ch8_4.repository;


import cc.lostyouth.spring.ch8_4.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by endless on 12/12/2017.
 */
public interface PersonRepo extends JpaRepository<Person, Long> {
}
