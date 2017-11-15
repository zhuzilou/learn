package cc.lostyouth.spring.highlight_springmvc4.web.ch4_5_3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * <p>基于SSE（Server Send Event服务端发送事件）的服务端推送，需要新式浏览器的支持。</p>
 * Created by endless on 11/15/2017.
 */
@Controller
public class SseController {
    @RequestMapping(value = "/push", produces = "text/event-stream")
    public @ResponseBody
    String push() {
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data: Testing 1,2,3" + r.nextInt() + "\n\n";
    }
}
