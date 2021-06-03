//package com.example.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Hello world
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class JobConfiguration {
//    //注入创建任务对象的对象
//    private final JobBuilderFactory jobBuilderFactory;
//
//    //任务的执行由Step决定，一个任务Job可以包含若干个Step
//    //注入创建Step对象的对象
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    //创建任务对象
//    @Bean
//    public Job helloWorldJob() {
//        return jobBuilderFactory.get("helloWorldJob")
//                .start(step1())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        //执行具体任务
//                        System.out.println("Hello World!");
//                        //Step执行完的状态，决定是否执行后续Step，只有正常结束时才能执行后续Step
//                        return RepeatStatus.FINISHED;
//                    }
//                })
//                .build();
//    }
//}
