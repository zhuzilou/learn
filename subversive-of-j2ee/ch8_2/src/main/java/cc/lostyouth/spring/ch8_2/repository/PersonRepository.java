package cc.lostyouth.spring.ch8_2.repository;

import cc.lostyouth.spring.ch8_2.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by endless on 12/4/2017.
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * 使用单个方法名查询，接受一个name参数，返回值为列表（可以返回单个对象，根据参数是否唯一决定）。
     *
     * @param name
     * @return
     */
    List<Person> findByAddress(String name);

    /**
     * 使用多个方法名查询，接受name和address参数，返回值为单个对象。
     *
     * @param name
     * @param address
     * @return
     */
    Person findByNameAndAddress(String name, String address);

    /**
     * 使用@Query查询，参数按照名称绑定。
     *
     * @param name
     * @param address
     * @return
     */
    @Query("select p from Person p where p.name= :name and p.address= :address")
    Person withNameAndAddressQuery(@Param("name") String name, @Param("address") String address);

    /**
     * 使用@NamedQuery查询，注意在裸体类中做的@NamedQuery的定义{@link Person}。
     *
     * @param name
     * @param address
     * @return
     */
    List<Person> withNameAndAddressNamedQuery(String name, String address);
}
