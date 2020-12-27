package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.Order;
import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.OrderNotFoundException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Order> getAllOrders(Long userid) throws UserNotFoundException {
		Optional<User> optionalUser = userService.getUserById(userid);
		return optionalUser.get().getOrders();
	}
	
	public Order createOrder(Long userid, Order order) throws UserNotFoundException {
		Optional<User> optionalUser = userService.getUserById(userid);
		order.setUser(optionalUser.get());
		return orderRepository.save(order);
	}
	
	public Order getOrderById(Long userid, Long orderid) throws UserNotFoundException, OrderNotFoundException {
		Optional<Order> optionalOrder = orderRepository.findById(orderid);
		if (!optionalOrder.isPresent() || optionalOrder.get().getUser().getId() != userid) {
			throw new OrderNotFoundException("Order not found in the order repository for given userid and orderid");
		}
		return optionalOrder.get();
	}
}
