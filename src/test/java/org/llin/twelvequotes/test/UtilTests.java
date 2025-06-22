package org.llin.twelvequotes.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.llin.demo.twelvequotes.config.TwelveQuotesConfig;
import org.llin.demo.twelvequotes.model.AllQuotes;
import org.llin.demo.twelvequotes.model.QuoteDetailResponse;
import org.llin.demo.twelvequotes.model.SingleQuote;
import org.llin.demo.twelvequotes.util.ChunkerUtil;
import org.llin.demo.twelvequotes.util.JsonUtilQuoteDetailResponse;
import org.llin.demo.twelvequotes.util.JsonUtilSingleQuote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;

import com.opencsv.exceptions.CsvValidationException;

@SpringBootTest
@ContextConfiguration(classes = TwelveQuotesConfig.class)
public class UtilTests {
	
	@Value("${api.twelve-quotes.url}")
	private String _url;
	
    @Value("${api.twelve-quotes.quotesPerPage}")
    private int quotesPerPage;

    @Value("${api.twelve-quotes.tabsPerLine}")
    private int tabsPerLine;
    
    @Test
    void testChunker() throws IOException {
		JsonUtilSingleQuote<SingleQuote> jsUtil = new JsonUtilSingleQuote<>(_url);
		List<SingleQuote> list = jsUtil.retrieveObject();
		AllQuotes<SingleQuote> aq = new AllQuotes<>(list); 
		ChunkerUtil.chunkList(aq, quotesPerPage, tabsPerLine);
	}
    
    /**
     * _url is wrong for this test
     * @throws HttpClientErrorException
     * @throws CsvValidationException
     * @throws NumberFormatException
     * @throws IOException
     */
    void testJsonUtilQuoteDetail() throws HttpClientErrorException, CsvValidationException, NumberFormatException, IOException {
    	JsonUtilQuoteDetailResponse juQdr = new JsonUtilQuoteDetailResponse(_url);
        // Act
        List<QuoteDetailResponse> result = juQdr.retrieveObject(); // Adjust type as per your actual return type
        
        // Assert
        assertNotNull(result, "The result list should not be null");
        assertTrue(result.size() > 0, "The result list should have more than 0 elements");
    	
    }   
    
}
