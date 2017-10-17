package cc.lostyouth.spring.highlight_spring4.ch01.aop;

import org.springframework.stereotype.Service;

/**
 * Created by endless on 10/17/2017.
 */
@Service
public class DemoAnnotationService {
    @Action(name="注解式拦截的add操作")
    public void add() {}
}
