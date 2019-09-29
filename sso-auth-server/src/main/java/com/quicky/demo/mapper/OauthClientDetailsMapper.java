package com.quicky.demo.mapper;

import com.quicky.demo.entity.OauthClientDetails;

import tk.mybatis.mapper.common.Mapper;

public interface OauthClientDetailsMapper extends Mapper<OauthClientDetails>{
	
	public OauthClientDetails getOne(String clientId);

}
