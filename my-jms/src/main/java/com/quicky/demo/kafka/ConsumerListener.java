package com.quicky.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
public class ConsumerListener {
	

    /**
     *  一旦配置好的kafka，并且指定下的topic有信息生产，spring会监听到kafka的信息
     * @param message 消费信息
     */
    @KafkaListener(topics = "mykafkatopic")
    public void listen(String message) {
    	System.out.println("接收到kafka信息:"+message);
        //这里消费kafka信息
    }
}