package org.llin.demo.twelvequotes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.twelve-quotes")
public class TwelveQuotesConfig {

	private String url;
	private String country;
	private String stockType;
	private int quotesPerPage;
	private int tabsPerLine;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStockType() {
		return stockType;
	}

	public void setStockType(String stockType) {
		this.stockType = stockType;
	}

	public int getQuotesPerPage() {
		return quotesPerPage;
	}

	public void setQuotesPerPage(int quotesPerPage) {
		this.quotesPerPage = quotesPerPage;
	}

	public int getTabsPerLine() {
		return tabsPerLine;
	}

	public void setTabsPerLine(int tabsPerLine) {
		this.tabsPerLine = tabsPerLine;
	}

	@Override
	public String toString() {
		return "TwelveQuotesConfig [url=" + url + ", country=" + country + ", stockType=" + stockType
				+ ", quotesPerPage=" + quotesPerPage + ", tabsPerLine=" + tabsPerLine + "]";
	}

}
