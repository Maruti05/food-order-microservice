package com.eatza.order;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.scheduling.annotation.EnableAsync;

import com.eatza.order.model.Order;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableKafkaStreams
@EnableAsync
@EnableKafka
public class OrderingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderingserviceApplication.class, args);
	}
	

	  @Bean
	    public NewTopic orders() {
	        return TopicBuilder.name("orders")
	                .partitions(1)
	                .compact()
	                .build();
	    }

	  @Bean
	    public KTable<Long, Order> table(StreamsBuilder builder) {
	        KeyValueBytesStoreSupplier store =
	                Stores.persistentKeyValueStore("orders");
	        JsonSerde<Order> orderSerde = new JsonSerde<>(Order.class);
	        KStream<Long, Order> stream = builder
	                .stream("orders", Consumed.with(Serdes.Long(), orderSerde));
	        return stream.toTable(Materialized.<Long, Order>as(store)
	                .withKeySerde(Serdes.Long())
	                .withValueSerde(orderSerde));
	    }
}
