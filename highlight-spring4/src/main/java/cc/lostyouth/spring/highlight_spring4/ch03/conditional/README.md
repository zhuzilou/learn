# Spring 条件注解@Conditional
通过满足特定条件来创建特定的Bean，类似于@Profile。
1. 实现org.springframework.context.annotation.Condition接口，重写matches方法来创建特定条件；
2. 配置中不需要扫描相应包体，通过@Conditional注解添加条件类。