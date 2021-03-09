package com.ly.kafka;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ly.kafka.components.Producer;
import com.ly.kafka.components.Topic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class KafkaTestApplication implements ApplicationRunner{
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private Topic topic;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaTestApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		producer.sendMsg("test","this is a msg");
//		topic.createTopic("test_topic2", 5, (short) 1);
		Set<String> topics = topic.ListTopics();
		topics.forEach((String topic)-> {
			log.info(topic);
		});
	}

}
