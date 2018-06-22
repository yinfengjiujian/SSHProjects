package com.neusoft.duanmlssh.admin.users.applogic;

import com.neusoft.duanmlssh.platform.core.db.interImpl.PaginationInterImpl;

public interface UserApplogic {
	
	public PaginationInterImpl ListUsers(Integer pageNum,Integer numPerPage);

}
