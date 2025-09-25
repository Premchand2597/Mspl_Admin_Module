package com.example.mspl_connect;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
	    config.enableSimpleBroker("/topic", "/queue"); // Allow messaging in /queue
	    config.setApplicationDestinationPrefixes("/app");
	    config.setUserDestinationPrefix("/user"); // Specify the user destination prefix
	}


	    @Override
	    public void registerStompEndpoints(StompEndpointRegistry  registry) {
	        registry.addEndpoint("/chat-websocket").withSockJS();
	    }
	    
	    /*   @Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	        registry.addEndpoint("/ws").withSockJS();
	    }*/
	
}
