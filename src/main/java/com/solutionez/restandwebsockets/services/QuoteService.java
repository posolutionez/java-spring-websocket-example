package com.solutionez.restandwebsockets.services;

import com.solutionez.restandwebsockets.utils.QuoteGenerator;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service // Mark as a service so Spring manages it
public class QuoteService {

    // Inject the tool for sending messages to the WebSocket broker
    private final SimpMessagingTemplate messagingTemplate;

    // Constructor injection
    public QuoteService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // This method will be called automatically every 5000 milliseconds (5 seconds)
    @Scheduled(fixedRate = 5000)
    public void broadcastQuote() {

        // 1. Generate the message payload
        Map<String, String> quote = new HashMap<>();
        quote.put("quote", QuoteGenerator.generateQuote());

        System.out.println("Broadcasting new quote: " + quote.get("quote"));

        // 2. Send the message to the STOMP broker
        // The destination MUST match the topic the clients are subscribed to.
        messagingTemplate.convertAndSend("/topic/quotes", quote);
    }
}
