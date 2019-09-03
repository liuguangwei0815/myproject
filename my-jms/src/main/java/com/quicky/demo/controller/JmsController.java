package com.quicky.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class JmsController {

	@Value("${kafka.topic}")
	private String topic;
	@Value("${kafka.groupId}")
	private String groupId;

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	/**
	 * 通过配置中心获取参数
	 * 
	 * @return
	 */
	@RequestMapping("/sendMsgTest")
	@ResponseBody
	public String sendMsgTest() {
		String data = "teee";
		// ListenableFuture 产生回调
		ListenableFuture<SendResult<Integer, String>> send = kafkaTemplate.send(topic, data);
		send.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
			public void onFailure(Throwable throwable) {
				System.out.println("失败");
			}
			public void onSuccess(SendResult<Integer, String> integerStringSendResult) {
				System.out.println("成功");
			}
		});
		return "";
	}

}
