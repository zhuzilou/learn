package cc.lostyouth.spring.ch8_3.repository;

import cc.lostyouth.spring.ch8_3.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by endless on 12/12/2017.
 */
//String Data REST默认节点路径是在实体类（Person）之后加“s”来形成路径，修改映射的话通过@RepositoryRestResource注解的path属性。
@RepositoryRestResource(path = "people")
public interface PersonRepo extends JpaRepository<Person, Long> {
    @RestResource(path = "nameStartsWith", rel = "nameStartsWith")
    Person findByNameStartsWith(@Param("name") String name);
}
