# Spring Boot 集成 MongoDB
配置application.properties时，spring.data.mongodb.uri与spring.data.mongodb.host、spring.data.mongodb.port、
spring.data.mongodb.database只能配置选择其中一种方式指定，不然会莫名提示“either uri or host/port/credentials must be specified”，未研究具体原因。