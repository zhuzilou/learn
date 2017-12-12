# 控制器全局配置@ControllerAdvice
使用@ControllerAdvice的类的方法可以使用@ExceptionHandler、@InitBinder、@ModelAttribute注解，对所有使用了
@RequestMapping的控制器内的方法有效。

## @ExceptionHandler 用于处理全局控制器里的异常
1. 新建控制器建言类，使用@ControllerAdvice注解；
2. 自定义方法并使用@ExceptionHandler注解；
3. 编写需要处理的业务逻辑。[ExceptionHandlerAdvice#exception](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/advice/ExceptionHandlerAdvice.java)

## @ModelAttribute 绑定键值对到Model里，视图页面中通过表达式访问。
1. **`当@ModelAttribute使用在@ControllerAdvice建言中，所有视图页面都可以访问到该键值对。`**
2. 使用@ModelAttribute注解的方法会优先于@Controller中的方法执行，因此重复使用相同键的@ModelAttribute，后使用注解的会覆盖前面的值。 
    参见[AdviceController#modelattribute](https://github.com/zhuzilou/spring-learn/blob/master/highlight-springmvc4/src/main/java/cc/lostyouth/spring/highlight_springmvc4/web/ch4_4/AdviceController.java)

## @InitBinder 用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Model中，待研究…