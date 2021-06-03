//package com.example.springbatch.itemreaderdb;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.database.JdbcPagingItemReader;
//import org.springframework.batch.item.database.Order;
//import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 从数据库中读取数据
// * Created by endless on 3/23/2020.
// */
//@Configuration
//public class ItemReaderDbDemo {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final DataSource dataSource;
//
//    public ItemReaderDbDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public Job itemReaderDbJob() {
//        return jobBuilderFactory.get("itemReaderDbJob")
//                .start(itemReaderDbStep())
//                .build();
//    }
//
//    @Bean
//    public Step itemReaderDbStep() {
//        return stepBuilderFactory.get("itemReaderDbStep")
//                .<User, User>chunk(2)
//                .reader(dbJdbcReader())
//                .writer(list -> {
//                    for (User item : list) {
//                        System.out.println(item);
//                    }
//                })
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JdbcPagingItemReader<User> dbJdbcReader() {
//        JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
//        reader.setDataSource(dataSource);
//        reader.setFetchSize(2);
//        //把读取到的记录转换成User
//        reader.setRowMapper((rs, rowNum) -> {
//            User user = new User();
//            user.setId(rs.getInt(1));
//            user.setUsername(rs.getString(2));
//            user.setPassword(rs.getString(3));
//            user.setAge(rs.getInt(4));
//            return user;
//        });
//        //指定sql语句
//        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
//        provider.setSelectClause("id,username,password,age");
//        provider.setFromClause("from user");
//
//        //指定根据字段进行排序
//        Map<String, Order> sort = new HashMap<>(1);
//        sort.put("id", Order.ASCENDING);
//        provider.setSortKeys(sort);
//
//        reader.setQueryProvider(provider);
//        return reader;
//    }
//}
