package com.eudemon.taurus.app.entity;

import java.sql.Timestamp;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User extends BaseEntity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 6065339087039687447L;
	private Integer id;
	private String name;
	private String password;
	private String passwordEncrypt;
	private String salt;
	private String roles;
	private String permissions;
	private Timestamp registerDate;
	private String photo;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	/** full constructor */
	public User(String name, String password, String passwordEncrypt, String salt, String roles, String permissions,
			Timestamp registerDate, String photo) {
		this.name = name;
		this.password = password;
		this.passwordEncrypt = passwordEncrypt;
		this.salt = salt;
		this.roles = roles;
		this.permissions = permissions;
		this.registerDate = registerDate;
		this.photo = photo;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordEncrypt() {
		return this.passwordEncrypt;
	}

	public void setPasswordEncrypt(String passwordEncrypt) {
		this.passwordEncrypt = passwordEncrypt;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoles() {
		return this.roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Timestamp getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}