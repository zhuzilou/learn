package cc.lostyouth.spring.highlight_spring4.ch01.di;

import org.springframework.stereotype.Service;

/**
 * <p>使用@Service注解声明当前FunctionService类是Spring管理的一个Bean。其中，使用@Component、@Service、@Repository
 * 和@Controller是等效的，可根据需要选用。</p>
 * Created by endless on 10/17/2017.
 */
@Service
public class FunctionService {
    public String sayHello(String word) {
        return "Hello " + word + "!";
    }
}
