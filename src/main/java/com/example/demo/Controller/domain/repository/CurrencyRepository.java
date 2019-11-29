package com.example.demo.Controller.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Controller.domain.Currency_table;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency_table, String>{
	

}
