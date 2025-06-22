package org.llin.twelvequotes.test.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llin.demo.twelvequotes.Constants;
import org.llin.demo.twelvequotes.config.TwelveQuotesConfig;
import org.llin.demo.twelvequotes.controller.QuotesController;
import org.llin.demo.twelvequotes.model.AllQuotes;
import org.llin.demo.twelvequotes.model.SingleQuote;
import org.llin.demo.twelvequotes.util.JsonUtilSingleQuote;
import org.llin.demo.twelvequotes.util.TestDataUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jakarta.servlet.http.HttpSession;

@SpringBootTest
@ContextConfiguration(classes = TwelveQuotesConfig.class)
public class QuotesControllerTest {
    private MockMvc mockMvc;
    private MockHttpSession mockSess;
    
    @Mock
    private HttpSession session;

    @MockBean
    private JsonUtilSingleQuote<SingleQuote> jsonUtilSingleQuote;

    @Mock
    private TwelveQuotesConfig quotesConfig; // Add mock for TwelveQuotesConfig
    
    @InjectMocks
    private QuotesController<SingleQuote> quotesController;
    
    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Mock behavior for TwelveQuotesConfig
        Mockito.when(quotesConfig.getQuotesPerPage()).thenReturn(20); // Example value
        Mockito.when(quotesConfig.getTabsPerLine()).thenReturn(10);   // Example value
        
        mockMvc = MockMvcBuilders.standaloneSetup(quotesController).build();
        mockSess = new MockHttpSession();

        mockSess.setAttribute(Constants.ALL_QUOTES, getAllQuotes());
    }

    @Test
    public void testShowList() throws Exception {
        mockMvc.perform(get("/quotes/list")
                .session(mockSess))
                .andExpect(status().isOk())
                .andExpect(view().name("quotes"));
    }

    @Test
    public void testGetSingleQuoteBySymbol() throws Exception {
        String symbol = "AAPL";

        mockMvc.perform(get("/quotes/" + symbol)
                .session(mockSess))
                .andExpect(status().isOk())
                .andExpect(view().name("quoteRequest"))
                .andExpect(model().attributeExists(Constants.QUOTE_REQUEST))
                .andExpect(model().attribute(Constants.QUOTE_REQUEST, hasProperty("stockTicker", equalTo(symbol))));
    }

    private AllQuotes<SingleQuote> getAllQuotes() throws IOException {
        // Mock API response
        List<SingleQuote> mockQuotes = TestDataUtil.getMockQuotes();
    	AllQuotes<SingleQuote> allQuotes = new AllQuotes<SingleQuote>(mockQuotes);
    	allQuotes.populateForOnlySelectedCountry();
    	allQuotes.populateTypeSet();
    	allQuotes.populateForOnlySelectedType();
    	return allQuotes;
    }
}