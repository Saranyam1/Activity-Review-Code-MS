package com.example.demo.Controller.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency_table {
	
	 @Id
	   String country_code;
	 	double currency;
	   int port;
	
	
	public Currency_table() {
		super();
	}
	
	public Currency_table(String country_code, double currency,int port) {
		super();
		this.country_code = country_code;
		this.currency = currency;
		this.port =port;
		
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public double getCurrency() {
		return currency;
	}
	public void setCurrency(double currency) {
		this.currency = currency;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
