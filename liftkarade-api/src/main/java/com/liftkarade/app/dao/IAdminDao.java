package com.liftkarade.app.dao;

import java.util.List;

import com.liftkarade.app.model.PoolTrip;
import com.liftkarade.app.model.Transaction;
import com.liftkarade.app.model.Trip;
import com.liftkarade.app.model.User;
import com.liftkarade.app.model.Vehicle;

public interface IAdminDao {

	boolean verifyUser(User user) throws Exception;
	boolean blockUser(User user) throws Exception;
	boolean unblockUser(User user) throws Exception;
	boolean verifyVehicle(Vehicle vehicle) throws Exception;
	User getUser(User user) throws Exception;
	List<User> getAllUser() throws Exception;
	List<Vehicle> getAllVehicle() throws Exception;
	Vehicle getVehicle(Vehicle vehicle) throws Exception;
	boolean removeVehicle(Vehicle vehicle) throws Exception;
	double checkWalletBalance(User user) throws Exception;
	boolean addWalletBalance(User user, double amount) throws Exception;
	boolean deductWalletBalance(User user, double amount) throws Exception;
	List<Trip> getTrips(User user) throws Exception;
	List<PoolTrip> getPoolTrips(User user) throws Exception;
	List<Trip> getTrips() throws Exception;
	List<PoolTrip> getPoolTrips() throws Exception;
	Trip getTrips(Trip trip) throws Exception;
	PoolTrip getPoolTrip(PoolTrip poolTrip) throws Exception;
	List<Transaction> getAllTransaction(User user) throws Exception;
	Transaction getTransaction(Transaction transaction) throws Exception;
	List<Transaction> getAllTransaction() throws Exception;

}
