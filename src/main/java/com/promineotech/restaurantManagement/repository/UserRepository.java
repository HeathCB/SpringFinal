package com.promineotech.restaurantManagement.repository;

import org.springframework.data.repository.CrudRepository;


import com.promineotech.restaurantManagement.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {

	public User findByuserName(String userName);
	
	
}
