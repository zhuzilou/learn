package cc.lostyouth.spring.ch8_2.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * <p>继承了JapRepository，具备JapRepository所提供的方法；继承了JpaSpecificationExecutor，具备使用Specification的能力。</p>
 * <p>使用@NoRepositoryBean将些Repository从自动装配中排除，通过CustomRepositoryFactoryBean手动装配。</p>
 * Created by endless on 12/6/2017.
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    Page<T> findByAuto(T example, Pageable pageable);

    List<T> findByCustomEm();
}
