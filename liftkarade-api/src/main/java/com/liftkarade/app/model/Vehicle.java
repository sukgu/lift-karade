package com.liftkarade.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Vehicle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private String company;
	@Column
	private String model;
	@Column
	private String colour;
	@Column
	@Enumerated(EnumType.STRING)
	private VehicleType type;
	@NotNull
	@Column(nullable=false)
	private String number;
	@Column
	private AccountStatus verified;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	public enum AccountStatus {
		no,progress,verified
	}
	
	public enum VehicleType {
		suv,hatchback,sedan,van,micro
	}
	
	public Vehicle() {
		super();
	}

	public Vehicle(String company, String model, String colour, VehicleType type, String number) {
		this.company = company;
		this.model = model;
		this.colour = colour;
		this.type = type;
		this.number = number;
	}

	public String getCompany() {
		return company;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public AccountStatus getVerified() {
		return verified;
	}

	public void setVerified(AccountStatus verified) {
		this.verified = verified;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [company=" + company + ", model=" + model + ", colour=" + colour + ", number=" + number + "]";
	}
	
	
}
