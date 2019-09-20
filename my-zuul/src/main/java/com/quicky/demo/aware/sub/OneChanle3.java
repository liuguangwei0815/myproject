package com.quicky.demo.aware.sub;

import org.springframework.stereotype.Component;

import com.quicky.demo.aware.FourElementAuthBase;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("oneChanle3")
public class OneChanle3 extends FourElementAuthBase {

	@Override
	public String getAuthType() {
		log.info("getAuthType3");
		return null;
	}

	@Override
	public String doValSome() {
		log.info("doValSome3");
		return null;
	}

}
