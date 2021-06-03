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
//
///**
// * flow可包含多个step，便于重复使用。
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class FlowDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//
//    public FlowDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Step flowDemoStep1() {
//        return stepBuilderFactory.get("flowDemoStep1")
//                .tasklet(((stepContribution, chunkContext) -> {
//                    System.out.println("flowDemoStep1");
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//    @Bean
//    public Step flowDemoStep2() {
//        return stepBuilderFactory.get("flowDemoStep2")
//                .tasklet(((stepContribution, chunkContext) -> {
//                    System.out.println("flowDemoStep2");
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//    @Bean
//    public Step flowDemoStep3() {
//        return stepBuilderFactory.get("flowDemoStep3")
//                .tasklet(((stepContribution, chunkContext) -> {
//                    System.out.println("flowDemoStep3");
//                    return RepeatStatus.FINISHED;
//                }))
//                .build();
//    }
//
//    //创建Flow对象
//    @Bean
//    public Flow flowDemoFlow() {
//        return new FlowBuilder<Flow>("flowDemoFlow")
//                .start(flowDemoStep1())
//                .next(flowDemoStep2())
//                .build();
//    }
//
//    @Bean
//    public Job flowDemoJob() {
//        return jobBuilderFactory.get("flowDemoJob")
//                .start(flowDemoFlow())
//                .next(flowDemoStep3())
//                .end()
//                .build();
//    }
//}
