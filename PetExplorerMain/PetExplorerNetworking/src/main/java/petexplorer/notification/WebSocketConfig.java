package petexplorer.notification;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws-stomp")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // configurăm prefixul pentru topicuri la care vom publica
        registry.enableSimpleBroker("/topic", "/queue");
        // prefix de pe care clapetele clienților vor trimite mesaje către @MessageMapping
        registry.setApplicationDestinationPrefixes("/app");
        // prefix pentru mesaje direcționate unui singur user
        registry.setUserDestinationPrefix("/user");
    }
}