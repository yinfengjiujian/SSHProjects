package com.neusoft.duanmlssh.platform.core.db.inter;

/**
 * 
*
* <p>Title: 河源人社局</p>
*
* <p>Description: 目的：用来实现分页查询而成立的接口</p>
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
public interface PaginationInter {
	
	/**
	 * 
	* <p>@Description:获得总共的数据条数</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public int getTotalCount();
	
	/**
	 * 
	* <p>@Description:获得总共的页数</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public int getTotalPage();
	
	/**
	 * 
	* <p>@Description:获得每页的数据大小</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public int getPageSize();
	
	/**
	 * 
	* <p>@Description:获得当前页的页号</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public int getPageNo();
	
	/**
	 * 
	* <p>@Description:判断是否是第一页</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public boolean isFirstPage();
	
	/**
	 * 
	* <p>@Description:判断是否是最后一页</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public boolean isLastPage();
	
	/**
	 * 
	* <p>@Description:取得下一页的页号</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public int getNextPage();
	
	/**
	 * 
	* <p>@Description:取得上一页的页号</p>
	* 
	* @author 段美林
	* @date  2014-8-30
	* @version 1.0
	 */
	public int getPrePage();
}
