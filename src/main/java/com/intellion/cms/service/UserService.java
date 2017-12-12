package com.intellion.cms.service;

import com.intellion.cms.domain.User;

public interface UserService {
	public User findUserByName(String name);
	public void saveUser(User user);
}
