package com.quicky.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private MyClientDetials myClientDetials;

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// 设置服务认证的加密方式
		security.passwordEncoder(passwordEncoder());
		//加了下面两句话，客户端才能通过jwt 获取 token_key
		security.allowFormAuthenticationForClients();
		security.tokenKeyAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// 设置客户端
		clients.withClientDetails(myClientDetials);
	}

//	@Autowired
//	AuthenticationManager authenticationManager;

//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		// 设置token的存储方式
//		 endpoints.tokenStore(tokenStore())
//		 .authenticationManager(authenticationManager)
//		 .allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET)
//		 ;
//		    //配置TokenService参数
//        DefaultTokenServices tokenService = new DefaultTokenServices();
//        tokenService.setTokenStore(endpoints.getTokenStore());
//        tokenService.setSupportRefreshToken(true);
//        tokenService.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenService.setTokenEnhancer(endpoints.getTokenEnhancer());
//		tokenService.setAccessTokenValiditySeconds(5/* (int)TimeUnit.DAYS.toSeconds(30) */); //30天
//		tokenService.setRefreshTokenValiditySeconds(5/* (int)TimeUnit.DAYS.toSeconds(50) */); //50天
//        tokenService.setReuseRefreshToken(false);
//        endpoints.tokenServices(tokenService);
//	}
//
//	public TokenStore tokenStore() {
//		return new RedisTokenStore(redisConnectionFactory);
//	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// 设置token的存储方式
		// endpoints.tokenStore(tokenStore());
		endpoints.accessTokenConverter(jwtAccessTokenConverter()).tokenStore(jwtTokenStore());
	}

	@Bean
	public JwtTokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter conver = new JwtAccessTokenConverter();
		conver.setSigningKey("cjs");// set singn
		return conver;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
