package com.neusoft.duanmlssh.admin.menus.interaction;

import java.util.List;

import com.neusoft.duanmlssh.admin.menus.action.form.MenuForm;
import com.neusoft.duanmlssh.comment.MutualObject;
import com.neusoft.duanmlssh.pojo.SshSysMenus;

public interface MenusInter {
	
	public SshSysMenus getMenusById(String menuId);
	
	public List<?> getAllChildrenMenus();
	
	public MutualObject saveMenu(MenuForm menuForm);
	
	public MutualObject updateMenu(MenuForm menuForm);
	
	public MutualObject deleteMenu(String menuId);

}
