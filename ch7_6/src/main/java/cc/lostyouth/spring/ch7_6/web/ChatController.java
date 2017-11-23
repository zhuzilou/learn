package cc.lostyouth.spring.ch7_6.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by endless on 11/23/2017.
 */
@Controller
public class ChatController {
    //通过SimpMessagingTemplate向浏览器发送消息
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 在Spring MVC中，可以直接在参数中获得principal，principal中包含当前用户的信息。
     *
     * @param principal
     * @param msg
     */
    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        if ("wyf".equals(principal.getName())) {
            messagingTemplate.convertAndSendToUser("wisely",
                    "/queue/notifications", principal.getName() + "-send: " + msg);
        } else {
            messagingTemplate.convertAndSendToUser("wyf",
                    "/queue/notifications", principal.getName() + "-send: " + msg);
        }
    }
}
