package com.solutionez.restandwebsockets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // 1. Enables WebSocket message handling, backed by a message broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 2. Registers the STOMP endpoint where clients will connect
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // The endpoint the client connects to (SockJS will use this path)
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:8000")
                // Enables SockJS fallback options for clients that don't support WebSockets
                .withSockJS();
    }

    /**
     * 3. Configures the message broker options
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        // Defines the prefix for destinations handled by controllers (e.g., @MessageMapping)
        // Client sends messages to: /app/{your-mapping} (e.g., /app/subscribe)
        config.setApplicationDestinationPrefixes("/app");

        // Defines the prefix for destinations used to send messages to the client
        // This is typically the topic the client subscribes to (e.g., /topic/quotes)
        config.enableSimpleBroker("/topic/quotes");
    }
}