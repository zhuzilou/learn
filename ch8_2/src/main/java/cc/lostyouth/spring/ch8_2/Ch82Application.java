package cc.lostyouth.spring.ch8_2;

import cc.lostyouth.spring.ch8_2.support.CustomRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class Ch82Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch82Application.class, args);
    }
}
