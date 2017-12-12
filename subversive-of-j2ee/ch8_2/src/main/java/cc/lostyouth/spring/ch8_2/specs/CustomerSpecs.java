package cc.lostyouth.spring.ch8_2.specs;

import com.google.common.collect.Iterables;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>结合Specification和自定义Repository实现来定制一个自动模糊查询</p>
 * <p>对于任意的实体对象进行查询，对象里有几个值就查几个值，当值为字符型时我们就自动like查询，其余的类型使用自动等于查询，没有值就查询全部。</p>
 * Created by endless on 12/4/2017.
 */
public class CustomerSpecs {
    public static <T> Specification<T> byAuto(final EntityManager entityManager, final T example) {
        //获得当前实体类对象的类型
        final Class<T> type = (Class<T>) example.getClass();
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //新建Predicate列表存储构造的查询条件
                List<Predicate> predicates = new ArrayList<>();
                //获得实体类的EntityType，可以从EntityType获得实体类的属性。
                EntityType<T> entity = entityManager.getMetamodel().entity(type);
                //对实体类的所有属性做循环
                for (Attribute<T, ?> attr : entity.getDeclaredAttributes()) {
                    //获得实体类对象某一个属性的值
                    Object attrValue = getValue(example, attr);
                    if (null != attrValue) {
                        //当前属性值为字符类型的时候
                        if (String.class == attr.getJavaType()) {
                            //若当前字符不为空的情况下
                            if (!StringUtils.isEmpty(attrValue)) {
                                //构造like属性值查询条件，并添加到条件列表中。
                                predicates.add(cb.like(root.get(attribute(entity, attr.getName(), String.class)), pattern((String) attrValue)));
                            }
                        } else {
                            //其余情况下，构造equal查询条件，并添加到条件列表中。
                            predicates.add(cb.equal(root.get(attribute(entity, attr.getName(), attrValue.getClass())), attrValue));
                        }
                    }
                }
                //将条件列表转换成Predicate
                return predicates.isEmpty() ? cb.conjunction() : cb.and(Iterables.toArray(predicates, Predicate.class));
            }

            /**
             * 通过反射获得实体类对象对应属性的属性值
             * @param example
             * @param attr
             * @param <T>
             * @return
             */
            private <T> Object getValue(T example, Attribute<T, ?> attr) {
                return ReflectionUtils.getField((Field) attr.getJavaMember(), example);
            }

            /**
             * 获得实体类的当前属性的SingularAttribute，SingularAttribute包含的是实体类的某个单独属性。
             * @param entity
             * @param fieldName
             * @param fieldClass
             * @param <E>
             * @param <T>
             * @return
             */
            private <E, T> SingularAttribute<T, E> attribute(EntityType<T> entity, String fieldName, Class<E> fieldClass) {
                return entity.getDeclaredSingularAttribute(fieldName, fieldClass);
            }
        };
    }

    /**
     * 构造like的查询模式，即前后加%。
     *
     * @param str
     * @return
     */
    static private String pattern(String str) {
        return "%" + str + "%";
    }
}
