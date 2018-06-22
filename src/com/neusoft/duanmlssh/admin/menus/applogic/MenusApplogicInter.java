package com.neusoft.duanmlssh.admin.menus.applogic;

import java.util.List;

import com.neusoft.duanmlssh.pojo.SshSysMenus;

public interface MenusApplogicInter {

	public List<?> getFistMenu();
	
	public List<?> getAllChildrenMenus();
	
	public List<?> getChildrenMenusByMenuId(String menuId);
	
	public SshSysMenus getMenuById(String menuId);
	
	public SshSysMenus getMenuByMenuKey(String menuKey);
	
	public void saveMenu(SshSysMenus menu);
	
	public void updateMenu(SshSysMenus menu);
	
	public void deleteMenu(String menuId);
	
}
