package com.quicky.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	@Autowired
	private MyClientDetials myClientDetials;
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//设置服务认证的加密方式
		security.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//设置客户端
		clients.withClientDetails(myClientDetials);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		//设置token的存储方式
		endpoints.tokenStore(tokenStore());
	}
	
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
