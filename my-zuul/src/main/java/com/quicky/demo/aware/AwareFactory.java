package com.quicky.demo.aware;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AwareFactory implements ApplicationContextAware{

	private static Map<String,FourElementAuthBase> autMap = new HashMap<>();
	/**
	 * 容器会扫描实现ApplicationContextAware实例 都会跑这个方法
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		 Map<String, FourElementAuthBase> map = applicationContext.getBeansOfType(FourElementAuthBase.class);
		 for (Map.Entry<String,FourElementAuthBase> fomap : map.entrySet()) {
			 autMap.put(fomap.getKey(), fomap.getValue());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends FourElementAuthBase> T getAutMode(String type){
		return (T)autMap.get(type);
	}
	

}
