package com.quicky.demo.auth;

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

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/**
	 * 我在用psotman 测试oauth授权码模式的出现了401的异常， 就是调用oauth/token.
	我是想用code换token，但是发现报错了。这是为什么呢？ 首先你要理解
	 * /oauth/token
	 * 这个如果配置支持allowFormAuthenticationForClients的，且url中有client_id和client_secret的会走ClientCredentialsTokenEndpointFilter来保护
	 * 如果没有支持allowFormAuthenticationForClients或者有支持但是url中没有client_id和client_secret的，走basic认证保护
	 * 所以我就明白怎么回事了
	 * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全与权限访问。
	 * 首先代码里面添加
	 * 
	 * @Override public void configure(AuthorizationServerSecurityConfigurer
	 *           oauthServer) throws Exception { oauthServer
	 *           .tokenKeyAccess("permitAll()") .checkTokenAccess("permitAll()")
	 *           .allowFormAuthenticationForClients();
	 * 
	 *           // oauthServer.allowFormAuthenticationForClients(); }
	 *           ———————————————— 版权声明：本文为CSDN博主「moocsea」的原创文章，遵循 CC 4.0 BY-SA
	 *           版权协议，转载请附上原文出处链接及本声明。
	 *           原文链接：https://blog.csdn.net/u012040869/article/details/80140515
	 */
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
	//security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
//		//security.allowFormAuthenticationForClients();
//	}
	

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(passwordEncoder());//设置clent_id的加密方式
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()
//		.withClient("my-client-1")
//		.secret(java.util.Base64.getEncoder().encodeToString("my-client-1:12345678".getBytes("UTF-8")))
//		.authorizedGrantTypes("authorization_code","refresh_token")
//		.scopes("all")
//		.redirectUris("http://www.baidu.com")
//		;
//	}
	
	 //secret密码规则：String finalSecret = "{bcrypt}"+new BCryptPasswordEncoder().encode("123456");
	/**
	 * ClientDetailsServiceConfigurer：用来配置客户端详情信息，一般使用数据库来存储或读取应用配置的详情信息（client_id ，client_secret，redirect_uri 等配置信息）。
	 */
	 @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		   String passwrod1 = java.util.Base64.getEncoder().encodeToString("my-client-1:a12345678".getBytes("UTF-8"));
		   log.info("======>secret_passwrod1:{}<====",passwrod1);
	        clients.inMemory()
	                .withClient("my-client-1")
	                .secret(passwordEncoder().encode("a12345678"))
	                .authorizedGrantTypes("authorization_code", "refresh_token")
	                //.scopes("all")
	                .scopes("read", "write", "execute")
	                .redirectUris("http://www.baidu.com");
	    }
	/**
	 * AuthorizationServerEndpointsConfigurer：用来配置授权以及令牌（Token）的访问端点和令牌服务（比如：配置令牌的签名与存储方式），token 存在redis 防止token 服务重启丢失
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore());
	}
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}
	
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("my-client-1:12345678"));
	}
	
}
