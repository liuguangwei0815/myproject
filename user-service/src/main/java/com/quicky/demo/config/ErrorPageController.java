package com.quicky.demo.config;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("/error")
public class ErrorPageController {
	
	@RequestMapping("/401")
	public String noautorient() {
		return "error_401";
	}
	@RequestMapping("/404")
	public String notFondPage() {
		return "error_404";
	}
	@RequestMapping("/505")
	public String serverInvalide() {
		return "error_505";
	}

}
