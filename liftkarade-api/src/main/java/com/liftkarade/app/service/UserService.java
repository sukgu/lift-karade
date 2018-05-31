package com.liftkarade.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liftkarade.app.dao.UserDao;
import com.liftkarade.app.model.PoolTrip;
import com.liftkarade.app.model.Transaction;
import com.liftkarade.app.model.Trip;
import com.liftkarade.app.model.User;
import com.liftkarade.app.model.Vehicle;
import com.liftkarade.app.model.Wallet;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserDao dao;

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public User addUser(User user) throws Exception {
		return dao.addUser(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public PoolTrip addPoolTrip(PoolTrip poolTrip) throws Exception {
		return dao.addPoolTrip(poolTrip);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public Trip addTrip(Trip trip) throws Exception {
		return dao.addTrip(trip);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public Vehicle addVehicle(Vehicle vehicle) throws Exception {
		return dao.addVehicle(vehicle);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public Transaction makeTransaction(Transaction transaction) throws Exception {
		return dao.makeTransaction(transaction);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public void updateProfile(User user) throws Exception {
		dao.updateProfile(user);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,timeout=10,readOnly=true)
	public boolean transferAmountWithWallet(Wallet fromWallet, Wallet toWallet, double amount) throws Exception {
		return dao.transferAmountWithWallet(fromWallet, toWallet, amount);
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
	public List<Trip> getTrips(User user) throws Exception {
		return dao.getTrips(user);
	}

	@Override
	public List<PoolTrip> getPoolTrips(User user) throws Exception {
		return dao.getPoolTrips(user);
	}

	@Override
	public Trip getTrips(Trip trip) throws Exception {
		return dao.getTrip(trip);
	}

	@Override
	public PoolTrip getPoolTrip(PoolTrip poolTrip) throws Exception {
		return dao.getPoolTrip(poolTrip);
	}

	@Override
	public Transaction getTransaction(Transaction transaction) throws Exception {
		return dao.getTransaction(transaction);
	}

	@Override
	public List<Transaction> getAllTransaction(User user) throws Exception {
		return dao.getAllTransaction(user);
	}

	@Override
	public Vehicle getVehicle(Vehicle vehicle) throws Exception {
		return dao.getVehicle(vehicle);
	}

	@Override
	public List<Vehicle> getAllVehicle(User user) throws Exception {
		return dao.getAllVehicle(user);
	}

}
