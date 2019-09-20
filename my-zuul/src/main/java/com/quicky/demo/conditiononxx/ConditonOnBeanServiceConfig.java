package com.quicky.demo.conditiononxx;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.ToString;

@Configuration
public class ConditonOnBeanServiceConfig {
	
	@Bean
	public CityBean cityBean() {
		return new CityBean();
	}
	
	
	@Bean
	@ConditionalOnBean(name = "cityBean")//到cityBean 那么则实例化Peple 否则不实例化
	public Peple peple(CityBean cityBean) {
		cityBean.setName("北京");
		cityBean.setCode("1");
		return new Peple();
	}
	
	@ToString
	@Data
	public static class Peple{
		private String username;
	}
	
	@ToString
	@Data
	public static class CityBean{
		private String name;
		private String code;
	}

}
