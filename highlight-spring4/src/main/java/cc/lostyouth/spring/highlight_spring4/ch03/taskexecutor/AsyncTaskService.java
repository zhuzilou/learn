package cc.lostyouth.spring.highlight_spring4.ch03.taskexecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by endless on 11/3/2017.
 */
@Service
public class AsyncTaskService {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncTaskService.class);

    /**
     * 通过@Async注释表明该方法是个异步方法，如果注解在类级别，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor。
     *
     * @param i
     */
    @Async
    public void executeAsyncTask(Integer i) {
        LOG.info("执行异步任务：" + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        LOG.info("执行异步任务+1：" + i);
    }
}
