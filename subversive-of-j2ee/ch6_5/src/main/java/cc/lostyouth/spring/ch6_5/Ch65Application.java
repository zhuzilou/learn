package cc.lostyouth.spring.ch6_5;

import cc.lostyouth.spring.spring_boot_starter_hello.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ch65Application {
    @Autowired
    HelloService helloService;

    @RequestMapping("/")
    public String index() {
        return helloService.sayHello();
    }

    public static void main(String[] args) {
        SpringApplication.run(Ch65Application.class, args);
    }
}
