//package com.example.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.job.flow.FlowExecutionStatus;
//import org.springframework.batch.core.job.flow.JobExecutionDecider;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 决策器（step执行后条件选择器，step返回固定值，通过JobExecutionDecider返回自定义值，决定下一步step）
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class DeciderDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//
//    public DeciderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Step deciderDemoStep1() {
//        return stepBuilderFactory.get("deciderDemoStep1")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("deciderDemoStep1");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step deciderDemoStep2() {
//        return stepBuilderFactory.get("deciderDemoStep2")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("even");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step deciderDemoStep3() {
//        return stepBuilderFactory.get("deciderDemoStep3")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("odd");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    //创建决策器
//    @Bean
//    public JobExecutionDecider myDecider() {
//        return new MyDecider();
//    }
//
//    @Bean
//    public Job deciderDemoJob() {
//        return jobBuilderFactory.get("deciderDemoJob")
//                .start(deciderDemoStep1())
//                .next(myDecider())
//                .from(myDecider()).on("even").to(deciderDemoStep2())
//                .from(myDecider()).on("odd").to(deciderDemoStep3())
//                //on("*")无论返回结果是什么，回到next(myDecider()) Line 72，再次执行Line 73
//                .from(deciderDemoStep3()).on("*").to(myDecider())
//                .end()
//                .build();
//    }
//
//    private class MyDecider implements JobExecutionDecider {
//        private int count;
//
//        @Override
//        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
//            count++;
//            if (count % 2 == 0) {
//                return new FlowExecutionStatus("even");
//            } else {
//                return new FlowExecutionStatus("odd");
//            }
//        }
//    }
//}
