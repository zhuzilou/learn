# Spring Data JPA
## 常用CRUD
定义Repository接口类，继承JpaRepository，不需要实现任何语句，即可使用常用的CRUD，满足一般需求。

## 使用Criteria准则查询扩展查询条件
Spring Data JPA实现的某些查询方式不支持添加条件（分页查询），通过实现Specification接口使用Criteria构造自定义查询条件。

        1. 定义查询条件需要实现Specification接口，参见[CustomerSpecs.java](https://github.com/zhuzilou/spring-boot-learn/blob/master/ch8_2/src/main/java/cc/lostyouth/spring/ch8_2/specs/CustomerSpecs.java)；
        2. 定义Repository接口类，必须实现JpaSpecificationExecutor接口，可以查询时使用自定义查询条件作为参数
        使用JpaSpecificationExecutor接口中的方法，参见[CustomRepository.java](https://github.com/zhuzilou/spring-boot-learn/blob/master/ch8_2/src/main/java/cc/lostyouth/spring/ch8_2/support/CustomRepository.java)，
        实现参见[CustomRepositoryImpl.java](https://github.com/zhuzilou/spring-boot-learn/blob/master/ch8_2/src/main/java/cc/lostyouth/spring/ch8_2/support/CustomRepositoryImpl.java)的findByAuto方法；

## 自定义Repository
通过自定义Repository接口，实现个性化数据库操作。

        1. 定义Repository接口类继承CurdRepository(PagingAndSortingRepository或JpaRepository)，
        该类需要注解@NoRepositoryBean指明当前这个接口不是我们领域类的接口，添加自定义数据库操作方法，参见[CustomRepository.java](https://github.com/zhuzilou/spring-boot-learn/blob/master/ch8_2/src/main/java/cc/lostyouth/spring/ch8_2/support/CustomRepository.java)；
        2. 定义Repository接口类的实现，实现类继承JpaRepository默认实现类SimpleJpaRepository可以使用其提供的方法，
        通过借助EntityManager实现自定义数据库操作方法，参见[CustomRepositoryImpl.java](https://github.com/zhuzilou/spring-boot-learn/blob/master/ch8_2/src/main/java/cc/lostyouth/spring/ch8_2/support/CustomRepositoryImpl.java)的findByCustomEm方法；
        3. 定义RepositoryFactoryBean，替代默认RepositoryFactoryBean，注册自定义的Repository的实现，
        即手动注册Bean达到@Repository效果（因为1中标记了@NoRepositoryBean），参见[CustomRepositoryFactoryBean](https://github.com/zhuzilou/spring-boot-learn/blob/master/ch8_2/src/main/java/cc/lostyouth/spring/ch8_2/support/CustomRepositoryFactoryBean.java)。
        4. 开启自定义Repository支持使用@EnableJpaRepositories，通过repositoryFactoryBeanClass来指定FactoryBean，
        参见[Ch82Application](https://github.com/zhuzilou/spring-boot-learn/blob/master/ch8_2/src/main/java/cc/lostyouth/spring/ch8_2/Ch82Application.java)。
    
**`自定义Repository可以与Criteria结合使用`**