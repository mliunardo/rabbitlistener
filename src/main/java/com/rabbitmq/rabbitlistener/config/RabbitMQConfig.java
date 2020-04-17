package com.rabbitmq.rabbitlistener.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.rabbitlistener.component.RabbitMQMessageListener;


@Configuration
public class RabbitMQConfig {

	private static final String MY_QUEUE = "MyQueue";
	
	@Bean
	public Queue myQueue() {
		return new Queue(MY_QUEUE, true);
	}
	
	@Bean
	public Exchange myExchange() {
		return ExchangeBuilder.topicExchange("myTopicExchange")
				.durable(true)
				.build();
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setPassword("guest");
		return cachingConnectionFactory;
	}
	
	@Bean
	public MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(myQueue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
		return simpleMessageListenerContainer;
	}
	
	/*
	 * Creating Binding
	 */
	
	@Bean
	public Binding binding() {
		/*
		 * Traditional way of Binding 
		 *
		
		return new Binding(MY_QUEUE, Binding.DestinationType.QUEUE, "myTopicExchange", "topic", null);
		*/
		
		return BindingBuilder
				.bind(myQueue())
				.to(myExchange())
				.with("topic")
				.noargs();
	}
	
	
}
