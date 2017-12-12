package cc.lostyouth.spring.highlight_spring4.ch01.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <ol>
 *     <li>@Configuration声明当前类是一个配置类</li>
 *     <li>使用@ComponentScan，自动扫描包名下所有使用@Service、@Component、@Repository和@Controller的类，并注册为Bean。</li>
 * </ol>
 * Created by endless on 10/17/2017.
 */
@Configuration
@ComponentScan("cc.lostyouth.spring.highlight_spring4.ch01.di")
public class DiConfig {
}
