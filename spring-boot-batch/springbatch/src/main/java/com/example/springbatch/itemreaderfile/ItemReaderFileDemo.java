//package com.example.springbatch.itemreaderfile;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.FieldSet;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.validation.BindException;
//
///**
// * Created by endless on 3/23/2020.
// */
//@Configuration
//public class ItemReaderFileDemo {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//
//    public ItemReaderFileDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job itemReaderFileDemoJob() {
//        return jobBuilderFactory.get("itemReaderFileDemoJob")
//                .start(itemReaderFileDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderFileDemoStep() {
//        return stepBuilderFactory.get("itemReaderFileDemoStep")
//                .<Customer, Customer>chunk(2)
//                .reader(flatFileReader())
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
//    public FlatFileItemReader<Customer> flatFileReader() {
//        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("customer.txt"));
//        //跳过某一行
//        reader.setLinesToSkip(1);
//
//        //解析数据
//        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//        tokenizer.setNames(new String[]{"id", "firstName", "lastName", "birthday"});
//
//        //把解析出的数据映射成对象
//        DefaultLineMapper<Customer> mapper = new DefaultLineMapper<>();
//        mapper.setLineTokenizer(tokenizer);
//        mapper.setFieldSetMapper(new FieldSetMapper<Customer>() {
//            @Override
//            public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
//                Customer customer = new Customer();
//                customer.setId(fieldSet.readLong("id"));
//                customer.setFirstName(fieldSet.readString("firstName"));
//                customer.setLastName(fieldSet.readString("lastName"));
//                customer.setBirthday(fieldSet.readString("birthday"));
//                return customer;
//            }
//        });
//        mapper.afterPropertiesSet();
//
//        reader.setLineMapper(mapper);
//        return reader;
//    }
//}
