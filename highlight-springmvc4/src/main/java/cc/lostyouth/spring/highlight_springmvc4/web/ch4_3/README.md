# Spring MVC 常用注解
## @Controller
@Controller注解在类上，表明这个类是Spring MVC里的Controller，将其声明为Spring的一个Bean，Dispatcher Servlet
会自动扫描此类注解（类似于Spring中的AOP注解拦截，见cc.lostyouth.spring.highlight_spring4.ch01.aop.AopTest），
并将Web请求映射到注解了@RequestMapping的方法上。
**`在声明普通Bean的时候，使用@Component、@Service、@Repository和@Controller是等同的，因为@Service、@Repository、
@Controller都组合了@Compoment元注解；但在Spring MVC声明控制器Bean的时候，只能使用@Controller。`**

## @RequestMapping
@RequestMapping注解是用来映射Web请求（访问路径和参数）、处理类和方法的。@RequestMapping可注解在类或方法上，注解
在方法上的路径会继承注解在类上的路径。@RequestMapping支持Servlet的request和response作为参数，也支持对request
和response的媒体类型进行配置。
### @RequestMapping可配置参数
#### produces
produces可定制返回的response的媒体类型和字符表
需要返回值是json对象，则设置produces="application/json;charset=UTF-8"；
需要返回值是xml对象，则设置produces="application/xml;charset=UTF-8"。

#### params
指定固定的请求参数，只有满足此设定才可以正确的请求到方法体。`通过request.getParameter获得参数值。`

#### headers
同params，`通过request.getHeader获得参数值。`

## @ResponseBody
@ResponseBody支持将返回值放在response体内，而不是返回一个页面。我们在很多基于Ajax的程序的时候，可以以此注解返回
数据而不是页面。`此注解可放置在返回值前或者方法上。`

## @RequestBody
@RequestBody允许request的参数在request体中，而不是在直接链接在地址后面。`此注解放置在参数前。`

## @PathVariable
@PathVariable用来接收路径参数，如/news/001，可接收001作为参数。`此注解放置在参数前。`

## @RestController
@RestController是一个组合注解，组合了@Controller和@ResponseBody，这就意味着你只开发一个和页面交互数据的控制的时候，
需要使用此注解。可以同时添加@Controller和@ResponseBody注解达到同样效果。
