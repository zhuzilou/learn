package cc.lostyouth.spring.highlight_spring4.ch03.taskscheduler;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 11/6/2017.
 */
public class ScheduleTaskTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScheduleTaskConfig.class);
//        context.close();
    }
}
