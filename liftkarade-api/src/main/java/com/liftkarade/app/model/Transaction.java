package com.liftkarade.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long id;
	@Column
	private Date paymentDate;
	@Column
	private double transactionAmount;
	@Column
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	@Column
	@Value(value="com.letsgo.model.Transaction.Status.inactive")
	private Status status;
	@OneToOne(targetEntity=com.liftkarade.app.model.User.class)
	private User toUser;
	
	@OneToOne(targetEntity=com.liftkarade.app.model.User.class)
	private User fromUser;
	@Column
	private double totalAmount;
	
	public enum Status {
		inactive,pending,declined,finished
	}
	
	public enum TransactionType {
		debit,credit
	}

	public Transaction() {
		super();
	}

	public Transaction(Date paymentDate, double transactionAmount, TransactionType transactionType,
			Status status, User toUser, User fromUser, double totalAmount) {
		this.paymentDate = paymentDate;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
		this.status = status;
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.totalAmount = totalAmount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", paymentDate=" + paymentDate + ", transactionAmount=" + transactionAmount
				+ ", transactionType=" + transactionType + ", status=" + status + ", toUser=" + toUser + ", fromUser="
				+ fromUser + ", totalAmount=" + totalAmount + "]";
	}


}
