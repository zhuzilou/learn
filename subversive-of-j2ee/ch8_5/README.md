# 数据缓存Cache 
## Spring缓存支持
        Spring中定义了org.springframework.cache.CacheManager和org.springframework.cache.Cache接口用来统一不同的
    缓存技术，其中CacheManager是Spring提供的各种缓存技术抽象接口，Cache接口包含缓存的各种操作（增加、删除、获得缓存）。

针对不同的缓存技术，需要实现不同的CacheManager。`（只听说过EhCache和RedisCache）`
* SimpleCacheManager 使用简单的Collection来存储缓存，主要用来测试。
* ConcurrentMapCacheManager 使用ConcurrentMap来存储缓存
* NoOpCacheManager 仅测试用途，不会实际存储缓存。*`与SimpleCacheManager相比有啥区别？？？`*
* EhCacheCacheManager 使用EhCache作为缓存技术
* GuavaCacheManager 使用Google Guava的GuavaCache作为缓存技术
* HazelcastCacheManager 使用Hazelcast作为缓存技术
* JCacheCacheManager 支持JCache（JSR-107）标准的实现作为缓存技术，如Apache Commons JCS
* RedisCacheManager 使用Redis作为缓存技术

### Spring声明式缓存注解
* @Cacheable 在执行方法前Spring先查看缓存中是否有数据，如果有数据，则直接返回缓存数据；若没有数据，调用方法并将方法返回值放进缓存。
* @CachePut 无论怎样，都会将方法的返回值放到缓存中。 @CachePut的属性和@Cacheable保持一致
* @CacheEvict 将一条或多条数据从缓存中删除
* @Caching 可以通过@Caching注解组合多个注解策略在一个方法上
**@Cacheable、@CachePut、@CacheEvict都有value属性，指定的是要使用的缓存名称；key属性指定的是数据在缓存中存储的键。**

### Spring开启声明式缓存支持
1. 注册实现的CacheManager的Bean，根据选择的缓存技术使用相应的实现类；
2. 每种缓存技术都有很多的额外配置，需要实际参考配置；
3. 在配置类上使用@EnableCaching注解开启声明式缓存支持。

## Spring Boot的支持
    Spring Boot自动配置了多个CacheManager的实现，放置在org.springframework.boot.autoconfigure.cache包中。
    
Spring Boot自动配置的CacheManager
* EhCacheCacheConfiguration
* GenericCacheConfiguration 使用Collection
* GuavaCacheConfiguration
* HazelcastCacheConfiguration
* InfinispanCacheConfiguration 使用Infinispan（Spring中并没有此项）
* JCacheCacheConfiguration
* NoOpCacheConfiguration
* RedisCacheConfiguration
* SimpleCacheConfiguration *`使用ConcurrentMap，不同于Spring中的SimpelCacheManager。`*
**在不做任何额外配置的情况下，默认使用的是SimpleCacheConfiguration。**

Spring Boot支持以spring.cache为前缀的属性来配置缓存
* spring.cache.type= # 可选generic, ehcache, hazelcast, infinispan, jcache, redis, guava, simple, none
* spring.cache.cache-name= # 程序启动时创建缓存名称 *`和声明式注解中的属性value有什么区别？？？`*
* spring.cache.ehcache.config= # ehcache配置文件地址
* spring.cache.hazelcast.config= # hazelcast配置文件地址
* spring.cache.infinispan.config= # infinispan配置文件地址
* spring.cache.jcache.config= # jcache配置文件地址
* spring.cache.jcache.provider= # 当多个jcache实现在类路径中的时候，指定jcache实现
* spring.cache.guava.spec # guava.spec *`什么东东？？？`*
**在Spring Boot环境下，使用缓存技术导入相关缓存技术的依赖包，并在配置类使用@EnableCaching注解开启支持。**