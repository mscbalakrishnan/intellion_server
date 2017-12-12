package com.intellion.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellion.cms.domain.User;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByName(String name);
}
