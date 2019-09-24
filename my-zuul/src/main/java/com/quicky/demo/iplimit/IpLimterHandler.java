package com.quicky.demo.iplimit;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.base.Preconditions;
import com.quicky.demo.util.IpAddressUtil;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class IpLimterHandler {

	@Autowired
	RedisTemplate redisTemplate;

	/**
	 * redis脚本工具类 因为返回Long
	 */
	private DefaultRedisScript<Long> getRedisScript;

	@PostConstruct
	public void init() {
		getRedisScript = new DefaultRedisScript<Long>();
		getRedisScript.setResultType(Long.class);
		getRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("ipLimiter.lua")));
		log.info("IpLimitHandler[分布式限流处理器]脚本加载成功");
	}

	@Around("@annotation(ipLimiter)")
	public Object around(ProceedingJoinPoint proceedingJoinPoint, IpLimiter ipLimiter) throws Throwable {
		log.info("IpLimitHandler开始限流操作");
		Signature signature = proceedingJoinPoint.getSignature();
		if (!(signature instanceof MethodSignature))
			throw new IllegalArgumentException("the Annotation @IpLimter must used on method!");

		// 获取当前IP
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String limitIp  = IpAddressUtil.getIpAddr(request);
		log.info("ip："+limitIp);
		Preconditions.checkNotNull(limitIp);
		long limitTimes = ipLimiter.limit();
		long expireTime = ipLimiter.timet();
		log.debug("IpLimterHandler[分布式限流处理器]参数值为-limitTimes={},limitTimeout={}", limitTimes, expireTime);
		// 限流提示语
        String message = ipLimiter.messge();
        /**
         * 执行Lua脚本
         */
        List<String> ipList = new ArrayList<>();
        // 设置key值为注解中的值
        ipList.add(limitIp);
        Long result = (Long)redisTemplate.execute(getRedisScript, ipList, expireTime,limitTimes);
        if (result == 0) {
            String msg = "由于超过单位时间=" + expireTime + "-允许的请求次数=" + limitTimes + "[触发限流]";
            log.debug(msg);
            // 达到限流返回给前端信息
            return message;
        }
        if (log.isDebugEnabled()) {
        	log.debug("IpLimterHandler[分布式限流处理器]限流执行结果-result={},请求[正常]响应", result);
        }
        return proceedingJoinPoint.proceed();
	}

}
