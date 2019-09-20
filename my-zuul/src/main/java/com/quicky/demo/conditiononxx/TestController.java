package com.quicky.demo.conditiononxx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quicky.demo.conditiononxx.ConditonOnBeanServiceConfig.Peple;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired(required = false)
	Peple peple;
	 
	  /**
     * 个人中心
     */
    @PreAuthorize("hasAuthority('UserIndex')")
    @GetMapping("/index")
    @ResponseBody
    public String index() {
    	log.info("peple:"+peple);
        return "ssss";
    }
	
}
