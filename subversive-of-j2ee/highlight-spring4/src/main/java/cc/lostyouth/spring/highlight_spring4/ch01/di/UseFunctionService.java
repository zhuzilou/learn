package cc.lostyouth.spring.highlight_spring4.ch01.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <ol>
 *     <li>使用@Service注解声明当前UseFunctionService类是Spring管理的一个Bean。</li>
 *     <li>使用@Autowired将FunctionService的实体Bean注入到UseFunctionService中，让UseFunctionService具备FunctionService的功能，
 *     此处使用JSR-330的@Inject注解或者JSR-250的@Resource注解是等效的。</li>
 * </ol>
 * Created by endless on 10/17/2017.
 */
@Service
public class UseFunctionService {
    @Autowired
    FunctionService functionService;

    public String sayHello(String word) {
        return functionService.sayHello(word);
    }
}
