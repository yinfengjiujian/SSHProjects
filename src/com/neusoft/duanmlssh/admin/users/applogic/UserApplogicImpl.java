package com.neusoft.duanmlssh.admin.users.applogic;

import org.springframework.stereotype.Repository;

import com.neusoft.duanmlssh.platform.core.db.MultipurposeDB;
import com.neusoft.duanmlssh.platform.core.db.interImpl.BaseDao;
import com.neusoft.duanmlssh.platform.core.db.interImpl.PaginationInterImpl;
import com.neusoft.duanmlssh.pojo.SshSysUsers;

@Repository
public class UserApplogicImpl extends BaseDao<SshSysUsers> implements UserApplogic{

	@Override
	protected Class<SshSysUsers> getEntityClass() {
		return SshSysUsers.class;
	}

	@Override
	public PaginationInterImpl ListUsers(Integer pageNum, Integer numPerPage) {
		final MultipurposeDB mudb = new MultipurposeDB(this);
		mudb.desc("userAccount");
		return this.findByMultipurposeDB(mudb, pageNum, numPerPage);
	}

}
