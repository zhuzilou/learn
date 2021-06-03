package cc.lostyouth.learn.subversiveofj2ee.ch9_2.batch;

import cc.lostyouth.learn.subversiveofj2ee.ch9_2.domain.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * <p>@EnableBatchProcessing开启批处理支持</p>
 *
 * @author endless
 * @date 4/11/2018
 */
@Configuration
@EnableBatchProcessing
public class CsvBatchConfig {
    @Bean
    public Validator<Person> csvBeanValidator() {
        return new CsvBeanValidator<>();
    }

    @Bean
    public CsvJobListener csvJobListener() {
        return new CsvJobListener();
    }

    @Bean
    public ItemReader<Person> reader() throws Exception {
        //使用FlatFileItemReader读取文件
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        //使用setResource方法设置csv文件的路径
        reader.setResource(new ClassPathResource("people.csv"));
        //对csv文件的数据和领域模型类做对应映射
        reader.setLineMapper(new DefaultLineMapper<Person>() {
            {
                //2货写法
                setLineTokenizer(new DelimitedLineTokenizer() {{
                    setNames(new String[]{"name", "age", "nation", "address"});
                }});
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }});
            }

        });
        return reader;
    }

    @Bean
    public ItemProcessor<Person, Person> processor() {
        //使用ItemProcessor的实现类
        CsvItemProcessor processor = new CsvItemProcessor();
        //指定校验器
        processor.setValidator(csvBeanValidator());
        return processor;
    }

    @Bean
    public ItemWriter<Person> writer(DataSource dataSource) {
        //使用JDBC批处理的JdbcBatchItemWriter写入数据
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        String sql = "insert into person " + "(name,age,nation,address) "
                + "values(:name, :age, :nation, :address)";
        //设置需要执行批处理的sql语句
        writer.setSql(sql);
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("oracle");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer, ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory
                .get("step1")
                //批处理每次提交65000条数据
                .<Person, Person>chunk(65000)
                //为Step绑定reader
                .reader(reader)
                //为Step绑定processor
                .processor(processor)
                //为Step绑定writer
                .writer(writer)
                .build();
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                //为job指定Step
                .flow(s1)
                .end()
                //绑定监听器
                .listener(csvJobListener())
                .build();
    }
}
