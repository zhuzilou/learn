package cc.lostyouth.spring.highlight_spring4.ch01.javaconfig;


/**
 * Created by endless on 10/17/2017.
 */
public class UseFunctionService {
    FunctionService functionService;

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public String sayHello(String word) {
        return functionService.sayHello(word);
    }
}
