package cc.lostyouth.learn.ch8_5.service.impl;

import cc.lostyouth.learn.ch8_5.domain.Person;
import cc.lostyouth.learn.ch8_5.repository.PersonRepo;
import cc.lostyouth.learn.ch8_5.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by endless on 12/13/2017.
 */
@Service
public class DemoServiceImpl implements DemoService {
    private static final Logger LOG = LoggerFactory.getLogger(DemoServiceImpl.class);
    @Autowired
    PersonRepo personRepo;

    @Override
    //缓存新增的或更新的数据到缓存，其中缓存名称为people，数据key是person的id。
    @CachePut(value = "people", key = "#person.id")
    public Person save(Person person) {
        Person p = personRepo.save(person);
        LOG.info("为id、key为：" + p.getId() + "数据做了缓存");
        return p;
    }

    @Override
    //从缓存people中删除key为id的数据
    @CacheEvict(value = "people")
    public void remove(Long id) {
        LOG.info("删除了id、key为" + id + "的缓存");
        personRepo.delete(id);
    }

    @Override
    //在方法执行前先查看缓存people中是否有key为person.id的数据，如果有数据直接返回，如果没有数据，调用方法并将返回值放进缓存中。
    // （书中解释缓存key为person的id数据到缓存people中）
    @Cacheable(value = "people", key = "#person.id")
    public Person findOne(Person person) {
        Person p = personRepo.findOne(person.getId());
        if (null != p) {
            LOG.info("为id、key为：" + p.getId() + "数据做了缓存");
        }
        return p;
    }
}
