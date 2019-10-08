package com.quicky.demo.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.quicky.demo.entity.OauthClientDetails;
import com.quicky.demo.mapper.OauthClientDetailsMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyClientDetials implements ClientDetailsService{

	@Autowired
	private OauthClientDetailsMapper oauthClientDetailsMapper;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
		log.info("当前客户端：{}",clientId);
		//通过可短短cleintId查询
		OauthClientDetails detal = oauthClientDetailsMapper.getOne(clientId);
		if(detal==null) {
			throw new NoSuchClientException(String.format("NO Client Error", clientId));
		}
		log.info("detal:{}",JSON.toJSONString(detal));
		BaseClientDetails client  =  new BaseClientDetails();
		client.setClientId(clientId);
		client.setClientSecret(detal.getClientSecret());
		List<String> scopeList = new ArrayList<>();
		scopeList.add(detal.getScope());
		client.setScope(scopeList);
		List<String> gtype = new ArrayList<>();
		gtype.add(detal.getAuthorizedGrantTypes());
		client.setAuthorizedGrantTypes(gtype);
		Set<String> uris = new HashSet<>();
		uris.add(detal.getWebServerRedirectUri());
		client.setRegisteredRedirectUri(uris);
		Set<String> approve = new HashSet<>();
		approve.add(detal.getAutoapprove());
		client.setAutoApproveScopes(approve);
		client.setAccessTokenValiditySeconds(5/* (int)TimeUnit.DAYS.toSeconds(1)*/); //1天
		client.setRefreshTokenValiditySeconds(5/*(int)TimeUnit.DAYS.toSeconds(1) */); //1天
		log.info("client:{}",JSON.toJSONString(client));
		return client;
	}

}
