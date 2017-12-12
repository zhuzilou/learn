# WebSocket
[WebSocket原理](https://www.zhihu.com/question/20215561)
## 基于子协议STOMP的实现
### 广播式
广播式即服务端有消息式，会将消息发送给所有连接了当前endpoint的浏览器。
#### 1. 定义配置类，参见[WebSocketConfig.java](https://github.com/zhuzilou/spring-learn/blob/master/ch7_6/src/main/java/cc/lostyouth/spring/ch7_6/WebSocketConfig.java)。
        (1) 继承AbstractWebSocketMessageBrokerConfigurer，实现方法registerStompEndpoints和configureMessageBroker；
        (2) registerStompEndpoints定义使用StockJS协议的endpoint，用于前端连接时使用（如 /endpointWisely）；
        (3) configureMessageBroker定义广播式消息代理的前缀名称（可配置多个前缀，如 /topic），类似于拦截器中的路径匹配'/*'。
#### 2. 定义控制器，参见[WsController.java](https://github.com/zhuzilou/spring-learn/blob/master/ch7_6/src/main/java/cc/lostyouth/spring/ch7_6/web/WsController.java)。
        (1) 使用@MessageMapping替代@RequestMapping，定义接收前端消息路径（如 /welcome）；
        (2) 使用@SendTo定义控制器消息返回路径（如 /topic/getResponse，此路径应该以广播式消息代理中定义的前缀名为基础），前端需要订阅该路径才能接收到控制器返回的消息。
#### 3. 定义前端，参见[ws.html](https://github.com/zhuzilou/spring-learn/blob/master/ch7_6/src/main/resources/templates/ws.html)。
        (1) 根据配置类中定义的endpoint使用SockJS创建socket；
        (2) 使用socket通过STOMP创建client；
        (3) 打开client的连接，并订阅控制器中@SendTo的路径，通过回调函数处理控制器返回的消息；
        (4) 调用client的send的方法，向控制器中@MessageMapping定义的路径发送消息。

### 点对点式（聊天室）
#### 1. 定义配置类，参见[WebSocketConfig.java](https://github.com/zhuzilou/spring-learn/blob/master/ch7_6/src/main/java/cc/lostyouth/spring/ch7_6/WebSocketConfig.java)。
同广播式
#### 2. 定义控制器，参见[ChatController.java](https://github.com/zhuzilou/spring-learn/blob/master/ch7_6/src/main/java/cc/lostyouth/spring/ch7_6/web/ChatController.java)。
        (1) 使用@MessageMapping定义接收浏览器消息路径（如 /chat）；
        (2) 通过SimpMessagingTemplate向浏览器发送消息；
        (3) 由于通过SimpMessagingTemplate.convertAndSendToUser方法发送点对点式消息，需要知道接收者的信息（示例借用Spring Security获取登录用户信息）作为方法的第一个参数。
#### 3. 定义前端，参见[chat.html](https://github.com/zhuzilou/spring-learn/blob/master/ch7_6/src/main/resources/templates/chat.html)
        (1) 根据配置类中定义的endpoint使用SockJS创建socket；
        (2) 使用socket通过STOMP创建client；
        (3) 打开client的连接，并订阅'/user/queue/notifications'路径，
            '/user'是SimpMessagingTemplate中预定义；
            '/queue'定义在配置类中消息代理定义；
            实际订阅路径'/user/user_info/queue/notifications'，'user_info'是控制器中传入到convertAndSendToUser里的用户信息。