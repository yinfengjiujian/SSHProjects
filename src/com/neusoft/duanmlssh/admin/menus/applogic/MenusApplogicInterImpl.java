package com.neusoft.duanmlssh.admin.menus.applogic;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.neusoft.duanmlssh.platform.core.db.MultipurposeDB;
import com.neusoft.duanmlssh.platform.core.db.interImpl.BaseDao;
import com.neusoft.duanmlssh.pojo.SshSysMenus;

@Repository("menusApplogic")
public class MenusApplogicInterImpl extends BaseDao<SshSysMenus> implements MenusApplogicInter{
	
	@Override
	protected Class<SshSysMenus> getEntityClass() {
		return SshSysMenus.class;
	}

	public List<?> getFistMenu(){
		MultipurposeDB mudb = new MultipurposeDB(this);
		mudb.asc("menuOrder");
		mudb.eq("parentId", "df5859d05ca4472aba438be48b239abc");
		return this.findByMultipurposeDB(mudb);
	}
	
	public List<?> getRootMenu(){
		MultipurposeDB mudb = new MultipurposeDB(this);
		mudb.asc("menuOrder");
		mudb.isNull("parentId");
		return this.findByMultipurposeDB(mudb);
	}
	
	public List<?> getAllChildrenMenus(){
		MultipurposeDB mudb = new MultipurposeDB(this);
		mudb.isNotNull("parentId");
		mudb.asc("menuOrder");
		return this.findByMultipurposeDB(mudb);
	}
	
	@Override
	public List<?> getChildrenMenusByMenuId(String menuId) {
		MultipurposeDB mudb = new MultipurposeDB(this);
		mudb.eq("parentId", menuId);
		return this.findByMultipurposeDB(mudb);
	}
	
	@Override
	public SshSysMenus getMenuById(String menuId) {
		return this.get(menuId);
	}
	
	@Override
	public SshSysMenus getMenuByMenuKey(String menuKey) {
		return this.findByField("menuKey", menuKey);
	}
	
	@Override
	public void saveMenu(SshSysMenus menu) {
		this.save(menu);
	}
	
	@Override
	public void updateMenu(SshSysMenus menu) {
		this.saveOrUpdate(menu);
	}
	
	@Override
	public void deleteMenu(String menuId) {
		this.delete(menuId);
	}
}
