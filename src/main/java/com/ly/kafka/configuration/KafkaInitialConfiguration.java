package com.ly.kafka.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaInitialConfiguration {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String servers;	
	
	@Bean
	public NewTopic NewTopic() {
		return TopicBuilder.name("test_topic1")
				.partitions(10)
				.replicas(1)
				.build();
	}
	
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> props = new HashMap<>();
		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		KafkaAdmin admin = new KafkaAdmin(props);
		return admin;
	}
	
	@Bean
	public AdminClient adminClient() {
		return AdminClient.create(kafkaAdmin().getConfigurationProperties());
	}

}
