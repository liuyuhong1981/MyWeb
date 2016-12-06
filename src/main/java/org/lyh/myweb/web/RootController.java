/**
 * 
 */
package org.lyh.myweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuyuho
 *
 */
@Controller
public class RootController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
