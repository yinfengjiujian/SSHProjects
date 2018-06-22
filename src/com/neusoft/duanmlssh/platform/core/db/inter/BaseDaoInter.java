package com.neusoft.duanmlssh.platform.core.db.inter;

import java.io.Serializable;

/**
 * 
*
* <p>Title: 河源人社局</p>
*
* <p>Description: </p>
*
* <p>Copyright: Copyright (c) 2014</p>
*
* <p>Company: neusoft</p>
*
* @author 段美林
*
* @date  2014-8-30
*
* @version 1.0
 */
public interface BaseDaoInter<T> {
	
	public void delete(Serializable id);
	
	public void delete(T entity);
	
	public void save(T entity);
	
	public void update(T entity);
	
	public void saveOrUpdate(T entity);

	public T load(Serializable id);
	
	public T get(Serializable id);
	
	public T findByField(String fieldName,Object fieldValue);
}
