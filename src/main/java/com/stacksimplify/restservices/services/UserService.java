package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException {
		User tempUser = userRepository.findByUsername(user.getUsername());
		if (tempUser != null) {
			throw new UserExistsException("User already exists in the Repository");
		}
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in the Repository");
		}
		return user;
	}
	
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> tempUser = userRepository.findById(id);
		if (!tempUser.isPresent()) {
			throw new UserNotFoundException("User not found in the Repository, please provide a valid user id!");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		Optional<User> tempUser = userRepository.findById(id);
		if (!tempUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User not found in the Repository, please provide a valid user id to be deleted!");
		}
		userRepository.deleteById(id);
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
