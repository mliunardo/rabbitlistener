package com.rabbitmq.rabbitlistener.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQExchangeConfig {

	@Bean
	public Exchange getExchange() {
		return new TopicExchange("ExampleExchange");
	}
	
	@Bean
	public Exchange getExchangeUsingBuilder() {
		return ExchangeBuilder.directExchange("DirectExchangeUsingBuilder")
				.autoDelete()
				.internal()
				.build();
	}
	
}
