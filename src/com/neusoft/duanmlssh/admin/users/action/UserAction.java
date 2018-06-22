package com.neusoft.duanmlssh.admin.users.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neusoft.duanmlssh.admin.users.interaction.UserInter;
import com.neusoft.duanmlssh.platform.core.db.interImpl.PaginationInterImpl;
import com.neusoft.duanmlssh.platform.core.db.interImpl.SimplePage;

@Controller
@RequestMapping("/admin/user/")
public class UserAction {
	
	@Autowired
	public UserInter userInter;

	@RequestMapping(value = "/userManager.do")
	public String mainUsers(Integer pageNum,Integer numPerPage,HttpServletRequest request,
			HttpServletResponse response, ModelMap model){
		if (numPerPage == null) {
			numPerPage = 10;
		}
		PaginationInterImpl page = userInter.ListUsers(SimplePage.cpn(pageNum), SimplePage.isCheckPageSize(numPerPage));
		model.put("ListUsers", page.getList());
		model.put("numPerPage", page.getPageSize());
		model.put("totalCount", page.getTotalCount());
		model.put("pageNum", page.getPageNo());
		return "admin/user/mainUsers";
	}
}
