package cc.lostyouth.spring.highlight_spring4.ch03.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by endless on 11/6/2017.
 */
@Service
public class DemoAnnotationService {
    private static final Logger LOG = LoggerFactory.getLogger(DemoAnnotationService.class);

    public void outputResult() {
        LOG.info("从组合注解配置获得的Bean");
    }
}
