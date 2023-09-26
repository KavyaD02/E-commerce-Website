package com.akshaya.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.akshaya.ecommerce.BaseTest;
import com.akshaya.ecommerce.domain.Customer;

public class CustomerServiceTest extends BaseTest{
	
	@Autowired
	CustomerService customerService; 
	
	@Test
	public void allCustomersTest() {
		List<Customer> customers = customerService.getCustomers();
		System.out.println("Number of customers: " + customers.size());
		assertTrue(customers != null, "Customers list must not be null");
		assertTrue(customers.size() > 0, "Customers list must not be empty");
		for (Customer customer: customers) {
			System.out.println(customer.getCustomerName());
		}
	}

}
