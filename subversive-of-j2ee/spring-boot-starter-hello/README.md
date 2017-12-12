# 自定义Spring Boot Starter
若使自动配置生效，需要注册自动配置类，需要在src/main/resources下新建META-INF/spring.factories。
若有多个自动配置，则用','隔开，换行显示使用'\'。

在application.properties中添加debug属性，在控制台查看相应bean自动配置报告。

## @ConditionalOnBean：当容器里有指定的Bean的条件下。

## @ConditionalOnClass：当类路径下有指定的类的条件下。

## @ConditionalOnExpression：基于SpEL表达式作为判断条件。

## @ConditionalOnJava：基于JVM版本作为判断条件。

## @ConditionalOnJndi：在JNDI存在的条件下查找指定的位置。

## @ConditionalOnMissingBean：当容器里没有指定Bean的情况下。

## @ConditionalOnMissingClass：当类路径下没有指定的类的条件下。

## @ConditionalOnNotWebApplication：当前项目不是Web项目的条件下。

## @ConditionalOnProperty：指定的属性是否有指定的值。
### prefix 定义文件中的属性前缀，如果没有指定连接字符，'.'作为默认。
A prefix that should be applied to each property. The prefix automatically ends with a dot if not specified.

### value 配合prefix定义测试文件中的属性值，当设置value后，如果matchIfMissing值为false，那么prefix.value值必须定义，否则启动会报异常(Bean method 'helloService' not loaded because @ConditionalOnProperty (hello.enabled) did not find property 'enabled')。
The name of the properties to test. If a prefix has been defined, it is applied to compute the full key of each property. For instance if the prefix is app.config and one value is my-value, the fully key would be app.config.my-value
Use the dashed notation to specify each property, that is all lower case with a "-" to separate words (e.g. my-long-property).

### matchIfMissing 默认值为false，当文件中没有设置prefix.value时，返回false。
Specify if the condition should match if the property is not set. Defaults to false.

## @ConditionalOnResource：类路径是否有指定的值。

## @ConditionalOnSingleCandidate：当指定Bean在容器中只有一个，或者虽然有多个但是指定首选的Bean。

## @ConditionalOnWebApplication：当前项目是Web项目的条件下。