//package com.example.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.annotation.AfterStep;
//import org.springframework.batch.core.annotation.BeforeStep;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Map;
//
///**
// * 通过监听设置传递给step的参数，方式略呆不够灵活。测试时需要修改启动配置添加键值对info=qianfeng
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class ParametersDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    private Map<String, JobParameter> mapParameter;
//
//    public ParametersDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @BeforeStep
//    public void beforeStep(StepExecution stepExecution) {
//        mapParameter = stepExecution.getJobParameters().getParameters();
//    }
//
//    @AfterStep
//    public void afterStep() {
//
//    }
//
//    @Bean
//    public Job parameterJob() {
//        return jobBuilderFactory.get("parameterJob")
//                .start(parameterStep())
//                .build();
//    }
//
//    //job实际执行的是step，job使用的数据肯定是在step中使用
//    //因此只需要给step传递数据，如何给step传递参数？
//    //使用step级别的监听来传递数据
//    @Bean
//    public Step parameterStep() {
//        return stepBuilderFactory.get("parameterStep")
//                .listener(this)
//                .tasklet((stepContribution, chunkContext) -> {
//                    //输出接收到的参数
//                    System.out.println(mapParameter.get("info"));
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//}
