package com.example.demo.service;

import com.example.demo.domain.User;

public interface UserService {
	public User findUserByEmail(String email);
	public User findUserByName(String name);
	public void saveUser(User user);
}
