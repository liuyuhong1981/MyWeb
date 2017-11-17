/**
 * 
 */
package org.lyh.myweb.web;

import static org.mockito.Mockito.calls;

import java.util.Calendar;

import org.lyh.myweb.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuyuho
 *
 */
@Controller
public class RootController {
    @Autowired
    MyService service;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        service.test();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/scroll")
    public String scroll() {
        service.test();
        return "scroll";
    }

    @RequestMapping("/MetaInfoResources")
    public String MetaInfoResources() {
        return "MetaInfoResources";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/public")
    public String publicJsp() {
        return "public";
    }

    @RequestMapping("/static")
    public String staticJsp() {
        return "static";
    }

    @RequestMapping("/resources")
    public ModelAndView resources() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("resources");
        return mv;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        System.out.println("【Receive Time】: " + Calendar.getInstance().getTime());
        service.test();
        System.out.println("Response Time: " + Calendar.getInstance().getTime());
        return "Test OK !!!";
    }
}
