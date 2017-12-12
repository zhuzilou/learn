package cc.lostyouth.spring.highlight_spring4.ch01.javaconfig;


import org.springframework.context.annotation.Bean;

/**
 * <ol>
 * <li>
 * Java配置是通过使用@Configuration和@Bean来实现的
 * <ul>
 * <li>@Configuration声明当前类是一个配置类，相当于一个Spring配置的xml文件。</li>
 * <li>@Bean注解在方法上，声明当前方法的返回值为一个Bean。</li>
 * </ul>
 * </li>
 * <li>
 * 全局配置使用Java配置（如数据库相关配置、MVC相关配置），业务Bean的配置使用注解配置（@Service、@Component、@Repository、@Controller）
 * </li>
 * </ol>
 * Created by endless on 10/17/2017.
 */
public class JavaConfig {
    @Bean
    public FunctionService functionService() {
        return new FunctionService();
    }

    @Bean
    public UseFunctionService useFunctionService() {
        UseFunctionService useFunctionService = new UseFunctionService();
        useFunctionService.setFunctionService(functionService());
        return useFunctionService;
    }

    /*
        另外一种注入的方式，直接将FunctionService作为参数给useFunctionService()，这也是
        Spring容器提供的极好的功能。在Spring容器中，只要容器中存在某个Bean，就可以在另外一个
        Bean的声明方法的参数中写入。
     */
//    @Bean
//    public UseFunctionService useFunctionService(FunctionService functionService) {
//        UseFunctionService useFunctionService = new UseFunctionService();
//        useFunctionService.setFunctionService(functionService);
//        return useFunctionService;
//    }
}
