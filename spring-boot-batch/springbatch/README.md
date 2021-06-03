# 千峰Java SpringBatch实战教程（示例未完成，ItemReader和ItemWriter部分重复太多，基础部分讲完就不知道飞哪去了）
[视频源地址](ttps://www.bilibili.com/video/BV1sJ411W7qV)
## job基础
step为最小单元，flow可以包含多个step，job可以包含多个step、flow以及job。
### JobConfiguration
Hello world
### JobDemo
### FlowDemo
### SplitDemo
并发执行
### DeciderDemo
根据Step结果有选择执行下一步
### NestedDemo
job嵌套执行，通过JobStepBuilder转换job成step，在父job中添加该step，同理使用JobFlowBuilder转换job成flow。

## 监听器
配置监听器
1. 实现接口JobExecutionListener
2. 使用注解@BeforeJob @BeforeChunk等实现不同级别的监听
### job监听器 MyJobListener
### chunk监听器 MyChunkListener
### 利用监听器传递数据给step（ParametersDemo），暂时未遇到实际应用场景。

## ItemReader 
使用ItemStreamReader记录异常时任务状态，任务重启后，通过数据库保存的之前状态（ExecutionContext），继续执行任务。
open方法在任务开始前执行，update方法在每次chunk后执行，close方法在任务结束后执行。
通常情况下在update方法中自定义记录当前状态，任务出现异常时，该状态会保存在数据库当中。
重启任务后，在open方法中判断是否继续之前的任务。

## ItemProcessor
使用CompositeItemProcessor.setDelegates添加多个ItemProcessor

## 容错机制
### restart
restart是针对job来使用，是重启job的一个操作。默认情况下，当任务出现异常时，SpringBatch会结束任务，当使用相同参数重启任务时，SpringBatch会去执行未执行的剩余任务
### retry
在step中添加.faultTolerant().retry(异常类型).retryLimit(重试次数)，让任务自动重试。重试次数=之前处理方法总异常数
### skip
在step中添加.faultTolerant().skip(异常类型).skipLimit(跳过次数)，遇到异常跳过。

## 设置任务不自动执行
spring.batch.job.enabled=false
### 通过JobLauncher启动任务
```java
@RestController
public class JobLauncherController {
    @Autowired
    private JobLauncher jobLauncher;
    //实际需要运行的job bean
    @Autowired
    private Job jobLauncherDemoJob;
    
    @RequestMapping("/job/{msg}")
    public String jobRun(@PathVariable String msg) {
        //把接收到的参数值传给任务
        JobParameters parameters = new JobParametersBuilder().addString("参数名", "参数值").toJobParameters();
        //启动任务，并把参数传给任务
        jobLauncher.run(jobLauncherDemoJob, parameters);
        
        return "success";
    }
}

//获取参数
public class StepListener implements StepExecutionListner {
    private Map<String, JobParameter> parameters;
    public void beforeStep(StepExecution stepExecution) { 
        parameters = stepExecution.getJobParameters().getParameters();
    }
}

//使用参数，参考ParametersDemo
```