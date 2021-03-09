package com.ly.kafka.components;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consmer {
	
	/**
	 * 单个消费
	 * @param record
	 */
	@KafkaListener(topics="test")    
	public void consumer(ConsumerRecord<?, ?> record) {
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());        
		if (kafkaMessage.isPresent()) {            
			Object message = kafkaMessage.get();            
			log.info("----------------- record :" + record);            
			log.info("------------------ message : " + message);       
		}    
	}
	
	
	/**
	 * 指定GroupId单个消费
	 * @param record
	 */
	@KafkaListener(groupId="testGroup-1",  topics="test")    
	public void consumerWithGroupId(ConsumerRecord<?, ?> record) {
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());        
		if (kafkaMessage.isPresent()) {            
			Object message = kafkaMessage.get();            
			log.info("----------------- record :" + record);            
			log.info("------------------ message : " + message);       
			}    
	}
	
	
	/**
	 * 批量消费
	 * @param records
	 */
	@KafkaListener(topics = "test",containerFactory="batchFactory")
    public void consumerBatch(List<ConsumerRecord<?, ?>> records){
        log.info("接收到消息数量：{}",records.size());
        for(ConsumerRecord<?, ?> record : records) {
    		Optional<?> kafkaMessage = Optional.ofNullable(record.value());        
    		if (kafkaMessage.isPresent()) {            
    			Object message = kafkaMessage.get();            
    			log.info("----------------- record :" + record);            
    			log.info("------------------ message : " + message);       
    		}  
        }
	}
	
	
	/**
	 * 批量消费
	 * @param records
	 */
	@KafkaListener(groupId="testGroup-2",  topics = "test",containerFactory="batchFactory")
    public void consumerBatchWithGroupId(List<ConsumerRecord<?, ?>> records){
        log.info("接收到消息数量：{}",records.size());
        for(ConsumerRecord<?, ?> record : records) {
    		Optional<?> kafkaMessage = Optional.ofNullable(record.value());        
    		if (kafkaMessage.isPresent()) {            
    			Object message = kafkaMessage.get();            
    			log.info("----------------- record :" + record);            
    			log.info("------------------message : " + message);       
    		}  
        }
	}
	
	/**
	 * 批量消费
	 * @param records
	 */
	@KafkaListener(groupId="testGroup-3",  topics = "test",containerFactory="batchFactory")
    public void consumerBatchWithConsumerRecords(ConsumerRecords<String, String> records){
        log.info("接收到消息数量：{}",records.count());
        records.forEach((ConsumerRecord<String, String> record) -> {
    		Optional<?> kafkaMessage = Optional.ofNullable(record.value());        
    		if (kafkaMessage.isPresent()) {            
    			Object message = kafkaMessage.get();            
    			log.info("----------------- record :" + record);            
    			log.info("------------------message : " + message);       
    		}  
        });
	}
}
