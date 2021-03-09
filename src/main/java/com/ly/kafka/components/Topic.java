package com.ly.kafka.components;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Topic {
	
	@Autowired
	private AdminClient adminClient;
	
	public void createTopic(String topicName, int patitionsNum, short replicationFactor) {
		NewTopic topic = new NewTopic(topicName,patitionsNum,replicationFactor);
		adminClient.createTopics(Arrays.asList(topic));
	}
	
	public Set<String> ListTopics() throws InterruptedException, ExecutionException {
		ListTopicsResult  topicList =    adminClient.listTopics();
		Set<String> topics = topicList.names().get();
		return topics;
	}
}
