package com.neusoft.duanmlssh.admin.users.interaction;

import com.neusoft.duanmlssh.platform.core.db.interImpl.PaginationInterImpl;

public interface UserInter {
	
	public PaginationInterImpl ListUsers(Integer pageNum,Integer numPerPage);

}
