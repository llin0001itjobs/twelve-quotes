package org.llin.twelvequotes.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llin.demo.twelvequotes.Constants;
import org.llin.demo.twelvequotes.config.TwelveQuotesConfig;
import org.llin.demo.twelvequotes.controller.CountryController;
import org.llin.demo.twelvequotes.model.AllQuotes;
import org.llin.demo.twelvequotes.model.SingleQuote;
import org.llin.demo.twelvequotes.util.QuotesRetriever;
import org.llin.demo.twelvequotes.util.TestDataUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jakarta.servlet.http.HttpSession;

@SpringBootTest
@ContextConfiguration(classes = TwelveQuotesConfig.class)
public class CountryControllerTest {
    private MockMvc mockMvc;
    private MockHttpSession mockSess;
    
    @Mock
    private HttpSession session;
	
    @InjectMocks
    private CountryController<SingleQuote> countryController;
    
    @Mock
    private QuotesRetriever<SingleQuote> quotesRetriever;
    
    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.openMocks(this);        
        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
        mockSess = new MockHttpSession();
        mockSess.setAttribute(Constants.ALL_QUOTES, getAllQuotes());
    }    
    
    @Test
    public void testGetAllQuotes() throws Exception {
        mockMvc.perform(get("/country/list")
        		.session(mockSess)
        		.param("selectedCountry", "US")
        	)
            .andExpect(status().isOk())
            .andExpect(view().name("country"));
    }    
    
    @Test
    public void testHandleFormSubmissionForStockTypeRedirect() throws Exception {
        mockMvc.perform(post("/country/submit")
        		.session(mockSess)
        		.param("selectedCountry", "US")
        		.param("submit", "stockType")
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/stockType/list"));

    }
        
    @Test    
    public void testHandleFormSubmissionForListQuotesRedirect() throws Exception {
        mockMvc.perform(post("/country/submit")
        		.session(mockSess)
        		.param("selectedCountry", "US")
        		.param("submit", "listQuotes")
        	)
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/quotes/list"));
    }    
    
    private AllQuotes<SingleQuote> getAllQuotes() throws IOException {
        // Mock API response
        List<SingleQuote> mockQuotes = TestDataUtil.getMockQuotes(); 
    	AllQuotes<SingleQuote> allQuotes = new AllQuotes<SingleQuote>(mockQuotes); 
    	allQuotes.populateForOnlySelectedCountry();
    	return allQuotes;
    }
    
}
