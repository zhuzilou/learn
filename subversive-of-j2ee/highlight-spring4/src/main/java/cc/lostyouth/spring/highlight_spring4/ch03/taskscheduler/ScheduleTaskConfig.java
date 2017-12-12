package cc.lostyouth.spring.highlight_spring4.ch03.taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by endless on 11/6/2017.
 */
@Configuration
@ComponentScan("cc.lostyouth.spring.highlight_spring4.ch03.taskscheduler")
@EnableScheduling
public class ScheduleTaskConfig {
}
