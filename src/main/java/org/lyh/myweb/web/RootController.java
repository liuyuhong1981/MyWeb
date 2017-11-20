/**
 * 
 */
package org.lyh.myweb.web;

import java.util.Calendar;

import org.lyh.myweb.service.MyService;
import org.lyh.myweb.util.HttpURLConnectionUtil;
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
        try {
            Thread.sleep(30000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        testTestServiceByHttp(null);
        System.out.println("Response Time: " + Calendar.getInstance().getTime());
        return "Test OK !!!";
    }

    private static void testTestServiceByHttp(String[] args) {
        String wsUrl = "http://192.168.139.130:7001/test";
        Integer waitSeconds = 30;
        if (args != null && args.length == 1) {
            wsUrl = args[0];
        } else if (args != null && args.length == 2) {
            wsUrl = args[0];
            waitSeconds = Integer.valueOf(args[1]);
        }

        StringBuffer request = new StringBuffer();
        request.append(
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.test.linglong.plm.dxc.com/\">");
        request.append(" <soapenv:Header/>");
        request.append("<soapenv:Body>");
        request.append("<ser:TestService>");
        request.append("<TestServiceRequest>");

        request.append("<ID>111</ID>");
        request.append("<NAME>aaa</NAME>");
        request.append("<VALUES>");
        request.append("<VALUE>0001</VALUE>");
        request.append("<VALUE>0002</VALUE>");
        request.append("</VALUES>");
        request.append("<WAITSECONDS>" + waitSeconds + "</WAITSECONDS>");

        request.append("</TestServiceRequest>");
        request.append(" </ser:TestService>");
        request.append("</soapenv:Body>");
        request.append("</soapenv:Envelope>");
        try {
            HttpURLConnectionUtil.postRequest(wsUrl, request.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
