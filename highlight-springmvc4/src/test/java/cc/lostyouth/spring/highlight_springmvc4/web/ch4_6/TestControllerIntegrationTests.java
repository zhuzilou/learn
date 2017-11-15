package cc.lostyouth.spring.highlight_springmvc4.web.ch4_6;

import cc.lostyouth.spring.highlight_springmvc4.MyMvcConfig;
import cc.lostyouth.spring.highlight_springmvc4.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * Created by endless on 11/15/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
//@WebAppConfiguration注解在类上，用来声明加载的ApplicationContext是一个WebApplicationContext，它的属性指定的是Web资源的位置。
@WebAppConfiguration("src/main/resources")
public class TestControllerIntegrationTests {
    //MockMvc模拟MVC对象
    private MockMvc mockMvc;

    @Autowired
    private DemoService demoService;

    @Autowired
    WebApplicationContext wac;

    //注入模拟的http session
    @Autowired
    MockHttpSession session;

    //注入模拟的http request
    @Autowired
    MockHttpServletRequest request;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testNormalController() throws Exception {
        //模拟向/normal进行get请求
        mockMvc.perform(get("/normal"))
                //预期控制返回状态为200
                .andExpect(status().isOk())
                //预期view的名称为page
                .andExpect(view().name("page"))
                //预期页面转向的真正路径为/WEB-INF/classes/views/page.jsp
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))
                //预期model里的值是demoService.saySomething()返回值hello
                .andExpect(model().attribute("msg", demoService.saySomething()));
    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(get("/testRest"))
                .andExpect(status().isOk())
                //预期返回值的媒体类型为text/plain;charset=UTF-8
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string(demoService.saySomething()));
    }
}
