//package com.example.springbatch.itemreader;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Created by endless on 3/19/2020.
// */
//@Configuration
//public class ItemReaderDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    public ItemReaderDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job itemReaderDemoJob() {
//        return jobBuilderFactory.get("itemReaderDemoJob")
//                .start(itemReaderDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderDemoStep() {
//        return stepBuilderFactory.get("itemReaderDemoStep")
//                .<String, String>chunk(2)
//                .reader(itemReaderDemoRead())
//                .writer(list -> {
//                    for(String item: list) {
//                        System.out.println(item + "...");
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    public MyReader itemReaderDemoRead() {
//        List<String> data = Arrays.asList("cat", "dog", "pig", "duck");
//        return new MyReader(data);
//    }
//
//    private class MyReader implements ItemReader<String> {
//
//        private final Iterator<String> iterator;
//
//        public MyReader(List<String> list) {
//            this.iterator = list.iterator();
//        }
//
//        @Override
//        public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//            if (iterator.hasNext()) {
//                return iterator.next();
//            }
//            return null;
//        }
//    }
//}
