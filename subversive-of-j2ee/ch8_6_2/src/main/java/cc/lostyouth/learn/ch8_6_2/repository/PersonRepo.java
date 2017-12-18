package cc.lostyouth.learn.ch8_6_2.repository;

import cc.lostyouth.learn.ch8_6_2.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by endless on 12/18/2017.
 */
@Repository
public class PersonRepo {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * idea这里会报error，'Could not autowire...'，实际在{@link org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.RedisConfiguration}中自动配置，不影响项目运行。
     */
    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valOpsStr;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    /**
     * idea这里会报error，同valOpsStr。
     */
    @Resource(name = "redisTemplate")
    ValueOperations<Object, Object> valOps;

    public void stringRedisTemplateDemo() {
        valOpsStr.set("xx", "yy");
    }

    public void save(Person person) {
        valOps.set(person.getId(), person);
    }

    public String getString() {
        return valOpsStr.get("xx");
    }

    public Person getPerson() {
        return (Person) valOps.get("1");
    }
}
