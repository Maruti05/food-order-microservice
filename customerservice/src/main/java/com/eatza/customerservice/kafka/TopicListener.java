package com.eatza.customerservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.eatza.customerservice.dto.Order;
import com.eatza.customerservice.service.customerservice.CustomerServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {
	private String topicName="orders";
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	   @KafkaListener(topics = "orders", groupId = "group_id")
	    public void consume(ConsumerRecord<String, String> payload){
		   log.info("Tópico: {}", topicName);
	        log.info("key: {}", payload.key());
	        log.info("Headers: {}", payload.headers());
	        log.info("Partion: {}", payload.partition());
	        log.info("Order: {}", payload.value());

	    }
}
