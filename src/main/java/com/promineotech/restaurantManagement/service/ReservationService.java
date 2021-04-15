package com.promineotech.restaurantManagement.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.restaurantManagement.entity.Reservation;
import com.promineotech.restaurantManagement.entity.Restaurant;
import com.promineotech.restaurantManagement.entity.User;
import com.promineotech.restaurantManagement.repository.ReservationRepository;
import com.promineotech.restaurantManagement.repository.RestaurantRepository;
import com.promineotech.restaurantManagement.repository.UserRepository;
import com.promineotech.restaurantManagement.util.ReservationStatus;

@Service
public class ReservationService {

		private static final Logger logger = LogManager.getLogger(ReservationService.class);
		
		@Autowired
		private ReservationRepository resRepo;
		
		@Autowired
		private UserRepository userRepo;
		
		@Autowired
		private RestaurantRepository restRepo;
		
		
		public Reservation submitNewReservation(Set<Long> userId, Long restaurantId) throws Exception {
			try {
				Restaurant restaurant = restRepo.findOne(restaurantId);
				Reservation reservation = initializeNewReservation(userId, restaurant);
				return resRepo.save(reservation);
			} catch (Exception e) {
				logger.error("Exception occurred while tring to create new reservation for user: " + userId, e);
				throw e;
			}
		}
		private Reservation initializeNewReservation(Set<Long> userIds, Restaurant restaurant) {
			Reservation reservation = new Reservation();
			reservation.setReservation_group(convertToUserSet(userRepo.findAll(userIds)));
			reservation.setSubmitted(reservation.getReservation_time());
			reservation.setRestaurant(restaurant);
			reservation.setStatus(ReservationStatus.SUBMITTED);
			addReservationToUsers(reservation);
			return reservation;
		}
		// gets the reservations, for each one, makes sure users are added to reservation. Makes relationship symmetric.
		private void addReservationToUsers(Reservation reservation) {
				Set<User> users = reservation.getReservation_group();
				for (User user : users) {
					user.getReservations().add(reservation);
				}
		}
		public Reservation cancelReservation(Long reservationId) throws Exception {
			try {
				Reservation reservation = resRepo.findOne(reservationId);
				reservation.setStatus(ReservationStatus.CANCELLED);
				return resRepo.save(reservation);
			} catch (Exception e) {
				logger.error("Exception occurred while trying to cancel reservation: " + reservationId, e);
				throw new Exception("Unable to update order.");
			}
		}
		 // the method below is for final booking of the reservation
		public Reservation completeReservation(Long reservationId) throws Exception {
			try {
				Reservation reservation = resRepo.findOne(reservationId);
				reservation.setStatus(ReservationStatus.BOOKED);
				reservation.setBooked( reservation.getReservation_time() );
				return resRepo.save(reservation);
			} catch (Exception e) {
				logger.error("Exception occurred while trying to complete reservation: " + reservationId, e);
				throw new Exception("Unable to complete reservation.");
			}
		}
		public Reservation getReservationById(Long id) throws Exception {
			try {
				return resRepo.findOne(id);
			} catch (Exception e) {
				logger.error("Exception occurred while trying to retrieve reservation: " + id, e);
				throw e;
			}
		}
		private Set<User> convertToUserSet (Iterable<User> iterable) {
			Set<User> set = new HashSet<User>();
			for (User user : iterable) {
				set.add(user);
			}
			return set;
		}
}
