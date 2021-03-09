package com.ly.kafka.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	@Autowired
	private KafkaTemplate<String,String>  template;
	
	/**
	 * 单条生产
	 * @param topic
	 * @param msg
	 */
	public void sendMsg(String topic,String msg) {
		template.send(topic, msg);
	}
	
	/**
	 * 批量生产
	 * @param topic
	 * @param msgList
	 */
	public void sendMsg(String topic,List<String> msgList) {
		template.send(topic, msgList.toString());
	}
}
