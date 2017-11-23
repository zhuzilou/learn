# WebSocket
## 基于子协议STOMP的实现
### 广播式
广播式即服务端有消息式，会将消息发送给所有连接了当前endpoint的浏览器。
#### 1. 定义配置类
(1) 继承AbstractWebSocketMessageBrokerConfigurer，实现方法registerStompEndpoints和configureMessageBroker；
(2) registerStompEndpoints定义使用StockJS协议的endpoint，用于前端连接时使用（如 /endpointWisely）；
(3) configureMessageBroker定义广播式消息代理的前缀名称（可配置多个前缀，如 /topic），类似于拦截器中的路径匹配'/*'。
#### 2. 定义控制器
(1) 使用@MessageMapping替代@RequestMapping，定义接收前端消息路径（如 /welcome）；
(2) 使用@SendTo定义控制器消息返回路径（如 /topic/getResponse，此路径应该以广播式消息代理中定义的前缀名为基础），前端需要订阅该路径才能接收到控制器返回的消息。
#### 3. 定义前端，参见[ws.html](https://github.com/zhuzilou/spring-learn/blob/master/ch7_6/src/main/resources/templates/ws.html)
(1) 根据endpoint使用SockJS创建socket；
(2) 使用socket通过STOMP创建client；
(3) 打开client的连接，并订阅控制器中@SendTo的路径，通过回调函数处理控制器返回的消息；
(4) 调用client的send的方法，向控制器中@MessageMapping定义的路径发送消息。