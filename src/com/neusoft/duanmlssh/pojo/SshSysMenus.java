package com.neusoft.duanmlssh.pojo;

import java.util.Date;

/**
 * SshSysMenus entity. @author MyEclipse Persistence Tools
 */

public class SshSysMenus implements java.io.Serializable {

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
	* @date  2016-11-17
	* @version 1.0
	*/
	private static final long serialVersionUID = -1965910234067326923L;
	// Fields

	private String menuId;
	private String menuName;
	private String parentId;
	private Long menuOrder;
	private String menuUrl;
	private String aae013;
	private Date aae036;
	private String menuKey;

	/** default constructor */
	public SshSysMenus() {
	}

	/** full constructor */
	public SshSysMenus(String menuName, String parentId, Long menuOrder,
			String menuUrl, String aae013, Date aae036,String menuKey) {
		this.menuName = menuName;
		this.parentId = parentId;
		this.menuOrder = menuOrder;
		this.menuUrl = menuUrl;
		this.aae013 = aae013;
		this.aae036 = aae036;
		this.menuKey = menuKey;
	}

	// Property accessors

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(Long menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getAae013() {
		return this.aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public Date getAae036() {
		return this.aae036;
	}

	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}
	
	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

}