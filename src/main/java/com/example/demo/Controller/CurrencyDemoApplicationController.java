package com.example.demo.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Controller.domain.Currency_table;
import com.example.demo.Controller.domain.repository.CurrencyRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CurrencyDemoApplicationController {
		
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	  @Autowired 
	  private CurrencyRepository currencyRepository;
	
	  @Autowired
	  private Environment environment;
	
	  @HystrixCommand(fallbackMethod = "fallbackupdateConversionFactor")
	@PutMapping("/update-conversion-factor/country-code/{countryCode}/currency-amt/{currency}")
	public String updateConversionFactor(@PathVariable String countryCode,@PathVariable int currency)  {
		 List<Currency_table> rec = currencyRepository.findAll(); 
		 boolean isfound = false;
		 for (Currency_table currency_table : rec) {
			
			if(currency_table.getCountry_code().equalsIgnoreCase(countryCode)) {
				currency_table.setCurrency(currency);
			 currencyRepository.save(currency_table);
			 isfound = true;
			 return " Successfully Updated";
			}
		}
		 if (!isfound) throw new RuntimeException("No Record");	
			return ""; 
	
	}
	
	  public String fallbackupdateConversionFactor(@PathVariable String countryCode,@PathVariable int currency)  {
		  return "fall back method called No record found";
	  }
		
	  
	
	  @GetMapping("/currency-conversion-factor/country-code/{country_code}") 
	  public Currency_table getConversionFactor(@PathVariable String country_code) {
		
		 Currency_table  rec = currencyRepository.getOne(country_code) ;
		 String portval = environment.getProperty("local.server.port");
		 rec.setPort(Integer.parseInt(portval));
		 logger.info("currecy-conversion-factor" + rec);
		 return rec;
		 
		  
	  }
	  
	@PostMapping("/add-conversion-factor")
	public Currency_table addConversionFactor(@RequestBody Currency_table currencydetails) {
		Currency_table added_currency = currencyRepository.save(currencydetails);
		return added_currency;
	}
	
	
	
	
}
