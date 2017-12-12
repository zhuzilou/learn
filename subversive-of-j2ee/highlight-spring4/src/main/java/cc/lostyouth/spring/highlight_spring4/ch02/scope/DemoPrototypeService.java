package cc.lostyouth.spring.highlight_spring4.ch02.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by endless on 10/17/2017.
 */
@Service
@Scope("prototype")
public class DemoPrototypeService {
}
