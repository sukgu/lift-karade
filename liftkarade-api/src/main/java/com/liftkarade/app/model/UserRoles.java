package com.liftkarade.app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the NUSER_RULES database table.
 * 
 */
@Entity
@Table
public class UserRoles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ROLES_ID")
	private long userRolesId;

	@Column(name="authority")
	private String authority;

	//bi-directional many-to-one association to Nuser
    @ManyToOne
	@JoinColumn(name="id")
	private User user;

    public UserRoles() {
    }

	public long getUserRolesId() {
		return this.userRolesId;
	}

	public void setUserRolesId(long userRolesId) {
		this.userRolesId = userRolesId;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User nuser) {
		this.user = nuser;
	}
	
}