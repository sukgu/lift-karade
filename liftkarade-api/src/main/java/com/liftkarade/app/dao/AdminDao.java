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
import com.liftkarade.app.model.User.AccountStatus;

@Repository
public class AdminDao implements IAdminDao {
	
	@Autowired
	 SessionFactory sf;

	@Override
	public boolean verifyUser(User user) throws Exception {
		user.setVerified(AccountStatus.verified);
		sf.getCurrentSession().update(user);
		return true;
	}

	@Override
	public boolean blockUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unblockUser(User user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyVehicle(Vehicle vehicle) throws Exception {
		vehicle.setVerified(Vehicle.AccountStatus.verified);
		sf.getCurrentSession().update(vehicle);
		return true;
	}

	@Override
	public User getUser(User user) throws Exception {
		return (User) sf.getCurrentSession().get(User.class, user.getId());
	}

	@Override
	public List<User> getAllUser() throws Exception {
		String hql = "select c from User c";
		List<User> users = sf.getCurrentSession().createQuery(hql).list();
		return users;
	}

	@Override
	public List<Vehicle> getAllVehicle() throws Exception {
		String hql = "select c from Vehicle c";
		List<Vehicle> vehilces = sf.getCurrentSession().createQuery(hql).list();
		return vehilces;
	}

	@Override
	public Vehicle getVehicle(Vehicle vehicle) throws Exception {
		return (Vehicle) sf.getCurrentSession().get(Vehicle.class, vehicle);
	}

	@Override
	public boolean removeVehicle(Vehicle vehicle) throws Exception {
		sf.getCurrentSession().delete(vehicle);
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
	public boolean deductWalletBalance(User user, double amount) throws Exception {
		Wallet wallet = (Wallet) sf.getCurrentSession().get(Wallet.class, user.getWallet().getId());
		wallet.setAmount(wallet.getAmount()-amount);
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
	public Trip getTrips(Trip trip) throws Exception {
		return (Trip) sf.getCurrentSession().get(Trip.class, trip.getId());
	}

	@Override
	public PoolTrip getPoolTrip(PoolTrip poolTrip) throws Exception {
		return (PoolTrip) sf.getCurrentSession().get(PoolTrip.class, poolTrip.getId());
	}

	@Override
	public List<Transaction> getAllTransaction(User user) throws Exception {
		String hql = "select c from Transaction c where c.fromUser=:user or c.toUser=:user";
		List<Transaction> transactions = sf.getCurrentSession().createQuery(hql).setParameter("user",user).list();
		return transactions;
	}

	@Override
	public Transaction getTransaction(Transaction transaction) throws Exception {
		return (Transaction) sf.getCurrentSession().get(Transaction.class, transaction.getId());
	}

	@Override
	public List<Transaction> getAllTransaction() throws Exception {
		String hql = "select c from Transaction c";
		List<Transaction> transactions = sf.getCurrentSession().createQuery(hql).list();
		return transactions;
	}

	@Override
	public List<Trip> getTrips() throws Exception {
		String hql = "select c from Trip c";
		List<Trip> trips = sf.getCurrentSession().createQuery(hql).list();
		return trips;
	}

	@Override
	public List<PoolTrip> getPoolTrips() throws Exception {
		String hql = "select c from PoolTrip c)";
		List<PoolTrip> trips = sf.getCurrentSession().createQuery(hql).list();
		return trips;
	}

}
