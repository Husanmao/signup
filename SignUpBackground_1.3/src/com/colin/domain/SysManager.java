package com.colin.domain;

/**
 * SysManager entity. @author MyEclipse Persistence Tools
 */

public class SysManager implements java.io.Serializable {

	// Fields

	private Integer managerId;
	private String accountName;
	private String accountPassword;

	// Constructors

	/** default constructor */
	public SysManager() {
	}

	/** full constructor */
	public SysManager(String accountName, String accountPassword) {
		this.accountName = accountName;
		this.accountPassword = accountPassword;
	}

	// Property accessors

	public Integer getManagerId() {
		return this.managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return this.accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

}