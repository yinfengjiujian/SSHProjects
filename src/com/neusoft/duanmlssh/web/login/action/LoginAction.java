package com.neusoft.duanmlssh.web.login.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neusoft.duanmlssh.web.login.interaction.LoginInter;

@Controller
public class LoginAction {
	
	@Autowired
	private LoginInter loginInter;

	@RequestMapping(value="/login/userlogin.do")
	public String userLogin(ModelMap model){
		List<?> list = loginInter.getFistMenus();
	    model.put("ListMenus",list);
	    model.put("fristmunus", list.get(0));
		return "login/index";
	}
}
