package com.rabbitmq.rabbitlistener.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfig {

	@Bean
	public Queue getQueue() {
		return new Queue("ExampleQueue", false);
	}
	
	@Bean
	public Queue getQueueBuilder() {
		return QueueBuilder.durable("ExampleQueueBuilder")
				.autoDelete()
				.exclusive()
				.build();
	}
	
}
