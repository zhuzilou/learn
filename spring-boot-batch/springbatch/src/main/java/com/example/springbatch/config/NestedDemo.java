//package com.example.springbatch.config;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.JobStepBuilder;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
///**
// * 嵌套Job，需要在配置文件中指定启动运行的job，避免子job冲突。spring.batch.job.name=父job
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class NestedDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final Job childJobOne;
//    private final Job childJobTwo;
//    //job启动对象
//    private final JobLauncher jobLauncher;
//
//    public NestedDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, Job childJobOne, Job childJobTwo, JobLauncher jobLauncher) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.childJobOne = childJobOne;
//        this.childJobTwo = childJobTwo;
//        this.jobLauncher = jobLauncher;
//    }
//
//    @Bean
//    public Job parentJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return jobBuilderFactory.get("parentJob")
//                .start(childJob1(jobRepository, platformTransactionManager))
//                .next(childJob2(jobRepository, platformTransactionManager))
//                .build();
//    }
//
//    //返回的是Job类型的Step，特殊的Step
//    private Step childJob2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new JobStepBuilder(new StepBuilder("childJob2"))
//                .job(childJobTwo)
//                //子job不能单独运行，使用父job的启动对象来启动子Job
//                .launcher(jobLauncher)
//                .repository(jobRepository)
//                .transactionManager(platformTransactionManager)
//                .build();
//    }
//
//    private Step childJob1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
//        return new JobStepBuilder(new StepBuilder("childJob1"))
//                .job(childJobOne)
//                //子job不能单独运行，使用父job的启动对象来启动子Job
//                .launcher(jobLauncher)
//                .repository(jobRepository)
//                .transactionManager(platformTransactionManager)
//                .build();
//    }
//}
