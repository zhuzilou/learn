package cc.lostyouth.spring.highlight_spring4.ch02.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by endless on 10/31/2017.
 */
@Configuration
public class ProfileConfig {
    @Bean
    @Profile("dev")
    public DemoProfileBean devDemoBean() {
        return new DemoProfileBean("from development profile");
    }

    @Bean
    @Profile("prod")
    public DemoProfileBean prodDemoBean() {
        return new DemoProfileBean("from production profile");
    }
}
