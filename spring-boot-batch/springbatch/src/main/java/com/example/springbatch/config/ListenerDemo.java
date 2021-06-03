//package com.example.springbatch.config;
//
//import com.example.springbatch.listener.MyChunkListener;
//import com.example.springbatch.listener.MyJobListener;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.support.ListItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
///**
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class ListenerDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public ListenerDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job listenerJob() {
//        return jobBuilderFactory.get("listenerJob")
//                .start(step1())
//                .listener(new MyJobListener())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                //数据分割，影响监听调用次数。
//                .<String, String>chunk(2)
//                //容错？
//                .faultTolerant()
//                .listener(new MyChunkListener())
//                .reader(read())
//                .writer(write())
//                .build();
//    }
//
//    @Bean
//    public ItemWriter<String> write() {
//        return list -> list.stream()
//                .forEach(System.out::println);
//    }
//
//    @Bean
//    public ItemReader<String> read() {
//        return new ListItemReader<>(Arrays.asList("java", "spring", "mybatis"));
//    }
//}
