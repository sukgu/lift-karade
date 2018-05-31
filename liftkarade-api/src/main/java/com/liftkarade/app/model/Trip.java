package com.liftkarade.app.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Trip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	@ManyToOne
	@JoinColumn(name="pooltripid")
	private PoolTrip poolTrip;
	@Column
	private double amount;
	@Column
	private double distance;
	@Column
	private String fromAddress;
	@Column
	private String toAddress;
	@Column
	private User driver;
	@Column
	private User passenger;
	
	public Trip() {
		super();
	}
	
	public Trip(PoolTrip poolTrip, double amount, double distance, String from, String to, User driver,
			User passenger) {
		this.poolTrip = poolTrip;
		this.amount = amount;
		this.distance = distance;
		this.fromAddress = from;
		this.toAddress = to;
		this.driver = driver;
		this.passenger = passenger;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PoolTrip getPoolTrip() {
		return poolTrip;
	}
	public void setPoolTrip(PoolTrip poolTrip) {
		this.poolTrip = poolTrip;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getFrom() {
		return fromAddress;
	}
	public void setFrom(String from) {
		this.fromAddress = from;
	}
	public String getTo() {
		return toAddress;
	}
	public void setTo(String to) {
		this.toAddress = to;
	}
	public User getDriver() {
		return driver;
	}
	public void setDriver(User driver) {
		this.driver = driver;
	}
	public User getPassenger() {
		return passenger;
	}
	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", poolTrip=" + poolTrip + ", amount=" + amount + ", distance=" + distance + ", from="
				+ fromAddress + ", to=" + toAddress + ", driver=" + driver + ", passenger=" + passenger + "]";
	}
	
}
