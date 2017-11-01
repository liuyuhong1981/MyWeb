/**
 * 
 */
package org.lyh.myweb.web;

import org.lyh.myweb.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
