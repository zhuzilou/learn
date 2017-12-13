package cc.lostyouth.learn.ch8_5.repository;


import cc.lostyouth.learn.ch8_5.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by endless on 12/12/2017.
 */
public interface PersonRepo extends JpaRepository<Person, Long> {
}
