package com.liftkarade.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liftkarade.app.dao.AdminDao;
import com.liftkarade.app.model.PoolTrip;
import com.liftkarade.app.model.Transaction;
import com.liftkarade.app.model.Trip;
import com.liftkarade.app.model.User;
import com.liftkarade.app.model.Vehicle;

@Service
public class AdminService implements IAdminService {

	@Autowired
	private AdminDao dao;
	
	@Override
	public boolean verifyUser(User user) throws Exception {
		return dao.verifyUser(user);
	}

	@Override
	public boolean blockUser(User user) throws Exception {
		return dao.blockUser(user);
	}

	@Override
	public boolean unblockUser(User user) throws Exception {
		return dao.unblockUser(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public boolean verifyVehicle(Vehicle vehicle) throws Exception {
		return dao.verifyVehicle(vehicle);
	}

	@Override
	public User getUser(User user) throws Exception {
		return dao.getUser(user);
	}

	@Override
	public List<User> getAllUser() throws Exception {
		return dao.getAllUser();
	}

	@Override
	public List<Vehicle> getAllVehicle() throws Exception {
		return dao.getAllVehicle();
	}

	@Override
	public Vehicle getVehicle(Vehicle vehicle) throws Exception {
		return dao.getVehicle(vehicle);
	}

	@Override
	public boolean removeVehicle(Vehicle vehicle) throws Exception {
		return dao.removeVehicle(vehicle);
	}

	@Override
	public double checkWalletBalance(User user) throws Exception {
		return dao.checkWalletBalance(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public boolean addWalletBalance(User user, double amount) throws Exception {
		return dao.addWalletBalance(user, amount);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public boolean deductWalletBalance(User user, double amount) throws Exception {
		return dao.deductWalletBalance(user, amount);
	}

	@Override
	public List<Trip> getTrips(User user) throws Exception {
		return dao.getTrips(user);
	}

	@Override
	public List<PoolTrip> getPoolTrips(User user) throws Exception {
		return dao.getPoolTrips(user);
	}

	@Override
	public Trip getTrips(Trip trip) throws Exception {
		return dao.getTrips(trip);
	}

	@Override
	public PoolTrip getPoolTrip(PoolTrip poolTrip) throws Exception {
		return dao.getPoolTrip(poolTrip);
	}

	@Override
	public List<Transaction> getAllTransaction(User user) throws Exception {
		return dao.getAllTransaction(user);
	}

	@Override
	public Transaction getTransaction(Transaction transaction) throws Exception {
		return dao.getTransaction(transaction);
	}

	@Override
	public List<Transaction> getAllTransaction() throws Exception {
		return dao.getAllTransaction();
	}

	@Override
	public List<Trip> getTrips() throws Exception {
		return dao.getTrips();
	}

	@Override
	public List<PoolTrip> getPoolTrips() throws Exception {
		return dao.getPoolTrips();
	}

}
