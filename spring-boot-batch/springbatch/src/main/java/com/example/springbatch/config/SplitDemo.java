//package com.example.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.builder.FlowBuilder;
//import org.springframework.batch.core.job.flow.Flow;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//
///**
// * 并发执行，split后只能添加flow类型，不能添加step。
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class SplitDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public SplitDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Step splitDemoStep1() {
//        return stepBuilderFactory.get("splitDemoStep1")
//                .tasklet(((stepContribution, chunkContext) -> {
//                    System.out.println("splitDemoStep1");
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//    @Bean
//    public Step splitDemoStep2() {
//        return stepBuilderFactory.get("splitDemoStep2")
//                .tasklet(((stepContribution, chunkContext) -> {
//                    System.out.println("splitDemoStep2");
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//    @Bean
//    public Step splitDemoStep3() {
//        return stepBuilderFactory.get("splitDemoStep3")
//                .tasklet(((stepContribution, chunkContext) -> {
//                    System.out.println("splitDemoStep3");
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//    @Bean
//    public Flow splitDemoFlow1() {
//        return new FlowBuilder<Flow>("splitDemoFlow1")
//                .start(splitDemoStep1())
//                .build();
//    }
//
//    @Bean
//    public Flow splitDemoFlow2() {
//        return new FlowBuilder<Flow>("splitDemoFlow2")
//                .start(splitDemoStep2())
//                .next(splitDemoStep3())
//                .build();
//    }
//
//    @Bean
//    public Job splitDemoJob() {
//        return jobBuilderFactory.get("splitDemoJob")
//                .start(splitDemoFlow1())
//                //异步执行
//                .split(new SimpleAsyncTaskExecutor())
//                .add(splitDemoFlow2())
//                .end()
//                .build();
//    }
//}
