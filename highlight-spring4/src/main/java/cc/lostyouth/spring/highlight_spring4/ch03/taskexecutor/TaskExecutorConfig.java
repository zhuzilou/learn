package cc.lostyouth.spring.highlight_spring4.ch03.taskexecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan("cc.lostyouth.spring.highlight_spring4.ch03.taskexecutor")
//利用@EnableAsync注解开启异步任务支持，并通过在实际执行的Bean的方法中使用@Async注解来声明其是一个异步任务。
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {
    /**
     * 配置实现类AsyncConfigurer接口并重写getAsyncExecutor方法，并返回一个ThreadPoolTaskExecutor，这样我们就获得了一个基于线程池TaskExecutor。
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //线程池维护的最小数量
        taskExecutor.setCorePoolSize(5);
        //线程池维护的最大数量
        taskExecutor.setMaxPoolSize(10);
        //等待队列的最大长度
        taskExecutor.setQueueCapacity(25);
        //设置空闲线程结束的超时时间
        taskExecutor.setKeepAliveSeconds((int) TimeUnit.DAYS.toSeconds(1));
        taskExecutor.initialize();
        return taskExecutor;
    }

    /**
     * 用于捕捉调用异步任务抛出的异常
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (throwable, method, params) -> {
            System.out.println("调用异步任务出错了, message：" + throwable.getMessage());
        };
    }
}
