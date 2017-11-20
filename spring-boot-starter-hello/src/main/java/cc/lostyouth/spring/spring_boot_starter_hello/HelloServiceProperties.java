package cc.lostyouth.spring.spring_boot_starter_hello;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>类型安全的配置：通过将properties属性和一个Bean及其属性关联，从而实现类型安全的配置。</p>
 * Created by endless on 11/16/2017.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {
    private static final String MSG = "world";

    private String msg = MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
