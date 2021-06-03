//package com.example.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class ChildJob1 {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public ChildJob1(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Step childJob1Step1() {
//        return stepBuilderFactory.get("childJob1Step1")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("childJob1Step1");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Job childJobOne() {
//        return jobBuilderFactory.get("childJobOne")
//                .start(childJob1Step1())
//                .build();
//    }
//}
