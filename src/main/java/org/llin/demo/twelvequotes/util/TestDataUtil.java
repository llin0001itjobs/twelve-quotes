package org.llin.demo.twelvequotes.util;

import java.util.Arrays;
import java.util.List;

import org.llin.demo.twelvequotes.model.SingleQuote;

public class TestDataUtil {

    public static List<SingleQuote> getMockQuotes() {
        SingleQuote quote1 = new SingleQuote();
        quote1.setSymbol("AAPL");
        quote1.setName("Apple Inc.");
        quote1.setCurrency("USD");
        quote1.setExchange("NASDAQ");
        quote1.setMic_code("XNAS");
        quote1.setCountry("United States");
        quote1.setType("Common Stock");

        SingleQuote quote2 = new SingleQuote();
        quote2.setSymbol("GOOGL");
        quote2.setName("Alphabet Inc.");
        quote2.setCurrency("USD");
        quote2.setExchange("NASDAQ");
        quote2.setMic_code("XNAS");
        quote2.setCountry("United States");
        quote2.setType("Common Stock");

        return Arrays.asList(quote1, quote2);
    }

    public static String getMockJson() {
        return "{\"data\":[" +
                "{\"symbol\":\"AAPL\",\"name\":\"Apple Inc.\",\"currency\":\"USD\",\"exchange\":\"NASDAQ\",\"mic_code\":\"XNAS\",\"country\":\"United States\",\"type\":\"Common Stock\"}," +
                "{\"symbol\":\"GOOGL\",\"name\":\"Alphabet Inc.\",\"currency\":\"USD\",\"exchange\":\"NASDAQ\",\"mic_code\":\"XNAS\",\"country\":\"United States\",\"type\":\"Common Stock\"}" +
                "]}";
    }
}