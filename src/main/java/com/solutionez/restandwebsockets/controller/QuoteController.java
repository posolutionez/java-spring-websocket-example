package com.solutionez.restandwebsockets.controller;


import com.solutionez.restandwebsockets.utils.QuoteGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/quote")
public class QuoteController {


    @GetMapping("/")
    public Map<String, String> getQuote() {
        Map<String, String> response = new HashMap<>();
        response.put("quote", QuoteGenerator.generateQuote());
        return response;
    }
}
