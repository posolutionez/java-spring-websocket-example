package com.solutionez.restandwebsockets.utils;

import java.util.UUID;

public class QuoteGenerator {

    public static String generateQuote(){
        return "this is a quote_" + UUID.randomUUID();
    }
}
