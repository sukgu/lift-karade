package com.liftkarade.app.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class PoolTrip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	
	@Column
	private double totalAmount;
	@Column
	private double totalDistance;
	@Column
	private String fromAddress;
	@Column
	private String toAddress;
	
	@Column(name="tripid")
	@OneToMany(mappedBy="poolTrip")
	private List<Trip> trips;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
