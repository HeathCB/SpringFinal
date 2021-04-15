package com.promineotech.restaurantManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.promineotech.restaurantManagement.entity.Restaurant;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>  {

}
