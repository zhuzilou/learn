package cc.lostyouth.spring.highlight_spring4.ch01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by endless on 10/17/2017.
 */
@Aspect
@Component
public class LogAspect {
    @Pointcut("@annotation(cc.lostyouth.spring.highlight_spring4.ch01.aop.Action)")
    public void annotationPointCut() {}

    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截 " + action.name());
    }
}
