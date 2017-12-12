package cc.lostyouth.spring.highlight_spring4.ch03.taskscheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by endless on 11/6/2017.
 */
@Service
public class ScheduleTaskService {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleTaskService.class);

    /**
     * 通过@Scheduled声明该方法是计划任务，使用fixedRate属性每隔固定时间执行。
     */
    @Scheduled(initialDelay = 10000, fixedRate = 5000)
    public void reportCurrentTime() {
        LOG.info("Run every 5 seconds: " + LocalDateTime.now());
    }

    /**
     * 使用cron属性可按照指定时间执行，此处指的是每天11点28分执行。
     */
    @Scheduled(cron = "0 28 11 ? * *")
    public void fixTimeExecution() {
        LOG.info("Run at the fix time: " + LocalDateTime.now());
    }

    @Scheduled(fixedDelay = 10000)
    public void fixTimeDelay() {
        LOG.info("Delay 10 seconds between the end of the last invocation and the start of the next: " + LocalDateTime.now());
    }
}
