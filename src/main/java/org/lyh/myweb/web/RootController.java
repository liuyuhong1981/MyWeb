/**
 * 
 */
package org.lyh.myweb.web;

import org.lyh.myweb.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuyuho
 *
 */
@Controller
public class RootController {
	@Autowired
	MyService service;

	@RequestMapping("/")
	public String index() {
		service.test();
		return "index";
	}

	@RequestMapping("/scroll")
	public String scroll() {
		service.test();
		return "scroll";
	}
}
