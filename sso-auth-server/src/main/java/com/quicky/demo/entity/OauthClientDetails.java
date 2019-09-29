package com.quicky.demo.entity;

import java.io.Serializable;

public class OauthClientDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1404922549780958223L;

	private String clientId; // 
	
	private String resourceIds; // 
	
	private String clientSecret; // 
	
	private String scope; // 
	
	private String authorizedGrantTypes; // 
	
	private String webServerRedirectUri; // 
	
	private String authorities; // 
	
	private Long accessTokenValidity; // 
	
	private Long refreshTokenValidity; // 
	
	private String additionalInformation; // 
	
	private String autoapprove; // 
	
	public final void setClientId(String value) {
		this.clientId = value;
	}

	public final String getClientId() {
		if(this.clientId==null){
			this.clientId="";
		}
		return this.clientId;
	}
	public final void setResourceIds(String value) {
		this.resourceIds = value;
	}

	public final String getResourceIds() {
		if(this.resourceIds==null){
			this.resourceIds="";
		}
		return this.resourceIds;
	}
	public final void setClientSecret(String value) {
		this.clientSecret = value;
	}

	public final String getClientSecret() {
		if(this.clientSecret==null){
			this.clientSecret="";
		}
		return this.clientSecret;
	}
	public final void setScope(String value) {
		this.scope = value;
	}

	public final String getScope() {
		if(this.scope==null){
			this.scope="";
		}
		return this.scope;
	}
	public final void setAuthorizedGrantTypes(String value) {
		this.authorizedGrantTypes = value;
	}

	public final String getAuthorizedGrantTypes() {
		if(this.authorizedGrantTypes==null){
			this.authorizedGrantTypes="";
		}
		return this.authorizedGrantTypes;
	}
	public final void setWebServerRedirectUri(String value) {
		this.webServerRedirectUri = value;
	}

	public final String getWebServerRedirectUri() {
		if(this.webServerRedirectUri==null){
			this.webServerRedirectUri="";
		}
		return this.webServerRedirectUri;
	}
	public final void setAuthorities(String value) {
		this.authorities = value;
	}

	public final String getAuthorities() {
		if(this.authorities==null){
			this.authorities="";
		}
		return this.authorities;
	}
	public final void setAccessTokenValidity(Long value) {
		this.accessTokenValidity = value;
	}

	public final Long getAccessTokenValidity() {
		return this.accessTokenValidity;
	}
	public final void setRefreshTokenValidity(Long value) {
		this.refreshTokenValidity = value;
	}

	public final Long getRefreshTokenValidity() {
		return this.refreshTokenValidity;
	}
	public final void setAdditionalInformation(String value) {
		this.additionalInformation = value;
	}

	public final String getAdditionalInformation() {
		if(this.additionalInformation==null){
			this.additionalInformation="";
		}
		return this.additionalInformation;
	}
	public final void setAutoapprove(String value) {
		this.autoapprove = value;
	}

	public final String getAutoapprove() {
		if(this.autoapprove==null){
			this.autoapprove="";
		}
		return this.autoapprove;
	}

}
