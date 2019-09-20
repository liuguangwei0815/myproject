package com.quicky.demo.aware.sub;

import org.springframework.stereotype.Component;

import com.quicky.demo.aware.FourElementAuthBase;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component("oneChanle2")
public class OneChanle2 extends FourElementAuthBase {

	@Override
	public String getAuthType() {
		log.info("getAuthType2");
		return null;
	}

	@Override
	public String doValSome() {
		log.info("doValSome2");
		return null;
	}

}
