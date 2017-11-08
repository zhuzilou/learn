package cc.lostyouth.spring.highlight_springmvc4.web.ch4_3;

import cc.lostyouth.spring.highlight_springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by endless on 11/8/2017.
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

    /**
     * 此方法未标注路径，因此使用类级别的路径/anno；
     *
     * @param request
     * @return
     */
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String index(HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access.";
    }

    /**
     * 接受路径参数，并在方法参数前结合@PathVariable使用
     *
     * @param str
     * @param request
     * @return
     */
    @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String demoPathVar(@PathVariable String str, HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access, str: " + str;
    }

    /**
     * 常规的request参数获取，访问路径为/anno/requestParam?id=1
     */
    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String passRequestParam(Long id, HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access, id: " + id;
    }

    /**
     * 解析参数到对象，访问路径为/anno/obj?id=1&name=xx
     *
     * @param obj
     * @param request
     * @return
     */
    @RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String passObj(DemoObj obj, HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access, obj id: " + obj.getId() + " obj name: " + obj.getName();
    }

    /**
     * 映射不同的路径到相同的方法，访问路径为/anno/name1或/anno/name2
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"/name1", "/name2"}, produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String remove(HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access.";
    }

    /**
     * 请求中需要包含固定的参数列表，通过params定义。
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/fixedparam", params = "fixed=param")
    @ResponseBody
    public String fixedParam(HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access, fixed param: " + request.getParameter("fixed");
    }

    /**
     * 请求的Header中需要带有固定的参数，通过headers定义。
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/headers", headers = {"username=1", "passwd=2"})
    @ResponseBody
    public String headers(HttpServletRequest request) {
        return "url: " + request.getRequestURL() + " can access, headers username: " + request.getHeader("username") + ", headers passwd: " + request.getHeader("passwd");
    }
}
