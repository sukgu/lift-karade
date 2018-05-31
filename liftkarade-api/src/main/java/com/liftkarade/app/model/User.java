package com.liftkarade.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	
	@Column(name="enabled")
	private int enabled;

	@Column(name="username")
	private String username;

	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@Column(name="USER_ROLES_ID")
	private Set<UserRoles> userRoles;
	
	@Column
	private String firstName;
	@Column
	private String lastName;
	@NotNull
	@Column(nullable=false,unique=true)
	private String email;
	@Column
	private String password;
	@NotNull
	@Column(nullable=false,unique=true)
	private String phone;
	@Column
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Address> address;
	@NotNull
	@Column(nullable=false)
	private String panCardNumber;
	@Column
	private Date created;
	@Column
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id")
	private Wallet wallet;
	@Column                      //remove if error
	@OneToMany(cascade=CascadeType.ALL)
	private List<Transaction> transactions;
	@Column
	@OneToMany(mappedBy="id",cascade=CascadeType.ALL)
	private List<Vehicle> vehicle;
	@Column
	private AccountStatus verified;
	
	public enum AccountStatus {
		no,progress,verified
	}
	
	public enum Gender {
		male,female
	}
	
	public User() {
		super();
	}
	
	public User(String firstName, String lastName, String email, String password, String phone, List<Address> address,
			String panCardNumber, Date created, Gender gender) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.panCardNumber = panCardNumber;
		this.created = created;
		this.gender = gender;
	}

	@PrePersist
	@Temporal(TemporalType.TIMESTAMP)
	protected void onCreate() {
		created = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public AccountStatus getVerified() {
		return verified;
	}

	public void setVerified(AccountStatus verified) {
		this.verified = verified;
	}

	public List<Vehicle> getVehicle() {
		return vehicle;
	}

	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", address=" + address + ", panCardNumber="
				+ panCardNumber + ", created=" + created + ", gender=" + gender + "]";
	}
	
	
	
	

}
