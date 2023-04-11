package com.shopme.customer;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	
	//List of countries displayed in the registration form
	//åutting list, pagetitle and new customer obj onto the form
	@GetMapping("/register")
    public String showRegisterForm(Model model) {
	List<Country> listCountries = service.listAllCountries();
	
	model.addAttribute("listCountries",listCountries);
	model.addAttribute("pageTitle", "Customer Registration");
	model.addAttribute("customer", new Customer());
	
	return "register/register_form";
}
	
	//For sumbission of create customer form 
	@PostMapping("/create_customer")
	public String createCustomer(Customer customer, Model model) {
	service.registerCustomer(customer);
	
	model.addAttribute("pageTitle", "Registration Succeeded!");
	
	return "/register/register_success";
	}
}
