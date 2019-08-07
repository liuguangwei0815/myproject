package com.quicky.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quicky.demo.model.User;
import com.quicky.demo.redis.RedisLockHelper;
import com.quicky.demo.service.RedisLockBiz;
import com.quicky.demo.service.UserService;

@RestController
public class IndexController {

    @RequestMapping("index")
    public String index() {
        return "hello world!";
    }
    
    @Autowired
    private UserService userService;

    /**
     * test redis case
     * @return
     */
    @GetMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers(){
        return userService.findAll();
    }
    
    @Autowired
    RedisLockHelper redisLockHelper;


    /***
     * test redis lock
     * @param targetId
     * @return
     */
    @RequestMapping(value = "/seckilling")
    @ResponseBody
    public String Seckilling(String targetId){
    	
    	testSkill();

        return "开始执行";
    }
    
    
    
    @Autowired
	RedisLockBiz redisLockBiz;
	public void testSkill() {
		
		for (int i = 0; i < 50000; i++) {
			final int j  = i;
			System.out.println("开始执行线程:"+j);
			new Thread() {
				@Override
				public void run() {
					redisLockBiz.seckilling(String.valueOf(j));
				}
			}.start();;
		}
		
	}
    
    
}