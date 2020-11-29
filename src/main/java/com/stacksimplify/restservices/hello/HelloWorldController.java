package com.stacksimplify.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	//URI - /helloworld
	//GET
	//@RequestMapping(method = RequestMethod.GET, path="helloworld")
	@GetMapping("helloworld1")
	public String helloWorld() {
		return "Hello World again!";
	}
	
	@GetMapping("helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Nilay", "Vora", "Pune");
	}
}
