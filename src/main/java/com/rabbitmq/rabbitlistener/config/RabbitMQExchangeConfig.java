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
	
	@Bean
	public Exchange getTopicExchangeUsingBuilder() {
		return ExchangeBuilder.topicExchange("TopicExchangeUsingBuilder")
				.autoDelete()
				.durable(true)
				.internal()
				.build();
	}
	
	@Bean
	public Exchange getFanoutExchangeUsingBuilder() {
		return ExchangeBuilder.fanoutExchange("FanoutExchangeUsingBuilder")
				.autoDelete()
				.durable(false)
				.internal()
				.build();
	}
	
	@Bean
	public Exchange getHeadersExchangeUsingBuilder() {
		return ExchangeBuilder.headersExchange("HeadersExchangeUsingBuilder")
				.autoDelete()
				.durable(true)
				.ignoreDeclarationExceptions()
				.build();
	}
	
}
