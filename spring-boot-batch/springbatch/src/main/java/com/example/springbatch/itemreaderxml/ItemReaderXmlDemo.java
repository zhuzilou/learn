//package com.example.springbatch.itemreaderxml;
//
//import com.example.springbatch.itemreaderfile.Customer;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.xml.StaxEventItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.oxm.xstream.XStreamMarshaller;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by endless on 3/24/2020.
// */
//@Configuration
//public class ItemReaderXmlDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//
//    public ItemReaderXmlDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job itemReaderXmlDemoJob() {
//        return jobBuilderFactory.get("itemReaderXmlDemoJob")
//                .start(itemReaderXmlDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderXmlDemoStep() {
//        return stepBuilderFactory.get("itemReaderXmlDemoStep")
//                .<Customer, Customer>chunk(3)
//                .reader(xmlFileReader())
//                .writer(list -> {
//                    for (Customer item : list) {
//                        System.out.println(item);
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public StaxEventItemReader<Customer> xmlFileReader() {
//        StaxEventItemReader<Customer> reader = new StaxEventItemReader<>();
//        reader.setResource(new ClassPathResource("customer.xml"));
//
//        //指定需要处理的根标签，root标签子标签
//        reader.setFragmentRootElementName("customer");
//        //把xml转成对象
//        XStreamMarshaller unmarshaller = new XStreamMarshaller();
//        Map<String, Class> mapper = new HashMap<>();
//        mapper.put("customer", Customer.class);
//        unmarshaller.setAliases(mapper);
//
//        reader.setUnmarshaller(unmarshaller);
//        return reader;
//    }
//}
