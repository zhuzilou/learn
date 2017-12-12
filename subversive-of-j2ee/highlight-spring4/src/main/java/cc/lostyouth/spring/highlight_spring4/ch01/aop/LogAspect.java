package cc.lostyouth.spring.highlight_spring4.ch01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * <p>@Self 使用@Before和@After来明确在方法体之前还是之后执行拦截，注解式拦截优先于方法式拦截执行。</p>
 * Created by endless on 10/17/2017.
 */
//通过@Aspect注解声明一个切面
@Aspect
//通过@Component让此切面成为Spring容器管理的Bean
@Component
public class LogAspect {
    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    //通过@Pointcut注解声明切点
    @Pointcut("@annotation(cc.lostyouth.spring.highlight_spring4.ch01.aop.Action)")
    public void annotationPointCut() {
    }

    //通过@After注解声明一个建言，并使用@Pointcut定义的切点。
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //通过反射可获得注解上的属性，然后做日志记录相关的操作，下同。
        Action action = method.getAnnotation(Action.class);
        LOG.info("Action注解式拦截: " + action.name());
    }

    //通过@Before注解声明一个建言，此建言直接使用拦截规则作为参数。
    @After("execution(* cc.lostyouth.spring.highlight_spring4.ch01.aop.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LOG.info("DemoMethodService方法规则拦截: " + method.getName());
    }
}
