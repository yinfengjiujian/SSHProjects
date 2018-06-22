package com.neusoft.duanmlssh.platform.core.db.interImpl;

import java.io.Serializable;
import java.util.List;

import com.neusoft.duanmlssh.platform.core.db.inter.PaginationInter;

/**
 * 
*
* <p>Title: 河源人社局</p>
*
* <p>Description: 分页实现类，并且有List属性</p>
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
@SuppressWarnings("serial")
public class PaginationInterImpl extends SimplePage implements PaginationInter,Serializable {
	
	/**
	 * 当前页的数据
	 */
	private List<?> list;
	
	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public void setList(List list) {
		this.list = list;
	}

	public PaginationInterImpl(){}
	
	/**
	 * 
	*
	* <p>Title: 河源人社局</p>
	*
	* <p>Description: 构造器传入PageNo、PageSize、TotalCount</p>
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
	public PaginationInterImpl(int pageNo,int pageSize,int totalCount){
		super(pageNo, pageSize, totalCount);
	}
	
	/**
	 * 
	*
	* <p>Title: 河源人社局</p>
	*
	* <p>Description: 构造器传入PageNo、PageSize、TotalCount、List<?></p>
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
	public PaginationInterImpl(int pageNo,int pageSize,int totalCount,List<?> list ){
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}
	
	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}
	

}
