package org.llin.demo.twelvequotes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

//based on https://api.twelvedata.com/stocks

/**
 * {
  "symbol": "000001",
  "name": "Ping An Bank Co., Ltd.",
  "currency": "CNY",
  "exchange": "SZSE",
  "mic_code": "XSHE",
  "country": "China",
  "type": "Common Stock",
  "figi_code": "BBG000BZDQD3",
  "cfi_code": "ESVTFR",
  "isin": "request_access_via_add_ons",
  "cusip": "request_access_via_add_ons"
}
 */
@Entity
@Table(name = "all_quotes")
public class SingleQuote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String symbol;
	private String name;
	private String currency;
	private String exchange;
	private String mic_code;
	private String country;
	private String type;
	private String figi_code;
	private String cfi_code;
	private String isin;
	private String cusip;
	
	/* Related to pagination */

	@Transient
	private String firstSymbol;

	@Transient
	private String lastSymbol;

	@Transient
	private int groupIndex;

	@Transient
	private int quoteIndex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getMic_code() {
		return mic_code;
	}

	public void setMic_code(String mic_code) {
		this.mic_code = mic_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getFigi_code() {
		return figi_code;
	}

	public void setFigi_code(String figi_code) {
		this.figi_code = figi_code;
	}

	public String getCfi_code() {
		return cfi_code;
	}

	public void setCfi_code(String cfi_code) {
		this.cfi_code = cfi_code;
	}

	public String getIsin() {
		return isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public String getFirstSymbol() {
		return firstSymbol;
	}

	public void setFirstSymbol(String firstSymbol) {
		this.firstSymbol = firstSymbol;
	}

	public String getLastSymbol() {
		return lastSymbol;
	}

	public void setLastSymbol(String lastSymbol) {
		this.lastSymbol = lastSymbol;
	}

	public String getTab() {
		return firstSymbol + "-" + lastSymbol;
	}

	public int getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(int groupIndex) {
		this.groupIndex = groupIndex;
	}

	public int getQuoteIndex() {
		return quoteIndex;
	}

	public void setQuoteIndex(int quoteIndex) {
		this.quoteIndex = quoteIndex;
	}

	@Override
	public String toString() {
		return "SingleQuote [id=" + id + ", symbol=" + symbol + ", name=" + name + ", currency=" + currency
				+ ", exchange=" + exchange + ", mic_code=" + mic_code + ", country=" + country + ", type=" + type
				+ ", figi_code=" + figi_code + ", cfi_code=" + cfi_code + ", isin=" + isin + ", cusip=" + cusip
				+ ", firstSymbol=" + firstSymbol + ", lastSymbol=" + lastSymbol + ", groupIndex=" + groupIndex
				+ ", quoteIndex=" + quoteIndex + "]";
	}

}
