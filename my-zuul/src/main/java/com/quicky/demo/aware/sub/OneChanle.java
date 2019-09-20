package com.quicky.demo.aware.sub;

import org.springframework.stereotype.Component;

import com.quicky.demo.aware.FourElementAuthBase;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("oneChanle")
public class OneChanle extends FourElementAuthBase {

	@Override
	public String getAuthType() {
		log.info("getAuthType1");
		return null;
	}

	@Override
	public String doValSome() {
		log.info("doValSome1");
		return null;
	}

}
