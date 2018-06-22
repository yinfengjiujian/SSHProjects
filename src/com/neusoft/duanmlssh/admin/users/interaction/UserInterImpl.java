package com.neusoft.duanmlssh.admin.users.interaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.duanmlssh.admin.users.applogic.UserApplogic;
import com.neusoft.duanmlssh.platform.core.db.interImpl.PaginationInterImpl;

@Service
public class UserInterImpl implements UserInter{

	@Autowired
	public UserApplogic userApplogic;
	
	@Override
	@Transactional(readOnly = true)
	public PaginationInterImpl ListUsers(Integer pageNum, Integer numPerPage) {
		return userApplogic.ListUsers(pageNum, numPerPage);
	}

}
