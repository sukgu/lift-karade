package com.liftkarade.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liftkarade.app.model.PoolTrip;
import com.liftkarade.app.model.Transaction;
import com.liftkarade.app.model.Trip;
import com.liftkarade.app.model.User;
import com.liftkarade.app.model.Vehicle;
import com.liftkarade.app.model.Wallet;
import com.liftkarade.app.model.Transaction.Status;

@Repository
public class UserDao implements IUserDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public User addUser(User user) throws Exception {
		Long id = (Long)sf.openSession().save(user);
		
		user.setId(id);
		return user;
	}

	@Override
	public PoolTrip addPoolTrip(PoolTrip poolTrip) throws Exception {
		Long id = (Long)sf.getCurrentSession().save(poolTrip);
		poolTrip.setId(id);
		return poolTrip;
	}

	@Override
	public Trip addTrip(Trip trip) throws Exception {
		Long id = (Long)sf.getCurrentSession().save(trip);
		trip.setId(id);
		return trip;
	}

	@Override
	public Vehicle addVehicle(Vehicle vehicle) throws Exception {
		Long id = (Long)sf.getCurrentSession().save(vehicle);
		vehicle.setId(id);
		return vehicle;
	}

	@Override
	public Transaction makeTransaction(Transaction transaction) throws Exception {
		transaction.setStatus(Status.pending);
		Wallet fromWallet = transaction.getFromUser().getWallet();
		Wallet toWallet = transaction.getToUser().getWallet();
		double amount = transaction.getTransactionAmount();
		if(transferAmountWithWallet(fromWallet, toWallet, amount)) {
			transaction.setStatus(Status.finished);
		} else {
			transaction.setStatus(Status.declined);
		}
		long id = (Long) sf.getCurrentSession().save(transaction);
		transaction.setId(id);
		return transaction;
	}

	@Override
	public void updateProfile(User user) throws Exception {
		sf.getCurrentSession().update(user);
	}

	@Override
	public boolean transferAmountWithWallet(Wallet fromWallet, Wallet toWallet, double amount) throws Exception {
		fromWallet.setAmount(fromWallet.getAmount()-amount);
		toWallet.setAmount(toWallet.getAmount()+amount);
		sf.getCurrentSession().update(fromWallet);
		sf.getCurrentSession().update(toWallet);
		return true;
	}

	@Override
	public double checkWalletBalance(User user) throws Exception {
		Wallet wallet = (Wallet) sf.getCurrentSession().get(Wallet.class, user.getWallet().getId());
		return wallet.getAmount();
	}

	@Override
	public boolean addWalletBalance(User user, double amount) throws Exception {
		Wallet wallet = (Wallet) sf.getCurrentSession().get(Wallet.class, user.getWallet().getId());
		wallet.setAmount(wallet.getAmount()+amount);
		sf.getCurrentSession().update(wallet);
		return true;
	}

	@Override
	public List<Trip> getTrips(User user) throws Exception {
		String hql = "select c from Trip c where c.driver=:driver or c.passenger=:passenger";
		List<Trip> trips = sf.getCurrentSession().createQuery(hql).setParameter("driver",user).setParameter("passenger", user).list();
		return trips;
	}

	@Override
	public List<PoolTrip> getPoolTrips(User user) throws Exception {
		String hql = "from Trip as t where t.driver=:driver (select c.trip from PoolTrip c)";
		List<PoolTrip> trips = sf.getCurrentSession().createQuery(hql).setParameter("driver",user).list();
		return trips;
	}

	@Override
	public Trip getTrip(Trip trip) throws Exception {
		return (Trip) sf.getCurrentSession().get(Trip.class, trip.getId());
	}

	@Override
	public PoolTrip getPoolTrip(PoolTrip poolTrip) throws Exception {
		return (PoolTrip) sf.getCurrentSession().get(PoolTrip.class, poolTrip.getId());
	}

	@Override
	public Transaction getTransaction(Transaction transaction) throws Exception {
		return (Transaction) sf.getCurrentSession().get(Transaction.class, transaction.getId());
	}

	@Override
	public List<Transaction> getAllTransaction(User user) throws Exception {
		String hql = "select c from Transaction c where c.fromUser=:user or c.toUser=:user";
		List<Transaction> transactions = sf.getCurrentSession().createQuery(hql).setParameter("user",user).list();
		return transactions;
	}

	@Override
	public Vehicle getVehicle(Vehicle vehicle) throws Exception {
		return (Vehicle) sf.getCurrentSession().get(Vehicle.class, vehicle.getId());
	}

	@Override
	public List<Vehicle> getAllVehicle(User user) throws Exception {
		String hql = "select c from Vehicle c where c.user=:driver";
		List<Vehicle> vehicles = sf.getCurrentSession().createQuery(hql).setParameter("driver",user).list();
		return vehicles;
	}

	

}
