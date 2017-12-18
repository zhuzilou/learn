package cc.lostyouth.learn.ch8_6_1.repository;

import cc.lostyouth.learn.ch8_6_1.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by endless on 12/18/2017.
 */
public interface PersonRepo extends MongoRepository<Person, String> {
    Person findByName(String name);

    /**
     * 支持@Query查询，查询参数构造JSON字符串即可。
     *
     * @param age
     * @return
     */
    @Query("{'age': ?0}")
    List<Person> withQueryFindByAge(Integer age);
}
