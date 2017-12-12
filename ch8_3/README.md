# Spring Data REST
虽然比较简短，但还是很想用一个词来形容 **`Real Niubility`**。
        
        1. 通过添加spring-boot-starter-data-rest依赖构建REST支持；
        2. 创建实体类和对应的Repository，通过访问http://localhost:8080/实体类+s，以JSON结构获得所有该实体对应的数据列表；
        3. 将Repository中自定义的方法暴露为REST资源，需要在方法上添加注解@RestResource(path = "nameStartsWith", rel = "nameStartsWith")；
        4. REST资源根路径通过spring.data.rest.base-path=/api自定义，默认为http://localhost:8080/；
        5. REST资源节点路径通过在对应的Repository上使用@RepositoryRestResource注解中的path属性修改。
## REST请求方式（Postman测试）
        列表：GET，http://localhost:8080/people；
        获取单一对象：GET，http://localhost:8080/people/1，获得id为1的对象；
        查询：GET，http://localhost:8080/people/search/nameStartsWith?name=汪；
        分页：GET，http://localhost:8080/people/?page=1&size=2，page=1即第二页，size=2即每页数量为2，
            同时会给出上一页、下一页、第一页和最后一页的REST资源路径；
        排序：GET，http://localhost:8080/people/?sort=age,desc，即按照age属性倒序；
        保存：POST，http://localhost:8080/people，请求数据类型设置为JSON，内容为{"name":"cc","address":"成都","age": 24}；
        更新：PUT，http://localhost:8080/people/21，根据id更新数据，需要提交更新内容，以JSON格式{"name":"cc2","address":"成都","age": 23}；
        删除：DELETE，http://localhost:8080/people/21。