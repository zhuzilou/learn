# Spring多线程
Spring通过任务执行器（TaskExecutor）来实现多线程和并发编程。使用ThreadPoolTaskExecutor可实现一个基于线程池的TaskExecutor。
1. 配置类中通过@EnableAsync开启对异步任务的支持；
2. 通过在实际执行的Bean的方法中使用@Async注解来声明其是一个异步任务。

## ThreadPoolTaskExecutor 参考[spring线程池ThreadPoolTaskExecutor](http://www.cnblogs.com/chkk/p/5386356.html)
ThreadPoolTaskExecutor是一个spring的线程池技术，它是使用jdk中的java.util.concurrent.ThreadPoolExecutor进行实现。参考spring线程池ThreadPoolTaskExecutor。
### ThreadPoolTaskExecutor的处理流程:
1. 当池子大小小于corePoolSize，就新建线程，并处理请求；
2. 当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理；
3. 当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来拒绝处理；
4. 当池子的线程数大于corePoolSize，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁。

>>任务执行器优先会创建corePoolSize线程，当继续增加线程时，先放入Queue中，当corePoolSize和Queue都满的时候，就增加创建新线程，当线程达到MaxPoolSize的时候，就会抛出错误org.springframework.core.task.TaskRejectedException。

>>另外maxPoolSize的设定如果比系统支持的线程数还要大时，会抛出java.lang.OutOfMemoryError: unable to create new native thread异常。

关于java.util.concurrent.ThreadPoolExecutor 参考[ThreadPoolExecutor 线程池](http://www.cnblogs.com/kelin1314/archive/2010/03/18/1689408.html)。