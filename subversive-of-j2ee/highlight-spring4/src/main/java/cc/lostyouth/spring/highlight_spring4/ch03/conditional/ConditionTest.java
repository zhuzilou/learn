package cc.lostyouth.spring.highlight_spring4.ch03.conditional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by endless on 11/6/2017.
 */
public class ConditionTest {
    private static final Logger LOG = LoggerFactory.getLogger(ConditionTest.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ListService listService = context.getBean(ListService.class);
        LOG.info(context.getEnvironment().getProperty("os.name") + "系统下的列表命令为：" + listService.showListCmd());
    }
}
