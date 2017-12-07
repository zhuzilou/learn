package cc.lostyouth.spring.ch8_2.support;

import cc.lostyouth.spring.ch8_2.specs.CustomerSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * <p>继承JapRepository的实现类SimpleJapRepository，可以使用SimpleJpaRepository的方法。</p>
 * Created by endless on 12/6/2017.
 */
public class CustomRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements CustomRepository<T, ID> {
    private final EntityManager entityManager;

    public CustomRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }

    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        return findAll(CustomerSpecs.byAuto(entityManager, example), pageable);
    }

    /**
     * 这不是一个好的示例，实现在PersonRepositoryImpl中更为合适，这里仅仅为了演示使用EntityManager。
     *
     * @return
     */
    @Override
    public List<T> findByCustomEm() {
        return entityManager.createQuery("select p.address, count(p.age) as 统计 from Person p group by p.address, p.age having p.age < 30").getResultList();
    }
}
