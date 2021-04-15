package com.promineotech.restaurantManagement.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.restaurantManagement.util.ReservationStatus;

@Entity
public class Reservation {

		private Long id;
		private Set<User> reservation_group;
		private ReservationStatus status;
		
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private String reservation_time; 
		
		@JsonIgnore
		private Restaurant restaurant;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getReservation_time() {
			return reservation_time;
		}
		public void setReservation_time(String reservation_time) {
			this.reservation_time = reservation_time;
		}
		
		//TODO gave this many to many annotation. Not sure about mappedBy
		@ManyToMany(mappedBy = "reservations")
		public Set<User> getReservation_group() {
			return reservation_group;
		}
		public void setReservation_group(Set<User> reservation_group) {
			this.reservation_group = reservation_group;
		}
		//TODO added many to one. 
		@ManyToOne
		@JoinColumn(name = "restaurantId")
		public Restaurant getRestaurant() {
			return restaurant;
		}
		public void setRestaurant(Restaurant restaurant) {
			this.restaurant = restaurant;
		}
		public ReservationStatus getStatus() {
			return status;
		}
		public void setStatus(ReservationStatus status) {
			this.status = status;
		}
		public void setSubmitted(String reservation_time2) {
			// TODO Auto-generated method stub
			
		}
		public void setBooked(String reservation_time2) {
			// TODO Auto-generated method stub
			
		}
		
		 
}
