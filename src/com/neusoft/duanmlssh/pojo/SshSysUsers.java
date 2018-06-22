package com.neusoft.duanmlssh.pojo;

import java.util.Date;

/**
 * SshSysUsers entity. @author MyEclipse Persistence Tools
 */

public class SshSysUsers implements java.io.Serializable {

	// Fields

	/**
	*
	* <p>Title: 河源人社局</p>
	*
	* <p>Description: </p>
	*
	* <p>Copyright: Copyright (c) 2014</p>
	*
	* <p>Company: neusoft</p>
	*
	* @author 段美林
	* @date  2016-12-24
	* @version 1.0
	*/
	private static final long serialVersionUID = 4153181673515809684L;
	private String userId;
	private Integer version;
	private String userAccount;
	private String userFullname;
	private String userPassword;
	private String description;
	private Date passwordChangedDate;
	private String accountEnabled;
	private String accountLocked;
	private String sex;
	private Date birthdate;
	private String email;
	private String mobileTelephone;
	private String homeTelephone;
	private String homeAddress;
	private String createdBy;
	private Date creationDate;
	private String lastUpdatedBy;
	private Date lastUpdateDate;
	private String activeFlag;
	private String deletedBy;
	private Date deletedDate;

	// Constructors

	/** default constructor */
	public SshSysUsers() {
	}

	/** minimal constructor */
	public SshSysUsers(String userAccount, String userFullname,
			String userPassword, String accountEnabled, String accountLocked) {
		this.userAccount = userAccount;
		this.userFullname = userFullname;
		this.userPassword = userPassword;
		this.accountEnabled = accountEnabled;
		this.accountLocked = accountLocked;
	}

	/** full constructor */
	public SshSysUsers(String userAccount, String userFullname,
			String userPassword, String description, Date passwordChangedDate,
			String accountEnabled, String accountLocked, String sex,
			Date birthdate, String email, String mobileTelephone,
			String homeTelephone, String homeAddress, String createdBy,
			Date creationDate, String lastUpdatedBy, Date lastUpdateDate,
			String activeFlag, String deletedBy, Date deletedDate) {
		this.userAccount = userAccount;
		this.userFullname = userFullname;
		this.userPassword = userPassword;
		this.description = description;
		this.passwordChangedDate = passwordChangedDate;
		this.accountEnabled = accountEnabled;
		this.accountLocked = accountLocked;
		this.sex = sex;
		this.birthdate = birthdate;
		this.email = email;
		this.mobileTelephone = mobileTelephone;
		this.homeTelephone = homeTelephone;
		this.homeAddress = homeAddress;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdateDate = lastUpdateDate;
		this.activeFlag = activeFlag;
		this.deletedBy = deletedBy;
		this.deletedDate = deletedDate;
	}

	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getVersion() {
		return this.version;
	}

	@SuppressWarnings("unused")
	private void setVersion(Integer version) {
		this.version = version;
	}

	public String getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserFullname() {
		return this.userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPasswordChangedDate() {
		return this.passwordChangedDate;
	}

	public void setPasswordChangedDate(Date passwordChangedDate) {
		this.passwordChangedDate = passwordChangedDate;
	}

	public String getAccountEnabled() {
		return this.accountEnabled;
	}

	public void setAccountEnabled(String accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	public String getAccountLocked() {
		return this.accountLocked;
	}

	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileTelephone() {
		return this.mobileTelephone;
	}

	public void setMobileTelephone(String mobileTelephone) {
		this.mobileTelephone = mobileTelephone;
	}

	public String getHomeTelephone() {
		return this.homeTelephone;
	}

	public void setHomeTelephone(String homeTelephone) {
		this.homeTelephone = homeTelephone;
	}

	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getDeletedBy() {
		return this.deletedBy;
	}

	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDeletedDate() {
		return this.deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

}