package cc.lostyouth.spring.highlight_spring4.ch03.taskexecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * ThreadPoolTaskExecutor是一个spring的线程池技术，它是使用jdk中的{@link java.util.concurrent.ThreadPoolExecutor}进行实现。参考<a href='http://www.cnblogs.com/chkk/p/5386356.html'>spring线程池ThreadPoolTaskExecutor</a>。
 * <ol>
 * <li>当池子大小小于corePoolSize，就新建线程，并处理请求；</li>
 * <li>当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理；</li>
 * <li>当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来拒绝处理；</li>
 * <li>当池子的线程数大于corePoolSize，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁。</li>
 * </ol>
 * </p>
 * <p>
 * 优先会创建corePoolSize线程，当继续增加线程时，先放入Queue中，当corePoolSize和Queue都满的时候，就增加创建新线程，当线程达到MaxPoolSize的时候，就会抛出错误{@link org.springframework.core.task.TaskRejectedException}。
 * </p>
 * <p>
 * 另外maxPoolSize的设定如果比系统支持的线程数还要大时，会抛出java.lang.OutOfMemoryError: unable to create new native thread异常。
 * </p>
 * <p>
 * 关于{@link java.util.concurrent.ThreadPoolExecutor} 参考 <a href='http://www.cnblogs.com/kelin1314/archive/2010/03/18/1689408.html'>ThreadPoolExecutor 线程池</a>。
 * </p>
 * Created by endless on 11/2/2017.
 */
@Configuration
@ComponentScan("cc.lostyouth.spring.highlight_spring4.ch03.taskexecutor")
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {
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

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
