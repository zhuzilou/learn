package cc.lostyouth.spring.spring_boot_starter_hello;

/**
 * Created by endless on 11/16/2017.
 */
public class HelloService {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String sayHello() {
        return "Hello " + msg;

    }
}
