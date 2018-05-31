package com.liftkarade.app.service;

import java.util.List;

import com.liftkarade.app.model.PoolTrip;
import com.liftkarade.app.model.Transaction;
import com.liftkarade.app.model.Trip;
import com.liftkarade.app.model.User;
import com.liftkarade.app.model.Vehicle;
import com.liftkarade.app.model.Wallet;

public interface IUserService {
	
	User addUser(User user) throws Exception;
	PoolTrip addPoolTrip(PoolTrip poolTrip) throws Exception;
	Trip addTrip(Trip trip) throws Exception;
	Vehicle addVehicle(Vehicle vehicle) throws Exception;
	Transaction makeTransaction(Transaction transaction) throws Exception;
	void updateProfile(User user) throws Exception;
	boolean transferAmountWithWallet(Wallet fromWallet,Wallet toWallet, double amount) throws Exception;
	double checkWalletBalance(User user) throws Exception;
	boolean addWalletBalance(User user, double amount) throws Exception;
	List<Trip> getTrips(User user) throws Exception;
	List<PoolTrip> getPoolTrips(User user) throws Exception;
	Trip getTrips(Trip trip) throws Exception;
	PoolTrip getPoolTrip(PoolTrip poolTrip) throws Exception;
	Transaction getTransaction(Transaction transaction) throws Exception;
	List<Transaction> getAllTransaction(User user) throws Exception;
	Vehicle getVehicle(Vehicle vehicle) throws Exception;
	List<Vehicle> getAllVehicle(User user) throws Exception;
	

}
