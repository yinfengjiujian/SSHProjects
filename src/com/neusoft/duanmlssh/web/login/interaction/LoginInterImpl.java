package com.neusoft.duanmlssh.web.login.interaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.duanmlssh.admin.menus.applogic.MenusApplogicInter;

@Service
public class LoginInterImpl implements LoginInter {
	
	@Autowired
	private MenusApplogicInter getMenusApplogic;

	@Override
	@Transactional
	public List<?> getFistMenus() {
		return getMenusApplogic.getFistMenu();
	}
	
	@Override
	public List<?> getchildMeuns(String menuId) {
		return null;
	}

}
