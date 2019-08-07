package com.quicky.demo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @JmsListener(destination = "publish.queue", containerFactory = "jmsListenerContainerQueue")
    //@SendTo("out.queue")
    public String receive(String text){
        System.out.println("QueueListener: consumer-a111 收到一条信息: " + text);
        return "consumer-a received : " + text;
    }
}