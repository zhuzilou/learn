package cc.lostyouth.spring.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by endless on 11/21/2017.
 */
@Configuration
//通过@EnableWebSocketMessageBroker注解开启使用STOMP协议来传输基于代理（message broker）的消息，这时控制器使用@MessageMapping，就像使用@RequestMapping一样。
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    //注册STOMP协议的节点(endpoint)，并映射到指定的URL。
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        //注册一个STOMP的endpoint，并指定使用SockJS协议。
        stompEndpointRegistry.addEndpoint("/endpointWisely").withSockJS();
        //注册点对点式endpoint
        stompEndpointRegistry.addEndpoint("/endpointChat").withSockJS();
    }

    //配置消息代理（Message Broker）
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/topic", "/queue");
    }
}
