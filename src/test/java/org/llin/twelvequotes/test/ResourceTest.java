package org.llin.twelvequotes.test;

import org.junit.jupiter.api.Test;
import org.llin.demo.twelvequotes.config.TwelveQuotesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TwelveQuotesConfig.class)
public class ResourceTest {

	@Autowired
    private TwelveQuotesConfig quotesConfig;
	    
    @Test
    void yourTest() {
        System.out.println("Quotes Per Page: " + quotesConfig.getQuotesPerPage());
        System.out.println("Tabs Per Line: " + quotesConfig.getTabsPerLine());
        // Test code using quotesPerPage and tabsPerLine
    }
}
