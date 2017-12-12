package cc.lostyouth.spring.highlight_springmvc4.advice;

import cc.lostyouth.spring.highlight_springmvc4.domain.DemoObj;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by endless on 11/8/2017.
 */
//声明一个控制器建言，@ControllerAdvice组合了@Controller注解，所以自动注册为Spring的Bean。
@ControllerAdvice
public class ExceptionHandlerAdvice {
    //@ExceptionHandler在此处定义全局处理，通过@ExceptionHandler的value属性可过滤拦截的条件。
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    //使用@ModelAttribute注解将键值对添加到全局，所有注解的@RequestMapping的方法可获得此键值对。
    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "额外信息");
        model.addAttribute("demoObj", new DemoObj(2L, "默认全局键值对"));
    }

    //通过@InitBinder注解定制WebDataBinder。用法有待研究……
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        //忽略请求参数为id的值，@RequestMapping方法中看到id值为null
//        webDataBinder.setDisallowedFields("id");
    }
}
