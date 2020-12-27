package com.stacksimplify.restservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.services.OrderService;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		return orderService.getAllOrders(userid);
	}

	@PostMapping("/{userid}/createOrder")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		return orderService.createOrder(userid, order);
	}
	
	@GetMapping("/{userid}/order/{orderid}")
	public Order getOrderById(@PathVariable Long userid, @PathVariable Long orderid) 
			throws UserNotFoundException, OrderNotFoundException {
		return orderService.getOrderById(userid, orderid);
	}
}
