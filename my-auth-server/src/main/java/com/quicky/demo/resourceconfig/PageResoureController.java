package com.quicky.demo.resourceconfig;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 受保护的资源
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller
public class PageResoureController {
	
	@GetMapping("/pageRole/sayHello")
	public String hhh(Model model) {
	   Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
       model.addAttribute("msg", msg);
		return "hello2";
	}

}
