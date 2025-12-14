package com.solutionez.restandwebsockets.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class MessagingController {


    @MessageMapping("/subscribe") // Client sends a message to this destination
    @SendTo("/topic/quotes")    // Server broadcasts the response to this destination
    public Map<String, String> streamQuote(Map<String, String> message) {
        System.out.println("got a message?");
        // Business logic to get a quote and return it
        Map<String, String> response = new HashMap<>();
        response.put("quote", "WebSocket Quote: " + UUID.randomUUID());
        return response;
    }
}
