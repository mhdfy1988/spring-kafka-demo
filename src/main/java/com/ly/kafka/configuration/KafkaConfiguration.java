package com.ly.kafka.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@Configuration
public class KafkaConfiguration {
	
	/**
	 * 批量
	 * @param consumerFactory
	 * @return
	 */
	@Bean
	public KafkaListenerContainerFactory<?> batchFactory(ConsumerFactory consumerFactory){
	    ConcurrentKafkaListenerContainerFactory<Integer,String> factory =
	    new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory);
	    factory.setConcurrency(10);
	    factory.getContainerProperties().setPollTimeout(1500);
	    factory.setBatchListener(true);//设置为批量消费，每个批次数量在Kafka配置参数中设置
	    return factory; 
	}
	
	/**
	 * 批量且手动提交
	 * @param consumerFactory
	 * @return
	 */
	@Bean
	public KafkaListenerContainerFactory<?> batchAndNoAutoFactory(ConsumerFactory consumerFactory){
	    ConcurrentKafkaListenerContainerFactory<Integer,String> factory =
	    new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory);
	    factory.setConcurrency(10);
	    factory.getContainerProperties().setPollTimeout(1500);
	    factory.setBatchListener(true);//设置为批量消费，每个批次数量在Kafka配置参数中设置
	    //设置手动提交模式
	    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
	    return factory; 
	}
	
}
