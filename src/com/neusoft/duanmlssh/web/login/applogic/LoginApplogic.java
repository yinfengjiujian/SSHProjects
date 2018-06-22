package com.neusoft.duanmlssh.web.login.applogic;

import com.neusoft.duanmlssh.platform.core.db.interImpl.BaseDao;
import com.neusoft.duanmlssh.pojo.SshSysMenus;

public class LoginApplogic extends BaseDao<SshSysMenus>{

	@Override
	protected Class<SshSysMenus> getEntityClass() {
		return SshSysMenus.class;
	}

}
