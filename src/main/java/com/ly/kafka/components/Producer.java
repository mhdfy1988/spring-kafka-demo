package com.ly.kafka.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	@Autowired
	private KafkaTemplate<String,String>  template;
	
	public void sendMsg(String topic,String msg) {
		template.send(topic, msg);
	}
}
