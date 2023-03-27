package com.shopme.admin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shopme.common.entity.User;

@Controller
public class UserController {
	
	@Autowired    //inject an instance of user service at runtime 
	private UserService service;
	
	//link to user in navbar
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
		
	}
	
	//maps to users.html
	//map value of formfield to model / form
	@GetMapping("/users/new")
	public String newUser(Model model) {
		User user =   new User();
		model.addAttribute("user",user);
		return "user_form";
		
	}
	
	//when pushing save button, map values of form fields to user objects
	@PostMapping("/users/save")
	public String saveUser(User user) {
		System.out.println(user);  //to string in User class
		return "redirect:/users";
	}
	

}