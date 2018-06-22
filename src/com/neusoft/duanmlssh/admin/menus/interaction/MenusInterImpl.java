package com.neusoft.duanmlssh.admin.menus.interaction;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.duanmlssh.admin.menus.action.form.MenuForm;
import com.neusoft.duanmlssh.admin.menus.applogic.MenusApplogicInter;
import com.neusoft.duanmlssh.comment.DateUtils;
import com.neusoft.duanmlssh.comment.MutualObject;
import com.neusoft.duanmlssh.pojo.SshSysMenus;

@Service
public class MenusInterImpl implements MenusInter{
	
	@Autowired
	public MenusApplogicInter menusApplogicInter;

	@Override
	public SshSysMenus getMenusById(String menuId) {
		return menusApplogicInter.getMenuById(menuId);
	}
	
	@Override
	public List<?> getAllChildrenMenus() {
		return menusApplogicInter.getAllChildrenMenus();
	}
	
	@Override
	@Transactional
	public MutualObject saveMenu(MenuForm menuForm) {
		MutualObject mutual = new MutualObject();
		try {
			String menuKey = menuForm.getMenukey();
			SshSysMenus menuobject = menusApplogicInter.getMenuByMenuKey(menuKey);
			if (menuobject == null) {
				SshSysMenus menu = new SshSysMenus();
				menu.setMenuId(menuForm.getMenuidname());
				menu.setMenuName(menuForm.getMenuname());
				menu.setMenuKey(menuForm.getMenukey());
				menu.setParentId(menuForm.getParentidname());
				if (StringUtils.isNotBlank(menuForm.getOrdername())) {
					menu.setMenuOrder(Long.parseLong(menuForm.getOrdername()));
				}
				menu.setMenuUrl(menuForm.getAddressname());
				menu.setAae013(menuForm.getAuthorname());
				menu.setAae036(DateUtils.strToDate(menuForm.getDatename()));
				menusApplogicInter.saveMenu(menu);
				mutual.setResultFlag(true);
			} else {
				mutual.setResultFlag(false);
				mutual.put("errMesg", "系统中已经存在key值为：【"+menuKey+"】的菜单，请修改key值！");
			}
		} catch (Exception e) {
			mutual.setResultFlag(false);
			e.printStackTrace();
			mutual.put("errMesg", e.getMessage());
		}
		return mutual;
	}
	
	@Override
	@Transactional
	public MutualObject updateMenu(MenuForm menuForm) {
		MutualObject mutual = new MutualObject();
		String menuid = menuForm.getMenuidname();
		if (StringUtils.isNotBlank(menuid)) {
			mutual.setResultFlag(true);
			SshSysMenus menu = menusApplogicInter.getMenuById(menuid);
			menu.setAae013(menuForm.getAuthorname());
			menu.setAae036(DateUtils.strToDate(menuForm.getDatename()));
			if (StringUtils.isNotBlank(menuForm.getOrdername())) {
				menu.setMenuOrder(Long.parseLong(menuForm.getOrdername()));
			}
			menu.setMenuUrl(menuForm.getAddressname());
			menu.setMenuName(menuForm.getMenuname());
			menu.setMenuKey(menuForm.getMenukey());
			menusApplogicInter.updateMenu(menu);
		}else{
			mutual.setResultFlag(false);
			mutual.put("errMesg", "更新菜单信息的时候报错，菜单ID值为空！");
		}
		return mutual;
	}
	
	@Override
	@Transactional
	public MutualObject deleteMenu(String menuId) {
		MutualObject mutual = new MutualObject();
		List<?> menuList = menusApplogicInter.getChildrenMenusByMenuId(menuId);
		if (menuList.size() >= 1) {
			mutual.setResultFlag(false);
			mutual.put("errMesg", "此菜单下面存在子菜单，不允许删除！请确认");
		} else {
			mutual.setResultFlag(true);
			menusApplogicInter.deleteMenu(menuId);
		}
		return mutual;
	}

}
