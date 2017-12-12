package cc.lostyouth.spring.highlight_spring4.ch03.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 11/3/2017.
 */
public class TaskExecutorTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        //当i的范围超过线程池最大的维护数量时，会引发TaskRejectedException异常。
        for (int i = 0; i < 20; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        context.close();
    }
}
