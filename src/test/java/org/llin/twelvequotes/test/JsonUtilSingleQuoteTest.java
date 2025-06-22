package org.llin.twelvequotes.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.llin.demo.twelvequotes.model.SingleQuote;
import org.llin.demo.twelvequotes.util.JsonUtilSingleQuote;

class JsonUtilSingleQuoteTest {

    private static JsonUtilSingleQuote<SingleQuote> jsonUtil;

    @BeforeAll
    static void setUp() throws IOException {
    	jsonUtil = new JsonUtilSingleQuote<>("https://api.twelvedata.com/stocks");
        // Preload cache with real API data
        jsonUtil.retrieveObject();
    }

    @AfterAll
    static void tearDown() {
        // Clear cache to prevent interference with other test classes
        JsonUtilSingleQuote.clearCache();
    }

    @Test
    void testRetrieveObject() throws IOException {
        List<SingleQuote> result = jsonUtil.retrieveObject();
        assertNotNull(result);
        assertFalse(result.isEmpty(), "API response should not be empty");
        // Additional assertions (e.g., check specific fields)
        //assertTrue(result.size() <= 100, "Response should respect API limit");
    }
}