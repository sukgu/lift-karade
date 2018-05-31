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
public class Address implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private String flatNumber;
	@Column
	private String Street;
	@Column
	private String nearBy;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String country;
	@Column
	private String pinCode;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	public Address() {
		super();
	}

	public Address(String flatNumber, String street, String nearBy, String city, String state, String country,
			String pinCode) {
		
		this.flatNumber = flatNumber;
		Street = street;
		this.nearBy = nearBy;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}

	public long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getNearBy() {
		return nearBy;
	}

	public void setNearBy(String nearBy) {
		this.nearBy = nearBy;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	public String getAddress() {
		
		String address = ""+getFlatNumber()+"\n"+getNearBy()+"\n"+getCity()+"\n"+getState()+"\n"+getCountry()+"\n"+getPinCode();
		return address;
	}

	@Override
	public String toString() {
		return "Address [flatNumber=" + flatNumber + ", Street=" + Street + ", nearBy=" + nearBy + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", pinCode=" + pinCode + "]";
	}

	
}
